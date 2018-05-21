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
        <td class="page-form-title" colspan="4"><s:text name="login.model"/><s:text name="title.info"/></td>
        <s:hidden key="login.id"/>
        <s:token/>
    </tr>
    <tr>
		<td class="page-form-label"><s:text name="login.person"/></td>
		<td class="page-form-value">
        	<s:select name="login.personId" list="listMasterPerson" listKey="key" listValue="value" value="login.personId" />
        </td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="login.loginName"/></td>
        <td class="page-form-value"><s:textfield key="login.loginName"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="login.password"/></td>
        <td class="page-form-value"><s:password key="login.hashedPassword"/></td>
        <td></td>
        <td></td>
    </tr>
    <tr>
    	<td class="page-form-label"><s:text name="login.email"/></td>
    	<td class="page-form-value"><s:textfield key="login.email"/></td>
    	<td></td>
        <td></td>
    </tr>
    <tr>
		<td class="page-form-label"><s:text name="login.enabled"/></td>
		<td class="page-form-value">
        	<s:select name="login.enabled" list="listReferenceBoolean" listKey="key" listValue="value" value="login.enabled" />
        </td>
        <td></td>
        <td></td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="login.createdBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="login.createdAt"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="login.updatedBy"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="login.updatedAt"/></td>
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
			"login.loginName": {
				required: true,
				maxlength: 20
			},
			"login.email": {
				required: true,
				email: true,
				maxlength: 50
			},
			"login.hashedPassword": {
				maxlength: 50
			},
			"login.enabled": {
				maxlength: 20
			}
		},
		messages: {
			"login.loginName": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='login.loginName'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='login.loginName'/></s:param><s:param>20</s:param></s:text>"
			},
			"login.email": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='login.email'/></s:param></s:text>",
				email: "<s:text name='js.validate.email'><s:param><s:text name='login.email'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='login.email'/></s:param><s:param>50</s:param></s:text>"
			},
			"login.hashedPassword": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='login.hashedPassword'/></s:param><s:param>50</s:param></s:text>"
			},
			"login.enabled": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='login.enabled'/></s:param><s:param>20</s:param></s:text>"
			}
		}
	});
});
</script>
