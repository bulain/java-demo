<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%> 
<html>
	<head>
		<link href="<s:url value='/public/jquery/css/validate/jquery.validate.css'/>" rel="stylesheet" type="text/css"/>
		<link href="<s:url value='/public/jquery/css/ui-lightness/jquery-ui-1.8.4.custom.css'/>" rel="stylesheet" type="text/css"/>
		<link href="<s:url value='/public/jquery/css/ui-lightness/jquery-ui-timepicker.css'/>" rel="stylesheet" type="text/css"/>
		<link href="<s:url value='/public/stylesheets/globel.css'/>" rel="stylesheet" type="text/css"/>
		<script src="<s:url value='/public/jquery/js/jquery-1.4.2.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/public/jquery/js/jquery.validate.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/public/jquery/js/jquery.validate.extends.js'/>" type="text/javascript"></script>
		<s:set name="locale" value="#session['WW_TRANS_I18N_LOCALE']" />
        <script src="<s:url value='/public/jquery/js/localization/messages_%{#locale}.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/public/jquery/js/jquery-ui-1.8.4.custom.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/public/jquery/js/jquery.ui.timepicker.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/public/javascripts/public.js'/>" type="text/javascript"></script>
		<title><decorator:title default="sitemesh" /></title>
		<decorator:head />
	</head>
	<body>
		<div id="container">
		  <div id="header">
		  </div>
		  <div id="login"><span><a href="?request_locale=en_US">English</a>|<a href="?request_locale=zh_CN">Chinese</a></span></div>
		  <div id="main">
		    <div id="sidebar"></div>
		    <div id="content"><decorator:body /></div>
		  </div>
		  <hr/>
		  <div id="footer"><center>Power by Bulain</center></div>
		</div>
	</body>
</html>