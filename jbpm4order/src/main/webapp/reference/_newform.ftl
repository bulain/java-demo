<div style="color:red;">
	<@s.fielderror />
	<@s.actionmessage/>
	<@s.actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4">Referance Information</td>
    </tr>
    <tr>
        <td class="page-form-label">Name</td>
        <td class="page-form-value"><input type="text" name="referanceBean.name" value="${referanceBean.name!''}"/></td>
        <td class="page-form-label">Code</td>
        <td class="page-form-value"><input type="text" name="referanceBean.code" value="${referanceBean.code!''}"/></td>
    </tr>
    <tr>
        <td class="page-form-label">Catagory</td>
        <td class="page-form-value"><input type="text" name="referanceBean.catagory" value="${referanceBean.catagory!''}"/></td>
        <td/>
        <td/>
    </tr>
    <tr>
        <td class="page-form-label">English</td>
        <td class="page-form-value" colspan="3"><textarea name="referanceBean.textEN" rows="4" cols="50">${referanceBean.textEN!''}</textarea></td>
    </tr>
    <tr>
        <td class="page-form-label">Chinese</td>
        <td class="page-form-value" colspan="3"><textarea name="referanceBean.textCN" rows="4" cols="50">${referanceBean.textCN!''}</textarea></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table> 