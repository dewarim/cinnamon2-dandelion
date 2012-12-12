<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/header" model="[controllerName: 'lifeCycle']"/>
    <title><g:message code="lifeCycle.list.title"/></title>
</head>

<body>
<g:render template="/shared/logo"/>
<div class="nav">
    <g:homeButton><g:message code="home"/></g:homeButton>
    <span class="menuButton">
        <g:remoteLink class="create" action="create"
                      update="[success: 'createLifeCycle', failure: 'message']">
            <g:message code="lifeCycle.create"/></g:remoteLink>
    </span>
    <span class="menuButton">
        <g:link class="list" action="index" controller="lifeCycleState">
            <g:message code="lcs.list"/></g:link>
    </span>
</div>

<div class="body">

    <g:render template="/shared/infoMessage" model="[infoMessage: infoMessage]"/>

    <div class="create_form" id="createLifeCycle"></div>

    <h1><g:message code="lifeCycle.list.title"/></h1>


    <div class="list" id="lifeCycleTable">
        <g:if test="${lifeCycleList?.isEmpty()}">
            <g:message code="lifeCycle.none.defined"/>
        </g:if>
        <g:else>
            <g:render template="list_table" model="[lifeCycleList: lifeCycleList]"/>
        </g:else>
    </div>
</div>

<g:render template="/shared/footer"/>
</body></html>
