import server.Acl
import server.AclEntry
import server.Format
import server.Group
import server.GroupUser
import server.ObjectType
import server.RelationType
import server.User
import org.apache.log4j.PropertyConfigurator
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityFilterPosition

class BootStrap {

    def grailsApplication

    def init = { servletContext ->

        if (!grailsApplication.config.configLoaded) {
            log.warn("merge config file by hand")
            def configFile = new File("${System.env.DANDELION_HOME_DIR}/dandelion-config.groovy")
            def configScript = new ConfigSlurper().parse(configFile.text)
            grailsApplication.config.merge(configScript)
        }

        // create and store EntityManagerFactories
        environments {
            production {
                log.info "**** BootStrap detected production"
                //  configureForProduction()
                // copied code here because Grails pretends it cannot find the method "configureForProduction" WTF?
                String logFilename = System.env.DANDELION_HOME_DIR + "/dandelion.log4j.properties"
                log.debug("logFilename: $logFilename")
                if (new File(logFilename).exists()) {
                    PropertyConfigurator.configure(logFilename);
                }
            }
            development {
                log.info "**** BootStrap detected development"
//		    	 configureForDevelopment()
                configureForProduction()
            }
            test {
                log.info "**** BootStrap detected test"
                configureForTest()
            }
        }
    }

    void configureForDevelopment() {
        log.debug 'configureForDevelopment()'

    }


    void configureForTest() {
        log.debug 'configureForTest()'
    }

    void configureForProduction() {
        log.debug 'configureForProduction()'
        String logFilename = System.env.DANDELION_HOME_DIR + "/dandelion.log4j.properties"
        if (new File(logFilename).exists()) {
            PropertyConfigurator.configure(logFilename);
        }

    }

    def destroy = {
    }
} 
