<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="page" uri="/page-tags" %>

<table id="list" class="list-table" cellspacing="0" width="100%" c>
    <tr class="list-table-header">
        <th><s:text name="content.contentType"/></th>
        <th><s:text name="content.refName"/></th>
        <th><s:text name="content.refId"/></th>
        <th><s:text name="content.catagory"/></th>
        <th><s:text name="content.lang"/></th>
        <th><s:text name="content.fileName"/></th>
        <th><s:text name="common.updatedBy"/></th>
        <th><s:text name="common.updatedAt"/></th>
        <th class="minNoWrap"><s:text name="action.action"/></th>
    </tr>
    <s:iterator value="listContent" status="status">
        <tr class="<s:if test="#status.even">list-line-even</s:if><s:else>list-line-odd</s:else>" >
            <td class="noWrap">
            	<s:url id='url' action='download'><s:param name='id' value='%{id}'></s:param></s:url>
            	<a href="<s:property value='#url'/>"><s:property value="fileName"/></a>
            </td>
            <td class="noWrap"><s:property value="contentType"/></td>
            <td class="noWrap"><s:property value="refName"/></td>
            <td class="noWrap"><s:property value="refId"/></td>
            <td class="noWrap"><s:property value="catagory"/></td>
            <td class="noWrap"><s:property value="lang"/></td>
            <td class="noWrap"><s:property value="updatedBy"/></td>
            <td class="noWrap"><s:property value="updatedAt"/></td>
            <td class="minNoWrap">
        	<s:url id='url' action='ajaxDestroy'><s:param name='id' value='%{id}'></s:param></s:url><a href="<s:property value="#url"/>"><s:text name="action.destroy"/></a>
        	</td>
        </tr>
    </s:iterator>
</table>

<p>&nbsp;</p>
<page:link page="page.page" totalPage="page.totalPage"/>

<script type="text/javascript">
// <![CDATA[
$(document).ready(function() {
	$('a.destory').click(function(){
		var delEle = $(this).parent().parent();
		$.ajax({
		 url: $(this).attr('href'),
		 type: 'POST',
		 dataType: 'json',
		 timeout: 10000,
		 data: {'format':'json'},
		 error: function () {
				alert('Error loading XML document');
		 	},
		 success: function(data){
				delEle.remove();
		 	}
		});
		return false;
	});
});
// ]]>
</script>