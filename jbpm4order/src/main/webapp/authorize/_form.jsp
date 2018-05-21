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
        <td class="page-form-title" colspan="4"><s:text name="authorize.model"/><s:text name="title.info"/></td>
        <s:hidden key="authorize.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="authorize.controller"/></td>
        <td class="page-form-value"><s:textfield key="authorize.controller"/></td>
        <td/>
        <td/>
	</tr>
	<tr>
        <td class="page-form-label"><s:text name="authorize.action"/></td>
        <td class="page-form-value"><s:textfield key="authorize.action"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="authorize.permission"/></td>
        <td class="page-form-value"><s:textfield key="authorize.permission"/></td>
        <td/>
        <td/>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="authorize.createdBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="authorize.createdAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="authorize.updatedBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="authorize.updatedAt"/></td>
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
			"authorize.controller": {
				required: true,
				maxlength: 50
			},
			"authorize.action": {
				required: true,
				maxlength: 50
			},
			"authorize.permission": {
				required: true,
				maxlength: 50
			}
		},
		messages: {
			"authorize.controller": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='authorize.controller'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='authorize.controller'/></s:param><s:param>50</s:param></s:text>"
			},
			"authorize.action": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='authorize.action'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='authorize.action'/></s:param><s:param>50</s:param></s:text>"
			},
			"authorize.permission": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='authorize.permission'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='authorize.permission'/></s:param><s:param>50</s:param></s:text>"
			}
		}
	});
});
</script>
