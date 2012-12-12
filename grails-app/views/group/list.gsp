<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/header"/>

    <title><g:message code="group.list"/></title>
</head>

<body>
<g:render template="/shared/logo"/>  <div class="nav">
    <g:homeButton><g:message code="home"/></g:homeButton>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="group.create"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="group.list.title"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list" id="groupList">
        <g:render template="groupList" model="[groupList:groupList]"/>
    </div>
</div>

<g:render template="/shared/footer"/></body></html>
