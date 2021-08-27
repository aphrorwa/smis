var xmlHttp;
function createXMLHttpRequest() {
	if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}

function doRequestUsingGETProvince(value) {
	document.getElementById('district').options.length = 0;
	document.getElementById('sector').options.length = 0;
	createXMLHttpRequest();
	queryString = "displaydistrict.ap";
	queryString = queryString + "?province=" + value;
	xmlHttp.onreadystatechange = handleStateChange;
	xmlHttp.open("GET", queryString, true);
	xmlHttp.send(null);
}
function parseResults() {
	var responseText = document.createTextNode(xmlHttp.responseText);
	var returnElements = xmlHttp.responseText.split("||");
	// Process each of the elements
	for ( var i = 0; i < returnElements.length; i++) {

		if (returnElements[i] != "") {
			valueLabelPair = returnElements[i].split(";");
			document.getElementById('district').options.length = returnElements.length;
			document.getElementById('district').options[i] = new Option(
					valueLabelPair[0], valueLabelPair[1]);
		}
	}
}
function handleStateChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			parseResults();
		}
	}
}
function doRequestUsingGETDistrict(value) {
	createXMLHttpRequest();
	queryString = "displaysector.ap";
	queryString = queryString + "?district=" + value;
	xmlHttp.onreadystatechange = handleDistrictStateChange;
	xmlHttp.open("GET", queryString, true);
	xmlHttp.send(null);
}

function handleDistrictStateChange() {
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {
			parseResultsDistricts();
		}
	}
}

function parseResultsDistricts() {
	var responseText = document.createTextNode(xmlHttp.responseText);
	var returnElements = xmlHttp.responseText.split("||");
	// Process each of the elements
	for ( var i = 0; i < returnElements.length; i++) {

		if (returnElements[i] != "") {
			valueLabelPair = returnElements[i].split(";");
			document.getElementById('sector').options.length = returnElements.length;
			document.getElementById('sector').options[i] = new Option(
					valueLabelPair[0], valueLabelPair[1]);
		}
	}
}

function autoTab(startPoint, endPoint) {
	if (startPoint.getAttribute
			&& startPoint.value.length == startPoint.getAttribute("maxlength"))
		endPoint.focus();
}
function checkNumber(elem) {
	var x = elem;
	var elem = /(^\d+$)|(^\d+\.\d+$)/;
	if (elem.test(x))
		testResult = true;

	else {
		alert("Please enter a valid number for this field.");
		elem.focus();
		elem.value = "";
		testResult = false;
	}

	return (testResult);
}