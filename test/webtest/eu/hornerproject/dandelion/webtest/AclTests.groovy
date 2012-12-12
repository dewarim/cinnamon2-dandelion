package eu.hornerproject.dandelion.webtest

import org.grails.plugins.webtest.WebTestCase as WTC

class AclTests extends BaseTest {

    // Unlike unit tests, functional tests are often sequence dependent.
    // Specify that sequence here.

    void suite() {
        testAclListNewDelete()
        // add tests for more operations here

    }

    void testAclListNewDelete() {
        webtest('Acl basic operations: view list, create new entry, view, edit, delete, view') {
            login()
            verifyText 'ACLs'

            invoke 'acl'
            verifyText 'Home'

            verifyText 'Default ACL'

            createAcl()

            verifyText 'Show ACL', description: 'Detail page'
            clickLink 'List', description: 'Back to list view'

            verifyText 'testAcl'
            verifyText 'Default ACL'

            group(description: 'edit the one element') {
                showFirstElementDetails('testAcl')

                clickButton 'Edit'
                verifyText 'Edit ACL'
                clickButton 'Update'
                verifyText 'Show ACL'
                clickLink 'List', description: 'Back to list view'
            }

            verifyText 'Default ACL'

            deleteAcl()

            verifyText 'Default ACL'

            logout()
        }
    }

    void testNoDeleteInEditView() {
        webtest('No Delete button in edit view') {
            login()

            invoke 'acl'
            verifyText 'Home'

            createAcl()

            verifyText 'Show ACL', description: 'Detail page'
            clickLink 'List', description: 'Back to list view'

            group(description: 'verify that no delete button is in edit view') {
                showFirstElementDetails('testAcl')

                clickButton 'Edit'
                verifyText 'Edit ACL'
                not {
                    clickButton name: '_action_Delete'
                }
            }

            invoke 'acl/list'

            verifyText 'Default ACL'
            verifyText 'testAcl'

            deleteAcl()


            logout()
        }
    }

    def createAcl() {
        WTC.ant.group(description: 'create ACL') {
            clickLink 'New ACL'
            verifyText 'Create ACL'

            setInputField name: 'name', value: 'testAcl'
            setInputField name: 'description', value: 'test acl'

            clickButton 'Create'
        }
    }

    def deleteAcl() {
        group(description: 'delete the only element') {
            showFirstElementDetails('testAcl')
            clickButton 'Delete'

            verifyXPath xpath: "//div[@class='message']",
                    text: /.*Acl.*deleted.*/,
                    regex: true

            verifyText('testAcl has been deleted')
        }
    }

    def showLastListPage() {
        // TODO: da momentan cmn_dev verwendet wird, enthält die Liste momentan noch mehrere existierende Einträge
        invoke 'acl/list?offset=30&amp;max=10'
    }

}