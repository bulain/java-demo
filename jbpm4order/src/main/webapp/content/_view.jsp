<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table class="page-form" cellspacing="0" width="100%">
	<tr>
        <td class="page-form-title" colspan="4"><s:text name="content.model"/><s:text name="title.info"/></td>
        <s:hidden value="content.id"/>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.fileName"/></td>
        <td class="page-form-value"><s:property value="content.fileName"/></td>
    	<td class="page-form-label"><s:text name="content.contentType"/></td>
        <td class="page-form-value"><s:property value="content.contentType"/></td>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.refName"/></td>
        <td class="page-form-value"><s:property value="content.refName"/></td>
    	<td class="page-form-label"><s:text name="content.refId"/></td>
        <td class="page-form-value"><s:property value="content.refId"/></td>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.catagory"/></td>
        <td class="page-form-value"><s:property value="content.catagory"/></td>
    	<td class="page-form-label"><s:text name="content.lang"/></td>
        <td class="page-form-value"><s:property value="content.lang"/></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="content.createdBy"/></td>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="content.createdAt"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="content.updatedBy"/></td>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="content.updatedAt"/></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table>