<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/loadCodeMirror"/>
    <g:render template="/shared/header"/>

	<title><g:message code="language.show.title"/></title>
</head>

<body>
<g:render template="/shared/logo"/>

<div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton"><g:link class="list" action="list"><g:message code="language.list"/></g:link></span>
	<span class="menuButton"><g:link class="create" action="create"><g:message code="language.create"/></g:link></span>
</div>

<div class="body">
	<h1><g:message code="language.show.title"/></h1>
	<g:render template="/shared/message"/>

	<div class="dialog">
		<table>
			<tbody>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="language.id"/></td>

				<td valign="top" class="value">${fieldValue(bean: language, field: 'id')}</td>

			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="language.isoCode"/></td>

				<td valign="top" class="value">${fieldValue(bean: language, field: 'isoCode')}</td>

			</tr>

			<tr class="prop">
				<td class="name"><g:message code="language.metadata"/></td>
				<td>
					<g:render template="/shared/renderXML"
							  model="[renderId:language.id, xml:language.metadata]"/>
				</td>
			</tr>
			</tbody>
		</table>
	</div>

	<div class="buttons">
		<g:form>
			<input type="hidden" name="id" value="${language?.id}"/>
			<span class="button"><g:actionSubmit name="editSubmit" action="edit" class="edit" value="${message(code:'edit')}"/></span>
			<span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');"
												 value="${message(code:'delete')}"/></span>
		</g:form>
	</div>

</div>

<g:render template="/shared/footer"/>
</body></html>
