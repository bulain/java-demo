<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="account.model"/><s:text name="title.edit"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="account.model"/><s:text name="title.edit"/></td>
    </tr>
</table>
<br/>

<s:form action="update" method="post" theme="simple">
    <s:include value="_form.jsp"/>
    <table cellspacing="0" width="100%">
        <tr>
            <td class="page-form-centered"><s:submit id="btn_update" value="%{getText('action.update')}"/></td>
        </tr>
    </table>
</s:form>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><s:url id="url" action="list" /><a href="<s:property value="#url"/>"><s:text name="action.back"/></a></td>
    </tr>
</table>

<script type="text/javascript">
$(document).ready(function() {
	$("#update").validate();
    $("#update_account_targetGoLiveDate").datepicker({dateFormat:'<s:text name="fmt.jq.date"/>'});
    $("#update_account_targetClosingDate").datepicker({dateFormat:'<s:text name="fmt.jq.date"/>'});
    $("#update_account_closingDate").datepicker({dateFormat:'<s:text name="fmt.jq.date"/>'});
});
</script>

</body>
</html>
