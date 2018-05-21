<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="order.model"/><s:text name="title.approval"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="order.model"/><s:text name="title.approval"/></td>
    </tr>
</table>
<br/>

<s:form action="submitApproval" method="post" theme="simple">
    <s:include value="_view.jsp"/>
    <s:hidden key="taskId"/>
    <table cellspacing="0" width="100%">
        <tr>
            <td class="page-form-centered"><input type="submit" name="submit" value="Approve"/><input type="submit" name="submit" value="Reject"/></td>
        </tr>
    </table>
</s:form>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><s:url id="url" value="/workflow/list" /><a href="<s:property value="#url"/>"><s:text name="action.back"/></a></td>
    </tr>
</table>
</body>
</html>
