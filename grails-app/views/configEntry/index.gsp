<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/loadCodeMirror"/>
    <g:render template="/shared/header" model="[controllerName:'configEntry']"/>

    <title><g:message code="configEntry.list.title"/></title>
</head>

<body>
<g:render template="/shared/logo"/>  <div class="nav">
    <g:homeButton><g:message code="home"/></g:homeButton>
    <span class="menuButton">
        <g:remoteLink class="create" action="create"
                      update="[success:'createConfigEntry', failure:'message']">
            <g:message code="configEntry.create"/></g:remoteLink>
    </span>
</div>

<div class="body">
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div id="ajaxMessage" style="margin-top:1ex;"></div>

    <div class="create_form" id="createConfigEntry"></div>

    <h1><g:message code="configEntry.list.title"/></h1>

<div class="list" id="configEntryList">
    <g:if test="${configEntryList?.isEmpty()}">
    <g:message code="configEntry.none.defined"/>
    </g:if>
    <g:else>

        <g:render template="list_table" model="[configEntryList:configEntryList]"/>

        </div>

    </g:else>
</div>

<g:render template="/shared/footer"/>
</body>
</html>