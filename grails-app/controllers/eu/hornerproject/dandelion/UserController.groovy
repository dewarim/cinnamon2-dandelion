package eu.hornerproject.dandelion

import server.Acl
import server.Folder
import server.Group
import server.GroupUser
import server.User
import server.global.Constants
import server.FolderType
import grails.plugins.springsecurity.Secured
import server.i18n.UiLanguage

@Secured(["hasRole('_superusers')"])
class UserController extends BaseController{

    def showUsersByGroup () {
        def group = Group.get(params.id)
        def groupUsers = GroupUser.findAll("from GroupUser gu where gu.group=:group",[group:group], params)

        // only show those users who are not already part of the list:
        def addList = User.findAll("from User u where u not in (select gu.user from GroupUser gu where gu.group=:group)", [group:group])
        
//            User.list().findAll { user ->
//            !groupUsers.find { groupUser -> groupUser.userId == user.id }
//        }

        def hasSubGroups = false
        if(Group.findAllWhere(parent:group).size() > 0){
            hasSubGroups = true
            log.debug("group has subgroups")
        }else{
            log.debug("group has no subgroups")
        }

        [userList: groupUsers.collect { it.user },
                addList: addList,
                hasSubGroups:hasSubGroups,
                group: group,
                groupUserCount: GroupUser.executeQuery("select count(gu) from GroupUser gu where gu.group=:group", [group:group])[0]
        ]
    }

    /**
     * Replace a user in the repositories.
     * Present a form with source and target user.
     * All objects which belong to the source user in some way will be transferred.
     */
    def replaceUser () {
    	[userList : User.list(),
                forbidden:! userService.transferAssetsAllowed(session.repositoryName)
        ]
    }

    def transferAssets () {
        try{
            if(! userService.transferAssetsAllowed(session.repositoryName)){
                throw new RuntimeException(message(code:'user.replaceUser.forbidden'))
            }
            if(params.sourceId.equals(params.targetId)){
                throw new RuntimeException(message(code:'user.replaceUser.targets.equal'))
            }

            User source = User.get(params.sourceId)
            if(! source){
                throw new RuntimeException(message(code:'user.replaceUser.source.not_found'))
            }

            User target = User.get(params.targetId)
            if(! target){
                throw new RuntimeException(message(code:'user.replaceUser.target.not_found'))
            }

            // check that the source user is not an admin
            userService.transferAssets source,target
            flash.message = message(code:'user.replaceUser.success', args:[source.name, target.name])
        }
        catch (RuntimeException e){
            flash.message = message(code:'user.replaceUser.failed', args:[message(code:e.getMessage())])
            return redirect(controller:'user', action:'replaceUser')
        }
        return redirect(controller:'user', action:'replaceUser')
    }

	def create () {
		
	}

    def list () {
        setListParams()
    	[userList : User.list(params)]
    }
    
    def show () {
    	[user : User.get(params.id)]
    }

    def edit () {
    	[user : User.get(params.id)]
    }
    
    def update () {
    	User user = User.get(params.id)
        if(! user){
            flash.message = message(code: 'user.not.found')
            redirect(controller: 'user', action: 'list')
            return
        }
    	String old_password = ''
    	if(params.pwd?.length() == 0 ){
    		// only change password on valid input
    		old_password = user.pwd
    	}
		// TODO: validate username (for example, prevent special chars and trailing whitespace)
		if(params.name?.length() == 0){
			// do not allow empty username.			
			params.name = user.name
		}

        def checkFields = ['sudoable', 'sudoer', 'activated']
        checkFields.each{field ->
            user."$field" =params.containsKey(field) 
        }
        
		if(user.name.equals('admin')){
			if ( ! user.activated ){
                log.debug("params.activated: ${params.activated}")
				log.debug('Preventing user from deactivating admin account.')
				flash.message = message(code:'user.update.fail.deactivate')
				redirect(action:'edit', params:[id:user.id])
				return
			}
			else if(! params.name?.equals(user.name)){
				log.debug('Preventing user from changing admin\'s name.')
				flash.message = message(code:'user.update.fail.rename')
				redirect(action:'edit', params:[id:user.id])
				return
			}
		}
		
		// if the name was changed, also change the user's personal group name
		if(! user.name.equals(params.name)){
			Group group = Group.findByName("_${user.id}_${user.name}")
			group.name = "_${user.id}_${params.name}"
			group.description = "${params.name}'s personal group"
			group.save()
		}

        // TODO: this use of user.properties is problematic. Better use explicit value mapping.
    	user.properties = params        
    	if(old_password){
    		user.updatePassword(old_password)
    	}
    	else{
    		user.setPwd(params.pwd)
    	}        

    	if(user.save(flush:true)){
    		flash.message = message(code:"user.update.success")
    		redirect(action:'show', params:[id:user.id])
    	}
    	else{
    		flash.message = message(code:"user.update.fail", args:[user.errors])
    		redirect(action:'edit', params:[id:user.id])
    	}
    }
    
    def deleteAsk () {
        [userList:User.list(),
         forbidden: ! userService.deleteUserAllowed(session.repositoryName),
                showTransferLink:params.showTransferLink
        ]
    }

    def doDelete () {
        try{
            if(! userService.deleteUserAllowed(session.repositoryName)){
                throw new RuntimeException(message(code:'user.delete.forbidden'))
            }
            User user = User.get(params.user)
            if(! user ){
                throw new RuntimeException(message(code:'user.delete.not_found'))
            }
            if(user.equals(userService.getUser())){
                throw new RuntimeException(message(code:'user.delete.yourself'))
            }
            if(userService.userHasAssets(user)){
                throw new RuntimeException(message(code:'user.has.dependencies'))
            }
            // check that the source user is not an admin
            userService.delete user
            flash.message = message(code:'user.delete.success', args:[user.name.encodeAsHTML()])
        }
        catch (RuntimeException e){
//            log.debug("failed to delete user: ",e)
            flash.message = message(code:'user.delete.failed', args:[message(code:e.getMessage())])
            return redirect(controller:'user', action:'deleteAsk', params:[showTransferLink:true])
        }
        return redirect(controller:'user', action:'deleteAsk')
    }

	/**
	 * add a group to a user
	 */
	def addGroup () {
			// -get selected user and group
			// -add group to user via groupuser
			// -update view
			def group = Group.get(params?.group_list)
			def user = User.get(params.userId)
			new GroupUser(user, group).save()
			
			redirect(controller:'group', action:'showGroupsByUser', params:[id : user.id])
	}
	
	/**
	 * Remove a group from a user
	 */
	def removeGroup () {
			// -get selected user and group
			// -remove group from user via groupuser
			// -update view
			def group = Group.get(params?.id)
			def user = User.get(params.userId)
			def gu = GroupUser.findByUserAndGroup(user, group)
			gu.delete()
			
			redirect(controller:'group', action:'showGroupsByUser', params:[id : user.id])
	}
    
    /**
     * Called after the 'save' button in create.gsp is called
     */
    def save () {
//        setHibernateSessionEm(session)
        def user = null
        try {
            user =  new User(params.name, params.pwd, params.fullname, params.description)
            user.email = params.email
            user.language = UiLanguage.findByIsoCode('und')
            if (params.containsKey('sudoable')){
                user.sudoable = true
            }
            if (params.containsKey('sudoer')){
                user.sudoer = true
            }
            user.save(flush: true)
        }
        catch (Exception e) {
            log.debug("failed to save user:",e)
            flash.message = e.getLocalizedMessage()
            return redirect(action: 'create')
        }

        // create home/, searches/, carts/, config/ in .../users/<username>-Folder:
        def folderPath = findAllByPath('/system/users/', true)
        log.debug "folderPath = ${folderPath.dump()}"

        def defaultAcl = Acl.findByName(Constants.ACL_DEFAULT)
        def defaultType = FolderType.findByName(Constants.FOLDER_TYPE_DEFAULT)

        def userFolder = new Folder(user.name,'', defaultAcl,folderPath[-1],findAdminUser(),defaultType)  
        userFolder.save(flush: true)
        log.debug "created user folder '${userFolder.dump()}'"

        ['home', 'searches', 'carts', 'config'].each {
            def folder = new Folder(it,'', defaultAcl, userFolder, user, defaultType)
            folder.save()
            log.debug "created folder '${folder.dump()}'"
        }

        // create user-group:
        groupService.createUserGroup(user)
        userService.addUserToUsersGroup(user)

        redirect(action: 'show', params: [id: user.id])
    }
    
    /**
     * Groovy version of FolderDAOHibernate.findAllByPath()
     */
	protected List<Folder> findAllByPath(String path, Boolean createMissingFolders){
		def parent = Folder.find("from Folder as f where name=:name and parent_id=id", [name : Constants.ROOT_FOLDER_NAME])
		
		List<Folder> ret = new ArrayList<Folder>()
		path.split("/").each() { seg ->
			if (seg.length() > 0) {
				def folders = Folder.findAllWhere(parent : parent, name : seg)
				
				if (folders.size() == 0) { // create missing folders
					if(createMissingFolders){
						Folder f = new Folder(name : seg,
								owner: findAdminUser(),
								parent:parent,
								type: FolderType.findByName(Constants.FOLDER_TYPE_DEFAULT),
								acl:Acl.findByName(Constants.ACL_DEFAULT))
						f.save(flush:true)
						folders = [f]
					}
					else{
						throw new RuntimeException("Invalid path '$path'")
					}
				}
				Folder folder = folders[0]
				parent = folder
				ret << folder
			}
		}
		return ret
	}
    
    protected User findAdminUser(){
    	def user = User.findByName('admin')
    	if(user == null){
    		throw new RuntimeException('Dandelion cannot use the Cinnamon Server without a user "admin".')
    	}
    	return user
    }

    def updateList () {
        setListParams()
        render(template: 'userList', model:[userList:User.list(params)])
    }

}
