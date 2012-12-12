<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>

	<title><g:message code="message.list.title"/></title>
</head>

<body>
<g:render template="/shared/logo"/>

<div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton"><g:link class="create" action="create"><g:message code="message.create"/></g:link></span>
	<span class="menuButton"><g:remoteLink controller="message" action="selectExportLanguage"
										   update="[success:'import_export', failure:'import_export']">
		<g:message
				code="message.export.messages"/></g:remoteLink></span>
	<span class="menuButton">
		<g:remoteLink controller="message" action="showUploadForm"
					  update="[success:'import_export', failure:'import_export']">
			<g:message
					code="message.import.messages"/></g:remoteLink>
	</span>
</div>

<div class="body">

	<div id="import_export">${importResults}</div>

	<h1><g:message code="message.list.title"/></h1>

	<g:render template="/shared/message"/>

	<div class="list" id="messageList">
        <g:render template="list_table" model="[distinctMessages:distinctMessages, messageCount:messageCount]"/>
	</div>


</div>

<g:render template="/shared/footer"/>
</body></html>
