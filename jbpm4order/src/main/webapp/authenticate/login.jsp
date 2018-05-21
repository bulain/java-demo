<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title><s:text name="login.model"/></title>
</head>
<body>

<s:form action="logon" method="post" theme="simple">
    <div style="color:red; text-align:center;">
		<s:fielderror />
		<s:actionmessage/>
		<s:actionerror/>
	</div>
	
	<table class="page-form" cellspacing="0" width="400px">
	    <tr>
	        <td class="page-form-title" colspan="4"><s:text name="login.model"/><s:text name="title.info"/></td>
	        <s:token/>
	    </tr>
	    <tr>
	        <td class="page-form-label"><s:text name="login.loginName"/></td>
	        <td class="page-form-value"><s:textfield key="login.loginName"/></td>
	    </tr>
	    <tr>
	        <td class="page-form-label"><s:text name="login.password"/></td>
	        <td class="page-form-value"><s:password key="login.hashedPassword"/></td>
	    </tr>
	    <tr>
	        <td width="50%"></td>
	        <td width="50%"></td>
	    </tr>
	</table> 
    <table cellspacing="0" width="100%">
        <tr>
            <td class="page-form-centered"><s:submit value="%{getText('action.login')}"/><s:reset value="%{getText('action.reset')}"/></td>
        </tr>
    </table>
</s:form>

</body>
</html>
