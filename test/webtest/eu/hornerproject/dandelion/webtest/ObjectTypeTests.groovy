package eu.hornerproject.dandelion.webtest

class ObjectTypeTests extends BaseTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testObjectTypeListNewDelete()
        // add tests for more operations here
    }

    void testObjectTypeListNewDelete() {
        webtest('ObjectType basic operations: view list, create new entry, view, edit, delete, view') {
            login()
            verifyText		'Object Types'
            invoke      'objectType'
            verifyText  'Home'

            verifyListSize 1

            createObjectType()
            
            verifyText  'Show Object Type', description:'Detail page'
            clickLink   'List', description:'Back to list view'

            verifyListSize 2

            group(description:'edit the one element') {
                showFirstElementDetails('testObjectType')
                clickButton 'Edit'
                verifyText  'Edit Object Type'
                clickButton 'Update'
                verifyText  'Show Object Type'
                clickLink   'List', description:'Back to list view'
            }

            verifyListSize 2

            deleteObjectType()

            verifyListSize 1
            
            logout()
        }
    }

    void testNoDeleteInEditView() {
        webtest('ObjectType basic operations: view list, create new entry, view, edit, delete, view') {
            login()
        	
            invoke      'objectType'
            verifyText  'Home'

            verifyListSize 1

            createObjectType()
            
            verifyText  'Show Object Type', description:'Detail page'
            clickLink   'List', description:'Back to list view'

            verifyListSize 2

            group(description:'verify that no delete button is in edit view') {
    			showFirstElementDetails('testObjectType')

    			clickButton 'Edit'
    			verifyText  'Edit Object Type'
    			not {
    				clickButton name: '_action_Delete'
    			}
            }

            invoke		'objectType/list'

            verifyListSize 2

            deleteObjectType()

            verifyListSize 1
            
            logout()
        }
    }
    
    def createObjectType() {
        group(description: 'create Object Type') {
            clickLink   'New Object Type'
            verifyText  'Create Object Type'
    		setInputField 	name:'name', value:'testObjectType'
            clickButton 'Create'
        }
    }

    def deleteObjectType() {
        group(description:'delete the only element') {
            showFirstElementDetails('testObjectType')
            clickButton 'Delete'
            verifyXPath xpath:  "//div[@class='message']",
                        text:   /.*ObjectType.*deleted.*/,
                        regex:  true
        }
    }
   
}