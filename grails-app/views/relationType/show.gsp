<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>

        <title><g:message code="relationType.show.title"/></title>
    </head>
    <body>
        <g:render template="/shared/logo"/>
	<div class="nav">
			<g:homeButton><g:message code="home"/></g:homeButton>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="relationType.list"/></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="relationType.create"/></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="relationType.show.title"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
				
				<g:render template="/relationType/relationTypeTable" model="[relationType:relationType]"/>
 
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${relationType?.id}" />
                    <span class="button"><g:actionSubmit class="edit" value="${message(code:'edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" onclick="return confirm('${message(code:'relationType.confirm.delete')?.encodeAsHTML()}');" value="${message(code:'delete')}" /></span>
                </g:form>
            </div>
        </div>

<g:render template="/shared/footer"/>
</body></html>
