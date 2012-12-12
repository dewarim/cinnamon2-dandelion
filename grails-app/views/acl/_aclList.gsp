<table>
    <thead>
    <tr>

        <g:sortableColumn property="id" title="${message(code:'acl.id')}"/>

        <g:sortableColumn property="name" title="${message(code:'acl.name')}"/>

        <g:sortableColumn property="description" title="${message(code:'acl.description')}"/>

    </tr>
    </thead>
    <tbody>
    <g:each in="${aclList}" status="i" var="acl">
        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

            <td><g:link action="show" id="${acl.id}">${fieldValue(bean: acl, field: 'id')}</g:link></td>

            <td>${fieldValue(bean: acl, field: 'name')}</td>

            <td>${fieldValue(bean: acl, field: 'description')}</td>

        </tr>
    </g:each>
    </tbody>
</table>

<div class="paginateButtons">
    <%@ page import="server.Acl" %>
    <util:remotePaginate controller="acl" action="updateList" total="${Acl.count()}"
                         update="aclList" max="10" pageSizes="[10, 20, 50, 100, 250, 500, 1000]"/>
</div>