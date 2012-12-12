<%@ page import="server.Format" %>
<table>
    <thead>
    <tr>

        <g:sortableColumn property="id" title="${message(code:'format.id')}"/>

        <g:sortableColumn property="name" title="${message(code:'format.name')}"/>

        <g:sortableColumn property="contenttype" title="${message(code:'format.contenttype')}"/>

        <g:sortableColumn property="description" title="${message(code:'format.description')}"/>

        <g:sortableColumn property="extension" title="${message(code:'format.extension')}"/>

    </tr>
    </thead>
    <tbody>
    <g:each in="${formatList}" status="i" var="formatInstance">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td><g:link action="show"
                        id="${formatInstance.id}">${fieldValue(bean: formatInstance, field: 'id')}</g:link></td>

            <td>${fieldValue(bean: formatInstance, field: 'name')}</td>

            <td>${fieldValue(bean: formatInstance, field: 'contenttype')}</td>

            <td>${fieldValue(bean: formatInstance, field: 'description')}</td>

            <td>${fieldValue(bean: formatInstance, field: 'extension')}</td>

        </tr>
    </g:each>
    </tbody>
</table>

<div class="paginateButtons">
    <util:remotePaginate controller="format" action="updateList" total="${Format.count()}"
                         update="formatList" max="10" pageSizes="[10, 20, 50, 100, 250, 500, 1000]"/>
</div>