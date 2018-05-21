<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="person.model"/><spring:message code="title.show"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><spring:message code="person.model"/><spring:message code="title.show"/></td>
    </tr>
</table>
<br/>

<c:import url="_view.jsp"/>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><c:url var="url" value="/index/list" /><a id="lnk_back" href="${url}"><spring:message code="action.back"/></a></td>
    </tr>
</table>
</body>
</html>
