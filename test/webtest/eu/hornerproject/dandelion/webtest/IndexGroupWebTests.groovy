package eu.hornerproject.dandelion.webtest

class IndexGroupWebTests extends BaseTest {

    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001XclassNameXListNewDelete

    void testCreateDelete() {
  	   login()
        verifyText		'Index Groups'

  	   invoke      'indexGroup'
  	   verifyText  'Home'
  	
  	   verifyListSize 1

  	   createIndexGroup()
  	   
  	   verifyText  'Show Index Group', description:'Detail page'
  	   clickLink   'List', description:'Back to list view'
  	
  	   verifyListSize 2

  	   invoke 'indexGroup/list?sort=id&order=desc', description:'sort by ID'
  	   
         group(description:'edit the one element') {
             showFirstElementDetails('testIndexGroup')
             clickButton name:'_action_edit'
             verifyText  'Edit Index Group'
             clickButton name:'_action_update'
             verifyText  'Show Index Group'
             clickLink   'List', description:'Back to list view'
         }

         verifyListSize 2

         invoke 'indexGroup/list?sort=id&order=desc', description:'sort by ID'
        	 
         deleteIndexGroup()
         
         verifyListSize 1
  	   
  	   logout()
  	}

    void testNoName() {
    	   login()
    	   
    	   invoke      'indexGroup'
    	   verifyText  'Home'
    	
    	   verifyListSize 1
    	
    	   clickLink   'New Index Group'
    	   verifyText  'Create Index Group'
    		   
    	   clickButton name:'create'
    	   verifyText  'Create Index Group'
    	   clickLink   'List', description:'Back to list view'
    	
    	   verifyListSize 1
    	   
    	   logout()
    	}

    void testDuplicateNameFail() {
  	   login()
  	   
  	   invoke      'indexGroup'
       verifyText  'Home'

       verifyListSize 1

       createIndexGroup()
  	   
  	   verifyText  'Show Index Group', description:'Detail page'

  	   invoke		'indexGroup/list'
  	   verifyListSize 2

  	   // try to create a duplicate of the test index type
  	   createIndexGroup()
  	   verifyText 'A Index Group with the Name testIndexGroup already exists.'

  	   invoke		'indexGroup/list'
  	   verifyListSize 2
  	   
  	   deleteIndexGroup()

  	   verifyListSize 1
         
  	   logout()
     }


    void testNoDeleteInEditView() {
	   login()
	   
	   invoke      'indexGroup'
	   verifyText  'Home'
	
	   verifyListSize 1

	   createIndexGroup()
	   
	   verifyText  'Show Index Group', description:'Detail page'
	   clickLink   'List', description:'Back to list view'
	
	   verifyListSize 2

	   invoke 'indexGroup/list?sort=id&order=desc', description:'sort by ID'
	   
       group(description:'verify that no delete button is in edit view') {
  			showFirstElementDetails('testIndexGroup')

  			clickButton 'Edit'
  			verifyText  'Edit Index Group'
  			not {
  				clickButton name: '_action_Delete'
  			}
       }

       invoke 'indexGroup/list'

       verifyListSize 2

	   invoke 'indexGroup/list?sort=id&order=desc', description:'sort by ID'
		   
       deleteIndexGroup()
       
       verifyListSize 1
	   
	   logout()
	}


    def createIndexGroup() {
	   group(description:'create Index Group') {
	  	   clickLink   'New Index Group'
	  	   verifyText  'Create Index Group'
	  	   setInputField 	name:'name', 			value:'testIndexGroup'
	  		   
	  	   clickButton name:'create'
  	   }
    }

    def deleteIndexGroup() {
       group(description:'delete the only element') {
           showFirstElementDetails('testIndexGroup')
           clickButton name:'_action_delete'
           verifyXPath xpath:  "//div[@class='message']",
                       text:   /.*Index Group.*deleted.*/,
                       regex:  true
       }
    }
    
}