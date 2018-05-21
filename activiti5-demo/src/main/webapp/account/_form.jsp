<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="account.model"/><s:text name="title.info"/></td>
        <s:hidden name="id"></s:hidden>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.iban"/></td>
        <td class="page-form-value"><s:textfield key="account.iban" maxlength="50"/></td>
        <td class="page-form-label"><s:text name="account.schemeName"/></td>
        <td class="page-form-value"><s:textfield key="account.schemeName" maxlength="50"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.identification"/></td>
        <td class="page-form-value"><s:textfield key="account.identification" maxlength="50"/></td>
        <td class="page-form-label"><s:text name="account.issuer"/></td>
        <td class="page-form-value"><s:textfield key="account.issuer" maxlength="50"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.name"/></td>
        <td class="page-form-value"><s:textfield key="account.name" cssClass="required" maxlength="50"/></td>
        <td class="page-form-label"><s:text name="account.status"/></td>
        <td class="page-form-value"><s:select name="account.status" list="listReferanceAccountStatus" listKey="key" listValue="value" value="account.status" cssClass="required"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.currency"/></td>
        <td class="page-form-value"><s:select name="account.currency" list="listReferanceCurrency" listKey="key" listValue="value" value="account.currency" cssClass="required"/></td>
        <td class="page-form-label"><s:text name="account.type"/></td>
        <td class="page-form-value"><s:select name="account.type" list="listReferanceCashAccountType" listKey="key" listValue="value" value="account.type" cssClass="required"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.monthlyPaymentValue"/></td>
        <td class="page-form-value"><s:textfield key="account.monthlyPaymentValue" maxlength="50"/></td>
        <td class="page-form-label"><s:text name="account.monthlyReceivedValue"/></td>
        <td class="page-form-value"><s:textfield key="account.monthlyReceivedValue" maxlength="50"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.monthlyTransactionNumber"/></td>
        <td class="page-form-value"><s:textfield key="account.monthlyTransactionNumber" maxlength="50"/></td>
        <td class="page-form-label"><s:text name="account.averageBalance"/></td>
        <td class="page-form-value"><s:textfield key="account.averageBalance" maxlength="50"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.floorNotificationAmount"/></td>
        <td class="page-form-value"><s:textfield key="account.floorNotificationAmount" maxlength="50"/></td>
        <td class="page-form-label"><s:text name="account.ceilingNotificationAmount"/></td>
        <td class="page-form-value"><s:textfield key="account.ceilingNotificationAmount" maxlength="50"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.statementCycle"/></td>
        <td class="page-form-value"><s:select name="account.statementCycle" list="listReferanceFrequency" listKey="key" listValue="value" value="account.statementCycle"/></td>
        <td class="page-form-label"><s:text name="account.closingDate"/></td>
        <td class="page-form-value"><s:date id="closingDate" name="account.closingDate" format="%{getText('fmt.date')}" /><s:textfield key="account.closingDate" value="%{closingDate}" cssClass="date" maxlength="10"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.purpose"/></td>
        <td class="page-form-value"><s:textfield key="account.purpose" maxlength="50"/></td>
        <td class="page-form-label"><s:text name="account.restriction"/></td>
        <td class="page-form-value"><s:textfield key="account.restriction" maxlength="50"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.targetGoLiveDate"/></td>
        <td class="page-form-value"><s:date id="targetGoLiveDate" name="account.targetGoLiveDate" format="%{getText('fmt.date')}" /><s:textfield key="account.targetGoLiveDate" value="%{targetGoLiveDate}" cssClass="date" maxlength="10"/></td>
        <td class="page-form-label"><s:text name="account.targetClosingDate"/></td>
        <td class="page-form-value"><s:date id="targetClosingDate" name="account.targetClosingDate" format="%{getText('fmt.date')}" /><s:textfield key="account.targetClosingDate" value="%{targetClosingDate}" cssClass="date" maxlength="10"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.urgencyFlag"/></td>
        <td class="page-form-value"><s:select name="account.urgencyFlag" list="listReferanceBoolean" listKey="key" listValue="value" value="account.urgencyFlag" /></td>
        <td />
        <td />
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 
