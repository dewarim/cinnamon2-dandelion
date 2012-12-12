package eu.hornerproject.dandelion

import eu.hornerproject.humulus.EnvironmentHolder
import utils.PersistenceSessionProvider
import utils.DefaultPersistenceSessionProvider
import javax.persistence.EntityManager
import utils.HibernateSession

class BaseController {

    def inputValidationService
    def aclEntryService
    def healthService
    def lifeCycleStateService
    def outputService
    def userService
    def groupService

    void setListParams() {
        params.offset = params.offset ? inputValidationService.checkAndEncodeInteger(params, "offset", "offset") : 0
        params.sort = params.sort ? inputValidationService.checkAndEncodeText(params, "sort", "sort") : 'id'
        params.max = params.max ? inputValidationService.checkAndEncodeInteger(params, 'max', 'paginate.max') : 100
        params.firstResult = params.firstResult ? inputValidationService.checkAndEncodeInteger(params, 'firstResult', 'paginate.firstResult') : 0
    }

    /*
     * this code does no longer work since Cinnamon 2.1.0 / Grails 2.0 , it seems.
     */
    void setHibernateSessionEm(session){
        def env = EnvironmentHolder.getEnvironment()
        PersistenceSessionProvider psp = new DefaultPersistenceSessionProvider(
                session.repositoryName, env.persistenceUnit, env.dbConnectionUrl
        )
        EntityManager em = HibernateSession.getRepositoryEntityManager(psp)
        HibernateSession.setLocalEntityManager(em)
    }

    def rePaginate () {
        render(template: 'rePaginate')
    }
}
