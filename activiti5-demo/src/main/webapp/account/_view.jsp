<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="account.model"/><s:text name="title.info"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.iban"/></td>
        <td class="page-form-value"><span id="show_account_iban"><s:property value="account.iban"/></span></td>
        <td class="page-form-label"><s:text name="account.schemeName"/></td>
        <td class="page-form-value"><span id="show_account_scheme"><s:property value="account.schemeName"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.identification"/></td>
        <td class="page-form-value"><span id="show_account_identification"><s:property value="account.identification"/></span></td>
        <td class="page-form-label"><s:text name="account.issuer"/></td>
        <td class="page-form-value"><span id="show_account_issuer"><s:property value="account.issuer"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.name"/></td>
        <td class="page-form-value"><span id="show_account_name"><s:property value="account.name"/></span></td>
        <td class="page-form-label"><s:text name="account.status"/></td>
        <td class="page-form-value"><span id="show_account_status"><s:property value="account.statusName"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.currency"/></td>
        <td class="page-form-value"><span id="show_account_currency"><s:property value="account.currencyName"/></span></td>
        <td class="page-form-label"><s:text name="account.type"/></td>
        <td class="page-form-value"><span id="show_account_type"><s:property value="account.typeName"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.monthlyPaymentValue"/></td>
        <td class="page-form-value"><span id="show_account_monthlyPaymentValue"><s:property value="account.monthlyPaymentValue"/></span></td>
        <td class="page-form-label"><s:text name="account.monthlyReceivedValue"/></td>
        <td class="page-form-value"><span id="show_account_monthlyReceivedValue"><s:property value="account.monthlyReceivedValue"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.monthlyTransactionNumber"/></td>
        <td class="page-form-value"><span id="show_account_monthlyTransactionNumber"><s:property value="account.monthlyTransactionNumber"/></span></td>
        <td class="page-form-label"><s:text name="account.averageBalance"/></td>
        <td class="page-form-value"><span id="show_account_averageBalance"><s:property value="account.averageBalance"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.floorNotificationAmount"/></td>
        <td class="page-form-value"><span id="show_account_floorNotificationAmount"><s:property value="account.floorNotificationAmount"/></span></td>
        <td class="page-form-label"><s:text name="account.ceilingNotificationAmount"/></td>
        <td class="page-form-value"><span id="show_account_ceilingNotificationAmount"><s:property value="account.ceilingNotificationAmount"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.statementCycle"/></td>
        <td class="page-form-value"><span id="show_account_statementCycleName"><s:property value="account.statementCycleName"/></span></td>
        <td class="page-form-label"><s:text name="account.closingDate"/></td>
        <td class="page-form-value"><s:date id="closingDate" name="account.closingDate" format="%{getText('fmt.date')}" /><span id="show_account_closingDate"><s:property value="%{closingDate}"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.purpose"/></td>
        <td class="page-form-value"><span id="show_account_purpose"><s:property value="account.purpose"/></span></td>
        <td class="page-form-label"><s:text name="account.restriction"/></td>
        <td class="page-form-value"><span id="show_account_restriction"><s:property value="account.restriction"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.targetGoLiveDate"/></td>
        <td class="page-form-value"><s:date id="targetGoLiveDate" name="account.targetGoLiveDate" format="%{getText('fmt.date')}" /><span id="show_account_targetGoLiveDate"><s:property value="%{targetGoLiveDate}"/></span></td>
        <td class="page-form-label"><s:text name="account.targetClosingDate"/></td>
        <td class="page-form-value"><s:date id="targetClosingDate" name="account.targetClosingDate" format="%{getText('fmt.date')}" /><span id="show_account_targetClosingDate"><s:property value="%{targetClosingDate}"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="account.urgencyFlag"/></td>
        <td class="page-form-value"><span id="show_account_urgencyFlag"><s:property value="account.urgencyFlagName"/></span></td>
        <td />
        <td />
    </tr>
    
    <s:set name="track" value="account"/>
    <s:include value="/common/_track.jsp"/>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 