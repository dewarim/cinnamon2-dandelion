package eu.hornerproject.dandelion.webtest

import eu.hornerproject.dandelion.webtest.BaseTest

class GroupTests extends BaseTest {

	def defaultGroupCount = 4
	
    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
    	testNoDeleteInEditView()
        testGroupListNewDelete()
        // add tests for more operations here

        // add / remove user from group:
        testAddRemoveUserFromGroup()
        // add / remove group from another group:
        testAddRemoveGroupFromGroup()
        testShowAcls()
        testAddRemoveAclFromGroup()

    }

    void testGroupListNewDelete() {
        webtest('Group basic operations: view list, create new entry, view, edit, delete, view') {
        	login()
        	verifyText		'Groups'

            invoke      'group/list'
            verifyText  'Home'

            verifyListSize defaultGroupCount

			createGroup()
            verifyText  'Show Group', description:'Detail page'
            clickLink   'List', description:'Back to list view'
            	
            verifyListSize defaultGroupCount +1

            group(description:'edit the one element') {
                showFirstElementDetails('testGroup')
                clickButton 'Edit'
                verifyText  'Edit Group'
                clickButton 'Update'
                verifyText  'Show Group'
                clickLink   'List', description:'Back to list view'
            }

        	verifyListSize defaultGroupCount +1

            group(description:'delete the only element') {
                showFirstElementDetails('testGroup')
                clickButton 'Delete'
                verifyXPath xpath:  "//div[@class='message']",
                            text:   /.*Group.*deleted.*/,
                            regex:  true
            }

            verifyListSize defaultGroupCount
            
            logout()
        }
    }

    void testAddRemoveUserFromGroup() {
        webtest('Add/remove user to/from group') {
        	login()
        	
            invoke      'group/list'
            verifyText  'Home'

			createGroup()
            clickLink   'List', description:'Back to list view'

            group(description:'edit first group') {
                showFirstElementDetails('testGroup')
                clickButton 'Edit'
                verifyTitle 'Edit Group'
            }
        	
            clickLink 'Show Users'
            verifyText 'User List'
            
			setSelectField name:'user_list', text:'admin', description:"select user 'admin' in combobox"
        	clickButton 'Add to Group'
        	
            verifyText(text:'admin', description:"is 'admin' on list") {
        		table row:'1', column:'3'
        	}
            clickLink href:'/dandelion/group/removeUser/', description:"click 'remove from group' for 'admin'"
        		
        	not(description:"'admin' should no longer be on the list") {
	        	verifyText(text:'admin') {
	        		table row:'1', column:'3'
	        	}
        	}
            clickLink   'List', description:'Back to list view'

            deleteGroup()

        	logout()
        }
    }

    void testAddRemoveGroupFromGroup() {
        
        webtest('edit: show only groups which have not been added yet') {

        	login()



            invoke      'group/list'
            verifyText  'Home'

            
			createGroup()
            clickLink   'List', description:'Back to list view'

        	// add test2 as parent
        	group(description:"change to 'edit group'") {
        		invoke 'group/list'
        		verifyTitle 'Group List'
        		
        		showFirstElementDetails('testGroup')
        		verifyTitle 'Show Group'
        		
        		clickButton 'Edit', description:"edit group 'test2'"
        		verifyTitle 'Edit Group'
        	}

        	def groupname = '_superusers'
			setSelectField name:'parent.id', text:groupname, description:"select'$groupname' in combobox."
			
        	clickButton 'Update'
        	verifyTitle 'Show Group'                      
        	verifyText 'Group was saved successfully'
            
        	// check if test2 is being listed correctly
        	verifyText(text:groupname, description:"'$groupname' is in list?") {
        		table row:'3', column:'1'
        	}

        	// remove parent group:
        	group(description:"remove parent") {
        		clickButton 'Edit', description:"edit group '$groupname'"
        		verifyTitle 'Edit Group'

				 setSelectField name:'parent.id', value:'null', description:"unselect parent group field"

				clickButton 'Update'
				verifyTitle 'Show Group'
        	}


			// verify that parent is empty
        	verifyText(text:'', description:"check: parent is empty") {
        		table row:'3', column:'1'
        	}
        			
            clickLink   'List', description:'Back to list view'
			deleteGroup()

			logout()
        }

    }

    // show acls
    void testShowAcls() {
        webtest('Show ACLs') {
        	login()
        	
            invoke      'group/list'
            verifyText  'Home'

			createGroup()
            clickLink   'List', description:'Back to list view'

       		showFirstElementDetails('testGroup')
        	clickLink 'Show ACLs'
            verifyTitle 'ACL List'

            clickLink   'List', description:'Back to list view'
            deleteGroup()
        	
        	logout()
        }
    }
    

    // acl-eintrag zu gruppe hinzufügen/entfernen
    void testAddRemoveAclFromGroup() {
        webtest('ACL hinzufügen zu/entfernen von Gruppe') {
        	login()
        	
            invoke      'group/list'
            verifyText  'Home'

			createGroup()
            clickLink   'List', description:'Back to list view'

            group(description:'edit first group') {
                showFirstElementDetails('testGroup')
                clickButton 'Edit'
                verifyTitle 'Edit Group'
            }
        	
            clickLink 'Show ACLs'
            verifyText 'ACL List'

        	def aclname = '_default_acl'

        	setSelectField name:'acl_list', text: aclname, description:"acl '$aclname' in combobox auswählen"
        	clickButton 'Add to Group'
        	
        	verifyText(text: aclname, description:"'$aclname' appears in list?") {
				table row:'1', column:'2'
        	}
           	clickLink href:'/dandelion/group/removeAcl/', description:"click 'remove from group' for '$aclname'"
        	
        	not(description:"'$aclname' no longer appears in list?") {
	        	verifyText(text:'$aclname') {
					table row:'1', column:'2'
	        	}
        	}

            clickLink   'List', description:'Back to list view'
            deleteGroup()
                
        	logout()
        }
    }

    void testNoDeleteInEditView() {
        webtest('Group basic operations: view list, create new entry, view, edit, delete, view') {
        	login()
        	
            invoke      'group/list'
            verifyText  'Home'
            
            verifyListSize defaultGroupCount

			createGroup()
            verifyText  'Show Group', description:'Detail page'
            clickLink   'List', description:'Back to list view'

            verifyListSize defaultGroupCount+1

            group(description:'verify that no delete button is in edit view') {
    			showFirstElementDetails('testGroup')

    			clickButton 'Edit'
    			verifyText  'Edit Group'
    			not {
    				clickButton name: '_action_Delete'
    			}
            }

        	invoke 'group/list'

        	verifyListSize defaultGroupCount+1

            group(description:'delete the only element') {
                showFirstElementDetails('testGroup')
                clickButton 'Delete'
                verifyXPath xpath:  "//div[@class='message']",
                            text:   /.*Group.*deleted.*/,
                            regex:  true
            }

        	verifyListSize defaultGroupCount
            
            logout()
        }
    }

    String ROW_COUNT_XPATH = "count(//div[@class='list']//tbody/tr)"

   def verifyListSizeWithTestuserCheck(int a, int b){
    	/*
    	 * Check if another test has already created a test user account.
    	 */
        ifStep(){
    		condition{
    			verifyText 'test2'
    		}
    		then{
    			verifyListSize a
    		}
    		'else'{
    			verifyListSize b
    		}
    	}
    }
    	
    def verifyListSize(int size) {
        org.grails.plugins.webtest.WebTestCase.ant.group(description:"verify Group list view with $size row(s)") {
            verifyText  'Group List'
            verifyXPath xpath:      ROW_COUNT_XPATH,
                        text:       size,
                        description:"$size row(s) of data expected"
        }
    }

    def createGroup() {
	    clickLink   'New Group'
	    verifyText  'Create Group'
		setInputField 	name:'name', value:'testGroup'
	    clickButton 'Create'
    }
    
    def deleteGroup() {
        group(description:'delete the only element') {
            showFirstElementDetails('testGroup')
            clickButton 'Delete'
        }
    }
    
}