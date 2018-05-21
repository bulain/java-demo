<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%> 
<html>
    <head>
        <link href="<c:url value='/web-static/jquery/css/validate/jquery.validate.css'/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/web-static/jquery/css/ui-lightness/jquery-ui-1.8.4.custom.css'/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/web-static/jquery/css/ui-lightness/jquery-ui-timepicker.css'/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/web-static/static/css/globel.css'/>" rel="stylesheet" type="text/css"/>
        <script src="<c:url value='/web-static/jquery/js/jquery-1.4.2.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/jquery/js/jquery.validate.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/jquery/js/jquery.validate.extends.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/jquery/js/jquery-ui-1.8.4.custom.min.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/jquery/js/jquery.cookie.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/static/js/jquery.cookie.extend.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/static/js/jquery.cookie.menu.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/jquery/js/jquery.ui.timepicker.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/static/js/public.js'/>" type="text/javascript"></script>
        <title><decorator:title default="sitemesh" /></title>
        <meta http-equiv="pragma" content="no-cache,no-store">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <decorator:head />
    </head>
    <body>
        <div id="container">
          <div id="header">
          </div>
          <div id="login"><span><a href="javascript:void(0);" onclick="javascript:requestLocale('en_US');">English</a>|<a href="javascript:void(0);" onclick="javascript:requestLocale('zh_CN');">Chinese</a></span></div>
          <div id="main">
            <div id="sidebar">
<div>
    <div class="section">
        <a id="a" class="header-open" href="javascript:void(0);">Admins</a>
        <ul id="a_ul">
            <li class="sub-menu"><a href="<c:url value='/person/list'/>">Person</a></li>
        </ul>
    </div>
    <div class="section">
        <a id="hideAllMenu" class="header-open" href="javascript:void(0);">Hide All</a>
        <a id="showAllMenu" class="header-closed" href="javascript:void(0);">Show All</a>
    </div>
</div>
<script language="JavaScript" type="text/javascript">
    $(document).ready(function(){
        var menus = ['a'];
        jQueryMenu.cookieMenu(menus, {path:'/springmvc-demo', expirec:7});
    });
</script>
            </div>
            <div id="content"><decorator:body /></div>
          </div>
          <hr/>
          <div id="footer"><center>Power by Bulain</center></div>
        </div>
    </body>
</html>