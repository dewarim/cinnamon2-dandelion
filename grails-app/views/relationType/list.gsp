<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>
	<title><g:message code="relationType.list.title"/></title>
</head>
<body>
<g:render template="/shared/logo"/>  <div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton"><g:link class="create" action="create"><g:message code="relationType.create"/></g:link></span>
</div>
<div class="body">
	<h1><g:message code="relationType.list.title"/></h1>
	<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:if test="${relationTypeList?.isEmpty()}">
		<g:message code="relationType.none.defined"/>
	</g:if>
	<g:else>
		<div id="relationTypeList" class="list">
            <g:render template="relationTypeList" model="[relationTypeList:relationTypeList]"/>
		</div>

	</g:else>
</div>

<g:render template="/shared/footer"/>
</body></html>
