<html>
<head>
	<link href="<@s.url value='/public/stylesheets/globel.css'/>" rel="stylesheet" type="text/css"/>
    <title>Referance</title>
</head>
<body>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title">Referance List</td>
    </tr>
</table>
<br/>

<@s.form action="list">
    <table class="page-form" cellspacing="0" width="100%">
        <tr>
            <td class="page-form-title" colspan="4">Search</td>
        </tr>
        <tr>
            <td class="page-form-label">Name</td>
            <td class="page-form-value"><input type="text" name="search.name" value="${search.name!''}"/></td>
            <td class="page-form-label">Code</td>
            <td class="page-form-value"><input type="text" name="search.code" value="${search.code!''}"/></td>
        </tr>
        <tr>
            <td class="page-form-label">Lang</td>
            <td class="page-form-value"><input type="text" name="search.lang" value="${search.lang!''}"/></td>
            <td class="page-form-label">Catagory</td>
            <td class="page-form-value"><input type="text" name="search.catagory" value="${search.catagory!''}"/></td>
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
                        <td width="40%"><@s.url id="url" action='new'/><a href="${url}">Add New Referance</a></td>
                        <td class="page-form-centered"><input type="submit" value="Search"/></td>
                        <td width="40%" />
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</@s.form>
<br/>


<table id="list" class="list-table" cellspacing="0" width="100%" bperson="1">
    <tr class="list-table-header">
        <th>Name</th>
        <th>Code</th>
        <th>Text</th>
        <th>Lang</th>
        <th>Catagory</th>
        <th>Updated By</th>
        <th>Updated At</th>
        <th class="minNoWrap">Action</th>
    </tr>
    <#list listReferance as item>
        <tr class="<#if item_index%2==1>list-line-even<#else>list-line-odd</#if>" >
            <td class="noWrap">${item.name}</td>
            <td class="noWrap">${item.code}</td>
            <td class="noWrap">${item.text}</td>
            <td class="noWrap">${item.lang}</td>
            <td class="noWrap">${item.catagory}</td>
            <td class="noWrap">${item.updatedBy}</td>
            <td class="noWrap">${item.updatedAt?date}</td>
            <td class="minNoWrap">
        	<@s.url id='url' action='show'><@s.param name='id' value='${item.id}'/></@s.url><a href="${url}">Show</a>
        	|<@s.url id='url' action='edit'><@s.param name='id' value='${item.id}'/></@s.url><a href="${url}">Edit</a>
        	|<@s.url id='url' action='destroy'><@s.param name='id' value='${item.id}'/></@s.url><a href="${url}">Destroy</a>
        	</td>
        </tr>
    </#list>
</table>

<p>&nbsp;</p>
<#assign p=JspTaglibs["/WEB-INF/page-tags.tld"] />
<@p.link page='${page.page}' totalPage='${page.totalPage}'/>

</body>
</html>
