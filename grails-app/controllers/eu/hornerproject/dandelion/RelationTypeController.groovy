package eu.hornerproject.dandelion

import server.RelationType
import grails.plugins.springsecurity.Secured
import server.RelationResolver

@Secured(["hasRole('_superusers')"])
class RelationTypeController extends BaseController{

    def create() {}

    def index() {
        redirect(action:'list')
    }
    
    def save(String name, String description, Long left_resolver_id, Long right_resolver_id) {
        log.debug("relationType::${params}")

        RelationResolver leftResolver = RelationResolver.get(left_resolver_id)
        RelationResolver rightResolver = RelationResolver.get(right_resolver_id)
        RelationType relationType = new RelationType(name:name, description:description,
                leftResolver:leftResolver, rightResolver:rightResolver
        )
        setBooleanFields(relationType)
    	try{
    		relationType.save(failOnError:true)
    	}
    	catch(Exception e){
            log.debug("failed to save relationType: ",e)
    		flash.message = e.getLocalizedMessage().encodeAsHTML()
    		return redirect(action:'create', controller:'relationType', model:[relationType:relationType]) //, params:[relationType:relationType])
    	}

    	return redirect(action : 'show', params : [id : relationType?.id])
    }
    
    protected final booleanFields = ['leftobjectprotected', 'rightobjectprotected',
            'cloneOnLeftCopy', 'cloneOnRightCopy', 'cloneOnLeftVersion', 'cloneOnRightVersion']

    protected setBooleanFields(relationType){
        booleanFields.each{ fName ->
            if(params.containsKey(fName)){
                relationType."$fName" = params.get(fName).toString().matches(/true|on/) ?: false
            }
            else{
                relationType."$fName" = false
            }
        }      
    }
    
    def list() {
    		setListParams()
    		[relationTypeList : RelationType.list(params)]
    }

    def show() {
        if(params.id){
            RelationType rt = RelationType.get(params.id)
            if(rt != null){
    		    return [relationType : rt]
            }
        }
        flash.message = message(code:'error.access.failed')
        return redirect(action:'list', controller:'relationType')
    }

    def edit(){
    		[relationType : RelationType.get(params.id)]
    }

    def delete() {
        RelationType rt = RelationType.get(params.id)
		try{
			rt.delete()
		}
		catch(Exception e){
			flash.message = e.getLocalizedMessage()
			return redirect(action:'list')
		}

		flash.message = message(code: 'relationtype.delete.success', args:[rt.name.encodeAsHTML()])
		return redirect(action:'list')
    }
      
    def update(Long id) {
        RelationType rt = RelationType.get(id)
        try{
            if(! rt){
                throw new RuntimeException("error.object.not.found")
            }
            setBooleanFields(rt)          
            rt.leftResolver = RelationResolver.get(params.left_resolver_id)
            rt.rightResolver = RelationResolver.get(params.right_resolver_id)
            rt.name = params.name
            rt.save(flush:true)
        }
        catch (Exception e){
           	flash.message = e.getLocalizedMessage()
			return redirect(action:'edit', params:[id:id])
        }
        return redirect(action:'show', params:[id:id])
    }

    def updateList() {
        setListParams()
        render(template: 'relationTypeList', model:[relationTypeList:RelationType.list(params)])
    }

}
