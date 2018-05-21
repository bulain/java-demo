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
        <td class="page-form-title" colspan="4"><s:text name="order.model"/><s:text name="title.info"/></td>
        <s:hidden key="order.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="order.name"/></td>
        <td class="page-form-value"><s:textfield key="order.name"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="order.note"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="order.note"  rows="4" cols="50"></s:textarea></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="order.createdBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="order.createdAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="order.updatedBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="order.updatedAt"/></td>
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
			"order.name": {
				required: true,
				maxlength: 20
			},
			"order.note": {
				maxlength: 255
			}
		},
		messages: {
			"order.name": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='order.name'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='order.name'/></s:param><s:param>20</s:param></s:text>"
			},
			"order.note": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='group.name'/></s:param><s:param>255</s:param></s:text>"
			}
		}
	});
});
</script>
