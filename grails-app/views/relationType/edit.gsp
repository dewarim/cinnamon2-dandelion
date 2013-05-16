<%@ page import="server.RelationResolver" %>
<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/header"/>
    <title><g:message code="relationType.edit.title"/></title>
</head>

<body>
<g:render template="/shared/logo"/>  <div class="nav">
    <g:homeButton><g:message code="home"/></g:homeButton>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="relationType.list"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message
            code="relationType.create"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="relationType.edit.title"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${relationType}">
        <div class="errors">
            <g:renderErrors bean="${relationType}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <input type="hidden" name="id" value="${relationType?.id}"/>

        <div class="dialog">
             <g:render template="form" model="[relationType:relationType]"/>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" value="${message(code: 'update')}"/></span>
        </div>
    </g:form>
</div>

<g:render template="/shared/footer"/>
</body></html>
        