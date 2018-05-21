<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <title><spring:message code="person.model"/><spring:message code="title.add"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><spring:message code="person.model"/><spring:message code="title.add"/></td>
    </tr>
</table>
<br/>

<c:url var="url" value="/person/create" />
<form:form method="POST" action="${url}" modelAttribute="person">
    <form:errors id="notice" cssStyle="display: block;" path="*"/>
    <c:import url="_form.jsp"/>
    <table cellspacing="0" width="100%">
        <tr>
            <td class="page-form-centered"><input type="submit" id="btn_create" value="<spring:message code="action.create"/>"/></td>
        </tr>
    </table>
</form:form>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><c:url var="url" value="/person/list" /><a id="lnk_back" href="${url}"><spring:message code="action.back"/></a></td>
    </tr>
</table>
</body>
</html>
