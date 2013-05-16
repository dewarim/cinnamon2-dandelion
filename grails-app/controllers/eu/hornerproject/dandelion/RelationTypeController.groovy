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
        Boolean leftprotected = params.leftobjectprotected?.matches(/true|on/) ?: false
        Boolean rightprotected = params.rightobjectprotected?.matches(/true|on/) ?: false
        Boolean cloneOnLeftCopy = params.cloneOnLeftCopy?.matches(/true|on/) ?: false
        Boolean cloneOnLeftVersion = params.cloneOnLeftVersion?.matches(/true|on/) ?: false
        Boolean cloneOnRightCopy = params.cloneOnRightCopy?.matches(/true|on/) ?: false
        Boolean cloneOnRightVersion = params.cloneOnRightVersion?.matches(/true|on/) ?: false
        RelationResolver leftResolver = RelationResolver.get(left_resolver_id)
        RelationResolver rightResolver = RelationResolver.get(right_resolver_id)
        RelationType relationType = new RelationType(name, description,
                leftprotected, rightprotected,
                leftResolver, rightResolver,
                cloneOnRightCopy, cloneOnLeftCopy,
                cloneOnRightVersion, cloneOnLeftVersion
        )
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

    protected booleanFields = ['leftobjectprotected', 'rightobjectprotected',
        'cloneOnLeftCopy', 'cloneOnRightCopy', 'cloneOnLeftVersion', 'cloneOnRightVersion']
    
    def update(Long id) {
        RelationType rt = RelationType.get(id)
        try{
            if(! rt){
                throw new RuntimeException("error.object.not.found")
            }
            booleanFields.each{name ->
                if(params.containsKey(name)){
                    rt."$name" = params.get(name).toString().matches(/true|on/) ?: false
                }
                else{
                    rt."$name" = false
                }
            }
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
