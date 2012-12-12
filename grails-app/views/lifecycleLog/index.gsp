<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/header" model="[controllerName: 'lifecycleLog']"/>
    <title><g:message code="lifecycle.log.title"/></title>
</head>

<body>
<g:render template="/shared/logo"/>
<div class="nav">
    <g:homeButton><g:message code="home"/></g:homeButton>

</div>

<div class="body">

    <g:render template="/shared/infoMessage" model="[infoMessage: infoMessage]"/>

    <div class="create_form" id="createLifeCycle"></div>

    <h1><g:message code="lifecycle.log.title"/></h1>


    <div class="list" id="lifeCycleLogTable">
        <g:if test="${logEntries?.isEmpty()}">
            <g:message code="lifecycle.log.none"/>
        </g:if>
        <g:else>
            <div id="logTable">
                <g:render template="logTable" model="[logEntries: logEntries, pagination:pagination]"/>
            </div>
        </g:else>
    </div>
</div>

<g:render template="/shared/footer"/>
</body></html>
