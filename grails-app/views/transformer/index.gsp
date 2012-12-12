<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header" model="[controllerName:'transformer']"/>
	<title><g:message code="transformer.title"/></title>

</head>
<body>
<g:render template="/shared/logo"/>  <div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton">
		<g:remoteLink class="create" action="create" controller="transformer"
				update="[success:'createTransformer', failure:'message']">
			<g:message code="transformer.create"/></g:remoteLink>
	</span>
</div>
<div class="body">

	<g:render template="/shared/infoMessage" model="[infoMessage:infoMessage]"/>

	<div class="create_form" id="createTransformer"></div>

	<h1><g:message code="transformer.title"/></h1>


	<g:if test="${transformerList?.isEmpty()}">
			<div class="list" id="transformerTable">
		<g:message code="transformer.none.defined"/>
		</div>
	</g:if>
	<g:else>

		<div class="list" id="transformerTable">

			<g:render template="list_table" model="[transformerList:transformerList]"/>

		</div>

	</g:else>
</div>

<g:render template="/shared/footer"/>
</body></html>
