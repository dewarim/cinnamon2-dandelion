<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>

        <g:set var="entityName" value="${message(code: 'indexItem.label', default: 'IndexItem')}" />
        <title><g:message code="indexItem.list.title" /></title>
    </head>
    <body>
        <g:render template="/shared/logo"/>  <div class="nav">
            <g:homeButton><g:message code="home"/></g:homeButton>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="indexItem.create.link" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="indexItem.list.h1" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list" id="indexItemList">
                <g:render template="indexItemList" model="[indexItemList:indexItemList]"/>
            </div>
        </div>
    
<g:render template="/shared/footer"/></body></html>
