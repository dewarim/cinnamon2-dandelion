package eu.hornerproject.dandelion.webtest

class IndexTypeWebTests extends BaseTest {

	def defaultListSize = 10
	
    // Unlike unit tests, functional tests are sometimes sequence dependent.
    // Methods starting with 'test' will be run automatically in alphabetical order.
    // If you require a specific sequence, prefix the method name (following 'test') with a sequence
    // e.g. test001XclassNameXListNewDelete

   void testCreateDelete() {
	   login()
       verifyText		'Index Types'
	   
	   invoke      'indexType'
       verifyText  'Home'

       verifyListSize defaultListSize

       createIndexType()
	   
       verifyText  'Show Index Type', description:'Detail page'

       invoke		'indexType/list?max='+ (defaultListSize+1)
       verifyListSize defaultListSize+1    

       group(description:'edit the one element') {
           showFirstElementDetails('testIndexType')
           clickButton name:'_action_edit'
           verifyText  'Edit Index Type'
           clickButton name:'_action_update'
           verifyText  'Show Index Type'
           invoke		'indexType/list?max='+ (defaultListSize+1)
       }

       verifyListSize defaultListSize+1 

       deleteIndexType()

       verifyListSize defaultListSize
       
	   logout()
   }

   void testCreateWithoutName() {
	   login()
	   
	   invoke      'indexType'
       verifyText  'Home'

       verifyListSize defaultListSize

       group(description: 'create Index Type withoutName') {
	       clickLink   'New Index Type'
	       verifyText  'Create Index Type'
	       setSelectField 	name:'dataType', optionIndex:'0'
	       setSelectField 	name:'indexerClass', text:'server.index.indexer.DefaultIndexer'
	       setSelectField 	name:'vaProviderClass', text:'server.index.valueAssistance.DefaultProvider'
	       clickButton name:'create'
	   }
       verifyText  'Create Index Type', description:'Detail page'
       clickLink   'List', description:'Back to list view'

       verifyListSize defaultListSize

	   logout()
   }

   void testDeleteReferencedIndexType() {
	   def indexTypeName = 'testIndexType'
	   
	   login()
	   
	   invoke      'indexType'
       verifyText  'Home'

       verifyListSize defaultListSize

       createIndexType()

	   /*
        * Simple clickLink 'List' won't cut it, because paging will return only 10 results. 
        */
       invoke		'indexType/list?max='+ (defaultListSize+1)
       verifyListSize defaultListSize+1

       // TODO: create IndexItem
       invoke 'security/index'
       group(description:'create IndexItem') {
		   invoke      'indexItem'
		   verifyText  'Home'
		
		   clickLink   'New Index Item'
		   verifyText  'Create Index Item'
		   setInputField 	name:'name', 			value:'testIndexItem'
		   setInputField	name:'fieldname', 		value:'testFieldName'
		   setCheckbox 		name:'forContent', 		checked:'true' 	
		   setCheckbox 		name:'forMetadata', 	checked:'true' 	
		   setCheckbox 		name:'forSysMeta', 		checked:'true' 	
		   setSelectField	name:'indexGroup.id', 	optionIndex:'0' 	
		   setSelectField	name:'indexType.id', 	text: indexTypeName	
		   setCheckbox 		name:'multipleResults', checked:'true' 	
		   setInputField 	name:'searchCondition', value:'search condition'
		   setInputField 	name:'searchString', 	value:'search string'
		   setCheckbox 		name:'systemic', 		checked:'true' 	
		   setInputField 	name:'vaProviderParams', 	value:'VA Provider Params'
			   
		   clickButton name:'create'
		   verifyText  'Show Index Item', description:'Detail page'
		   clickLink   'List', description:'Back to list view'
	   }
       
       invoke		'indexType/list?max='+ (defaultListSize+1)
      
      group(description:'try to delete the only element') {
           showFirstElementDetails('testIndexType')
           not{
               clickButton name:'_action_delete'
           }
       }

       group(description: 'delete IndexItem') {
    	   invoke 'indexItem/list?sort=id&order=desc', description:'sort by ID'
    	   
		   clickLink   description:'go to detail view',
				xpath: "//div[@class='list']//tbody/tr[td='testIndexItem']/td[1]/a"
		   clickButton name: '_action_delete'
       }
       
       invoke		'indexType/list?max='+ (defaultListSize+1)

       deleteIndexType()

       verifyListSize defaultListSize
       
	   logout()
   }

   void testNoDeleteInEditView() {
	   login()
	   
	   invoke      'indexType'
       verifyText  'Home'

       verifyListSize defaultListSize

       createIndexType()
	   
       verifyText  'Show Index Type', description:'Detail page'

       invoke		'indexType/list?max='+ (defaultListSize+1)
       verifyListSize defaultListSize+1

       group(description:'verify that no delete button is in edit view') {
			showFirstElementDetails('testIndexType')

			clickButton 'Edit'
			verifyText  'Edit Index Type'
			not {
				clickButton name: '_action_Delete'
			}
	   }

       invoke		'indexType/list?max='+ (defaultListSize+1)
       verifyListSize defaultListSize+1

       deleteIndexType()

       verifyListSize defaultListSize
       
	   logout()
   }

   void testDuplicateNameFail() {
	   login()
	   
	   invoke      'indexType'
       verifyText  'Home'

       verifyListSize defaultListSize

       createIndexType()
	   
       verifyText  'Show Index Type', description:'Detail page'

       invoke		'indexType/list?max='+ (defaultListSize+1)
       verifyListSize defaultListSize+1

       // try to create a duplicate of the test index type
	   createIndexType()
	   verifyText 'A indexType.label with the Name testIndexType already exists.'

       invoke		'indexType/list?max='+ (defaultListSize+1)
       verifyListSize defaultListSize+1
	   
       deleteIndexType()

       verifyListSize defaultListSize
       
	   logout()
   }


   
   def createIndexType() {
       group(description: 'create IndexType') {
	       clickLink   'New Index Type'
	       verifyText  'Create Index Type'
	       setInputField 	name:'name', value:'testIndexType'
	       setSelectField 	name:'dataType', optionIndex:'0'
	       setSelectField 	name:'indexerClass', text:'server.index.indexer.DefaultIndexer'
	       setSelectField 	name:'vaProviderClass', text:'server.index.valueAssistance.DefaultProvider'
	       clickButton name:'create'
	   }
   }

   def deleteIndexType() {
       group(description:'delete the only element') {
           showFirstElementDetails('testIndexType')
           clickButton name:'_action_delete'
           verifyXPath xpath:  "//div[@class='message']",
                       text:   /.*IndexType.*deleted.*/,
                       regex:  true
       }	   
   }
   
}