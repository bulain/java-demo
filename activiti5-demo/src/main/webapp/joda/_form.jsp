<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="joda.model"/><s:text name="title.info"/></td>
        <s:hidden key="id"/>
        <s:hidden key="joda.version"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="joda.xdate"/></td>
        <td class="page-form-value"><s:date id="xdate" name="joda.xdate" format="%{getText('fmt.date')}" /><s:textfield key="joda.xdate" value="%{xdate}" cssClass="required date" maxlength="10"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="joda.xtime"/></td>
        <td class="page-form-value"><s:date id="xtime" name="joda.xtime" format="%{getText('fmt.time')}" /><s:textfield key="joda.xtime" value="%{xtime}" cssClass="time" maxlength="5"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="joda.xdatetime"/></td>
        <td class="page-form-value"><s:date id="xdatetime" name="joda.xdatetime" format="%{getText('fmt.datetime')}" /><s:textfield key="joda.xdatetime" value="%{xdatetime}" cssClass="datetime" maxlength="16"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="joda.xtimestamp"/></td>
        <td class="page-form-value"><s:date id="xtimestamp" name="joda.xtimestamp" format="%{getText('fmt.datetime')}" /><s:textfield key="joda.xtimestamp" value="%{xtimestamp}" cssClass="datetime" maxlength="16"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table>