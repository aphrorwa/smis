<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" href="cssfile.css" type="text/css" />
<link rel="stylesheet" href="css/displaytag.css" type="text/css" />
<link rel="stylesheet" href="css/screen.css" type="text/css" />
<link rel="stylesheet" href="css/site.css" type="text/css" />
<title>Departments</title>
</head>
<body>
	<div id="layout">
		<div id="banner">
			<div class="bannerlogo"></div>
			<div class="text_header">School Management System</div>
			<div class="right" style="text-align: right;">
				<c:if test="${authenticatedUser !=null}">
					<b><a
						href="listuser.ap?page=profile&&id=${authenticatedUser.id}"><button>Profile
							</button></a> | <img src="icons/user.png" /> <font color="#ffffff">${authenticatedUser.fullName}</font>
						| <a href="login.ap?logout=logout"><font color="#ffffff">Logout</font></a>
					</b>
				</c:if>
				<c:if test="${authenticatedUser ==null}">
					<div class="menu" align="left">
						| <a href="login.ap?"><font color="#ffffff">Login</font></a> |
					</div>
				</c:if>
			</div>
		</div>
		<div>
			<%@ include file="menu.jsp"%>
		</div>
		<div id="middle">
			<c:if test="${authenticatedUser !=null}">
				<div class="options">
					<a href="createorganization.ap?page=createorganization"><img
						src="icons/add.png" /> Create New Department</a>
				</div>

				<div class="search">
					<form action="listorganization.ap" method="get">
						<table>
							<tr>
								<td style="color: #004216;">Enter Department Code</td>
								<td><input type="text" name="code" id="code" /></td>
								<td><input type='submit' name="organizationsearch"
									value='search' /></td>
							</tr>
						</table>
					</form>
				</div>
				<hr />
				<display:table name="sessionScope.organizations" pagesize="25"
					id="row" export="true" sort="list" requestURI="listorganization.ap"
					varTotals="totals">
					<display:column property="code" title="Code" sortable="true"
						headerClass="sortable" />
					<display:column property="name" title="Name" sortable="true"
						headerClass="sortable" />
					<display:column property="parent.name" title="Parent Department"
						sortable="true" headerClass="sortable" />
				</display:table>

			</c:if>
			<c:if test="${authenticatedUser ==null}">
				<%@ include file="loginafterlogout.jsp"%>
			</c:if>
		</div>
		<%@ include file="footer.jsp"%>