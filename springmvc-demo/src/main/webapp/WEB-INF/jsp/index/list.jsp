<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<html>
<head>
    <title><spring:message code="person.model"/><spring:message code="title.list"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><spring:message code="person.model"/><spring:message code="title.list"/></td>
    </tr>
</table>
<br/>

<c:if test="${not empty message}">
<div id="notice">${message}</div>
</c:if>

<c:url var="url" value="/index/list" />
<form:form method="POST" action="${url}" modelAttribute="search">
    <table class="page-form" cellspacing="0" width="100%">
        <tr>
            <td class="page-form-title" colspan="4"><spring:message code="title.search"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><spring:message code="person.firstName"/></td>
            <td class="page-form-value"><form:input id="first_name" path="firstName"/></td>
            <td class="page-form-label"><spring:message code="person.lastName"/></td>
            <td class="page-form-value"><form:input id="last_name" path="lastName"/></td>
        </tr>
        <tr>
            <td width="20%"></td>
            <td width="30%"></td>
            <td width="20%"></td>
            <td width="30%"></td>
        </tr>
        <tr>
            <td colspan="4">
                <table cellspacing="0" width="100%">
                    <tr>
                        <td width="40%"><c:url var="url" value="/index/new" /><a id="lnk_new" href="${url}"><spring:message code="action.new"/><spring:message code="person.model"/></a></td>
                        <td class="page-form-centered"><input type="submit" id="btn_search" value="<spring:message code="action.search"/>"/></td>
                        <td width="40%"></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form:form>
<br/>

<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th width="10%"><page:order fixOrderBy="first_name"><spring:message code="person.firstName"/></page:order></th>
    	<th width="10%"><page:order fixOrderBy="last_name"><spring:message code="person.lastName"/></page:order></th>
        <th width="10%"><page:order fixOrderBy="updated_by"><spring:message code="common.updatedBy"/></page:order></th>
        <th width="10%"><page:order fixOrderBy="updated_at"><spring:message code="common.updatedAt"/></page:order></th>
        <th class="minNoWrap"><spring:message code="action.action"/></th>
    </tr>
    <c:forEach items="${persons}" var="person">
        <tr>
            <td class="noWrap">${person.firstName}</td>
            <td class="noWrap">${person.lastName}</td>
            <td class="noWrap">${person.updatedBy}</td>
            <td class="noWrap">${person.updatedAt}</td>
            <td class="minNoWrap">
            <c:url var="url" value="/index/show/${person.id}"></c:url><a href="${url}"><spring:message code="action.show"/></a>
            <c:url var="url" value="/index/edit/${person.id}"></c:url><a href="${url}"><spring:message code="action.edit"/></a>
            <c:url var="url" value="/index/destroy/${person.id}"></c:url><a onclick="javascript:destroy(this);return false;" href="${url}"><spring:message code="action.destroy"/></a>
            </td>
        </tr>
    </c:forEach>
</table>

<br/>
<page:link/>

</body>
</html>
