<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>
	<g:set var="entityName" value="${message(code: 'indexGroup.label', default: 'IndexGroup')}" />
        <title><g:message code="indexGroup.list.title" /></title>
    </head>
    <body>
        <g:render template="/shared/logo"/>  <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="home"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="indexGroup.create.link" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="indexGroup.list.h1"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="indexGroupList">
                <g:render template="indexGroupList" model="[indexGroupList:indexGroupList]"/>
            </div>

        </div>
<g:render template="/shared/footer"/>
    </body>
</html>
