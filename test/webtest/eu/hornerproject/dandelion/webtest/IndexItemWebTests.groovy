package eu.hornerproject.dandelion.webtest

class IndexItemWebTests extends BaseTest {
	
    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001XclassNameXListNewDelete
	def defaultListSize = 32

    void testCreateDelete() {
	   login()
	   verifyText		'Index Items'

	   invoke      'indexItem'
	   verifyText  'Home'
	
	   verifyListSize 10

	   createIndexItem()
	   
	   verifyText  'Show Index Item', description:'Detail page'
	   clickLink   'List', description:'Back to list view'
	
	   verifyListSize 10

	   invoke 'indexItem/list?sort=id&order=desc', description:'sort by ID'
	   
       group(description:'edit the one element') {
           showFirstElementDetails('testIndexItem')
           clickButton name:'_action_edit'
           verifyText  'Edit Index Item'
           clickButton name:'_action_update'
           verifyText  'Show Index Item'
           clickLink   'List', description:'Back to list view'
       }

       verifyListSize 10

	   invoke 'indexItem/list?sort=id&order=desc', description:'sort by ID'
		   
	    deleteIndexItem()
	   
	   logout()
	}

    void testNoDeleteInEditView() {
  	   login()
  	   
  	   invoke      'indexItem'
  	   verifyText  'Home'
  	
  	   verifyListSize 10

  	   createIndexItem()
  	   
  	   verifyText  'Show Index Item', description:'Detail page'
  	   clickLink   'List', description:'Back to list view'
  	
  	   verifyListSize 10

  	   invoke 'indexItem/list?sort=id&order=desc', description:'sort by ID'
  	   
       group(description:'verify that no delete button is in edit view') {
			showFirstElementDetails('testIndexItem')

			clickButton 'Edit'
			verifyText  'Edit Index Item'
			not {
				clickButton name: '_action_Delete'
			}
  	   }

  	   invoke 'indexItem/list'

        verifyListSize 10

        invoke 'indexItem/list?sort=id&order=desc', description:'sort by ID'
  		   
        deleteIndexItem()
  	   
  	   	logout()
  	}

    void testDuplicateNameFail() {
    	login()
    	   
    	invoke      'indexItem'
        verifyText  'Home'

        invoke		'indexItem/list?max='+ (defaultListSize+1)
        verifyListSize defaultListSize

        createIndexItem()
    	  
    	verifyText  'Show Index Item', description:'Detail page'
		
    	invoke		'indexItem/list?max='+ (defaultListSize+1)
		verifyListSize defaultListSize + 1
		
		// try to create a duplicate of the test index type
		createIndexItem()
		verifyText 'A Index Item with the Name testIndexItem already exists.'
		
		invoke		'indexItem/list?max='+ (defaultListSize+1)
		verifyListSize defaultListSize + 1
		   
		deleteIndexItem()

    	invoke		'indexItem/list?max='+ (defaultListSize+1)
		verifyListSize defaultListSize
		   
		logout()
    }

    
    def createIndexItem() {
  	   group (description: 'create Index Item') {
		   clickLink   'New Index Item'
		   verifyText  'Create Index Item'
		   setInputField 	name:'name', 			value:'testIndexItem'
		   setInputField	name:'fieldname', 		value:'testFieldName'
		   setCheckbox 		name:'forContent', 		checked:'true' 	
		   setCheckbox 		name:'forMetadata', 	checked:'true' 	
		   setCheckbox 		name:'forSysMeta', 		checked:'true' 	
		   setSelectField	name:'indexGroup.id', 	optionIndex:'0' 	
		   setSelectField	name:'indexType.id', 	optionIndex:'0' 	
		   setCheckbox 		name:'multipleResults', checked:'true' 	
		   setInputField 	name:'searchCondition', value:'search condition'
		   setInputField 	name:'searchString', 	value:'search string'
		   setCheckbox 		name:'systemic', 		checked:'true' 	
		   setInputField 	name:'vaProviderParams', 	value:'VA Provider Params'
			   
		   clickButton name:'create'
	   }
    }

    def deleteIndexItem() {
        group(description:'delete the only element') {
            showFirstElementDetails('testIndexItem')
            clickButton name:'_action_delete'
            verifyXPath xpath:  "//div[@class='message']",
                        text:   /.*Index Item.*deleted.*/,
                        regex:  true
        }
    }

}