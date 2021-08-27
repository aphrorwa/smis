<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="headeradmin.jsp"><jsp:param
		name="title" value="Edit User" />
</jsp:include>
<div id="middle">
	<c:if test="${authenticatedUser !=null}">
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
		<form action="CreateUserServlet?page=edituser" method="post">
			<table>
				<tr>
					<td align="left" colspan="2"><input type="text" size="25"
						maxlength="50" name="userid" id="userid" value="${usr.id}"
						hidden="hidden" /></td>
				</tr>
				<tr>
					<td align="right"><b>Username</b></td>
					<td align="left"><input type="text" size="25" maxlength="50"
						name="username" id="username" value="${usr.username}" /></td>
				</tr>
				<c:if
					test="${authenticatedUser !=null && (!authenticatedUser.userRole eq 'ADMINISTRATOR')}">
					<tr>
						<td align="right"><b>Existing password</b></td>
						<td align="left"><input type="password" size="25"
							maxlength="50" name="expassword" id="expassword" /></td>
					</tr>
				</c:if>
				<tr>
					<td align="right"><b>New password</b></td>
					<td align="left"><input type="password" size="25"
						maxlength="50" name="password" id="password" /></td>
				</tr>
				<tr>
					<td align="right"><b>User Full Name</b></td>
					<td align="left"><input type="text" size="50" maxlength="50"
						name="userfullname" id="userfullname" value="${usr.fullName}" /></td>
				</tr>
				<tr>
					<td align="right"><b>Email</b></td>
					<td align="left"><input type="text" size="50" maxlength="50"
						name="email" id="email" value="${usr.email}" /></td>
				</tr>
				<c:if
					test="${authenticatedUser !=null && (authenticatedUser.userRole eq 'ADMINISTRATOR')}">
					<tr>
						<td align="right"><b>Organization</b></td>
						<td align="left"><input type="text" size="25" maxlength="50"
							name="organization" id="organization"
							value="${usr.organization.name}" readonly="readonly" /></td>

					</tr>
					<tr>
						<td align="right"><b>Station</b></td>
						<td><select name="organization" id="organization">
								<option selected="selected" value="-1">Select
									Station if applicable</option>
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
						<td align="left"><input type="text" size="25" maxlength="50"
							name="userRole" id="userRole"
							value="${usr.userRole.getRoleDescription()}" readonly="readonly" /></td>
					</tr>

					<tr>
						<td align="right"><b>Approval Status</b></td>
						<td align="left"><input type="text" size="25" maxlength="50"
							name="status" id="status"
							value="${usr.status.getApprovalStatusDescription()}"
							readonly="readonly" /></td>
					</tr>
				</c:if>
				<tr>
					<td align="right">&nbsp;</td>
					<td align="left"><input type="submit"
						name="saveDataUserChanges" value="Save changes" /></td>
				</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${authenticatedUser ==null}">
		<%@ include file="loginafterlogout.jsp"%>
	</c:if>
</div>
<%@ include file="footer.jsp"%>