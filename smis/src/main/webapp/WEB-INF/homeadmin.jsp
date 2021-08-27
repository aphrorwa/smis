<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="headeradmin.jsp">
	<jsp:param value="Home Admin" name="title" /></jsp:include>
<div id="middle" align="center">
	<script type="text/javascript" src="js/Waziup.js"></script>

	<img src="images/st7.png" style="width: 620px; height: 400px" />

	
	<script>
    Waziup.ApiClient.instance.basePath = 'http://api.waziup.io/api/v1'
    var api = new Waziup.SensorsApi()
    api.getMeasurement("waziup-Aceiot-Rwanda", "Aceiot-Rwanda_Sensor203", "CM").then((meas) => {
      document.getElementById("sensor_value").innerHTML = "My home temperature is: " + meas.last_value;
    })
  </script>
	
	 <div id="sensor_value">Loading...</div>
	
</div>
<%@ include file="footer.jsp"%>