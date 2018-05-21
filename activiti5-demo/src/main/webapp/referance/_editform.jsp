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
        <td class="page-form-title" colspan="4"><s:text name="referance.model"/></td>
        <s:hidden name="referance.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.name"/></td>
        <td class="page-form-value">
        	<s:select name="referance.name" list="listReferanceName" listKey="key" listValue="value" value="referance.name" />
        </td>
        <td class="page-form-label"><s:text name="referance.code"/></td>
        <td class="page-form-value"><s:textfield name="referance.code"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.lang"/></td>
        <td class="page-form-value">
        	<s:select name="referance.lang" list="listReferanceLang" listKey="key" listValue="value" value="referance.lang" />
        </td>
        <td class="page-form-label"><s:text name="referance.category"/></td>
        <td class="page-form-value">
        	<s:select name="referance.category" list="listReferanceCategory" listKey="key" listValue="value" value="referance.category" />
        </td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.text"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="referance.text"  rows="4" cols="50"></s:textarea></td>
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
			"referance.name": {
				required: true,
				maxlength: 50
			},
			"referance.code": {
				required: true,
				maxlength: 50
			},
			"referance.text": {
				maxlength: 250
			},
			"referance.lang": {
				required: true,
				maxlength: 20
			},
			"referance.category": {
				maxlength: 20
			}
		},
		messages: {
			"referance.name": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='referance.name'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referance.name'/></s:param><s:param>50</s:param></s:text>"
			},
			"referance.code": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='referance.code'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referance.code'/></s:param><s:param>50</s:param></s:text>"
			},
			"referance.text": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='referance.text'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referance.text'/></s:param><s:param>250</s:param></s:text>"
			},
			"referance.lang": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='referance.lang'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referance.lang'/></s:param><s:param>20</s:param></s:text>"
			},
			"referance.category": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referance.category'/></s:param><s:param>20</s:param></s:text>"
			}
		}
	});
});
</script>
