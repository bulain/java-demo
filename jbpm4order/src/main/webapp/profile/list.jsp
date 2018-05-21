<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<html>
<head>
    <title><s:text name="profile.model"/><s:text name="title.list"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="profile.model"/><s:text name="title.list"/></td>
    </tr>
</table>
<br/>

<s:form action="list" method="post" id="search" namespace="/profile" theme="simple">
    <table class="page-form" cellspacing="0" width="100%">
        <tr>
            <td class="page-form-title" colspan="4"><s:text name="title.search"/></td>
        </tr>
        <tr>
	        <td class="page-form-label"><s:text name="profile.language"/></td>
	        <td class="page-form-value">
	        	<s:select name="search.language" list="listReferenceLanguage" listKey="key" listValue="value" value="search.language" />
	        </td>
	        <td class="page-form-label"><s:text name="profile.country"/></td>
	        <td class="page-form-value">
	        	<s:select name="search.country" list="listReferenceCountry" listKey="key" listValue="value" value="search.country" />
	        </td>
	    </tr>
        <tr>
            <td width="20%"/>
            <td width="30%"/>
            <td width="20%"/>
            <td width="30%"/>
        </tr>
        <tr>
            <td colspan="4">
                <table cellspacing="0" width="100%">
                    <tr>
                        <td width="40%"><s:url id="url" action="new" /><a href="<s:property value="#url"/>"><s:text name="action.new"/><s:text name="profile.model"/></a></td>
                        <td class="page-form-centered"><s:submit value="%{getText('action.search')}" /></td>
                        <td width="40%" />
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</s:form>
<br/>

<table id="list" class="list-table" cellspacing="0" width="100%" border="1">
    <tr class="list-table-header">
    	<th><page:order fixOrderBy="person_id"><s:text name="profile.person"/></page:order></th>
        <th><page:order fixOrderBy="language"><s:text name="profile.language"/></page:order></th>
        <th><page:order fixOrderBy="country"><s:text name="profile.country"/></page:order></th>
        <th><page:order fixOrderBy="created_by"><s:text name="common.createdBy"/></page:order></th>
        <th><page:order fixOrderBy="created_at"><s:text name="common.createdAt"/></page:order></th>
        <th><page:order fixOrderBy="updated_by"><s:text name="common.updatedBy"/></page:order></th>
        <th><page:order fixOrderBy="updated_at"><s:text name="common.updatedAt"/></page:order></th>
        <th class="minNoWrap"><s:text name="action.action"/></th>
    </tr>
    <s:iterator value="listProfile" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="personId"/></td>
            <td class="noWrap"><s:property value="languageName"/></td>
            <td class="noWrap"><s:property value="countryName"/></td>
            <td class="noWrap"><s:property value="createdBy"/></td>
            <td class="noWrap"><s:property value="createdAt"/></td>
            <td class="noWrap"><s:property value="updatedBy"/></td>
            <td class="noWrap"><s:property value="updatedAt"/></td>
            <td class="minNoWrap">
        	<s:url id="url" action="show"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.show"/></a>
        	|<s:url id="url" action="edit"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.edit"/></a>
        	|<s:url id="url" action="destroy"><s:param name="id" value="id"></s:param></s:url><a onclick="javascript:destroy(this);return false;" href="<s:property value="#url"/>"><s:text name="action.destroy"/></a>
        	</td>
        </tr>
    </s:iterator>
</table>

<p>&nbsp;</p>
<page:link page="page.page" totalPage="page.totalPage"/>

</body>
</html>
