<%@ page import="cinnamon.AuditLog" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'auditLog.label', default: 'AuditLog')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<a href="#show-auditLog" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div class="nav">
    <g:homeButton><g:message code="home"/></g:homeButton>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="auditLog.list"/></g:link></span>
</div>

<div id="show-auditLog" class="content scaffold-show" role="main">
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list auditLog">

        <li class="fieldcontain">
            <span id="repository-label" class="property-label"><g:message code="auditLog.repository.label"
                                                                          default="Repository"/></span>

            <span class="property-value" aria-labelledby="repository-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                          field="repository"/></span>

        </li>

        <li class="fieldcontain">
            <span id="hibernateId-label" class="property-label"><g:message code="auditLog.hibernateId.label"
                                                                           default="Hibernate Id"/></span>

            <span class="property-value" aria-labelledby="hibernateId-label"><g:fieldValue
                    bean="${auditLogInstance}" field="hibernateId"/></span>

        </li>

        <li class="fieldcontain">
            <span id="fieldName-label" class="property-label"><g:message code="auditLog.fieldName.label"
                                                                         default="Field Name"/></span>

            <span class="property-value" aria-labelledby="fieldName-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                         field="fieldName"/></span>

        </li>

        <li class="fieldcontain">
            <span id="oldValueName-label" class="property-label"><g:message code="auditLog.oldValueName.label"
                                                                            default="Old Value Name"/></span>

            <span class="property-value" aria-labelledby="oldValueName-label"><g:fieldValue
                    bean="${auditLogInstance}" field="oldValueName"/></span>

        </li>

        <li class="fieldcontain">
            <span id="newValueName-label" class="property-label"><g:message code="auditLog.newValueName.label"
                                                                            default="New Value Name"/></span>

            <span class="property-value" aria-labelledby="newValueName-label"><g:fieldValue
                    bean="${auditLogInstance}" field="newValueName"/></span>

        </li>
        <li class="fieldcontain">
            <span id="username-label" class="property-label"><g:message code="auditLog.username.label"
                                                                        default="Username"/></span>

            <span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                        field="username"/></span>

        </li>
        <li class="fieldcontain">
            <span id="userId-label" class="property-label"><g:message code="auditLog.userId.label"
                                                                      default="User Id"/></span>

            <span class="property-value" aria-labelledby="userId-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                      field="userId"/></span>

        </li>
        <li class="fieldcontain">
            <span id="className-label" class="property-label"><g:message code="auditLog.className.label"
                                                                         default="Class Name"/></span>

            <span class="property-value" aria-labelledby="className-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                         field="className"/></span>

        </li>
        <li class="fieldcontain">
            <span id="objectName-label" class="property-label"><g:message code="auditLog.objectName.label"
                                                                          default="Object Name"/></span>

            <span class="property-value" aria-labelledby="objectName-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                          field="objectName"/></span>

        </li>
        <li class="fieldcontain">
            <span id="oldValue-label" class="property-label"><g:message code="auditLog.oldValue.label"
                                                                        default="Old Value"/></span>

            <span class="property-value" aria-labelledby="oldValue-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                        field="oldValue"/></span>

        </li>
        <li class="fieldcontain">
            <span id="newValue-label" class="property-label"><g:message code="auditLog.newValue.label"
                                                                        default="New Value"/></span>

            <span class="property-value" aria-labelledby="newValue-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                        field="newValue"/></span>

        </li>
        <li class="fieldcontain">
            <span id="dateCreated-label" class="property-label"><g:message code="auditLog.dateCreated.label"
                                                                           default="Date Created"/></span>

            <span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate
                    date="${auditLogInstance?.dateCreated}"/></span>

        </li>
        <li class="fieldcontain">
            <span id="metadata-label" class="property-label"><g:message code="auditLog.metadata.label"
                                                                        default="Metadata"/></span>

            <span class="property-value" aria-labelledby="metadata-label"><g:fieldValue bean="${auditLogInstance}"
                                                                                        field="metadata"/></span>

        </li>
        <li class="fieldcontain">
            <span id="logMessage-label" class="property-label">
                <g:message code="auditLog.logMessage.label" default="LogMessage"/></span>

            <span class="property-value" aria-labelledby="metadata-label">
                <g:fieldValue bean="${auditLogInstance}" field="logMessage"/></span>

        </li>
    </ol>
</div>
</body>
</html>
