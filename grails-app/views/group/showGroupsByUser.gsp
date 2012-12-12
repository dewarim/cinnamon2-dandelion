<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>
	<title><g:message code="group.list"/></title>
    </head>
    <body>
        <g:render template="/shared/logo"/>
	<div class="nav">
			<g:homeButton><g:message code="home"/></g:homeButton>
			<span class="menuButton"><g:link class="list" action="list"><g:message code="group.list"/></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="group.create"/></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="group.list.title"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code:'group.list.id')}" />
                        
                   	        <g:sortableColumn property="description" title="${message(code:'group.list.description')}" />
                        
                   	        <g:sortableColumn property="name" title="${message(code:'group.list.name')}" />
                        
                   	        <th>${message(code:'group.list.parent')}</th>
                   	        <th>&nbsp;</th>                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${groupList}" status="i" var="group">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${group.id}">${fieldValue(bean:group, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:group, field:'description')}</td>
                        
                            <td>${fieldValue(bean:group, field:'name')}</td>
                        
                            <td>${group?.parent?.name ?: "-"}</td>
                            
                            <td>
								<g:if test="${group.name.startsWith('_') && group.name != server.global.Constants.GROUP_SUPERUSERS}">
									---
								</g:if>
								<g:else>
									<g:link controller="user" action="removeGroup" id="${group.id}" params="[userId:user.id]"><g:message code="group.remove_from_user"/></g:link>
								</g:else>
                            </td>

						</tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${groupList.size()}" />
            </div>

            <!-- Combobox with all groups and an add button -->
            <g:if test="${addList?.size() > 0}">
			<div class="buttons">
				<g:form name="add.group.form" action="addGroup" controller="user">
				<input type="hidden" name="userId" value="${user.id}"/>
				<g:select from="${addList}" name="group_list" optionValue="name" optionKey="id"></g:select>
				
				<span class="button"><g:actionSubmit value="${message(code:'group.add_to_user')}" action="addGroup"/></span>
			</g:form>
        	</div>
        	</g:if>

<g:render template="/shared/footer"/>
</body></html>

