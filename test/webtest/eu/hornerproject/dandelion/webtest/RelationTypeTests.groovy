package eu.hornerproject.dandelion.webtest

import org.grails.plugins.webtest.WebTestCase as WTC

class RelationTypeTests extends BaseTest {

    Integer defaultRelationTypeCount = 4

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testRelationTypeListNewDelete()
        testNoDeleteInEditView()
        // add tests for more operations here
    }

    void testRelationTypeListNewDelete() {
        webtest('RelationType basic operations: view list, create new entry, view, edit, delete, view') {
            login()
            verifyText		'Relation Types'

            invoke      'relationType'
            verifyText  'Home'

//            verifyListSize defaultRelationTypeCount

            createRelationType()

            verifyText  'Show Relation Type', description:'Detail page'

            clickLink   'List', description:'Back to list view'
            verifyText  'testRelationType'
//            verifyListSize  defaultRelationTypeCount +1

            group(description:'edit the one element') {
                showFirstElementDetails('testRelationType')
                clickButton 'Edit'
                verifyText  'Edit Relation Type'
                clickButton 'Update'
                verifyText  'Show Relation Type'
                clickLink   'List', description:'Back to list view'
            }
//            verifyListSize defaultRelationTypeCount +1

            deleteRelationType()
            verifyText 'testRelationType has been deleted'
//            verifyListSize defaultRelationTypeCount
            
            logout()
        }
    }

    void testNoDeleteInEditView() {
        webtest('RelationType basic operations: view list, create new entry, view, edit, delete, view') {
            login()
        	
            invoke      'relationType'
            verifyText  'Home'

//            verifyListSize defaultRelationTypeCount

            createRelationType()
            
            verifyText  'Show Relation Type', description:'Detail page'

            clickLink   'List', description:'Back to list view'
//            verifyListSize defaultRelationTypeCount +1

            group(description:'verify that no delete button is in edit view') {
    			showFirstElementDetails('testRelationType')

    			clickButton 'Edit'
    			verifyText  'Edit Relation Type'
    			not {
    				clickButton name: '_action_Delete'
    			}
            }

            invoke		'relationType/list'
//            verifyListSize defaultRelationTypeCount +1

            deleteRelationType()

//            verifyListSize defaultRelationTypeCount
            
            logout()
        }
    }
    
    def createRelationType() {
        group(description: 'create Relation Type') {
            clickLink   'New Relation Type'
            verifyText  'Create Relation Type'
    		setInputField 	name:'name', value:'testRelationType'
            clickButton 'Create'
        }
    }

    def deleteRelationType() {
        group(description:'delete the only element') {
            showFirstElementDetails('testRelationType')
            clickButton 'Delete'
//            verifyXPath xpath:  "//div[@class='message']",
//                        text:   /.*RelationType.*deleted.*/,
//                        regex:  true
        }
    }
   
}