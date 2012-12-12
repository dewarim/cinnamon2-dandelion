<!DOCTYPE HTML>
<html>
<head>
	<g:render template="/shared/header"/>

        <title><g:message code="health.title"/></title>
    </head>
    <body>
        <g:render template="/shared/logo"/>
	<div class="nav">
		<g:homeButton><g:message code="home"/></g:homeButton>
        </div>
        <div class="body">
            <h1><g:message code="health.title"/></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>

			<div class="dialog">
				<table>
					<thead>
					</thead>
					<tbody>
					<tr>
						<td><g:message code="health.total.memory"/> </td>
						<td>${Runtime.getRuntime().totalMemory()}</td>
					</tr>
					<tr>
						<td><g:message code="health.free.memory"/> </td>
						<td>${Runtime.getRuntime().freeMemory()}</td>
					</tr>
					</tbody>
				</table>


			</div>

            <div class="dialog">
				<ul>
					<li>
						<g:link action="checkUsers" controller="health">
							<g:message code="health.to.checkUsers"/>
						</g:link>
					</li>
					<li>
						<g:link action="checkTypes" controller="health">
							<g:message code="health.to.checkTypes"/>
						</g:link>
					</li>
					<li>
						<g:link action="checkGroups" controller="health">
							<g:message code="health.to.checkGroups"/>
						</g:link>
					</li>
				</ul>
            </div>
        </div>

<g:render template="/shared/footer"/>
</body></html>
