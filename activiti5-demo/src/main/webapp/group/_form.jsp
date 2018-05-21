<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="group.model"/><s:text name="title.info"/></td>
        <s:hidden name="id"></s:hidden>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="group.name"/></td>
        <td class="page-form-value"><s:textfield key="group.name" cssClass="required" maxlength="50"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="group.type"/></td>
        <td class="page-form-value"><s:select name="group.type" list="listReferanceGroupType" listKey="key" listValue="value" value="group.type" cssClass="required"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="group.description"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="group.description"  rows="4" cols="50"></s:textarea></td>
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 
