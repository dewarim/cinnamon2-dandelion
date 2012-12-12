package eu.hornerproject.dandelion

import server.User
import server.Group
import server.GroupUser

/**
 *
 */
class GroupService {

    void createUserGroup(User user) {
        String groupName = "_${user.id}_${user.name}"
        String description = "${user.name}'s personal group"
        def group = new Group(groupName, description, true, null)
        group.save(flush: true)
        GroupUser gu = new GroupUser(user, group)
        user.addToGroupUsers(gu)
        group.addToGroupUsers(gu)
        gu.save(flush: true)
    }
}
