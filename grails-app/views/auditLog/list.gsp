
<%@ page import="cinnamon.AuditLog" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'auditLog.label', default: 'AuditLog')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-auditLog" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
    <div class="nav">
        <g:homeButton><g:message code="home"/></g:homeButton>
    </div>
		<div id="list-auditLog" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					    <g:sortableColumn property="id" title="${message(code: 'id')}"/>                    
						<g:sortableColumn property="repository" title="${message(code: 'auditLog.repository.label', default: 'Repository')}" />
						<g:sortableColumn property="hibernateId" title="${message(code: 'auditLog.hibernateId.label', default: 'Hibernate Id')}" />
						<g:sortableColumn property="fieldName" title="${message(code: 'auditLog.fieldName.label', default: 'Field Name')}" />
						<g:sortableColumn property="oldValueName" title="${message(code: 'auditLog.oldValueName.label', default: 'Old Value Name')}" />
						<g:sortableColumn property="oldValue" title="${message(code: 'auditLog.oldValue.label', default: 'Old Value')}" />
						<g:sortableColumn property="newValueName" title="${message(code: 'auditLog.newValueName.label', default: 'New Value Name')}" />
						<g:sortableColumn property="newValue" title="${message(code: 'auditLog.newValue.label', default: 'New Value')}" />
						<g:sortableColumn property="username" title="${message(code: 'auditLog.username.label', default: 'Username')}" />
					    <th><g:message code="auditLog.logMessage.label"/></th>                    
					</tr>
				</thead>
				<tbody>
				<g:each in="${auditLogInstanceList}" status="i" var="auditLogInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${auditLogInstance.id}">${auditLogInstance.id}</g:link></td>
						<td>${fieldValue(bean: auditLogInstance, field: "repository")}</td>
						<td>${fieldValue(bean: auditLogInstance, field: "hibernateId")}</td>
						<td>${fieldValue(bean: auditLogInstance, field: "fieldName")}</td>
						<td>${fieldValue(bean: auditLogInstance, field: "oldValueName")}</td>
						<td>${fieldValue(bean: auditLogInstance, field: "oldValue")}</td>
						<td>${fieldValue(bean: auditLogInstance, field: "newValueName")}</td>
						<td>${fieldValue(bean: auditLogInstance, field: "newValue")}</td>
						<td>${fieldValue(bean: auditLogInstance, field: "username")}</td>
						<td>${fieldValue(bean: auditLogInstance, field: "logMessage")}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${auditLogInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
