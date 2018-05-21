<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
	<link href="<s:url value='/public/uploadify/uploadify.css'/>" rel="stylesheet" type="text/css"/>
	<script src="<s:url value='/public/uploadify/swfobject.js'/>" type="text/javascript"></script>
	<script src="<s:url value='/public/uploadify/jquery.uploadify.v2.1.0.js'/>" type="text/javascript"></script>
    <title><s:text name="content.model"/><s:text name="title.list"/></title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title"><s:text name="content.model"/><s:text name="title.list"/></td>
    </tr>
</table>
<br/>

<s:form action="bulkList" method="post" id="search" namespace="/content" theme="simple">
    <table class="page-form" cellspacing="0" width="100%">
        <tr>
            <td class="page-form-title" colspan="4"><s:text name="title.search"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><s:text name="content.fileName"/></td>
            <td class="page-form-value"><input type="text" name="search.fileName" value="<s:property value="search.fileName"/>"/></td>
            <td class="page-form-label"><s:text name="content.contentType"/></td>
            <td class="page-form-value"><input type="text" name="search.contentType" value="<s:property value="search.contentType"/>"/></td>
        </tr>
        <tr>
        	<td class="page-form-label"><s:text name="content.refName"/></td>
            <td class="page-form-value"><input type="text" name="search.refName" value="<s:property value="search.refName"/>"/></td>
            <td class="page-form-label"><s:text name="content.refId"/></td>
            <td class="page-form-value"><input type="text" name="search.refId" value="<s:property value="search.refId"/>"/></td>
        </tr>
        <tr>
            <td class="page-form-label"><s:text name="content.catagory"/></td>
            <td class="page-form-value"><input type="text" name="search.catagory" value="<s:property value="search.catagory"/>"/></td>
            <td class="page-form-label"><s:text name="content.lang"/></td>
            <td class="page-form-value">
            	<s:select name="search.lang" list="listReferenceLang" listKey="code" listValue="text" value="search.lang" />
            </td>
        </tr>
        <tr>
            <td width="20%"/>
            <td width="30%"/>
            <td width="20%"/>
            <td width="30%"/>
        </tr>
        <tr>
            <td colspan="4">
                <table cellspacing="0" width="100%">
                    <tr>
                        <td width="40%"><s:url id="url" action="new" /><a href="<s:property value="#url"/>"><s:text name="action.new"/><s:text name="content.model"/></a></td>
                        <td class="page-form-centered"><s:submit value="%{getText('action.search')}" /></td>
                        <td width="40%" />
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</s:form>
<br/>

<s:form action="ajaxUpload" method="post" id="ajaxUpload" namespace="/content" theme="simple">
<table class="page-form" cellspacing="0" width="100%">
	<tr>
   		<td class="page-form-label"><s:text name="content.refName"/></td>
        <td class="page-form-value"><s:textfield key="content.refName"/></td>
    	<td class="page-form-label"><s:text name="content.refId"/></td>
        <td class="page-form-value"><s:textfield key="content.refId"/></td>
    </tr>
    <tr>
   		<td class="page-form-label"><s:text name="content.catagory"/></td>
        <td class="page-form-value"><s:textfield key="content.catagory"/></td>
    	<td class="page-form-label"><s:text name="content.lang"/></td>
        <td class="page-form-value">
        	<s:select name="content.lang" list="listReferenceLang" listKey="code" listValue="text" value="content.lang" />
        </td>
    </tr>
	<tr>
		<td class="page-form-label"><s:text name="content.file"/></td>
		<td class="page-form-value" colspan="3"><input id="blob" name="blob" type="file" /></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table> 
</s:form>

<br/>
<div id="ajaxList">
	<s:include value="_list.jsp"/>
</div>


<script type="text/javascript">
// <![CDATA[
$(document).ready(function() {
	$('#blob').uploadify({
	uploader  : "<s:url value='/public/uploadify/uploadify.swf'/>",
	script    : "<s:url action='ajaxUpload'/>",
	cancelImg : "<s:url value='/public/uploadify/cancel.png'/>",
	auto      : true,
	multi     : true,
	sizeLimit : 20971520,
	scriptData  : {'format':'json', 'sid':'${id}'},
	fileDataName: 'blob',
	onAllComplete: function() {
		$('#ajaxList').load('<s:url action="ajaxList"></s:url>');
	}
	});
	$('#content_refName').change(function(){
		$('#blob').uploadifySettings('scriptData', {'content.refName':$(this).val()});
	});
	$('#content_refId').change(function(){
		$('#blob').uploadifySettings('scriptData', {'content.refId':$(this).val()});
	});
	$('#content_catagory').change(function(){
		$('#blob').uploadifySettings('scriptData', {'content.catagory':$(this).val()});
	});
	$('#content_lang').change(function(){
		$('#blob').uploadifySettings('scriptData', {'content.lang':$(this).val()});
	});
});
// ]]>
</script>

</body>
</html>
