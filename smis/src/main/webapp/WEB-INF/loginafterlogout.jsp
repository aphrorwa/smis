<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<fieldset style="width: 100px;">
	<legend>User Login Form</legend>
	<form action="login.ap" method="post">
		<table>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td align="right"><b><t:field mandatory="yes"
							text="Username"></t:field></b></td>
				<td align="left"><input type="text" size="30" maxlength="50"
					name="username" id="username" /></td>
			</tr>
			<tr>
				<td align="right"><b><t:field mandatory="yes"
							text="Password"></t:field></b></td>
				<td align="left"><input type="password" size="30"
					maxlength="50" name="password" id="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
			</tr>
			<tr bgcolor="gray">
				<td align="right">&nbsp;</td>
				<td align="left"><input type="submit" value="Login" /></td>
			</tr>
		</table>
	</form>
</fieldset>