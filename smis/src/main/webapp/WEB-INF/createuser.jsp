<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<jsp:include page="headeradmin.jsp"><jsp:param
		name="title" value="Create User" />
</jsp:include>
<div id="middle">
<fieldset>
		<legend>User registration</legend>
	<c:if test="${authenticatedUser !=null && authenticatedUser.userRole eq 'ADMINISTRATOR'}">
		<h2 style="text-align: left;">
			<b>Enter User Details</b>
		</h2>
		<c:if test="${message != null}">
			<fieldset>
				<h3>
					<span style="color: red"> ${message}</span>
				</h3>
			</fieldset>
		</c:if>
		<form action="CreateUserServlet?page=createuser" method="post">
			<table>
				<tr>
					<td align="right"><b><t:field mandatory="yes"
								text="Username"></t:field></b></td>
					<td align="left"><input type="text" size="25" maxlength="50"
						name="username" id="username" /></td>
				</tr>
				<tr>
					<td align="right"><b><t:field mandatory="yes"
								text="Password"></t:field></b></td>
					<td align="left"><input type="password" size="25"
						maxlength="50" name="password" id="password" /></td>
				</tr>
				<tr>
					<td align="right"><b>User Full Name</b></td>
					<td align="left"><input type="text" size="50" maxlength="50"
						name="userfullname" id="userfullname" /></td>
				</tr>
				<tr>
					<td align="right"><b>Email</b></td>
					<td align="left"><input type="text" size="50" maxlength="50"
						name="email" id="email" /></td>
				</tr>

				<tr>
					<td align="right"><b>Department</b></td>
					<td><select name="organization" id="organization">
							<option selected="selected" value="-1">Select
								Department</option>
							<c:forEach items="${organizations}" var="organization"
								varStatus="organizationstatus">
								<option value="${organization.id}">
									<c:out value="${organization.name}, ${organization.code}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right"><b>Role</b></td>
					<td><select name="userRole" id="userRole">
							<option value="">Select Role</option>
							<c:forEach items="${userRoles}" var="userrole"
								varStatus="userrolestatus">
								<option value="${userrole}">
									<c:out value="${userrole.getRoleDescription()}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right"><b>Approval Status</b></td>
					<td><select name="status" id="status">
							<option value="">Select Status</option>
							<c:forEach items="${approvalStatus}" var="approv"
								varStatus="approvstatus">
								<option value="${approv}">
									<c:out value="${approv.getApprovalStatusDescription()}" />
								</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td align="right">&nbsp;</td>
					<td align="left"><input type="submit" name="saveDataUser"
						value="Create User" /></td>
				</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${authenticatedUser ==null}">
		<h2 style="text-align: left;">
			<b>Enter Your Details</b>
		</h2>
		<c:if test="${message != null}">
			<fieldset>
				<h3>
					<span style="color: red"> ${message}</span>
				</h3>
			</fieldset>
		</c:if>
		<form action="CreateUserServlet?page=registration" method="post">
			<table>
				<tr>
					<td align="right"><b><t:field mandatory="yes"
								text="Username"></t:field></b></td>
					<td align="left"><input type="text" size="12" maxlength="50"
						name="username" id="username" /></td>
				</tr>
				<tr>
					<td align="right"><b><t:field mandatory="yes"
								text="Password"></t:field></b></td>
					<td align="left"><input type="password" size="12"
						maxlength="50" name="password" id="password" /></td>
				</tr>
				<tr>
					<td align="right"><b>User Full Name</b></td>
					<td align="left"><input type="text" size="50" maxlength="50"
						name="userfullname" id="userfullname" /></td>
				</tr>
				<tr>
					<td align="right"><b>Email</b></td>
					<td align="left"><input type="text" size="50" maxlength="50"
						name="email" id="email" /></td>
				</tr>

				<tr>
					<td align="right">&nbsp;</td>
					<td align="left"><input type="submit" name="saveDataUserReg"
						value="Create User" /></td>
				</tr>
			</table>
		</form>
	</c:if>
	</fieldset>
</div>
<%@ include file="footer.jsp"%>