<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<html>
<head>
    <title><s:text name="joda.model"/><s:text name="title.list"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="joda.model"/><s:text name="title.list"/></td>
    </tr>
</table>
<br/>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<s:form action="list" method="post" id="search" namespace="/joda" theme="simple">
    <table class="page-form" cellspacing="0" width="100%">
        <tr>
            <td class="page-form-title" colspan="4"><s:text name="title.search"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><s:text name="joda.xdate"/></td>
            <td class="page-form-value"><s:date id="xdate" name="search.xdate" format="%{getText('fmt.date')}" /><s:textfield key="search.xdate" value="%{xdate}" maxlength="20"/></td>
            <td class="page-form-label"><s:text name="joda.xtime"/></td>
            <td class="page-form-value"><s:date id="xtime" name="search.xtime" format="%{getText('fmt.time')}" /><s:textfield key="search.xtime" value="%{xtime}" maxlength="10"/></td>
        </tr>
        <tr>
            <td width="20%"/>
            <td width="30%"/>
            <td width="20%"/>
            <td width="30%"/>
        </tr>
        <tr>
            <td colspan="4">
                <table cellspacing="0" width="100%">
                    <tr>
                        <td width="40%"><s:url id="url" action="new" /><a id="lnk_new" href="<s:property value="#url"/>"><s:text name="action.new"/><s:text name="joda.model"/></a></td>
                        <td class="page-form-centered"><s:submit id="btn_search" value="%{getText('action.search')}" /></td>
                        <td width="40%" />
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</s:form>
<script type="text/javascript">
$(document).ready(function() {
    $("#search").validate();
    $("#search_search_xdate").datepicker({dateFormat:'<s:text name="fmt.jq.date"/>'});
    $('#search_search_xtime').timepicker();
});
</script>
<br/>

<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th><page:order fixOrderBy="xdate"><s:text name="joda.xdate"/></page:order></th>
        <th><page:order fixOrderBy="xtime"><s:text name="joda.xtime"/></page:order></th>
        <th><page:order fixOrderBy="xdatetime"><s:text name="joda.xdatetime"/></page:order></th>
        <th><page:order fixOrderBy="xtimestamp"><s:text name="joda.xtimestamp"/></page:order></th>
        <th><page:order fixOrderBy="updated_by"><s:text name="common.updatedBy"/></page:order></th>
        <th><page:order fixOrderBy="updated_at"><s:text name="common.updatedAt"/></page:order></th>
        <th class="minNoWrap"><s:text name="action.action"/></th>
    </tr>
    <s:iterator value="listJoda" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:date name="xdate" format="%{getText('fmt.date')}" /></td>
            <td class="noWrap"><s:date name="xtime" format="%{getText('fmt.time')}" /></td>
            <td class="noWrap"><s:date name="xdatetime" format="%{getText('fmt.datetime')}" /></td>
            <td class="noWrap"><s:date name="xtimestamp" format="%{getText('fmt.timestamp')}" /></td>
            <td class="noWrap"><s:property value="updatedBy"/></td>
            <td class="noWrap"><s:date name="updatedAt" format="%{getText('fmt.timestamp')}" /></td>
            <td class="minNoWrap">
        	<s:url id="url" action="show"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.show"/></a>
        	|<s:url id="url" action="edit"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.edit"/></a>
        	|<s:url id="url" action="destroy"><s:param name="id" value="id"></s:param></s:url><a onclick="javascript:destroy(this);return false;" href="<s:property value="#url"/>"><s:text name="action.destroy"/></a>
        	</td>
        </tr>
    </s:iterator>
</table>

<p>&nbsp;</p>
<page:link page="page.page" totalPage="page.totalPage"/>

</body>
</html>
