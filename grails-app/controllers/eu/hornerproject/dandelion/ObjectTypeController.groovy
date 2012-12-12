package eu.hornerproject.dandelion

import server.data.ObjectSystemData
import server.ObjectType
import grails.plugins.springsecurity.Secured
import server.global.Constants

@Secured(["hasRole('_superusers')"])
class ObjectTypeController extends BaseController{

    def defaultObjectTypeName = Constants.OBJTYPE_DEFAULT

    def index () {
        redirect(action:'list', controller:'objectType')
    }

    def list () {
        setListParams()
        return [objectTypeList:ObjectType.list(params)]
    }

    def create () {
//        ObjectType oType = new ObjectType(params)
//        return [objectType:oType]
    }

    def edit () {
        ObjectType oType = ObjectType.get(params.id)
        if(oType){
            return [objectType:oType]
        }
        flash.message = message(code:'error.access.failed')
        return redirect(action:'list', controller:'objectType')
    }

    def show () {
       if(params.id){
            ObjectType oType = ObjectType.get(params.id)
            if(oType != null){
    		    return [objectType:oType]
            }
        }
        flash.message = message(code:'error.access.failed')
        return redirect(action:'list', controller:'objectType')
    }
    
    def save () {
    	if (!params.name || !params.name.trim()) {
       		flash.error = message(code: 'objectType.empty.name')
       		return redirect(action:'create', controller:'objectType', params:params)
    	}
        else{
            ObjectType oType = new ObjectType()
            oType.properties = params
            if(! oType.save()){
                return redirect(action:'create', controller:'objectType',  params:params)
            }
            else{
                
                return redirect(action:'show', controller:'objectType', id:oType.id)
            }
        }
    }

    def update () {
        ObjectType objectType = ObjectType.get(params.id)
        if (!params.name || !params.name.trim()) {
       		flash.message = message(code: 'objectType.empty.name')
       		return redirect(action:'edit', controller:'objectType', id:objectType.id)
    	}
        objectType.properties = params

        if(! objectType.save()){
            flash.message = message(code:'objectType.update.failed')
        }
        return redirect(action:'show', controller:'objectType', id:objectType.id)
    }
    
    def delete () {
        def objectType = ObjectType.get(params.id)
        if(! objectType){
            flash.message = message(code:'error.access.failed')
            return redirect(action:'list', controller:'objectType')
        }
        if (objectType.name == defaultObjectTypeName) {
            flash.error = message(code: 'error.delete.default.objectType', args: [defaultObjectTypeName])
            return redirect(action: 'show', params: [id: params.id])

        }

        def osdCount = ObjectSystemData.countByType(objectType)
        if (osdCount == 0) {
            flash.message = message(code: 'objectType.delete.success', args: [objectType.name])
            objectType.delete()
        }
        else {
            flash.message = message(code: 'objectType.still.used', args: [objectType.name])
        }
        return redirect(action: 'list')
    }

    def updateList () {
        setListParams()
        render(template: 'objectTypeList', model:[objectTypeList:ObjectType.list(params)])
    }

}
