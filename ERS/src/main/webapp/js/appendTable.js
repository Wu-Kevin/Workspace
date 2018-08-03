window.onload = function() {
	document.getElementById("listEmployees").addEventListener("click",
			getEmployees);
	document.getElementById("FNsubmit").addEventListener("click",
			changeFN);
	document.getElementById("LNsubmit").addEventListener("click",
			changeLN);
	document.getElementById("EMsubmit").addEventListener("click",
			changeEM);
	document.getElementById("PWsubmit").addEventListener("click",
			changePW);
};
function getEmployees() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			var ajaxObject = JSON.parse(xhttp.responseText);
			createRows(ajaxObject);
		};
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/listEmployees.ajax",
			true);
	xhttp.send();
    var x = document.getElementById("listEmployees");
    x.style.display = "none";
}

function createRows(ajaxObject) {
//	ajaxObject = [
//			{employeeId: "1", firstname: "hello1", lastname: "world1", password: "hello1", email: "world1"},
//			{employeeId: "2", firstname: "hello2", lastname: "world2", password: "hello2", email: "world2"}
//	]
	var tbody = document.createElement("tbody");
	var table = document.getElementById("employeeTable");

	if (ajaxObject.length === undefined) {
		var tr = document.createElement("tr");
		var td1 = document.createElement("td");			
		var td2 = document.createElement("td");			
		var td3 = document.createElement("td");			
		var td4 = document.createElement("td");			
		var td5 = document.createElement("td");			

		var id = document.createTextNode(ajaxObject.employeeId);
		var fn = document.createTextNode(ajaxObject.firstname);
		var ln = document.createTextNode(ajaxObject.lastname);
		var em = document.createTextNode(ajaxObject.email);
		var pw = document.createTextNode(ajaxObject.password);
		
		td1.appendChild(id);
		tr.appendChild(td1);

		td2.appendChild(fn);
		tr.appendChild(td2);

		td3.appendChild(ln);
		tr.appendChild(td3);

		td4.appendChild(em);
		tr.appendChild(td4);

		td5.appendChild(pw);
		tr.appendChild(td5);
		
		tbody.appendChild(tr);
		table.appendChild(tbody);
		
		
	} else {
		for (var i = 0; i < ajaxObject.length; i++) {
			var tr = document.createElement("tr");
			var td1 = document.createElement("td");			
			var td2 = document.createElement("td");			
			var td3 = document.createElement("td");			
			var td4 = document.createElement("td");			
			var td5 = document.createElement("td");

			var id = document.createTextNode(ajaxObject[i].employeeId);
			var fn = document.createTextNode(ajaxObject[i].firstname);
			var ln = document.createTextNode(ajaxObject[i].lastname);
			var em = document.createTextNode(ajaxObject[i].email);
			var pw = document.createTextNode(ajaxObject[i].password);
			
			td1.appendChild(id);
			tr.appendChild(td1);

			td2.appendChild(fn);
			tr.appendChild(td2);

			td3.appendChild(ln);
			tr.appendChild(td3);

			td4.appendChild(em);
			tr.appendChild(td4);

			td5.appendChild(pw);
			tr.appendChild(td5);
			
			tbody.appendChild(tr);
			table.appendChild(tbody);

		}
	}
	

}

