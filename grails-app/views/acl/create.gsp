<!DOCTYPE HTML>
<html>
<head>

<g:render template="/shared/header"/>

        <title><g:message code="acl.create.title"/></title>
    </head>
    <body>
	<g:render template="/shared/logo"/>
        <div class="nav">
			<g:homeButton><g:message code="home"/></g:homeButton>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="acl.list"/></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="acl.create.title"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${acl}">
            <div class="errors">
                <g:renderErrors bean="${acl}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="aclName"><g:message code="acl.name"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:acl,field:'name','errors')}">
                                    <input type="text" name="name" id="aclName" value="${fieldValue(bean:acl,field:'name')}" />
                                </td>
                            </tr> 
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="acl.description"/></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean:acl,field:'description','errors')}">
                                    <!-- <input type="text" name="description" id="description" value="${fieldValue(bean:acl,field:'description')}" /> -->
                                    <g:descriptionTextArea name="description" value="${fieldValue(bean:acl,field:'description')}" />
                                </td>
                            </tr> 
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><input class="save" type="submit" value="${message(code:'create')}" /></span>
                </div>
            </g:form>
            <script type="text/javascript">
                $('#aclName').focus()
            </script>
        </div>

<g:render template="/shared/footer"/>
</body></html>