package eu.hornerproject.dandelion

import org.codehaus.groovy.grails.commons.ConfigurationHolder;

import server.index.IndexType
import server.index.IndexItem
import server.index.Indexer
import server.index.ValueAssistanceProvider
import grails.plugins.springsecurity.Secured;

@Secured(["hasRole('_superusers')"])
class IndexTypeController extends BaseController{

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index () {
        redirect(action: "list", params: params)
    }

    def list () {
        setListParams()
        [indexTypeList: IndexType.list(params)]
    }

    def create () {
//        def indexTypeInstance = new IndexType()
//        indexTypeInstance.properties = params
        return [
//                indexTypeInstance: indexTypeInstance,
                indexers : ConfigurationHolder.config.indexers,
                valueAssistanceProviders : ConfigurationHolder.config.vaProviders,
        ]
    }

    def save () {
    	if (params.name.length() == 0) {
    		flash.error = message(code: 'error.create.indexType.empty.name')
    		render(view: "create", model: [:])
    		return
    	}
    	if (params.indexerClass.length() == 0) {
    		flash.error = message(code: 'error.create.indexType.empty.indexerClass')
    		render(view: "create", model: [:])
    		return
    	}
    	if (params.vaProviderClass.length() == 0) {
    		flash.error = message(code: 'error.create.indexType.empty.vaProviderClass')
    		render(view: "create", model: [:])
    		return
    	}
    	if (IndexType.findByName(params.name)) {
    		flash.error = message(code: 'error.create.duplicate.name', args: [message(code:'indexType.label'), message(code:'indexType.name.label'), params.name?.encodeAsHTML()])
    		render(view: "create", model: [:])
    		return
    	}

    	String name = params.name
    	try {
	    	Class<Indexer> indexer = Class.forName(params.indexerClass - 'class ', true, Thread.currentThread().contextClassLoader )
	    	Class<ValueAssistanceProvider> vaProviderClass = Class.forName(params.vaProviderClass - 'class ',  true, Thread.currentThread().contextClassLoader )
	    	IndexType.DataType dataType = Enum.valueOf(IndexType.DataType, params.dataType)
	    	IndexType indexTypeInstance = new IndexType(name, indexer, vaProviderClass, dataType)
	
	        if (indexTypeInstance.save(flush: true)) {
	            flash.message = "${message(code: 'default.created.message', args: [message(code: 'indexType.label', default: 'IndexType'), indexTypeInstance.id])}"
	            redirect(action: "show", id: indexTypeInstance.id)
	        }
	        else {
	            render(view: "create", model: [indexTypeInstance: indexTypeInstance])
	        }
    	} catch (ClassNotFoundException ex) {
            log.debug("ClassNotFound: ", ex);
    		flash.error = message(code:'class.not.found', args: [ex?.cause?.message])
    		render(view: "create", model: [:])
    	}
    }

    def show () {
//        IndexType foo = new IndexType();
//        log.debug(foo)
//        foo = IndexType.get(62)
        def indexTypeInstance = null
        try {
            indexTypeInstance = IndexType.get(params.id)
            if (! indexTypeInstance) {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'indexType.label', default: 'IndexType'), params.id])}"
                return redirect(action: "list")
            }
        }
        catch (Exception e) {
            log.debug("failed to display IndexType:",e)
        }
        
        return [indexTypeInstance:indexTypeInstance,
            deleteAllowed: IndexItem.findAll("from IndexItem as item where item.indexType=?", [indexTypeInstance])?.isEmpty()
            //IndexItem.findByIndexType(indexTypeInstance)
        ]
    }

    def edit () {
        def indexTypeInstance = IndexType.get(params.id)
        if (!indexTypeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'indexType.label', default: 'IndexType'), params.id])}"
            return redirect(action: "list")
        }
        else {
            return [indexTypeInstance: indexTypeInstance,
                    indexers : ConfigurationHolder.config.indexers,
                    valueAssistanceProviders : ConfigurationHolder.config.vaProviders,
            ]
        }
    }

    def update () {
        def indexTypeInstance = IndexType.get(params.id)
        if (indexTypeInstance) {
            if (params.obj_version) {
                def obj_version = params.obj_version.toLong()
                if (indexTypeInstance.obj_version > obj_version) {
                    
                    indexTypeInstance.errors.rejectValue("obj_version", "default.optimistic.locking.failure", [message(code: 'indexType.label', default: 'IndexType')] as Object[], "Another user has updated this IndexType while you were editing")
                    render(view: "edit", model: [indexTypeInstance: indexTypeInstance])
                    return
                }
            }
            indexTypeInstance.properties = params
            if (!indexTypeInstance.hasErrors() && indexTypeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'indexType.label', default: 'IndexType'), indexTypeInstance.id])}"
                redirect(action: "show", id: indexTypeInstance.id)
            }
            else {
                render(view: "edit", model: [indexTypeInstance: indexTypeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'indexType.label', default: 'IndexType'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete () {
        def indexTypeInstance = IndexType.get(params.id)
        if (indexTypeInstance) {
            try {
                indexTypeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'indexType.label', default: 'IndexType'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'indexType.label', default: 'IndexType'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'indexType.label', default: 'IndexType'), params.id])}"
            redirect(action: "list")
        }
    }

    def updateList () {
        setListParams()
        render(template: 'indexTypeList', model:[indexTypeList:IndexType.list(params)])
    }

}
