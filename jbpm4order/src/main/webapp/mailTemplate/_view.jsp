<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="mailTemplate.model"/><s:text name="title.info"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="mailTemplate.name"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.name"/></td>
        <td></td>
        <td></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.lang"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.langName"/></td>
        <td></td>
        <td></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.subject"/></td>
        <td colspan="3"><s:property value="mailTemplate.subject"/></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.body"/></td>
        <td colspan="3"><page:textarea value="mailTemplate.bodyName"/></td>
	</tr>
	<tr><td colspan="4"><div class="line"></div></td></tr>
	<tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.createdBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.createdAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.updatedBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.updatedAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table>