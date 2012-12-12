<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>

        <title><g:message code="user.list.title"/></title>
    </head>
    <body>
        <g:render template="/shared/logo"/>
	<div class="nav">
			<g:homeButton><g:message code="home"/></g:homeButton>
            <span class="menuButton"><g:link class="list" controller="group" action="list"><g:message code="group.list"/></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="usersByGroup.list.label" args="${[group.name]}"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code:'user.id')}" />
                        
                   	        <g:sortableColumn property="description" title="${message(code:'user.description')}" />
                        
                   	        <g:sortableColumn property="fullname" title="${message(code:'user.fullname')}" />
                        
                   	        <g:sortableColumn property="name" title="${message(code:'user.name')}" />
                            
                            <th>&nbsp;</th>
                            
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${userList}" status="i" var="user">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${user.id}">${fieldValue(bean:user, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:user, field:'description')}</td>
                        
                            <td>${fieldValue(bean:user, field:'fullname')}</td>
                        
                            <td>${fieldValue(bean:user, field:'name')}</td>
                        
                            <td>
                                <g:form controller="group" action="removeUser" id="${user.id}">
                                    <input type="hidden" name="groupId" value="${group.id}"/>
                                    <g:submitButton name="removeUser" value="${message(code: 'user.remove_from_group')}"/>
                                </g:form>
                            %{--<g:link controller="group" action="removeUser" id="${user.id}" params="[groupId:group.id]">--}%
                            %{----}%
                            %{--</g:link>--}%
                            </td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            
            <div class="paginateButtons">
                <g:paginate id="${group.id}" total="${groupUserCount}" />
            </div>

			<p>
				<g:message code="users.system_group.warning"/>
			</p>

            <!-- Combobox with all users and an add button -->
            <g:if test="${addList?.size() > 0}">
			<div class="buttons">
				<g:form name="add.user.form" action="addUser" controller="group">
					<input type="hidden" name="id" value="${group.id}"/>
					<g:select from="${addList}" name="user_list" optionValue="name" optionKey="id"/>
					<span class="button"><g:actionSubmit value="${message(code:'user.add_to_group')}" action="addUser"/></span>
				</g:form>
			
			</div>
			</g:if>

			<g:if test="${hasSubGroups}">
				<div class="link">
					<g:link action="showDescendantGroupUsers" controller="group" params="[id:group.id]">
						<g:message code="user.link.to.showDescendantGroupUsers"/>
					</g:link>
				</div>

				<div class="link">
					<g:link action="showSubGroups" controller="group" params="[id:group.id]">
						<g:message code="link.to.showSubGroups"/>
					</g:link>
				</div>
			</g:if>

        </div>

<g:render template="/shared/footer"/>
</body></html>
