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
        <td class="page-form-title" colspan="4"><s:text name="mailTemplate.model"/><s:text name="title.info"/></td>
        <s:hidden key="mailTemplate.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="mailTemplate.name"/></td>
        <td class="page-form-value"><s:textfield key="mailTemplate.name"/></td>
        <td></td>
        <td></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.lang"/></td>
        <td class="page-form-value"><s:select name="mailTemplate.lang" list="listReferenceLang" listKey="key" listValue="value" value="mailTemplate.lang" /></td>
        <td></td>
        <td></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.subject"/></td>
        <td class="page-form-value" colspan="3"><s:textfield key="mailTemplate.subject" cssStyle="width:400px;"/></td>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="mailTemplate.body"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="mailTemplate.bodyName"  rows="8" cols="60"></s:textarea></td>
	</tr>
	<tr><td colspan="4"><div class="line"></div></td></tr>
	<tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.createdBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.createdAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.updatedBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="mailTemplate.updatedAt"/></td>
        <td/>
        <td/>
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
			"mailTemplate.name": {
				required: true,
				maxlength: 255
			},
			"mailTemplate.lang": {
				required: true,
				maxlength: 20
			},
			"mailTemplate.subject": {
				required: true,
				maxlength: 255
			}
		},
		messages: {
			"mailTemplate.name": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='mailTemplate.name'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='mailTemplate.name'/></s:param><s:param>255</s:param></s:text>"
			},
			"mailTemplate.lang": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='mailTemplate.lang'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='mailTemplate.lang'/></s:param><s:param>20</s:param></s:text>"
			},
			"mailTemplate.subject": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='mailTemplate.subject'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='mailTemplate.subject'/></s:param><s:param>255</s:param></s:text>"
			}
		}
	});
});
</script>
