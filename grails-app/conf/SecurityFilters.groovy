import javax.persistence.*
import org.codehaus.groovy.grails.commons.ApplicationAttributes
import org.codehaus.groovy.grails.web.context.ServletContextHolder

import server.User

/**
 *
 */

public class SecurityFilters {
    def filters = {
        all(uri: '/**') {
            before = {
            }
        }
    }

}