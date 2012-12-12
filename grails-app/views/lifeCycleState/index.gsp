<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/loadCodeMirror"/>
    <g:render template="/shared/header" model="[controllerName:'lifeCycleState']"/>
	<title><g:message code="lifeCycleState.list.title"/></title>
</head>
<body>
<g:render template="/shared/logo"/>  <div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton">
		<g:remoteLink class="create" action="create"
				update="[success:'createlcs', failure:'message']">
			<g:message code="lifeCycleState.create"/></g:remoteLink>
	</span>
	<span class="menuButton">
		<g:link class="list" action="index" controller="lifeCycle">
			<g:message code="lifeCycle.list"/></g:link>
	</span>
</div>
<div class="body">

	<g:render template="/shared/infoMessage" model="[infoMessage:infoMessage]"/>

	<div class="create_form" id="createlcs"></div>

	<h1><g:message code="lifeCycleState.list.title"/></h1>


	<g:if test="${lcsList?.isEmpty()}">
			<div class="list" id="lcsTable">
		<g:message code="lifeCycleState.none.defined"/>
		</div>
	</g:if>
	<g:else>

		<div class="list" id="lcsTable">
			<g:render template="list_table" model="[lcsList:lcsList]"/>
		</div>

	</g:else>
</div>

<g:render template="/shared/footer"/>
</body></html>
