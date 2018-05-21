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
        <s:token/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.name"/></td>
        <td class="page-form-value">
        	<s:select name="referanceBean.name" list="listReferanceName" listKey="key" listValue="value" value="referanceBean.name" />
        </td>
        <td class="page-form-label"><s:text name="referance.code"/></td>
        <td class="page-form-value"><s:textfield name="referanceBean.code"/></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.category"/></td>
        <td class="page-form-value">
        	<s:select name="referanceBean.category" list="listReferanceCategory" listKey="key" listValue="value" value="referanceBean.category" />
        </td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.textEN"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="referanceBean.textEN"  rows="4" cols="50"></s:textarea></td>
    </tr>
    <tr>
        <td class="page-form-label"><s:text name="referance.textCN"/></td>
        <td class="page-form-value" colspan="3"><s:textarea name="referanceBean.textCN"  rows="4" cols="50"></s:textarea></td>
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
			"referanceBean.name": {
				required: true,
				maxlength: 50
			},
			"referanceBean.code": {
				required: true,
				maxlength: 50
			},
			"referanceBean.textEN": {
				maxlength: 250
			},
			"referanceBean.textCN": {
				maxlength: 250
			},
			"referanceBean.category": {
				maxlength: 20
			}
		},
		messages: {
			"referanceBean.name": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='referanceBean.name'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referanceBean.name'/></s:param><s:param>50</s:param></s:text>"
			},
			"referanceBean.code": {
				required: "<s:text name='js.validate.required'><s:param><s:text name='referanceBean.code'/></s:param></s:text>",
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referanceBean.code'/></s:param><s:param>50</s:param></s:text>"
			},
			"referanceBean.textEN": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referanceBean.textEN'/></s:param><s:param>250</s:param></s:text>"
			},
			"referanceBean.textCN": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referanceBean.textCN'/></s:param><s:param>250</s:param></s:text>"
			},
			"referanceBean.category": {
				maxlength: "<s:text name='js.validate.maxlength'><s:param><s:text name='referanceBean.category'/></s:param><s:param>20</s:param></s:text>"
			}
		}
	});
});
</script>
