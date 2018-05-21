<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="reference.model"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.name"/></td>
        <td class="page-form-value"><s:property value="reference.nameName"/></td>
        <td class="page-form-label"><s:text name="reference.code"/></td>
        <td class="page-form-value"><s:property value="reference.code"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.lang"/></td>
        <td class="page-form-value"><s:property value="reference.langName"/></td>
        <td class="page-form-label"><s:text name="reference.catagory"/></td>
        <td class="page-form-value"><s:property value="reference.catagoryName"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.text"/></td>
        <td class="page-form-value" colspan="3"><page:textarea value="reference.text"/></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="reference.createdBy"/></td>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="reference.createdAt"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="reference.updatedBy"/></td>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="reference.updatedAt"/></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table>