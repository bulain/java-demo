<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <title><s:text name="group.model"/><s:text name="title.show"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="group.model"/><s:text name="title.show"/></td>
    </tr>
</table>
<br/>

<s:include value="_view.jsp"/>

<br/>
<s:form action="updateLogin" method="post" theme="simple">
<table class="page-form" cellspacing="0" width="100%">
	<tr>
        <td class="page-form-title" colspan="4"><s:text name="group.model"/><s:text name="title.info"/></td>
        <s:hidden name="id"/>
        <s:token/>
    </tr>
    <tr>
    	<td width="45%" align="right">Have assgin to Group</td>
    	<td width="10px" valign="middle" align="center"></td>
    	<td width="45%">Have Not assgin to Group</td>
    </tr>
    <tr>
        <td align="right">
        	<s:select id="listLoginId" name="listLoginId" list="listLoginDist" listKey="id" listValue="loginName" value="listLoginId" multiple="true" size="15"/>
        </td>
        <td valign="middle" align="center">
        	<input type="button" id="moveRightAll" name="moveRightAll" value="&gt;&gt;" style="width:60px"/><br/><br/>
        	<input type="button" id="moveRight" name="moveRight" value="&gt;" style="width:60px"/><br/><br/>
        	<input type="button" id="moveLeft" name="moveLeft" value="&lt;" style="width:60px"/><br/><br/>
        	<input type="button" id="moveLeftAll" name="moveLeftAll" value="&lt;&lt;" style="width:60px"/>
        </td>
        <td>
        	<select id="listLoginSrc" multiple="multiple" size="15">
        		<s:iterator value="listLoginSrc" status="status">
        			<option value="${id}">${loginName}</option>
        		</s:iterator>
        	</select>
        </td>
    </tr>
</table>
<table cellspacing="0" width="100%">
    <tr>
        <td class="page-form-centered"><s:submit value="%{getText('action.update')}"/></td>
    </tr>
</table>
</s:form>

<script type="text/javascript">
// <![CDATA[
$(document).ready(function() {

$('#moveRightAll').click(function(){
	var elms = $('#listLoginId option');
	elms.clone().appendTo('#listLoginSrc');
	elms.remove();
});
$('#moveRight').click(function(){
	var elms = $('#listLoginId option:selected');
	elms.clone().appendTo('#listLoginSrc');
	elms.remove();
});
$('#moveLeft').click(function(){
	var elms = $('#listLoginSrc option:selected');
	elms.clone().appendTo('#listLoginId');
	elms.remove();
});
$('#moveLeftAll').click(function(){
	var elms = $('#listLoginSrc option');
	elms.clone().appendTo('#listLoginId');
	elms.remove();
});
$(':submit').click(function(){
	$('#listLoginId option').each(function (index, domEle){
		domEle.selected = 'selected';
	});
});
		
});
//]]>
</script>

<table class="footer-form" cellspacing="0" width="100%">
    <tr>
        <td class="footer-form-left"><s:url id="url" action="list" /><a href="<s:property value="#url"/>"><s:text name="action.back"/></a></td>
    </tr>
</table>
</body>
</html>
