window.onload = function() {
	document.getElementById("FNSubmit").addEventListener("click",
			updateEmployee);
};
function updateEmployee() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			var ajaxObject = JSON.parse(xhttp.responseText);
			createRows(ajaxObject);
		};
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/updateEmployeeFN.ajax",
			true);
	xhttp.send();
    var x = document.getElementById("FNdiv");
    x.style.display = "none";
}
