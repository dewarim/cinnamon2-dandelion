<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>
	<title><g:message code="group.show.title"/></title>
    </head>

    <body>
        <g:render template="/shared/logo"/>  <div class="nav">
			<g:homeButton><g:message code="home"/></g:homeButton>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="group.list"/></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="group.create"/></g:link></span>
            <span class="menuButton"><g:link action="list" controller="aclEntry" params="[groupId:group?.id]">
                		<g:message code="aclEntry.link.show"/></g:link></span>     
        </div>
        <div class="body">
            <h1><g:message code="group.show.title"/></h1>
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
                            <td valign="top" class="name"><g:message code="group.id"/></td>
                            
                            <td valign="top" class="value">${fieldValue(bean:group, field:'id')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="group.description"/></td>
                            
                            <td valign="top" class="value">${fieldValue(bean:group, field:'description')}</td>
                            
                        </tr>

                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="group.name"/></td>
                            
                            <td valign="top" class="value">${fieldValue(bean:group, field:'name')}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="group.parent"/></td>
                            
                            <td valign="top" class="value"><g:link controller="group" action="show" id="${group?.parent?.id}">${group?.parent?.name}</g:link></td>
                            
                        </tr>

	                    <g:if test="${!group.is_user}">
	                     	<!-- display a link to the list of acls -->
	                        <tr class="prop">
							     <td colspan="2" align="left" class="name">
								   <g:link controller='acl' action='showAclsByGroup'
								           id='${group.id}' ><g:message code="group.show_acls"/></g:link>
							    </td>
	                            
	                        </tr>
	                    </g:if>

                     	<!-- display a link to the list of users -->
                        <tr class="prop">
						     <td colspan="2" align="left" class="name">
							   <g:link controller='user' action='showUsersByGroup'
							           id='${group.id}' ><g:message code="group.show_users"/></g:link>
						    </td>
                            
                        </tr>

					<g:if test="${hasChildren}">

						<!-- display a link to the list of sub-groups-->
						<tr class="prop">
							<td colspan="2" align="left" class="name">
								<g:link controller='group' action='showSubGroups'
										id='${group.id}'><g:message code="link.to.showSubGroups"/></g:link>
							</td>

						</tr>
					</g:if>

                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <input type="hidden" name="id" value="${group?.id}" />
                    <span class="button"><g:actionSubmit name="edit" action="edit" class="edit" value="${message(code:'edit')}" /></span>
                    <g:if test="${!group.is_user}">
                    	<span class="button"><g:actionSubmit class="delete" onclick="return confirm('Are you sure?');" value="${message(code:'delete')}" /></span>
                    </g:if>
                </g:form>
            </div>
        </div>
    
<g:render template="/shared/footer"/>
</body></html>
    