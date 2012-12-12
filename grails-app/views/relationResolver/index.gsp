<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/loadCodeMirror"/>
    <g:render template="/shared/header" model="[controllerName:'relationResolver']"/>
	<title><g:message code="relationResolver.list.title"/></title>

</head>

<body>
<g:render template="/shared/logo"/>  <div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton">
		<g:remoteLink class="create" action="create"
					  update="[success:'createRelationResolver', failure:'message']">
			<g:message code="relationResolver.create"/></g:remoteLink>
	</span>
</div>

<div class="body">

	<g:render template="/shared/infoMessage" model="[infoMessage:infoMessage]"/>

	<div class="create_form" id="createRelationResolver"></div>

	<h1><g:message code="relationResolver.list.title"/></h1>

	<g:if test="${relationResolverList?.isEmpty()}">
		<g:message code="relationResolver.none.defined"/>
	</g:if>
	<g:else>

		<div class="list" id="relationResolverTable">
			<g:render template="list_table" model="[relationResolverList:relationResolverList]"/>
		</div>

	</g:else>
</div>

<g:render template="/shared/footer"/>
</body></html>
