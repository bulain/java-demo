<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%> 
<html>
    <head>
        <link href="<c:url value='/web-static/bootstrap/css/bootstrap.css'/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/web-static/jquery-ui/jquery-ui.css'/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/web-static/static/css/dashboard.css'/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/web-static/static/css/globel.css'/>" rel="stylesheet" type="text/css"/>
        <link href="<c:url value='/web-static/static/css/main.css'/>" rel="stylesheet" type="text/css"/>
        <title><decorator:title default="sitemesh" /></title>
        <meta http-equiv="pragma" content="no-cache,no-store">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <decorator:head />
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top header" role="navigation">
          <div class="container-fluid">
            <div class="navbar-header">
            </div>
            <div class="navbar-collapse collapse">
              <ul class="nav navbar-nav navbar-right">
                <c:choose>
                <c:when test="${sessionScope['org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE'] == 'en_US'}">
                <li><a href="javascript:void(0);" onclick="javascript:requestLocale('zh_CN');">Chinese</a></li>
                </c:when>
                <c:otherwise>
                <li><a href="javascript:void(0);" onclick="javascript:requestLocale('en_US');">English</a></li>
                </c:otherwise>
                </c:choose>
              </ul>
            </div>
          </div>
        </div>
        <div class="container-fluid">
          <div class="row">
            <div class="col-sm-3 col-md-2 sidebar">
              <ul class="nav nav-sidebar">
                <li class="active"><a href="<c:url value='/index/list'/>">Index</a></li>
                <li><a href="#">Test01</a></li>
                <li><a href="#">Test02</a></li>
                <li><a href="#">Test03</a></li>
              </ul>
            </div>
            <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                <decorator:body />
            </div>
          </div>
          <div id="footer"><center>@Power by Bulain</center></div>
        </div>
        <script src="<c:url value='/web-static/jquery/jquery.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/bootstrap/js/bootstrap.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/jquery-ui/jquery-ui.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/jquery-validation/jquery.validate.js'/>" type="text/javascript"></script>
        <script src="<c:url value='/web-static/static/js/public.js'/>" type="text/javascript"></script>
    </body>
</html>