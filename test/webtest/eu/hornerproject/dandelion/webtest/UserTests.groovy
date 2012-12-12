package eu.hornerproject.dandelion.webtest

class UserTests extends BaseTest {

    Integer defaultUserCount = 3

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testUserListNew()
        testMissingDeleteButton()
        testPasswordMissingInListOrShow()
        testDeactivateUser()
    }

    void testUserListNew() {
        webtest('User basic operations: view list, create new entry, view, edit, delete, view') {
			login()
            verifyText		'Users'

            invoke      'user/list'
            verifyText  'Home'

            verifyText  'admin' // admin user exists

            clickLink   'New User'
            verifyText  'Create User'
            setInputField 	name:'name', value:'test2'
	        setInputField 	name:'pwd', value:'passwort2'
	        setInputField	name:'email',	 value:'tester@example.invalid'	        
            clickButton 'Create'
            verifyText  'Show User', description:'Detail page'
            clickLink   'List', description:'Back to list view'

            verifyText 'admin'
            verifyText 'test2' // user test2 appears in user list

            group(description:'edit the created element') {
                showFirstElementDetails('test2')
                clickButton 'Edit'
                verifyText  'Edit User'
                clickButton 'Update'
                verifyText  'Show User'
                clickLink   'List', description:'Back to list view'
            }

			logout()
        }
    }

    void testDeactivateUser() {
        webtest('deactivate user account and check by trying to login)') {
        	// login as admin
        	login()

        	// test2 deaktivieren
        	group(description:"change to 'edit user'") {
        		invoke 'user/list'
        		verifyTitle 'User List'
        		
				org.grails.plugins.webtest.WebTestCase.ant.clickLink   description:"select user 'test2'",
					xpath: "//div[@class='list']//tbody/tr[td='test2']/td[1]/a"
        		verifyTitle 'Show User'
        		
        		clickButton 'Edit', description:"edit user 'test2'"
        		verifyTitle 'Edit User'
        	}
        	
        	setCheckbox name:"activated", checked:'false'
        	
        	clickButton 'Update'
        	verifyTitle 'Show User'
        	
        	clickLink 'Logout'
        	verifyText 'Login'
        	
        	group(description:"try log in as 'test2' again. Should fail.") {
            	invoke 			'/'
    		    setInputField 	name:'j_username', value:'test2'
    		    setInputField 	name:'j_password', value:'passwort2'
    		    clickButton 	'login'
    		    not {
        			verifyTitle		'UserController'
        		}
            }
        }
    }

    void testMissingDeleteButton() {
        webtest('delete user button should be absent from show/edit user.') {
			login()

            invoke      'user/list'
            verifyText  'Home'
            
            group(description:'delete button in show is missing') {
	            showFirstElementDetails('test2')
	            
	            not {
	            	verifyElement type:'input', text:'Delete'
	            }
	            clickLink 'List', description:'Back to list view'
			}

			group(description:'delete button in edit is missing') {
                showFirstElementDetails('test2')
                clickButton 'Edit'
                verifyText  'Edit User'
	            not {
	            	verifyElement type:'input', text:'Delete'
	            }
                
                clickLink   'List', description:'Back to list view'
            }
			
			logout()
        }
    }

    void testPasswordMissingInListOrShow() {
        webtest('test: do not show password field in list/show user') {
        	login()

            invoke      'user/list'
            verifyText  'Home'

            not(description:'password is missing in list') {
            	verifyText(text:'Password') {
        			table row:'0', column:'5'
        		}
           }

            group(description:'password is missing in show') {
	            showFirstElementDetails('test2')
	            
	            not {
	            	verifyElement type:'td', text:'Password:'
	            }
	            clickLink 'List', description:'Back to list view'
			}

            
			logout()
        }
    }

}