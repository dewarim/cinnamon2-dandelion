package eu.hornerproject.dandelion

import server.User
import server.Group
import server.GroupUser

/**
 * The HealthService class contains methods to repair or create essential objects in the database.
 */
class HealthService {

    def groupService

    def fixUserGroups() {
          User.list().each{user ->
             if(! user.groupUsers.find{it.group.is_user}){
                 groupService.createUserGroup(user)
             }
         }
    }

    /**
     * Create a group if it does not already exist, using a default description.
     * @param name
     */
    Group createGroup(String name){
        Group group = Group.findByName(name)
        if(! group){
            group = new Group(name, name+'.description', false, null)
            group.save()
        }
        return group
    }
}
