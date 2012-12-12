<!DOCTYPE HTML>
<html>
<head>
<g:render template="/shared/header"/>

 

        <title><g:message code="acl.showAclsByGroup.title"/></title>
    </head>
    <body>
	 <g:render template="/shared/logo"/>
        <div class="nav">
			<g:homeButton><g:message code="home"/></g:homeButton>
            <!--<span class="menuButton"><g:link class="create" action="create">New Acl</g:link></span>-->
            <span class="menuButton"><g:link class="list" controller="group" action="list">Group List</g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="acl.usersByGroup.list.label" args="${[group.name]}" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="${message(code:'acl.id')}" />
                        
                   	        <g:sortableColumn property="description" title="${message(code:'acl.description')}" />
                        
                   	        <g:sortableColumn property="name" title="${message(code:'acl.name')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${aclList}" status="i" var="acl">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${acl.id}">${fieldValue(bean:acl, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:acl, field:'description')}</td>
                        
                            <td>${fieldValue(bean:acl, field:'name')}</td>

                            <td>
                            <g:link controller="group" action="removeAcl" id="${acl.id}" params="[groupId:group.id]"><g:message code="acl.remove_from_group"/></g:link>
                            </td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${aclList.size()}" />
            </div>

            <!-- Combobox with all ACLs and an add button -->
            <g:if test="${addList?.size() > 0}">
			<div class="buttons">
				<g:form name="add.acl.form" action="addAcl" controller="group">
					<input type="hidden" name="id" value="${group.id}"/>
					<g:select from="${addList}" name="acl_list" optionValue="name" optionKey="id"></g:select>
					
					<span class="button"><g:actionSubmit value="${message(code:'acl.add_to_group')}" action="addAcl"/></span>
				</g:form>
			</div>
			</g:if>
        </div>

<g:render template="/shared/footer"/>
</body></html>