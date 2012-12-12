import org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration

// dataSource for LifecycleLog:
dataSource_logging {
    dbCreate = 'update'
    configClass = GrailsAnnotationConfiguration.class // use hibernate classes
    pooled = true
    driverClassName = "org.postgresql.Driver"
    url = "jdbc:postgresql://127.0.0.1:5432/demo"
    dialect = "org.hibernate.dialect.PostgreSQLDialect"
    username = "cinnamon"
    password = "cinnamon"
    properties {
        initialSize = 10
        maxActive = 500
        minEvictableIdleTimeMillis = 300000
        timeBetweenEvictionRunsMillis = 300000
        numTestsPerEvictionRun = 5
        testOnBorrow = true
        testWhileIdle = true
        testOnReturn = true
        validationQuery = "SELECT 1"
    }
}

dbconnections {

    demo {
        id = 1
        prefix = 'demo'
        driverClassName = 'org.postgresql.Driver'
        jdbcType = 'postgresql'
        host = 'localhost'
        cinnamonServerUrl = 'http://localhost:8080/cinnamon/cinnamon'
        port = 5432
        dbName = 'demo'
        dbname = 'demo' // legacy setting
        username = 'cinnamon'
        password = 'cinnamon'
        dbUser = 'cinnamon'
        dbPassword = 'cinnamon'
        dbType = 'postgresql'
        encryptPasswords = 'true'
    }
      
}

