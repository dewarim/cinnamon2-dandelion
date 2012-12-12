package eu.hornerproject.dandelion

import server.Format
import server.data.ObjectSystemData
import grails.plugins.springsecurity.Secured

@Secured(["hasRole('_superusers')"])
class FormatController extends BaseController{

    def index () {
        redirect(action:'list', controller:'format')
    }

    def list () {
        setListParams()
        return [formatList:Format.list(params)]
    }

    def create () {
//        Format f = new Format(params)
//        return [format:null
    }

    def edit () {
        Format format = Format.get(params.id)
        if(format){
            return [format:format]
        }
        flash.message = message(code:'error.access.failed')
        return redirect(action:'list', controller:'format')
    }

    def show () {
       if(params.id){
            Format format = Format.get(params.id)
            if(format != null){
    		    return [format: format]
            }
        }
        flash.message = message(code:'error.access.failed')
        return redirect(action:'list', controller:'format')
    }

    def delete () {
		def format = Format.get(params.id)
        if( ! format){
            flash.message = message(code:'error.access.failed')
            return redirect(action:'list', controller:'format')
        }

        def osdCount = ObjectSystemData.countByFormat(format)
        if (osdCount == 0) {
            flash.message = message(code:'format.delete.success', args:[format.name])
			format.delete()
        }
        else {
            flash.message = message(code: 'objectType.still.used', args: [format.name])
        }
        return redirect(action: 'list')
    }

    def save () {
    	if (!params.name || !params.name.trim()) {
       		flash.error = message(code: 'format.empty.name')
       		return redirect(action:'create', controller:'format', params:params)
    	}
        else{
            Format format = new Format()
            format.properties = params
            if(! format.save()){
                return redirect(action:'create', controller:'format',  params:params)
            }
            else{
                
                return redirect(action:'show', controller:'format', id:format.id)
            }
        }
    }

    def update () {
        Format format = Format.get(params.id)
        if (!params.name || !params.name.trim()) {
       		flash.message = message(code: 'format.empty.name')
       		return redirect(action:'edit', controller:'format', id:format.id)
    	}
        format.properties = params

        if(! format.save()){
            flash.message = message(code:'format.update.failed')
        }
        return redirect(action:'show', controller:'format', id:format.id)
    }

    def updateList () {
        setListParams()
        render(template: 'listTable', model:[formatList:Format.list(params)])
    }

}
