<%@ page import="server.trigger.ChangeTriggerType" %>
<g:set var="cid" value="${changeTrigger?.id}"/>
<tr>
    <td valign="top" class="value ${hasErrors(bean: changeTrigger, field: 'command', 'errors')}">
        <label for="command_${cid}"><g:message code="changeTrigger.command"/></label> <br>
        <input type="text" name="command" id="command_${cid}" value="${changeTrigger?.command}"/>
    </td>
    <td>
        <label for="triggerType_${cid}">
            <g:message code="changeTrigger.triggerType"/>
        </label> <br>

        <g:select from="${ChangeTriggerType.list()}" name="triggerType" id="triggerType_${cid}" optionKey="id"
                  optionValue="name" value="${changeTrigger?.triggerType?.id}"/>
    </td>
    <td class="center value">
        <label for="preTrigger_${cid}"><g:message code="changeTrigger.preTrigger"/></label> <br>
        <input type="checkBox" name="preTrigger" id="preTrigger_${cid}"
               <g:if test="${changeTrigger?.preTrigger}">checked</g:if>>

    </td>
    <td class="center value">
        <label for="postTrigger_${cid}"><g:message code="changeTrigger.postTrigger"/></label> <br>
        <input type="checkBox" name="postTrigger" id="postTrigger_${cid}"
               <g:if test="${changeTrigger?.postTrigger}">checked</g:if>>
    </td>
    <td>
        <label for="ranking_${cid}"><g:message code="changeTrigger.ranking"/></label> <br>
        <input type="text" name="ranking" id="ranking_${cid}" size="6" value="${changeTrigger?.ranking}"/>
    </td>
    <td class="center value">
        <label for="active_${cid}"><g:message code="changeTrigger.active"/></label> <br>
        <input type="checkBox" id="active_${cid}" name="active"
               <g:if test="${changeTrigger?.active}">checked</g:if>>

    </td>
</tr>
<g:render template="editConfig" model="[changeTrigger:changeTrigger]"/>