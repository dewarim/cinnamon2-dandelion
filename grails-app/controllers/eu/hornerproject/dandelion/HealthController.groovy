package eu.hornerproject.dandelion

import server.User
import server.ObjectType
import server.global.Constants
import server.FolderType
import server.Group
import server.GroupUser

class HealthController extends BaseController {

    def index () { }

    def checkGroups () {
        Map viewParams = [:]
        viewParams['ownerAlias'] = Group.findByName(Constants.ALIAS_OWNER)
        viewParams['everyoneAlias'] = Group.findByName(Constants.ALIAS_EVERYONE)
        def userGroup = Group.findByName(Constants.GROUP_USERS)
        viewParams['userGroup'] = userGroup
        viewParams['allUsersInUserGroup'] = userGroup?.groupUsers?.size() == User.count()
        return [viewParams:viewParams]
    }

    def fillUpUsersGroup () {
        def userGroup = Group.findByName(Constants.GROUP_USERS)
        if(userGroup?.groupUsers?.size() != User.count() ){
            User.list().each{user ->
                if(! user.groupUsers.find{it.group == userGroup}){
                    GroupUser ug = new GroupUser(user, userGroup)
                    user.addToGroupUsers(ug)
                    userGroup.addToGroupUsers(ug)
                    ug.save()
                }
            }
        }
        return redirect(action:'checkGroups', controller:'health')
    }

    def fixUsersGroup () {
        def name = Constants.GROUP_USERS
        healthService.createGroup(name)
        return redirect(action:'checkGroups', controller:'health')
    }

    def fixEveryoneAlias () {
        def name = Constants.ALIAS_EVERYONE
        healthService.createGroup(name)
        return redirect(action:'checkGroups', controller:'health')
    }

    def fixOwnerAlias () {
        def name = Constants.ALIAS_OWNER
        healthService.createGroup(name)
        return redirect(action:'checkGroups', controller:'health')
    }

    def checkUsers () {
        Map viewParams = [:]
        viewParams.putAll(checkUserGroups())
        return [viewParams:viewParams]
    }

    protected Map checkUserGroups(){
        def viewParams = [:]
        List<User> users = User.list()
        viewParams['userCount'] = users.size()
        def missingGroup = 0
        users.each{ user ->
            if(! user.groupUsers.find{it.group.is_user}){
                // user does not have userGroup.
                missingGroup++
            }
        }
        viewParams['userGroupMissing'] = missingGroup
        return viewParams
    }

    def fixUserGroups () {
        healthService.fixUserGroups()
        flash.message = message(code:'health.userGroups.fixed')
        return redirect(action:'checkUsers', controller:'health')
    }

    def checkTypes () {
        def viewParams = [:]
        viewParams['defaultObjectType'] = ObjectType.findByName(Constants.OBJTYPE_DEFAULT) 
        viewParams['defaultFolderType'] = FolderType.findByName(Constants.FOLDER_TYPE_DEFAULT)
        return [viewParams:viewParams]
    }

    def fixFolderTypes () {
        def name = Constants.FOLDER_TYPE_DEFAULT
        if(! FolderType.findByName(name)){
            FolderType defaultType = new FolderType(name, name+'.description')
            defaultType.save()
            flash.message = message(code:'health.fixed.folderType')
        }
        return redirect(action:'checkTypes', controller:'health')
    }

    def fixObjectTypes () {
        def name = Constants.OBJTYPE_DEFAULT
        if(! ObjectType.findByName(name)){
            ObjectType ot = new ObjectType(name, name+'.description')
            ot.save()
            flash.message = message(code:'health.fixed.objectType')
        }
        return redirect(action:'checkTypes', controller:'health')
    }
}
