<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<html>
<head>
    <title><s:text name="login.model"/><s:text name="title.list"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="login.model"/><s:text name="title.list"/></td>
    </tr>
</table>
<br/>

<s:form action="list" method="post" id="search" namespace="/login" theme="simple">
    <table class="page-form" cellspacing="0" width="100%">
        <tr>
            <td class="page-form-title" colspan="4"><s:text name="title.search"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><s:text name="login.loginName"/></td>
            <td class="page-form-value"><s:textfield key="search.loginName"/></td>
            <td class="page-form-label"><s:text name="group.model"/></td>
            <td class="page-form-value">
            	<s:select name="search.groupId" list="listMasterGroup" listKey="key" listValue="value" value="search.groupId" />
            </td>
        </tr>
        <tr>
        	<td class="page-form-label"><s:text name="login.email"/></td>
            <td class="page-form-value"><s:textfield key="search.email"/></td>
            <td class="page-form-label"><s:text name="login.enabled"/></td>
            <td class="page-form-value">
            	<s:select name="search.enabled" list="listReferenceBoolean" listKey="key" listValue="value" value="search.enabled" />
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
                        <td width="40%"><s:url id="url" action="new" /><a href="<s:property value="#url"/>"><s:text name="action.new"/><s:text name="login.model"/></a></td>
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
    	<th width="10%"><page:order fixOrderBy="login_name"><s:text name="login.loginName"/></page:order></th>
    	<th width="10%"><s:text name="login.person"/></th>
    	<th width="10%"><page:order fixOrderBy="email"><s:text name="login.email"/></page:order></th>
    	<th width="10%"><page:order fixOrderBy="enabled"><s:text name="login.enabled"/></page:order></th>
    	<th width="20%"><s:text name="group.model"/></th>
        <th width="10%"><page:order fixOrderBy="updated_by"><s:text name="common.updatedBy"/></page:order></th>
        <th width="10%"><page:order fixOrderBy="updated_at"><s:text name="common.updatedAt"/></page:order></th>
        <th class="minNoWrap"><s:text name="action.action"/></th>
    </tr>
    <s:iterator value="listLogin" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="loginName"/></td>
            <td class="noWrap"><s:property value="personName"/></td>
            <td class="noWrap"><s:property value="email"/></td>
            <td class="noWrap"><s:property value="enabledName"/></td>
            <td class="noWrap">
            	<s:iterator value="listGroup" status="s1">
            		<div>
            		<s:url id="url" namespace="/group" action="popupShow"><s:param name="id" value="id"></s:param></s:url>
					<a href="<s:property value='#url'/>" onclick="window.open('${url}', 'popup', 'height=300,width=800,toolbar=no,menubar=no,scrollbars=yes,resizable=yes');return false;"><s:property value='name'/></a>
					</div>
				</s:iterator>
            </td>
            <td class="noWrap"><s:property value="updatedBy"/></td>
            <td class="noWrap"><s:property value="updatedAt"/></td>
            <td class="minNoWrap">
        	<s:url id="url" action="show"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.show"/></a>
        	|<s:url id="url" action="edit"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.edit"/></a>
        	|<s:url id="url" action="destroy"><s:param name="id" value="id"></s:param></s:url><a onclick="javascript:destroy(this);return false;" href="<s:property value="#url"/>"><s:text name="action.destroy"/></a>
        	<br/>
        	<s:url id="url" action="editGroup"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="group.model"/></a>
        	</td>
        </tr>
    </s:iterator>
</table>

<p>&nbsp;</p>
<page:link page="page.page" totalPage="page.totalPage"/>

</body>
</html>
