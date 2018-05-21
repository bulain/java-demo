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
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.name"/></td>
        <td class="page-form-value">
        	<s:select name="referenceBean.name" list="listReferenceName" listKey="key" listValue="value" value="referenceBean.name" />
        </td>
        <td class="page-form-label"><s:text name="reference.code"/></td>
        <td class="page-form-value"><s:textfield name="referenceBean.code"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.catagory"/></td>
        <td class="page-form-value">
        	<s:select name="referenceBean.catagory" list="listReferenceCatagory" listKey="key" listValue="value" value="referenceBean.catagory" />
        </td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.textEN"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="referenceBean.textEN"  rows="4" cols="50"></s:textarea></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="reference.textCN"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="referenceBean.textCN"  rows="4" cols="50"></s:textarea></td>
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
			"referenceBean.name": {
				required: true,
				maxlength: 50
			},
			"referenceBean.code": {
				required: true,
				maxlength: 50
			},
			"referenceBean.textEN": {
				maxlength: 250
			},
			"referenceBean.textCN": {
				maxlength: 250
			},
			"referenceBean.catagory": {
				maxlength: 20
			}
		},
		messages: {
			"referenceBean.name": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='referenceBean.name'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referenceBean.name'/></s:param><s:param>50</s:param></s:text>"
			},
			"referenceBean.code": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='referenceBean.code'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referenceBean.code'/></s:param><s:param>50</s:param></s:text>"
			},
			"referenceBean.textEN": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referenceBean.textEN'/></s:param><s:param>250</s:param></s:text>"
			},
			"referenceBean.textCN": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referenceBean.textCN'/></s:param><s:param>250</s:param></s:text>"
			},
			"referenceBean.catagory": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referenceBean.catagory'/></s:param><s:param>20</s:param></s:text>"
			}
		}
	});
});
</script>
