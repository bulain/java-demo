<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="login.model"/><s:text name="title.info"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="login.loginName"/></td>
        <td class="page-form-value"><s:property value="login.loginName"/></td>
        <td class="page-form-label"><s:text name="login.password"/></td>
        <td class="page-form-value"><s:property value="login.hashedPassword"/></td>
    </tr>
    <tr>
    	<td class="page-form-label"><s:text name="login.email"/></td>
    	<td class="page-form-value"><s:property value="login.email"/></td>
		<td class="page-form-label"><s:text name="login.enabled"/></td>
		<td class="page-form-value"><s:property value="login.enabledName"/></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="login.createdBy"/></td>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="login.createdAt"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="login.updatedBy"/></td>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="login.updatedAt"/></td>
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 