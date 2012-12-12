<%@ page import="groovy.xml.XmlUtil; server.MetasetType" %>
<!doctype html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'metasetType.label', default: 'MetasetType')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
        <g:render template="/shared/loadCodeMirror"/>
	</head>
	<body>
		<a href="#show-metasetType" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-metasetType" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list metasetType">



                <g:if test="${metasetTypeInstance?.name}">
                    <li class="fieldcontain">
                        <span id="name-label" class="property-label"><g:message code="metasetType.name.label" default="Name" /></span>

                        <span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${metasetTypeInstance}" field="name"/></span>

                    </li>
                </g:if>

                <g:if test="${metasetTypeInstance?.description}">
                    <li class="fieldcontain">
                        <span id="description-label" class="property-label"><g:message code="metasetType.description.label" default="Description" /></span>

                        <span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${metasetTypeInstance}" field="description"/></span>

                    </li>
                </g:if>

				<g:if test="${metasetTypeInstance?.config}">
				<li class="fieldcontain">
					<span id="config-label" class="property-label"><g:message code="metasetType.config.label" default="Config" /></span>
					
                        <label for="config_${metasetTypeInstance?.id}">
                            <g:message code="metasetType.config.label" default="Config" />
                        </label>
                        <div class="value xml_editor">
                            <g:form>

                            <textarea id="config_${metasetTypeInstance?.id}" style="width:100ex;border:1px black solid; "
                                      name="config" cols="120" disabled="disabled"
                                      rows="10">${metasetTypeInstance.config ?  XmlUtil.serialize(metasetTypeInstance.config).encodeAsHTML() : '<metaset />'.encodeAsHTML()}</textarea>
                            <script type="text/javascript">
                                var renderMirror = CodeMirror.fromTextArea($('#config_${metasetTypeInstance?.id}').get(0), {
                                    mode:'application/xml',
                                    readOnly:true
                                });
                            </script>
                                </g:form>
                        </div>
				</li>
				</g:if>

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${metasetTypeInstance?.id}" />
					<g:link class="edit" action="edit" id="${metasetTypeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
