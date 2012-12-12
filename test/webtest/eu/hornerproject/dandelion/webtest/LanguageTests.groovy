package eu.hornerproject.dandelion.webtest

class LanguageTests extends BaseTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.
    void suite() {
        testLanguageListNewDelete()
        // add tests for more operations here
    }

    void testLanguageListNewDelete() {
        webtest('Basic Language operations: view list, create new entry, view, edit, delete, view') {
            login()
        	verifyText		'Languages'

            invoke      'language/list'
            verifyText  'Home'

            verifyListSize 3

            clickLink   'Add a language'
            verifyText  'Create'
    		setInputField 	name:'isoCode', value:'testLanguage'
            clickButton 'Create'
            verifyText  'Language object details', description:'Show language page'
            clickLink   'List languages', description:'Back to list view'

            verifyListSize 4

            group(description:'edit the test language') {
                showFirstElementDetails('testLanguage')
                clickButton 'Edit'
                verifyText  'Edit language'
                setInputField name:'isoCode', value:'testLanguage2'
                clickButton 'Save changes'
                verifyText  'Successfully updated language'
                // revert changes:
                clickButton 'Edit'
                verifyText  'Edit language'
                setInputField name:'isoCode', value:'testLanguage'
                clickButton 'Save changes'
                verifyText  'Successfully updated language'
                
                clickLink   'List languages', description:'Back to list view'
            }

            verifyListSize 4

            group(description:'edit the test with invalid isoCode') {
                showFirstElementDetails('testLanguage')
                clickButton 'Edit'
                verifyText  'Edit language'
                setInputField name:'isoCode', value:'und'
                clickButton 'Save changes'
                verifyText  'already exists'
                clickLink   'List languages', description:'Back to list view'
            }
            
            group(description:'delete the test language') {
                showFirstElementDetails('testLanguage')
                clickButton 'Delete'
                verifyXPath xpath:  "//div[@class='message']",
                            text:   /Successfully deleted language.*/,
                            regex:  true
            }

            verifyListSize 3
            
            logout()
        }
    }

}