package eu.hornerproject.dandelion

import grails.plugins.springsecurity.Secured;

class AjaxController {

    def session

	/*
	 * This is an action for a default error which can be displayed even if the
	 * user is not logged in.
	 */
	def defaultError () {
		def msg = message(code:params.msg)
		if(msg.equals(params.msg)){
			/*
			 *  prevent XSS-attacks: if the message is unknown,
			 *  show default error.
			 */
			msg = message(code:'error.request.failed')
		}
    	return render(text:msg,
    			contentType:'text/plain', encoding:'UTF-8')
	}

    @Secured(["hasRole('_superusers')"])
    def ping () {
        session.ping = session.ping ? (++session.ping) : 1
        log.debug("ping: $session.ping")
        return [ping:session.ping]
    }

}
