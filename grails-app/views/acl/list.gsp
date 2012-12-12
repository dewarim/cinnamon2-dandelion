<!DOCTYPE HTML>
<html>
<head>
<g:render template="/shared/header"/>

        <title><g:message code="acl.list.title"/></title>
    </head>
    <body>
		<g:render template="/shared/logo"/>
        <div class="nav">
			<g:homeButton><g:message code="home"/></g:homeButton>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="acl.create"/></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="acl.list.title"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="aclList">
                <g:render template="aclList" model="[aclList:aclList]"/>
            </div>

        </div>

<g:render template="/shared/footer"/>
</body>
</html>