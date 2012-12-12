<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header" model="[controllerName:'changeTriggerType']"/>
	<title><g:message code="changeTriggerType.list.title"/></title>
</head>
<body>
<g:render template="/shared/logo"/>  <div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton">
		<g:remoteLink class="create" action="create"
				update="[success:'createChangeTriggerType', failure:'message']">
			<g:message code="changeTriggerType.create"/></g:remoteLink>
	</span>
</div>
<div class="body">
	<g:render template="/shared/infoMessage" model="[infoMessage:infoMessage]"/>

	<div class="create_form" id="createChangeTriggerType"></div>

	<h1><g:message code="changeTriggerType.list.title"/></h1>


	<g:if test="${changeTriggerTypeList?.isEmpty()}">
		<g:message code="changeTriggerType.none.defined"/>
	</g:if>
	<g:else>

		<div class="list" id="changeTriggerTypeList">
			<g:render template="list_table" model="[changeTriggerTypeList:changeTriggerTypeList]"/>
		</div>

	</g:else>
</div>

<g:render template="/shared/footer"/>
</body></html>
