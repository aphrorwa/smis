<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="headeradmin.jsp">
	<jsp:param value="Home Admin" name="title" /></jsp:include>
<div id="middle" align="center">
	
	
	 <div id="mapid" style="height:600px"></div>
  <!--script src="https://cdn.jsdelivr.net/npm/waziup-js@0.0.18/lib/Waziup.js"></script-->
  <!--script src="./Waziup.js"></script-->

  <script>
      var mymap = L.map('mapid').setView([-1.97, 30.1], 13);
      L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
         attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
         maxZoom: 18,
         id: 'mapbox.streets',
         accessToken: 'pk.eyJ1IjoiY2R1cG9udDIiLCJhIjoiY2pnZTVkZ2xsMGxyZTJ4cjA5dDZ4cjNneSJ9.NJT7CULfcY0mjeavffR_vg'
      }).addTo(mymap);
    
    Waziup.ApiClient.instance.basePath = 'http://api.waziup.io/api/v1'
    var api = new Waziup.SensorsApi()
    var markers = []
    api.getSensor("waziup", "MySensor15").then((sensor) =>{
      console.log("data" + JSON.stringify(sensor))
      L.marker([sensor.location.latitude, sensor.location.longitude]).addTo(mymap);
    })
  </script>
	
</div>
<%@ include file="footer.jsp"%>