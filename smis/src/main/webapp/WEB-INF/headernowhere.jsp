<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>${param.title}</title>
</head>
<body>
	<div id="layout">
		<div id="banner">
			<div class="bannerlogo"></div>
			<div class="text_header">Home Monitoring and Notification System</div>
			<div class="right" style="text-align: right;">
				<c:if test="${authenticatedUser !=null}">
					<b><a href="listuser.ap?page=profile&&id=${authenticatedUser.id}"><button>Profile
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
