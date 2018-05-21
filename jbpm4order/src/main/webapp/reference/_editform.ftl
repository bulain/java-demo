<div style="color:red;">
	<@s.fielderror />
	<@s.actionmessage/>
	<@s.actionerror/>
</div>

<table class="page-form" cellspacing="0" width="100%">
    <tr>
        <td class="page-form-title" colspan="4">Referance Information</td>
        <input type="hidden" name="referance.id" value="${referance.id!''}"/>
    </tr>
    <tr>
        <td class="page-form-label">Name</td>
        <td class="page-form-value"><input type="text" name="referance.name" value="${referance.name!''}"/></td>
        <td class="page-form-label">Code</td>
        <td class="page-form-value"><input type="text" name="referance.code" value="${referance.code!''}"/></td>
    </tr>
    <tr>
        <td class="page-form-label">Lang</td>
        <td class="page-form-value"><input type="text" name="referance.lang" value="${referance.lang!''}"/></td>
        <td class="page-form-label">Catagory</td>
        <td class="page-form-value"><input type="text" name="referance.catagory" value="${referance.catagory!''}"/></td>
    </tr>
    <tr>
        <td class="page-form-label">Text</td>
        <td class="page-form-value" colspan="3"><textarea name="referance.text" rows="4" cols="50">${referance.text!''}</textarea></td>
    </tr>
    <tr>
        <td width="20%"/>
        <td width="30%"/>
        <td width="20%"/>
        <td width="30%"/>
    </tr>
</table> 