<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title><s:text name="login.model"/><s:text name="title.show"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="login.model"/><s:text name="title.show"/></td>
    </tr>
</table>
<br/>

<s:include value="_view.jsp"/>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><a href="#" onclick="window.close();"><s:text name="action.close"/></a></td>
    </tr>
</table>
</body>
</html>
