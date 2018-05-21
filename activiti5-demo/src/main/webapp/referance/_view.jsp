<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="referance.model"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.name"/></td>
        <td class="page-form-value"><span id="show_referance_name"><s:property value="referance.nameName"/></span></td>
        <td class="page-form-label"><s:text name="referance.code"/></td>
        <td class="page-form-value"><span id="show_referance_code"><s:property value="referance.code"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.lang"/></td>
        <td class="page-form-value"><span id="show_referance_lang"><s:property value="referance.langName"/></span></td>
        <td class="page-form-label"><s:text name="referance.category"/></td>
        <td class="page-form-value"><span id="show_referance_category"><s:property value="referance.categoryName"/></span></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.text"/></td>
        <td class="page-form-value" colspan="3"><span id="show_referance_text"><page:textarea value="referance.text"/></span></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <s:set name="track" value="referance"/>
    <s:include value="/common/_track.jsp"/>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table>