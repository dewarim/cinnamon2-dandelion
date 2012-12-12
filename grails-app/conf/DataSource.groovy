import org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration

dataSource {
	configClass = GrailsAnnotationConfiguration.class // use hibernate classes
	pooled = true
    properties{
        initialSize = 10
        maxActive = 500
        minEvictableIdleTimeMillis=300000
        timeBetweenEvictionRunsMillis=300000
        numTestsPerEvictionRun=5
        testOnBorrow=true
        testWhileIdle=true
        testOnReturn=true
        validationQuery="SELECT 1"
    }
}

hibernate {
    cache.use_second_level_cache=false
    cache.use_query_cache=false
    cache.provider_class='net.sf.ehcache.hibernate.EhCacheProvider'
    connection.provider_class='org.hibernate.connection.C3P0ConnectionProvider'
}
// environment specific settings
environments {
	development {
		dataSource {
            dbCreate = "none"            
//			dbCreate = "create-drop" // one of 'create', 'create-drop','update'
		}
	}
	test {
		dataSource {
			dbCreate = "none"
		}
	}
	production {
		dataSource {
            dbCreate = "none"
            properties{
                initialSize = 10
                maxActive = 500
                minEvictableIdleTimeMillis=300000
                timeBetweenEvictionRunsMillis=300000
                numTestsPerEvictionRun=5
                testOnBorrow=true
                testWhileIdle=true
                testOnReturn=true
                validationQuery="SELECT 1"
            }
//			driverClassName = "net.sourceforge.jtds.jdbc.Driver"
//			url = "jdbc:hsqldb:file:prodDb;shutdown=true"
		}
	}
}
