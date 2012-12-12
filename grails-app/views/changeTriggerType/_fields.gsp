<%@ page import="server.trigger.ChangeTriggerType" %>
<g:set var="cid" value="${changeTriggerType?.id}"/>
<td valign="top" class="value ${hasErrors(bean: changeTriggerType, field: 'name', 'errors')}">
	<label for="name_${cid}"><g:message code="changeTriggerType.name"/></label> <br>
	<input type="text" name="name" id="name_${cid}" value="${changeTriggerType?.name}"/>
</td>
<td colspan="2" valign="top" class="value ${hasErrors(bean: changeTriggerType, field: 'description', 'errors')}  ${hasErrors(bean: changeTriggerType, field: 'triggerClass', 'errors')} ">
	<label for="description_${cid}"><g:message code="changeTriggerType.description"/></label> <br>
	<input type="text" name="description" size="64" id="description_${cid}" value="${changeTriggerType?.description?.encodeAsHTML()}">
	<br>
	<label for="triggerClass_${cid}"><g:message code="changeTriggerType.triggerClass"/></label> <br>
    <g:select id="triggerClass_${cid}" name="triggerClass" from="${triggers}" value="${changeTriggerType?.triggerClass?.name}" />
</td>
