<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<html>
<head>
	<link href="<s:url value='/public/stylesheets/globel.css'/>" rel="stylesheet" type="text/css"/>
    <title>Workflow</title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">History Task</td>
    </tr>
</table>
<br/>
<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th>ID</th>
    	<th>ProcessDefinitionId</th>
        <th>ProcessInstanceId</th>
        <th>ExecutionId</th>
        <th>Name</th>
        <th>Description</th>
        <th>DeleteReason</th>
        <th>Assignee</th>
        <th>StartTime</th>
        <th>EndTime</th>
        <th>DurationInMillis</th>
    </tr>
    <s:iterator value="listHistoryTask" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="id"/></td>
            <td class="noWrap"><s:property value="processDefinitionId"/></td>
            <td class="noWrap"><s:property value="processInstanceId"/></td>
            <td class="noWrap"><s:property value="executionId"/></td>
            <td class="noWrap"><s:property value="name"/></td>
            <td class="noWrap"><s:property value="description"/></td>
            <td class="noWrap"><s:property value="deleteReason"/></td>
            <td class="noWrap"><s:property value="assignee"/></td>
            <td class="noWrap"><s:property value="startTime"/></td>
            <td class="noWrap"><s:property value="endTime"/></td>
            <td class="noWrap"><s:property value="durationInMillis"/></td>
        </tr>
    </s:iterator>
</table>
<br/>
<br/>


<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">History Activity Instance</td>
    </tr>
</table>
<br/>
<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th>ID</th>
    	<th>ActivityId</th>
        <th>ActivityName</th>
        <th>ActivityType</th>
        <th>ProcessDefinitionId</th>
        <th>ProcessInstanceId</th>
        <th>ExecutionId</th>
        <th>Assignee</th>
        <th>StartTime</th>
        <th>EndTime</th>
        <th>DurationInMillis</th>
    </tr>
    <s:iterator value="listHistoryActivityInstance" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="id"/></td>
            <td class="noWrap"><s:property value="activityId"/></td>
            <td class="noWrap"><s:property value="activityName"/></td>
            <td class="noWrap"><s:property value="activityType"/></td>
            <td class="noWrap"><s:property value="processDefinitionId"/></td>
            <td class="noWrap"><s:property value="processInstanceId"/></td>
            <td class="noWrap"><s:property value="executionId"/></td>
            <td class="noWrap"><s:property value="assignee"/></td>
            <td class="noWrap"><s:property value="startTime"/></td>
            <td class="noWrap"><s:property value="endTime"/></td>
            <td class="noWrap"><s:property value="durationInMillis"/></td>
        </tr>
    </s:iterator>
</table>
<br/>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><s:url id="url" action="list" /><a href="<s:property value="#url"/>">Back</a></td>
    </tr>
</table>

</body>
</html>
