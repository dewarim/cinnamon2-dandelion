<!DOCTYPE HTML>
<html>
<head>
<g:render template="/shared/header"/>

	  
        <g:set var="entityName" value="${message(code: 'indexType.label', default: 'IndexType')}" />
        <title><g:message code="indexType.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/shared/logo"/>
	<div class="nav">
            <g:homeButton><g:message code="home"/></g:homeButton>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="indexType.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="indexType.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="indexType.show.label" args="[entityName]" /></h1>

			<g:render template="/shared/message"/>

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
                            <td valign="top" class="name"><g:message code="indexType.id.label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: indexTypeInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="indexType.name.label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: indexTypeInstance, field: "name")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="indexType.dataType.label" /></td>
                            
                            <td valign="top" class="value">${indexTypeInstance?.dataType?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="indexType.indexerClass.label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: indexTypeInstance, field: "indexerClass")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="indexType.vaProviderClass.label" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: indexTypeInstance, field: "vaProviderClass")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${indexTypeInstance?.id}" />
                    <span class="button">
						<g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label')}" />
					</span>
                    <g:if test="${deleteAllowed}">
	                    <span class="button">
							<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label')}"
									onclick="return confirm('${message(code: 'indexType.delete.confirm')}');" />
						</span>
                    </g:if>
                </g:form>
            </div>
        </div>
    
<g:render template="/shared/footer"/>
</body></html>
