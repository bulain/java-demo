<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<html>
<head>
    <title><s:text name="content.model"/><s:text name="title.list"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="content.model"/><s:text name="title.list"/></td>
    </tr>
</table>
<br/>

<s:form action="list" method="post" id="search" namespace="/content" theme="simple">
    <table class="page-form" cellspacing="0" width="100%">
        <tr>
            <td class="page-form-title" colspan="4"><s:text name="title.search"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><s:text name="content.fileName"/></td>
            <td class="page-form-value"><input type="text" name="search.fileName" value="<s:property value="search.fileName"/>"/></td>
            <td class="page-form-label"><s:text name="content.contentType"/></td>
            <td class="page-form-value"><input type="text" name="search.contentType" value="<s:property value="search.contentType"/>"/></td>
        </tr>
        <tr>
        	<td class="page-form-label"><s:text name="content.refName"/></td>
            <td class="page-form-value"><input type="text" name="search.refName" value="<s:property value="search.refName"/>"/></td>
            <td class="page-form-label"><s:text name="content.refId"/></td>
            <td class="page-form-value"><input type="text" name="search.refId" value="<s:property value="search.refId"/>"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><s:text name="content.catagory"/></td>
            <td class="page-form-value"><input type="text" name="search.catagory" value="<s:property value="search.catagory"/>"/></td>
            <td class="page-form-label"><s:text name="content.lang"/></td>
            <td class="page-form-value">
            	<s:select name="search.lang" list="listReferenceLang" listKey="key" listValue="value" value="search.lang" />
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
                        <td width="40%"><s:url id="url" action="new" /><a href="<s:property value="#url"/>"><s:text name="action.new"/><s:text name="content.model"/></a></td>
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
    	<th><page:order fixOrderBy="file_name"><s:text name="content.fileName"/></page:order></th>
    	<th><page:order fixOrderBy="content_type"><s:text name="content.contentType"/></page:order></th>
    	<th><page:order fixOrderBy="ref_name"><s:text name="content.refName"/></page:order></th>
    	<th><page:order fixOrderBy="ref_id"><s:text name="content.refId"/></page:order></th>
    	<th><page:order fixOrderBy="catagory"><s:text name="content.catagory"/></page:order></th>
    	<th><page:order fixOrderBy="lang"><s:text name="content.lang"/></page:order></th>
         <th><page:order fixOrderBy="updated_by"><s:text name="common.updatedBy"/></page:order></th>
        <th><page:order fixOrderBy="updated_at"><s:text name="common.updatedAt"/></page:order></th>
        <th class="minNoWrap"><s:text name="action.action"/></th>
    </tr>
    <s:iterator value="listContent" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="fileName"/></td>
            <td class="noWrap"><s:property value="contentType"/></td>
            <td class="noWrap"><s:property value="refName"/></td>
            <td class="noWrap"><s:property value="refId"/></td>
            <td class="noWrap"><s:property value="catagory"/></td>
            <td class="noWrap"><s:property value="lang"/></td>
            <td class="noWrap"><s:property value="updatedBy"/></td>
            <td class="noWrap"><s:property value="updatedAt"/></td>
            <td class="minNoWrap">
        	<s:url id="url" action="show"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.show"/></a>
        	|<s:url id="url" action="edit"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.edit"/></a>
        	|<s:url id="url" action="destroy"><s:param name="id" value="id"></s:param></s:url><a onclick="javascript:destroy(this);return false;" href="<s:property value="#url"/>"><s:text name="action.destroy"/></a>
        	|<s:url id="url" action="download"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.download"/></a>
        	</td>
        </tr>
    </s:iterator>
</table>

<p>&nbsp;</p>
<page:link page="page.page" totalPage="page.totalPage"/>

</body>
</html>
