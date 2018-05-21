<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script language="JavaScript" type="text/javascript">
    var allMenus = new Array(1);
    allMenus[0] = 'menu_admin';
</script>

<div id="adminMenu">
	<div class="section">
        <a id="menu_admin" class="headerOpen" href="#" onclick="toggleMenu('menu_admin');return false;">Admin</a>
        <ul id="menu_admin_body">
			<li class="sub-menu"><a href="<s:url value="/group/list.action"/>"><s:text name="group.model"/><s:text name="title.list"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/login/list.action"/>"><s:text name="login.model"/><s:text name="title.list"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/person/list.action"/>"><s:text name="person.model"/><s:text name="title.list"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/authorize/list.action"/>"><s:text name="authorize.model"/><s:text name="title.list"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/reference/list.action"/>"><s:text name="reference.model"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/content/list.action"/>"><s:text name="content.model"/><s:text name="title.list"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/mailTemplate/list.action"/>"><s:text name="mailTemplate.model"/><s:text name="title.list"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/profile/list.action"/>"><s:text name="profile.model"/><s:text name="title.list"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/order/list.action"/>"><s:text name="order.model"/><s:text name="title.list"/></a></li>
			<li class="sub-menu"><a href="<s:url value="/workflow/list.action"/>">Workflow</a></li>
        </ul>
        <script language="JavaScript" type="text/javascript">restoreMenu('menu_admin');</script>
    </div>
    <div class="section">
        <a class="headerOpen" title="Click to hide all menu items" id="hideAllMenu" onclick="hideAll();return false;" style="display:none;" href="#">Hide ALL</a>
        <a class="headerClosed" title="Click to show all menu items"  id="showAllMenu" onclick="showAll();return false;" href="#">Show All</a>
        <script language="JavaScript" type="text/javascript">restoreShowHideAllMenu();</script>
    </div>
</div>
