<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>

	<title><g:message code="group.edit.title"/></title>
</head>
<body>
<g:render template="/shared/logo"/>  <div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton"><g:link class="list" action="list"><g:message code="group.list"/></g:link></span>
	<span class="menuButton"><g:link class="create" action="create"><g:message code="group.create"/></g:link></span>
</div>
<div class="body">
	<h1><g:message code="group.edit.title"/></h1>
	<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>
	<g:hasErrors bean="${group}">
		<div class="errors">
			<g:renderErrors bean="${group}" as="list"/>
		</div>
	</g:hasErrors>
	<g:form method="post">
		<input type="hidden" name="id" value="${group?.id}"/>
		<div class="dialog">
			<table>
				<tbody>

				<tr class="prop">
					<td valign="top" class="name">
						<label for="description"><g:message code="group.id"/></label>
					</td>
					<td valign="top" class="value ${hasErrors(bean: group, field: 'id', 'errors')}">
						<input type="text" disabled="disabled" name="id" id="id" value="${fieldValue(bean: group, field: 'id')}"/>
					</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name">
						<label for="description"><g:message code="group.description"/></label>
					</td>
					<td valign="top" class="value ${hasErrors(bean: group, field: 'description', 'errors')}">
						<!-- <input type="text" name="description" id="description" value="${fieldValue(bean: group, field: 'description')}" /> -->
						<g:descriptionTextArea name="description" value="${fieldValue(bean:group,field:'description')}"/>
					</td>
				</tr>

				<!--
				<tr class="prop">
					<td valign="top" class="name">
						<label for="is_user"><g:message code="group.is_user"/></label>
					</td>
					<td valign="top" class="value ${hasErrors(bean: group, field: 'is_user', 'errors')}">
						<g:checkBox name="is_user" disabled="true" value="${group?.is_user}"></g:checkBox>
					</td>
				</tr>
				-->

				<tr class="prop">
					<td valign="top" class="name">
						<label for="name"><g:message code="group.name"/></label>
					</td>
					<td valign="top" class="value ${hasErrors(bean: group, field: 'name', 'errors')}">
						<input type="text" name="name" id="name" value="${fieldValue(bean: group, field: 'name')}"/>
					</td>
				</tr>

				<tr class="prop">
					<td valign="top" class="name">
						<label for="parent"><g:message code="group.parent"/></label>
					</td>
					<td valign="top" class="value ${hasErrors(bean: group, field: 'parent', 'errors')}">
						<g:select id="parent" optionKey="id"
								from="${parentList}"
								name="parent.id"
								optionValue="name"
								noSelection="${['null':'No parent']}"
								value="${group?.parent?.id}"/>
					</td>
				</tr>

				<g:if test="${!group.is_user}">
					<!-- display a link to the list of acls -->
					<tr class="prop">
						<td colspan="2" align="left" class="name">
							<g:link controller='acl' action='showAclsByGroup'
									id='${group.id}'><g:message code="group.show_acls"/></g:link>
						</td>

					</tr>
				</g:if>

				<!-- display a link to the list of users -->
				<tr class="prop">
					<td colspan="2" align="left" class="name">
						<g:link controller='user' action='showUsersByGroup'
								id='${group.id}'><g:message code="group.show_users"/></g:link>
					</td>

				</tr>

				</tbody>
			</table>
		</div>
		<div class="buttons">
			<span class="button"><g:actionSubmit name="save" action="update" class="save" value="${message(code:'update')}"/></span>
		</div>
	</g:form>
</div>

<g:render template="/shared/footer"/>
</body></html>
