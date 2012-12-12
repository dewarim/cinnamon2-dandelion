<!DOCTYPE HTML>
<html>
<head>
    <g:render template="/shared/loadCodeMirror"/>
    <g:render template="/shared/header"/>

    <title><g:message code="language.create.title"/></title>
</head>

<body>
<g:render template="/shared/logo"/>
<div class="nav">
    <g:homeButton><g:message code="home"/></g:homeButton>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="language.list"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="language.create.title"/></h1>

    <g:render template="/shared/message"/>
    <g:render template="/shared/errors" bean="${language}"/>

    <g:form action="save" method="post">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="isoCode"><g:message code="language.isoCode"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: language, field: 'isoCode', 'errors')}">
                        <input type="text" name="isoCode" id="isoCode"
                               value="${fieldValue(bean: language, field: 'isoCode')}"/>
                        <script type="text/javascript">
                            $('#isoCode').focus();
                        </script>
                    </td>
                </tr>
                    <g:render template="editMetadata" model="[language:language]"/>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><input class="save" type="submit" value="${message(code: 'create')}"/></span>
        </div>
    </g:form>
</div>

<g:render template="/shared/footer"/>
</body>
</html>

