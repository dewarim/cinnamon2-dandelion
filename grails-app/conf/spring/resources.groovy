// Place your Spring DSL code here
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import eu.hornerproject.humulus.Environment
import eu.hornerproject.humulus.SwitchableDataSource2
import org.springframework.beans.factory.config.FieldRetrievingFactoryBean

beans = {
	parentDataSource(DriverManagerDataSource) { bean ->
		bean.'abstract' = true; 
	    username = "sa" 
//    	pooled = true
	} 

	Environment.list().each { env ->
        "${env.prefix}DataSource"(DriverManagerDataSource) { bean ->
	        bean.parent = parentDataSource 
	        bean.scope = 'prototype' 
   			url = env.dbConnectionUrl
			log.debug("url = '$url'")
			driverClassName = env.driverClassName
			if (env.username) { 
	            username = env.username 
	        } 
	        if (env.password) { 
	            password = env.password 
	        } 
        } 
    } 

	def dataSources = [:] 
	Environment.list().each {env ->
	    dataSources[env.id] = ref(env.prefix + 'DataSource')
        log.debug("dataSource: ${dataSources[env.id]}")
    }

    dataSource(SwitchableDataSource2) {
    	targetDataSources = dataSources
    }

    userDetailsService(eu.hornerproject.humulus.CinnamonUserDetailsService){
        // looks like this service is not injected automatically:
        repositoryService = ref('repositoryService')
    }
    passwordEncoder(eu.hornerproject.humulus.CinnamonPasswordEncoder)

    authenticationProvider(org.springframework.security.authentication.dao.DaoAuthenticationProvider){
        userDetailsService = ref('userDetailsService')
    }

    authenticationManager(org.springframework.security.authentication.ProviderManager){
        providers = ref('authenticationProvider')
    }
    
    repositoryLoginFilter(eu.hornerproject.humulus.RepositoryLoginFilter){
        authenticationManager = ref('authenticationManager')
    }

//    "javax.persistence.spi.PersistenceUnitTransactionType.RESOURCE_LOCAL"(org.springframework.beans.factory.config.FieldRetrievingFactoryBean)
//    entityManagerFactory(org.hibernate.ejb.EntityManagerFactoryImpl,
//            ref('sessionFactory'),
//            ref('javax.persistence.spi.PersistenceUnitTransactionType.RESOURCE_LOCAL'),true,null
//    )

}