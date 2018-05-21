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
        <td class="page-form-title" colspan="4"><s:text name="group.model"/><s:text name="title.info"/></td>
        <s:hidden key="group.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="group.name"/></td>
        <td class="page-form-value"><s:textfield key="group.name"/></td>
        <td />
        <td />
    </tr>
    <tr>
    	<td class="page-form-label"><s:text name="group.note"/></td>
		<td class="page-form-value" colspan="3"><s:textarea name="group.note"  rows="4" cols="50"></s:textarea></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="group.createdBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="group.createdAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="group.updatedBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="group.updatedAt"/></td>
        <td/>
        <td/>
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
			"group.name": {
				required: true,
				maxlength: 20
			}
		},
		messages: {
			"group.name": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='group.name'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='group.name'/></s:param><s:param>20</s:param></s:text>"
			}
		}
	});
});
</script>
