package eu.hornerproject.dandelion.webtest

class FormatTests extends BaseTest {

    Integer defaultFormatCount = 5

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testFormatListNewDelete()
        // add tests for more operations here
        testNoDeleteInEditView()
    }

    void testFormatListNewDelete() {
        webtest('Format basic operations: view list, create new entry, view, edit, delete, view') {
        	login()
            verifyText	'Formats'

            invoke      'format'
            verifyText  'Home'

        	verifyListSize defaultFormatCount

        	createFormat()
        	
            verifyText  'Show Format', description:'Detail page'
            clickLink   'List', description:'Back to list view'

            verifyListSize defaultFormatCount+1

            group(description:'edit the one element') {
                showFirstElementDetails('testFormat')

				clickButton 'Edit'
                verifyText  'Edit Format'
                clickButton 'Update'
                verifyText  'Show Format'
                clickLink   'List', description:'Back to list view'
            }

            verifyListSize defaultFormatCount+1

            deleteFormat()

        	verifyListSize defaultFormatCount
            logout()
        }
    }

    void testNoDeleteInEditView() {
        webtest('Format basic operations: view list, create new entry, view, edit, delete, view') {
        	login()

            invoke      'format'
            verifyText  'Home'

        	verifyListSize defaultFormatCount

        	createFormat()
        	
            verifyText  'Show Format', description:'Detail page'
            clickLink   'List', description:'Back to list view'

            verifyListSize defaultFormatCount+1

            group(description:'verify that no delete button is in edit view') {
    			showFirstElementDetails('testFormat')

    			clickButton 'Edit'
    			verifyText  'Edit Format'
    			not {
    				clickButton name: '_action_Delete'
    			}
            }

        	invoke 'format/list'

            verifyListSize defaultFormatCount+1

            deleteFormat()

        	verifyListSize defaultFormatCount

            logout()
        }
    }
    
    def createFormat() {
    	group(description: 'create new format') {
            clickLink   'New Format'

			setInputField 	name:'name', value:'testFormat'

            verifyText  'Create Format'
            clickButton 'Create'
    	}
    }

    def deleteFormat() {
        group(description:'delete the only element') {
            showFirstElementDetails('testFormat')
            clickButton 'Delete'

            verifyXPath xpath:  "//div[@class='message']",
                        text:   /.*Format.*deleted.*/,
                        regex:  true
        }
    }

    def showLastListPage() {
    	// TODO: da momentan cmn_dev verwendet wird, enthält die Liste momentan noch mehrere existierende Einträge
        invoke 'format/list?offset=10&amp;max=10'
    }

}