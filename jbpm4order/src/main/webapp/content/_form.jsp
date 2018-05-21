<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div style="color:red;">
	<s:fielderror />
	<s:actionmessage/>
	<s:actionerror/>
</div>
<div class="error"></div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4"><s:text name="content.model"/><s:text name="title.info"/></td>
        <s:hidden key="content.id"/>
        <s:token/>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.fileName"/></td>
        <td class="page-form-value"><s:textfield key="content.fileName"/></td>
    	<td class="page-form-label"><s:text name="content.contentType"/></td>
        <td class="page-form-value"><s:textfield key="content.contentType"/></td>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.refName"/></td>
        <td class="page-form-value"><s:textfield key="content.refName"/></td>
    	<td class="page-form-label"><s:text name="content.refId"/></td>
        <td class="page-form-value"><s:textfield key="content.refId"/></td>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.catagory"/></td>
        <td class="page-form-value"><s:textfield key="content.catagory"/></td>
    	<td class="page-form-label"><s:text name="content.lang"/></td>
        <td class="page-form-value">
        	<s:select name="content.lang" list="listReferenceLang" listKey="key" listValue="value" value="content.lang" />
        </td>
    </tr>
    <tr>
		<td class="page-form-label"><s:text name="content.file"/></td>
        <td class="page-form-value"><input type="file" name="blob"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="content.createdBy"/></td>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="content.createdAt"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="content.updatedBy"/></td>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="content.updatedAt"/></td>
    </tr>
    <tr>
        <td width="20%"></td>
        <td width="30%"></td>
        <td width="20%"></td>
        <td width="30%"></td>
    </tr>
</table> 


<script type="text/javascript">
$.validator.setDefaults({
	submitHandler: function(form) {form.submit();}
});
$(document).ready(function() {
	$("form").validate({
		errorLabelContainer: $("div.error"),
		rules: {
			"content.fileName": {
				required: true,
				maxlength: 50
			},
			"content.contentType": {
				required: true,
				maxlength: 255
			},
			"blob": {
				required: true,
			}
		},
		messages: {
			"content.fileName": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='content.fileName'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='content.fileName'/></s:param><s:param>50</s:param></s:text>"
			},
			"content.contentType": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='content.contentType'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='content.contentType'/></s:param><s:param>50</s:param></s:text>"
			},
			"blob": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='blob'/></s:param></s:text>",
			}
		}
	});
});
</script>
