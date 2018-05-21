<html>
<head>
	<link href="<@s.url value='/public/stylesheets/globel.css'/>" rel="stylesheet" type="text/css"/>
    <title>Referance</title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">New Referance</td>
    </tr>
</table>
<br/>

<@s.form action="create">
    <#include "_newform.ftl"/>
    <table cellspacing="0" width="100%">
        <tr>
            <td class="page-form-centered"><input type="submit" value="Create"/></td>
        </tr>
    </table>
</@s.form>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><@s.url id='url' action='list'/><a href="${url}">Back</a></td>
    </tr>
</table>
</body>
</html>
