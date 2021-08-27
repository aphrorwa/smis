<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="headeradmin.jsp"><jsp:param
		name="title" value="Create Station" />
</jsp:include>
<div id="middle">
	<div class="summary"></div>
	<div class="search">
		<form action="organization.ap?page=search?" method="get">
			<table>
				<tr>
					<td style="color: #004216;">Station</td>
					<td><input type="text" name="code" id="code" /></td>
					<td><input type='submit' name="organizationsearch"
						value='search' /></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<div id="tabs-wrapper" class="clear-block">
	<ul class="tabs primary">
		<li class=""><a
			href="listorganization.ap?page=organizations&&action=list">Station</a></li>
		<li class="active"><a
			href="createorganization.ap?page=createorganization"><img
				src="icons/add.png" /> New Station</a></li>
		<li class=""><a href="#">General Report</a></li>
	</ul>
	<span class="clear"></span>
</div>
<div id="middle">
	<c:if test="${authenticatedUser !=null}">
		<h2 style="text-align: left;">
			<b>Enter Station Details</b>
		</h2>
		<c:if test="${message != null}">
			<fieldset>
				<h3>
					<span style="color: red"> ${message}</span>
				</h3>
			</fieldset>
		</c:if>
		<form action="createorganization.ap?page=createorganization"
			method="post">

			<fieldset>
				<legend>
					Station
				</legend>
				<table style="border: 0;">
					<tr>

						<td align="right"><b>Station name</b></td>
						<td align="left" colspan="3"><input type="text" size="60"
							maxlength="50" name="name" id="name" /></td>

						<td align="left"><input type="text" name="user" id="user"
							value="${authenticatedUser.id}" hidden="hidden" /></td>
					</tr>
					<tr>

						<td align="right"><b>Initial</b></td>
						<td align="left" colspan="3"><input type="text" size="30"
							maxlength="50" name="code" id="code" /></td>
						<td></td>
					</tr>

					<tr>
						<td align="right"><b>Parent station</b></td>
						<td><select name="parent" id="parent">
								<option selected="selected" value="-1">Select Parent
									Station</option>
								<c:forEach items="${parents}" var="parent"
									varStatus="parentstatus">
									<option value="${parent.id}">
										<c:out value="${parent.name}, ${parent.code}" />
									</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td align="right">&nbsp;</td>
						<td align="left"><input type="submit"
							name="saveDataOrganization" value="Create Station" /></td>
					</tr>

				</table>
			</fieldset>
		</form>
	</c:if>
	<c:if test="${authenticatedUser ==null}">
		<%@ include file="loginafterlogout.jsp"%>
	</c:if>
</div>
<%@ include file="footer.jsp"%>