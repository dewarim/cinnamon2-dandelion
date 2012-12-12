/**
 * 
 */
package eu.hornerproject.dandelion

import org.codehaus.groovy.grails.commons.ApplicationAttributes
import org.codehaus.groovy.grails.web.context.ServletContextHolder


import server.Group
import server.GroupUser
import server.User
import server.global.Constants
import utils.security.HashMaker
import grails.plugins.springsecurity.Secured
import eu.hornerproject.humulus.EnvironmentHolder
import eu.hornerproject.humulus.Environment

/**
 *
 */
public class SecurityController extends BaseController{

	private def getDataSourceForEnv(env) {
	        def servletContext = ServletContextHolder.servletContext
	        def ctx = servletContext
	                  .getAttribute(ApplicationAttributes.APPLICATION_CONTEXT)
	        return ctx.dataSource
	}
	
	def login () {
		log.debug('old login page called.')
        return redirect(action:'auth', controller:'login')
	}

	/**
	 * Load custom properties files from the config dir.
	 * @param name - the name of the Properties file.
	 * @return a Properties object, which may be empty if no file was found.
	 */
	 // copied from Cinnamon server.global.Conf
	protected Properties findProperties(String name){
		Properties props = new Properties();
		try {
			String propPath =  System.env.DANDELION_HOME_DIR;
			if(propPath == null){
				propPath = System.env.CINNAMON_HOME_DIR;
			}			
			props.load(new BufferedInputStream(
					new FileInputStream(propPath + File.separator + name)));
		} catch (Exception ex) {
			log.debug("failed to load properties.", ex);
			props = new Properties()
		}
		return props;
	}

//    protected void connectToNewRepository(){
//
//    }


//    @Secured([Constants.GROUP_SUPERUSERS])    
//    @Secured(["hasRole('_superusers')"])
    @Secured(["isAuthenticated()"])
//    @Secured(['_superusers'])
	def index () {


              	if(params.submitForm == '1'){
        				log.debug "params.submitForm == '1'"
				// change to selected repository / database
				/*
				 * This code block should set the HibernateSession.localEntityManager.
				 * It was meant to offer a free selection of repositories, but
				 * that's not working at the moment as the Grails database connection
				 * does not switch over to the new settings but uses only one database,
				 * no matter what I do.
				 */
				if (!params.environment) {
		            flash.message = 'Missing parameter environment'
		            return redirect(action:'auth', controller:'login')
		        }

	            def env = Environment.list().find {it.id == new Integer(params.environment)}
	            log.debug("environment: ${env.dump()}")
				if (!env) {
	                flash.message = 'No such environment'
	                log.debug("Could not find environment '${params.environment}'")
	                return redirect(action:'auth', controller:'login')
	            }

            	def oldDs = getDataSourceForEnv(env)
                //test connection
                def oldEnv = EnvironmentHolder.getEnvironment()
                EnvironmentHolder.setEnvironment(env)
                def ds = getDataSourceForEnv(env)
                log.debug("datasource: ${ds.dump()}")

                try {
                    def con = ds.getConnection()
                    log.debug("connected to $con")
                    oldDs.determineTargetDataSource().getConnection().close() // close old connection.
                    session.environment = env
                    session.repositoryName = env.dbname

                    log.debug('Environment change complete.')
                } catch (e) {
                	log.debug("error: ",e)
                    EnvironmentHolder.setEnvironment(oldEnv)

                    flash.message = 'Unable to connect to database: '+ e.message
                    log.debug(flash.message)
                    return redirect(action:'auth', controller:'login')
                }

        }
		log.debug("user on index-page")
		Properties config = findProperties("dandelion.properties")
		log.debug("config: "+config.dump())
		//		return [config:config] // Properties into config does not work. wtf?

        User user = userService.user
		return [isSuperuser:userService.isSuperuser(user),
                props:config]
	}

	private Boolean checkLogin(User user, String password){
		if(user == null){
			log.debug("User is null")
			return false
		}
		
		if(! user.activated ){
			log.debug("Account for ${user.name} is currently not activated")
			flash.message = 'error.account.inactive'
			return false
		}
		
		def env = EnvironmentHolder.getEnvironment()
		if( env.get('encryptPasswords')?.equals("true")){
			// use secure password hashes:
			//			log.debug("${user.name} - ${user.pwd} - ${password}")
			if( ! HashMaker.compareWithHash(password, user.getPwd()) ){
				log.debug("User ${user.name} failed encrypted password check.")
				flash.message = message(code:'login.failed')
				return false
			}
		}
		else{
			// use cleartext passwords:
			if( ! user.getPwd().equals(password)){
				log.debug("User ${user.name} failed unencrypted password check.")
				flash.message = message(code:'login.failed')
				return false
			}
		}

		if(! isSuperuser(user)){
			flash.message = message(code:'login.not.superuser') 
			return false
		}

		session['userId'] = user.id
		return true
	}
	
	protected Boolean isSuperuser(User user){
		Group superusers = Group.findByName(Constants.GROUP_SUPERUSERS);
		Set<GroupUser> groupUsers = new HashSet<GroupUser>();
		groupUsers.addAll(superusers.getGroupUsers());
		return groupUsers.removeAll(user.getGroupUsers());
	}
}
