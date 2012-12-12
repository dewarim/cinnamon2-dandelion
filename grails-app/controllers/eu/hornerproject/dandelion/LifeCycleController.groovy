package eu.hornerproject.dandelion

import grails.plugins.springsecurity.Secured
import server.lifecycle.LifeCycle
import server.lifecycle.LifeCycleState
import server.data.ObjectSystemData

@Secured(["hasRole('_superusers')"])
class LifeCycleController extends BaseController {

    def create () {
        render(template: 'create', model: [
               states:getDefaultStates(null),
               defaultStates:getDefaultStates(null)])
    }

    def index () {
        params.max = params.max ?: 10
        params.sort = params.sort ?: 'id'
        [lifeCycleList: LifeCycle.list(params)]
    }

    def save () {
        LifeCycle lifeCycle = new LifeCycle()
        try {
            updateFields(lifeCycle)
            lifeCycle.save(flush: true)
            setListParams()
            render(template: 'list_table', model: [lifeCycleList: LifeCycle.list(params)])
        }
        catch (Exception e) {
            log.debug("failed to save lifeCycle: ", e)
            render(status: 503, template: 'create',
                    model: [lifeCycle: lifeCycle,
                            defaultStates:getDefaultStates(null),
                            states:getDefaultStates(null),
                            errorMessage: e.getLocalizedMessage().encodeAsHTML()])
            return
        }

    }

    def list () {
        redirect(action: 'index')
    }

    def updateList () {
        setListParams()
        log.debug("updateList called")
        render(template: 'list_table', model:[lifeCycleList:LifeCycle.list(params)])
    }

    def edit () {
        LifeCycle lifeCycle = LifeCycle.get(Long.parseLong(params.id))
        log.debug("defaultStates: ${getDefaultStates(lifeCycle).dump()}")
        render(template: 'edit', model: [lifeCycle:lifeCycle,
                defaultStates:getDefaultStates(lifeCycle),
                states:getDefaultStates(lifeCycle)
        ])
    }

    def cancelEdit () {
        render(template: 'row', model: [lifeCycle: LifeCycle.get(Long.parseLong(params.id))])
    }

    def delete () {
        LifeCycle lifeCycle = LifeCycle.get(params.id)
        try {
            lifeCycle.states.each {state ->
                // first check if any states are in use at the moment -
                // we cannot leave objects lying around with orphaned LifeCycleStates.
                if (ObjectSystemData.findByState(state)) {
                    throw new RuntimeException("error.object.in.use")
                }
            }
            def states = lifeCycle.states.collect{it}
            states.each{state ->
                lifeCycle.removeFromStates(state)
                state.lifeCycle = null
            }
            if(lifeCycle.defaultState){
                lifeCycle.defaultState.lifeCycle = null
                lifeCycle.defaultState = null
            }
            lifeCycle.delete(flush: true)
        }
        catch (Exception e) {
//            log.debug("ex: ",e)
            render(status: 503, template: '/shared/showError', model: [infoMessage: message(code: e.getLocalizedMessage())])
            return
        }
        setListParams()
        render(template: 'list_table', model: [lifeCycleList: LifeCycle.list(params)])
    }

    protected void updateFields(lifeCycle) {
        lifeCycle.name = inputValidationService.checkAndEncodeName(params.name, lifeCycle)
        if(params.defaultState){
            lifeCycle.defaultState = inputValidationService.checkObject(LifeCycleState.class, params.defaultState)
        }
        else if(lifeCycle.defaultState){
            log.debug("remove defaultState from lifeCycle")
            lifeCycle.removeFromStates(lifeCycle.defaultState)
            lifeCycle.defaultState = null
        }
        def states = params.list('states').collect {inputValidationService.checkObject(LifeCycleState.class, it)}
        if(! states.contains(lifeCycle.defaultState) ){
            states.add(lifeCycle.defaultState)
        }
        /*
         * Try to remove LifeCycleStates which are no longer valid.
         */
        def currentStates = lifeCycle.states.collect{it}
        currentStates.each {LifeCycleState state ->
            if (!states.contains(state)) {
                if (ObjectSystemData.findByState(state)) {
                    throw new RuntimeException("error.object.in.use")
                }
                log.debug("remove no longer valid LCS from lifeCycle.states")
                lifeCycle.removeFromStates(state)
                state.lifeCycle = null
            }
        }
        /*
         * Add all the new LifeCycleStates:
         */
        states.each {LifeCycleState state ->
            if (state && ! lifeCycle.states.contains(state)) {
                lifeCycle.states.add(state)
                state.lifeCycle = lifeCycle
            }
        }
    }

    def update () {
        LifeCycle lifeCycle = LifeCycle.get(params.id)
        try {
            updateFields(lifeCycle)
            lifeCycle.save(flush: true)
            render(template: 'row', model: [lifeCycle: lifeCycle])
        }
        catch (Exception e) {
            log.debug("failed to save lifeCycle: ", e)
            render( status:503,
                    template: 'edit',
                    model: [lifeCycle: lifeCycle,
                            defaultStates:getDefaultStates(lifeCycle),
                            states:getDefaultStates(lifeCycle),
                            errorMessage: e.getLocalizedMessage()])
            return
        }

    }

    protected List<LifeCycleState> getDefaultStates(LifeCycle lc){
        List<LifeCycleState> lcList = new ArrayList<LifeCycleState>()
        if(lc == null){
            // used where we have an unsaved lifeCycle instance.
            lcList.addAll(LifeCycleState.findAll("from LifeCycleState lcs where lcs.lifeCycle=null"))
        }
        else{
            lcList.addAll(LifeCycleState.findAll("from LifeCycleState lcs where lcs.lifeCycle=null or lcs.lifeCycle=:lifeCycle",[lifeCycle:lc]))
        }
        return lcList
    }

}
