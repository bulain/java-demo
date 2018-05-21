<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="category.model"/><s:text name="title.info"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="category.name"/></td>
        <td class="page-form-value"><span id="show_category_name"><s:property value="category.name"/></span></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="category.type"/></td>
        <td class="page-form-value"><span id="show_category_type"><s:property value="category.typeName"/></span></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="category.description"/></td>
        <td class="page-form-value" colspan="3"><span id="show_category_description"><page:textarea value="category.description"/></span></td>
    </tr>
    <s:set name="track" value="category"/>
    <s:include value="/common/_track.jsp"/>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 