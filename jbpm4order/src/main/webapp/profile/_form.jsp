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
        <td class="page-form-title" colspan="4"><s:text name="profile.model"/><s:text name="title.info"/></td>
        <s:hidden key="profile.id"/>
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="profile.language"/></td>
        <td class="page-form-value">
        	<s:select name="profile.language" list="listReferenceLanguage" listKey="key" listValue="value" value="profile.language" />
        </td>
        <td class="page-form-label"><s:text name="profile.country"/></td>
        <td class="page-form-value">
        	<s:select name="profile.country" list="listReferenceCountry" listKey="key" listValue="value" value="profile.country" />
        </td>
    </tr>
    <tr><td colspan="4"><div class="line"></div></td></tr>
    <tr>
        <td class="page-form-label"><s:text name="common.createdBy"/></td>
        <td class="page-form-value"><s:property value="profile.createdBy"/></td>
        <td class="page-form-label"><s:text name="common.createdAt"/></td>
        <td class="page-form-value"><s:property value="profile.createdAt"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="common.updatedBy"/></td>
        <td class="page-form-value"><s:property value="profile.updatedBy"/></td>
        <td class="page-form-label"><s:text name="common.updatedAt"/></td>
        <td class="page-form-value"><s:property value="profile.updatedAt"/></td>
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
			"profile.language": {
				required: true,
				maxlength: 50
			},
			"profile.country": {
				required: true,
				maxlength: 50
			}
		},
		messages: {
			"profile.language": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='profile.language'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='profile.language'/></s:param><s:param>50</s:param></s:text>"
			},
			"profile.country": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='profile.country'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='profile.country'/></s:param><s:param>50</s:param></s:text>"
			}
		}
	});
});
</script>
