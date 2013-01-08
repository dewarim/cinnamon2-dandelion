//grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)

grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}.war"

grails.project.repos.default = "myRepo"

grails.project.dependency.resolution = {
	inherits "global" // inherit Grails default dependencies
	log "verbose"

    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
        //excludes 'logback-classic'

        excludes "grails-plugin-logging"

    }

    repositories {
        mavenRepo name:'myRepo'
        mavenLocal()
        grailsRepo "http://grails.org/plugins"
		// uncomment the below to enable remote dependency resolution
	    // from public Maven repositories
	    //mavenCentral()
	    //mavenRepo "http://snapshots.repository.codehaus.org"
	    //mavenRepo "http://repository.codehaus.org"
	    //mavenRepo "http://download.java.net/maven/2/"
	    //mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://mirrors.ibiblio.org/pub/mirrors/maven2/" // Apache repository
    }
	
	dependencies {
        runtime 'net.sourceforge.jtds:jtds:1.3.0'
        runtime 'postgresql:postgresql:9.1-901.jdbc4'
        runtime([group:'org.hibernate', name:'hibernate-c3p0', version:'3.6.8.Final'])
        
        // for customized builds:
        runtime 'org.apache.httpcomponents:httpcore:4.2.1'
        runtime 'org.apache.httpcomponents:httpclient:4.2.1'
        runtime 'org.apache.httpcomponents:httpmime:4.2.1'
        runtime 'org.apache.httpcomponents:httpclient-cache:4.2.1'
        runtime 'org.apache.httpcomponents:fluent-hc:4.2.1'
	}

    plugins{
        runtime ":hibernate:$grailsVersion"
        build "org.grails.plugins:tomcat:$grailsVersion"
        compile ":remote-pagination:0.3"
        compile(':webxml:1.4.1')
        compile(':jquery:1.8.0')
        compile(':resources:1.1.6')
        compile(':spring-security-core:1.2.7.3')
        compile('cinnamon2:humulus:0.7.0')
        
    }
}
