<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="group.model"/><s:text name="title.info"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="group.name"/></td>
        <td class="page-form-value"><span id="show_group_name"><s:property value="group.name"/></span></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="group.type"/></td>
        <td class="page-form-value"><span id="show_group_type"><s:property value="group.typeName"/></span></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="group.description"/></td>
        <td class="page-form-value" colspan="3"><span id="show_group_description"><page:textarea value="group.description"/></span></td>
    </tr>
    <s:set name="track" value="group"/>
    <s:include value="/common/_track.jsp"/>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 