function changeFN() {
	let FN = document.getElementById("FNform").value;
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			updateFN(ajaxObject);
		};
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/updateEmployeeFN.ajax?FN="+FN, true);
	xhttp.send();
	let x = document.getElementById("FNdiv");
    x.style.display = "none";
}

function changeLN() {
	let LN = document.getElementById("LNform").value;
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			updateLN(ajaxObject);
		};
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/updateEmployeeLN.ajax?LN="+LN, true);
	xhttp.send();
    let x = document.getElementById("LNdiv");
    x.style.display = "none";
}

function changeEM() {
	let EM = document.getElementById("EMform").value;
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			updateEM(ajaxObject);
		};
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/updateEmployeeEM.ajax?EM="+EM, true);
	xhttp.send();
    let x = document.getElementById("EMdiv");
    x.style.display = "none";
}

function changePW() {
	let PW = document.getElementById("PWform").value;
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			updatePW(ajaxObject);
		};
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/updateEmployeePW.ajax?PW="+PW, true);
	xhttp.send();
    let x = document.getElementById("PWdiv");
    x.style.display = "none";
}

function updateFN(ajaxObject) {
	let change = document.getElementById("employeeTable").rows[1].cells;
	change[1].innerHTML = ajaxObject.firstname;
}

function updateLN(ajaxObject) {
	let change = document.getElementById("employeeTable").rows[1].cells;
	change[2].innerHTML = ajaxObject.lastname;
}

function updateEM(ajaxObject) {
	let change = document.getElementById("employeeTable").rows[1].cells;
	change[3].innerHTML = ajaxObject.email;
}

function updatePW(ajaxObject) {
	let change = document.getElementById("employeeTable").rows[1].cells;
	change[4].innerHTML = ajaxObject.password;
}
