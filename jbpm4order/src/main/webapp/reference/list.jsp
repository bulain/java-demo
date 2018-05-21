<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<html>
<head>
    <title><s:text name="reference.model"/><s:text name="title.list"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="reference.model"/><s:text name="title.list"/></td>
    </tr>
</table>
<br/>

<s:form action="list" method="post" id="search" namespace="/reference" theme="simple">
    <table class="page-form" cellspacing="0" width="100%">
        <tr>
            <td class="page-form-title" colspan="4"><s:text name="title.search"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><s:text name="reference.name"/></td>
            <td class="page-form-value">
            	<s:select name="search.name" list="listReferenceName" listKey="key" listValue="value" value="search.name" />
            </td>
            <td class="page-form-label"><s:text name="reference.code"/></td>
            <td class="page-form-value"><s:textfield key="search.code"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><s:text name="reference.lang"/></td>
            <td class="page-form-value">
            	<s:select name="search.lang" list="listReferenceLang" listKey="key" listValue="value" value="search.lang" />
            </td>
            <td class="page-form-label"><s:text name="reference.catagory"/></td>
            <td class="page-form-value">
            	<s:select name="search.catagory" list="listReferenceCatagory" listKey="key" listValue="value" value="search.catagory" />
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
                        <td width="40%"><s:url id="url" action="new" /><a href="<s:property value="#url"/>"><s:text name="action.new"/><s:text name="reference.model"/></a></td>
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
    	<th><page:order fixOrderBy="name"><s:text name="reference.name"/></page:order></th>
        <th><page:order fixOrderBy="code"><s:text name="reference.code"/></page:order></th>
        <th><page:order fixOrderBy="lang"><s:text name="reference.lang"/></page:order></th>
        <th><page:order fixOrderBy="catagory"><s:text name="reference.catagory"/></page:order></th>
        <th><page:order fixOrderBy="text"><s:text name="reference.text"/></page:order></th>
        <th class="minNoWrap"><s:text name="action.action"/></th>
    </tr>
    <s:iterator value="listReference" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap"><s:property value="nameName"/></td>
            <td class="noWrap"><s:property value="code"/></td>
            <td class="noWrap"><s:property value="langName"/></td>
            <td class="noWrap"><s:property value="catagoryName"/></td>
            <td class="noWrap"><page:text value="text"/></td>
            <td class="minNoWrap">
        	<s:url id="url" action="show"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.show"/></a>
        	|<s:url id="url" action="edit"><s:param name="id" value="id"></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.edit"/></a>
        	|<s:url id="url" action="destroy"><s:param name="id" value="id"></s:param></s:url><a onclick="javascript:destroy();return false;" href="<s:property value="#url"/>"><s:text name="action.destroy"/></a>
        	</td>
        </tr>
    </s:iterator>
</table>

<p>&nbsp;</p>
<page:link page="page.page" totalPage="page.totalPage"/>

</body>
</html>
