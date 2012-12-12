<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>

	<title><g:message code="aclEntry.show.title"/></title>
</head>
<body>
<g:render template="/shared/logo"/>
<div class="nav">
	<g:homeButton><g:message code="home"/></g:homeButton>
	<span class="menuButton"><g:link class="list" action="list"><g:message code="aclEntry.list"/></g:link></span>
</div>

<div class="body">
	<h1><g:message code="aclEntry.show.title"/></h1>
	<g:if test="${flash.message}">
		<div class="message">${flash.message}</div>
	</g:if>

	<g:if test="${flash.error}">
		<div class="errors">
			<ul class="errors">
				<li class="errors">${flash.error}</li>
			</ul>
		</div>
	</g:if>
	<div class="dialog">

		<table>
			<tbody>
			<tr class="prop">
				<td valign="top" class="name"><g:message code="acl.id"/></td>
				<td valign="top" class="value">${fieldValue(bean: aclEntry, field: 'id')}</td>
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="aclEntry.acl"/></td>
				<td valign="top" class="value">
					<g:link action="show" controller="acl" params="[id:aclEntry.acl.id]">
						${fieldValue(bean: aclEntry, field: 'acl.name')}
					</g:link>
				</td>
			</tr>

			<tr class="prop">
				<td valign="top" class="name"><g:message code="aclEntry.group"/></td>
				<td valign="top" class="value">
					<g:link action="show" controller="group" params="[id:aclEntry.group.id]">
						${fieldValue(bean: aclEntry, field: 'group.name')}
					</g:link>
				</td>

			</tr>
			<tr>
				<td><g:link controller="aclEntry" action="toggleAllPermissions" params="[id:aclEntry.id, toggle:'allow']">
					<g:message code="aclEntry.toggleAll.allow"/>
				</g:link></td>
				<td><g:link controller="aclEntry" action="toggleAllPermissions" params="[id:aclEntry.id, toggle:'deny']">
					<g:message code="aclEntry.toggleAll.deny"/>
				</g:link></td>
			</tr>
			</tbody>
		</table>

		<table>
			<thead>
			<tr>
				<th><g:message code="permission.activated"/></th>
				<th><g:message code="permission.name"/></th>
				<th><g:message code="permission.description"/></th>
			</tr>
			</thead>
			<tbody>
			<g:each in="${permissionList}" status="i" var="perm">
				<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
					<td>
						<g:link action="togglePermission" params="[permissionId:perm.id, id:aclEntry.id]">
							<g:if test="${aclEntry.findPermission(perm)}">
								<img src="${resource(dir: 'images', file: 'ok.png')}" alt="active" class="noborder">
							</g:if>
							<g:else>
								<img src="${resource(dir: 'images', file: 'no.png')}" alt="inactive" class="noborder">
							</g:else>
						</g:link></td>
					<td><g:link action="togglePermission" params="[permissionId:perm.id, id:aclEntry.id]">${fieldValue(bean: perm, field: 'name')}</g:link></td>
					<td><g:link action="togglePermission" params="[permissionId:perm.id, id:aclEntry.id]">${fieldValue(bean: perm, field: 'description')}</g:link></td>
				</tr>
			</g:each>

			</tbody>

		</table>

	</div>
	<div class="buttons">
		<g:form>
			<input type="hidden" name="id" value="${aclEntry?.id}"/>
		</g:form>
	</div>
</div>

<g:render template="/shared/footer"/>
</body></html>
