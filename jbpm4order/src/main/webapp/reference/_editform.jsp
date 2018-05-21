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
        <td class="page-form-title" colspan="4"><s:text name="reference.model"/></td>
        <s:hidden name="reference.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.name"/></td>
        <td class="page-form-value">
        	<s:select name="reference.name" list="listReferenceName" listKey="key" listValue="value" value="reference.name" />
        </td>
        <td class="page-form-label"><s:text name="reference.code"/></td>
        <td class="page-form-value"><s:textfield name="reference.code"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.lang"/></td>
        <td class="page-form-value">
        	<s:select name="reference.lang" list="listReferenceLang" listKey="key" listValue="value" value="reference.lang" />
        </td>
        <td class="page-form-label"><s:text name="reference.catagory"/></td>
        <td class="page-form-value">
        	<s:select name="reference.catagory" list="listReferenceCatagory" listKey="key" listValue="value" value="reference.catagory" />
        </td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.text"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="reference.text"  rows="4" cols="50"></s:textarea></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="reference.createdBy"/></td>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="reference.createdAt"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="reference.updatedBy"/></td>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="reference.updatedAt"/></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
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
			"reference.name": {
				required: true,
				maxlength: 50
			},
			"reference.code": {
				required: true,
				maxlength: 50
			},
			"reference.text": {
				maxlength: 250
			},
			"reference.lang": {
				required: true,
				maxlength: 20
			},
			"reference.catagory": {
				maxlength: 20
			}
		},
		messages: {
			"reference.name": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='reference.name'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='reference.name'/></s:param><s:param>50</s:param></s:text>"
			},
			"reference.code": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='reference.code'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='reference.code'/></s:param><s:param>50</s:param></s:text>"
			},
			"reference.text": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='reference.text'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='reference.text'/></s:param><s:param>250</s:param></s:text>"
			},
			"reference.lang": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='reference.lang'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='reference.lang'/></s:param><s:param>20</s:param></s:text>"
			},
			"reference.catagory": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='reference.catagory'/></s:param><s:param>20</s:param></s:text>"
			}
		}
	});
});
</script>
