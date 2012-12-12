package eu.hornerproject.dandelion.webtest

/**
 *
 */
class FolderControllerTests extends BaseTest {

    // define the order of tests:
    void suite(){
        
    }

    void testBrowseFolderContent(){
        webtest('Login as admin, go to folder, browse content list'){
            login()

            verifyText 'Browse repository'

            // the following does not work, as webtest uses an old version of HtmlUnit
            // which croaks on encountering jQuery.

            // go to folder list:
//            clickLink   'Browse repository', description:'go to repository view'
//            verifyText  'system'
//            verifyText  'testFolder'

//            // open test folder to view sub folders below
//            clickLink   href:'/dandelion/folder/fetchFolder'
//            verifyText  'subTestFolder'

        }
    }



}
