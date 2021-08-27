<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="headeradmin.jsp">
	<jsp:param value="Home Admin" name="title" /></jsp:include>
<div id="middle" align="center">
	<fieldset>
		<legend>Sensor output from Waziup cloud</legend>
	<script>
    Waziup.ApiClient.instance.basePath = 'http://api.waziup.io/api/v1'
    var api = new Waziup.SensorsApi()
    api.getMeasurement("waziup-Aceiot-Rwanda", "Aceiot-Rwanda_Sensor203", "CM").then((meas) => {
      document.getElementById("sensor_value").innerHTML = "<h1>Last attempt alert at the distance: " + meas.last_value +" cm</h1>";
    })
  </script>
	 <div id="sensor_value"><h1>Loading from WAZIUP cloud...</h1></div>
	 </fieldset>
</div>
<%@ include file="footer.jsp"%>