<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>

        <g:set var="entityName" value="${message(code: 'indexGroup.label', default: 'IndexGroup')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:render template="/shared/logo"/>
	<div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="home"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="indexGroup.list.link"/></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="indexGroup.new.label" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${indexGroupInstance}">
            <div class="errors">
                <g:renderErrors bean="${indexGroupInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${indexGroupInstance?.id}" />
                <g:hiddenField name="obj_version" value="${indexGroupInstance?.obj_version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="name"><g:message code="label.name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: indexGroupInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" value="${indexGroupInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="items"><g:message code="indexGroup.items.label" default="Items" /></label>
                                </td>
<!--
                                <td valign="top" class="value ${hasErrors(bean: indexGroupInstance, field: 'items', 'errors')}">
                                    <g:select name="items" from="${server.index.IndexItem.list()}" multiple="yes" optionKey="id" size="5" value="${indexGroupInstance?.items}" optionValue="name" />
                                </td>
-->
	                            <td valign="top" style="text-align: left;" class="value">
	                                <ul>
	                                <g:each in="${indexGroupInstance.items}" var="i">
	                                    <li><g:link controller="indexItem" action="show" id="${i.id}">${i.name}</g:link></li>
	                                </g:each>
	                                </ul>
	                            </td>

                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                </div>
            </g:form>
        </div>
<g:render template="/shared/footer"/>
    </body>
</html>
