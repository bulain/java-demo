<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="order.model"/><s:text name="title.info"/></td>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="order.name"/></td>
        <td class="page-form-value"><span id="show_order_name"><s:property value="order.name"/></span></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="order.note"/></td>
        <td class="page-form-value" colspan="3"><span id="show_order_note"><page:textarea value="order.note"/></span></td>
    </tr>
    <s:set name="track" value="order"/>
    <s:include value="/common/_track.jsp"/>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table>