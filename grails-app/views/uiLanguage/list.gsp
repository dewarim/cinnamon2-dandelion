<!DOCTYPE HTML>
<html>
<head>
<g:render template="/shared/header"/>

        <title><g:message code="uiLanguage.list.title"/></title>
    </head>
    <body>
    <g:render template="/shared/logo"/>	
 
        <div class="nav">
            <g:homeButton><g:message code="home"/></g:homeButton>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="uiLanguage.create"/></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="uiLanguage.list.title"/></h1>
            
            <g:render template="/shared/message"/>

            <div class="list" id="uiLanguageList">
                <g:render template="uiLanguageList" model="[uiLanguageList:uiLanguageList]"/>
            </div>

        </div>

<g:render template="/shared/footer"/>
</body></html>