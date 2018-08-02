window.onload = function() {
	document.getElementById("listEmployees").addEventListener("click", getEmployees);
};
function getEmployees() {
	var table = document.getElementById("employeeTable");
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			var ajaxObject = JSON.parse(xhttp.responseText);
			createRows(ajaxObject);
		}
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/listEmployees.ajax", true);
	xhttp.send();
}
function createRows(ajaxObject) {
	var columnNames = [];
	columnNames = Object.keys(ajaxObject[0])
	for (var i = 0; i < ajaxObject.length; i++) {
		var row = table.insertRow(-1);
		columnNames.forEach(function(columnName) {
			var cell = row.insertCell(-1);
			cell.innerHTML = ajaxObject[i][columnName];
		});
	};
}