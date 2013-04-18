import org.codehaus.groovy.grails.orm.hibernate.cfg.GrailsAnnotationConfiguration

// id : unique ID for this environment
// dbname : alias for this environment
// prefix: internal prefix for this environment
// jdbc:${env.jdbcType}://${env.host}:${port}/${env.dbname}

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

// dataSource for Audit-Trail:
dataSource_audit {
    dbCreate = 'update'
    configClass = GrailsAnnotationConfiguration.class // use hibernate classes
    pooled = true
    driverClassName = "org.postgresql.Driver"
    url = "jdbc:postgresql://127.0.0.1:5432/cinnamon_audit"
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



indexers = [
        'server.index.indexer.DefaultIndexer',
        'server.index.indexer.BooleanXPathIndexer',
        'server.index.indexer.DateXPathIndexer',
        'server.index.indexer.IntegerXPathIndexer',
        'server.index.indexer.DefaultIndexer',
        'server.index.indexer.DecimalXPathIndexer',
        'server.index.indexer.TimeXPathIndexer',
        'server.index.indexer.ReverseStringIndexer',
        'server.index.indexer.ReverseCompleteStringIndexer',
        'server.index.indexer.ParentFolderPathIndexer',
        'server.index.indexer.DateTimeIndexer'
]

valueAssistanceProviders = [
        'server.index.valueAssistance.DefaultProvider',
]

relationResolvers = [
        'server.resolver.FixedRelationResolver',
        'server.resolver.LatestHeadResolver',
        'server.resolver.LatestBranchResolver'
]

transformers = [
        'server.transformation.XhtmlToPdfTransformer'
]

lifeCycleStateClasses = [
        'server.lifecycle.state.NopState',
        'server.lifecycle.state.ChangeAclState'
]