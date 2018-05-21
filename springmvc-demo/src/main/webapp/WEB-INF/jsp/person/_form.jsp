<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<table class="page-form" cellspacing="0" width="100%">
    <thead>
        <tr>
            <td width="20%"></td>
            <td width="30%"></td>
            <td width="20%"></td>
            <td width="30%"></td>
        </tr>
    <thead>
    <tbody>
        <tr>
            <td class="page-form-title" colspan="4"><spring:message code="person.model"/><spring:message code="title.info"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><spring:message code="person.firstName"/></td>
            <td class="page-form-value"><form:input id="txt_first_name" path="firstName"/></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td class="page-form-label"><spring:message code="person.lastName"/></td>
            <td class="page-form-value"><form:input id="txt_last_name" path="lastName"/></td>
            <td></td>
            <td></td>
        </tr>
        <tr>
            <td width="20%"></td>
            <td width="30%"></td>
            <td width="20%"></td>
            <td width="30%"></td>
        </tr>
    </tbody>
</table> 