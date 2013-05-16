# Cinnamon2-Dandelion Changelog

## 2.5.x

* Updated to Grails 2.2.2
* Updated to Humulus 0.7.1
* see cinnamon2-server changelog for more details.
	
##2.4.0

+ Upgrade to Humulus 0.7.0
+ Move to new git repository.

##2.3.0

+ Fixed: you can now remove sudo-flag from a user account
+ New: XML in config fields is now properly formatted 
+ Improved: user.show and user.edit: checkboxes now have same order
+ Fixed: NPE in create::changeTriggerType.
+ Fixed: new versions of Grails / Groovy somehow need more explicit Class.forName invocations, else they throw an Exception.
+ Fixed: RelationType.create no longer swaps the copyOn{left,right} flags.
+ Improved: RelationTypeController uses explicit parameter checks on update 
    (instead of allowing arbitrary key-value pairs as parameters)
+ Fixed: wrong parameter name in UiLanguageController which prevented users from creating new UI languages.
+ Fixed: Button in show language form so editing languages works again.
+ New: Sudoer & sudoable flags may now be set during user creation.
+ Improved: database-config.example.groovy with connection info for lifecycle-logging db-configuration
+ Fixed: table layout in group views
+ Fixed: submitButton of group.show.edit and group.edit.save

##2.2.2

+ Added LifecycleLog MVC.
+ Fix FormatController: delete format was using wrong var.
+ Add missing message id for failed format update

##2.2.0

+ Added MetasetType CRUD
+ Updated CSS (to be continued)

##2.1.2

(Bugfixes mostly stemming from Grails 2.0.1 update)

+ Fixed: edit lifecycle caused "invalid id" error.
+ Fixed: StackOverflow-Error in edit lifecycle
+ Fixed: edit language
+ Fixed: create language (Exception)
+ Fixed: create lifecycle
+ Fixed: no access to lifecycle states
+ Fixed: create acl caused exception
+ Fixed: create group caused exception
+ Fixed: methods with public access caused problem in Controllers.
+ Fixed: edit user caused exception
+ Fixed: bug in config-textarea in /language/create
+ Fixed: Prevent double redirect on failed group delete (would throw exception)
+ Fixed: Check if user account exists before trying to update it.

##2.1.1

+ Fixed pagination bug in AclEntryController (would not go past page 1).

##2.1.0

+ Upgrade to Grails 2
+ Fixed bug (calling wrong method) in showAclsByGroup.gsp
+ Added "Boolean sessionIsClosed()" method to DAOs (to help detecting invalid EMs during debugging)
+ Fixed Bug in HQL with OSD-DAOHibernate.findAllByParent.
+ Upgraded to Humulus 0.5.40
+ From Humulus plugin: database-config is now read from "${System.env.CINNAMON_HOME_DIR}/database-config.groovy"
  instead of $appName-config.groovy. Please update your Illicium and Dandelion configuration accordingly.

##2.0.5

+ Updated to Spring-Security-Plugin 1.2.4 (and Humulus 0.4.6)
+ Updated relationType view and controller for cloneOn{Left,Right}Copy

##2.0.4

+ Fixed User.equals()
+ Humulus is now 0.4.4

## 2.0.3

+ Improved pagination: all classes can now display up to a 1000 items.
+ Added required array "triggerClasses" to dandelion-config.groovy. Default value:
    triggerClasses = [
        'server.trigger.impl.RelationChangeTrigger'
    ]
+ Added required array "relationResolvers" to dandelion-config.groovy. Default value:
    relationResolvers = [
        'server.resolver.FixedRelationResolver',
        'server.resolver.LatestHeadResolver',
        'server.resolver.LatestBranchResolver'
    ]
+ Added required array "transformers" to dandelion-config.groovy. Default value:
    transformers = [
        'server.transformation.XhtmlToPdfTransformer'
    ]
+ Added required array 'lifeCycleStateClasses' to dandelion-config.groovy. Default value:
    lifeCycleStateClasses = [
        'server.lifecycle.state.NopState'
    ]

## 2.0.2

+ Fix / added proper remotePagination to LifeCycle and LifeCycleState-Controller

## 2.0.1

+ Added simple XLIFF import and export for UiMessages.
+ Updated example config file

## 2.0.0

+ New major version.

## Old changes:
    
    1.0.13
    Fixed: #2102 create RelationType no longer automatically assigns "false" for left/rightobjectprotected
    
    1.0.12_RC6
    Upgrade to CodeMirror 2.01
    Fixed: some views did not reload the pagination buttons when going below / beyond 11 / 10 items.
    Fixed #2062: sortableColumn in changeTrigger-list-view fixed.
    Fixed #2069: uiLanguage.edit now works.
    Fixed #2065: parent group does no longer appear in list of its own sub groups.
    Fixed: group.showSubGroups now has working sortable columns.
    Fixed #2063: sortable columns did not work for Group.list.
    Fixed #2064: correct pagination of Group.list
    Added #2057: ConfigEntryController
    Fixed #2061: paginate-buttons would not appear on ChangeTrigger after creating the 11th trigger.
    Improved: #2060: use jQuery-Plugin in current version (1.6) instead of manually installed old jQuery 1.4
    Added: CodeMirror for ChangeTrigger, RelationResolver, Language (improved)
    Fixed: prevent RelationResolver from being created with empty config field.
    Added CodeMirror 2.0 to Dandelion.
    Language.metadata is now editable and will be rendered using CodeMirror.
    Improved LanguageController (check length of isoCode field etc).
    Added: Import and export of messageIds and translations for UiLanguage messages as XML.
    Fixed #2009: after a new lifecycle was created, the list of lifecycles was not updated.
    Fixed #2022: delete user fails if because lock ownership is not being transferred.
    Fixed #2023: Grails Runtime Exception when browsing repository (due to incomplete Permission => PermissionName conversion).
    
    1.0.11
    Fixed bug in LifeCycleState which could lead to infinite recursion in equals().
    Added ChangeTrigger.config field to controller and view.
    Fixed: encode ChangeTrigger.config (#2014) when displaying, not when storing it  - the server needs this in raw form.
    
    1.0.10
    Fixed / improved LifeCycle / LifeCycleState creation.
    Added TransformerController
    
    1.0.9
    Added support for user.sudoer and user.sudoable fields in UserController.
    
    1.0.8
    Improved Acl-management: when editing an ACL, the admin can now add and remove AclEntries on the same page.
    Added toggleAll for AclEntryPermissions (so an admin can allow or deny all for faster editing of AclEntryPermissions)
    Added controller for LifeCycle and LifeCycleState class.
    Improved layout of main page after login.
    Added ChangeTriggerController and ChangeTriggerTypeController.
    Fixed problem with path to log file.
    API method deleteAllVersions is now working for all users, not just admins.
    Updated to spring-security-plugin 1.1.2 and current spring-security libraries (3.0.5).
    Updated to Grails 1.3.7
    
    1.0.7_RC2
    New: delete user: it is now possible to delete users if they have no dependent items (folders, objects, groups, sessions).
    New: added transferAssets aka replaceUser to move all items (folders, objects, groups) from one user to another.
    Fixed #1954 - editing a deactivated user could lead to re-activation (because flag for "activated" was always checked).
    Fixed #1929 - Webclient: PSQL-Exception in cmn_test
    
    1.0.6
    Superusers may remove other user's locks on objects.
    Fixed lock/unlock of objects in WebClient.
    Added links to Cinnamon homepage and LGPL to auth page. 
    New users are automatically added to _users system group.
    HealthController checks if existing users are in the _users system group.
    When browsing the groups a user is in, the "remove from group" link is omitted from system groups.
    Added HealthController to check the existence of essential objects and settings.
    Fixed folder browser: "show all versions" now works.
    First working version of the UiLanguage-Controller
    Fixed folder view: show "folder_open" icon when opening a folder.
    Fixed tests.
    Fixed logging on Linux systems: Dandelion should no longer write everything to syslog or catalina.out
     but rather use dandelion.log4j.properties in $DANDELION_HOME_DIR.
    
    
    1.0.6_RC1
    Removed dependency on XStream.
    
    1.0.5
    Changed security handling: Dandelion now uses the Grails Spring security plugin.
    Added experimental FolderController
    Fixed HTML
    Show only groups in main group list which are != user_group.
    Added CodeMirror editor for editing OSD's custom metadata.
    Show relations in FolderController
    
    1.0.4
    Added changelog.
