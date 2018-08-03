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
	document.getElementById("showReim").addEventListener("click",
			getReimbursements);
	document.getElementById("insertReim").addEventListener("click",
			insertReimbursement);
};

function logOut() {
	    if (window.confirm("Are you sure you want to logout?")) {
	    	sessionStorage.clear();
	    	window.location.href = "http://localhost:8181/ERS/html/login.html";
	    } else {
	        return;
	    }
}

function getEmployees() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			createRows(ajaxObject);
		};
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/listEmployees.ajax",
			true);
	xhttp.send();
	let x = document.getElementById("listEmployees");
    x.style.display = "none";
}

function createRows(ajaxObject) {
//	ajaxObject = [
//			{employeeId: "1", firstname: "hello1", lastname: "world1", password: "hello1", email: "world1"},
//			{employeeId: "2", firstname: "hello2", lastname: "world2", password: "hello2", email: "world2"}
//	]
	let tbody = document.createElement("tbody");
	let table = document.getElementById("employeeTable");

	if (ajaxObject.length === undefined) {
		let tr = document.createElement("tr");
		let td1 = document.createElement("td");			
		let td2 = document.createElement("td");			
		let td3 = document.createElement("td");			
		let td4 = document.createElement("td");			
		let td5 = document.createElement("td");			

		let id = document.createTextNode(ajaxObject.employeeId);
		let fn = document.createTextNode(ajaxObject.firstname);
		let ln = document.createTextNode(ajaxObject.lastname);
		let em = document.createTextNode(ajaxObject.email);
		let pw = document.createTextNode(ajaxObject.password);
		
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
		for (let i = 0; i < ajaxObject.length; i++) {
			let tr = document.createElement("tr");
			let td1 = document.createElement("td");			
			let td2 = document.createElement("td");			
			let td3 = document.createElement("td");			
			let td4 = document.createElement("td");			
			let td5 = document.createElement("td");

			let id = document.createTextNode(ajaxObject[i].employeeId);
			let fn = document.createTextNode(ajaxObject[i].firstname);
			let ln = document.createTextNode(ajaxObject[i].lastname);
			let em = document.createTextNode(ajaxObject[i].email);
			let pw = document.createTextNode(ajaxObject[i].password);
			
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

function getReimbursements() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			createReimbursementRows(ajaxObject);
		};
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/listReimbursements.ajax",
			true);
	xhttp.send();
    let newx = document.getElementById("showReim");
    newx.style.display = "none";
    let newy = document.getElementById("showReimForm");
    newy.style.display = "block";
}

function createReimbursementRows(ajaxObject) {

	let tbody = document.createElement("tbody");
	let table = document.getElementById("reimbursementTable");

	if (ajaxObject.length === undefined) {
		let tr = document.createElement("tr");
		let td1 = document.createElement("td");			
		let td2 = document.createElement("td");			
		let td3 = document.createElement("td");			
		let td4 = document.createElement("td");			

		let id = document.createTextNode(ajaxObject.reimbursementid);
		let am = document.createTextNode("$" + ajaxObject.amount);
		let rt = document.createTextNode(ajaxObject.rationale);
		if (ajaxObject.approvalid == "0" ) {
			var app = document.createTextNode("Pending");
		} else {
			if (ajaxObject.approval == "1") {
				var app = document.createTextNode("Approved");
			}
			else {
				var app = document.createTextNode("Denied");
			}
		}
	
		td1.appendChild(id);
		tr.appendChild(td1);

		td2.appendChild(am);
		tr.appendChild(td2);

		td3.appendChild(rt);
		tr.appendChild(td3);

		td4.appendChild(app);
		tr.appendChild(td4);
		
		tbody.appendChild(tr);
		table.appendChild(tbody);
	} else {
		for (let i = 0; i < ajaxObject.length; i++) {
			let tr = document.createElement("tr");
			let td1 = document.createElement("td");			
			let td2 = document.createElement("td");			
			let td3 = document.createElement("td");			
			let td4 = document.createElement("td");			

			let id = document.createTextNode(ajaxObject[i].reimbursementid);
			let am = document.createTextNode("$" + ajaxObject[i].amount);
			let rt = document.createTextNode(ajaxObject[i].rationale);
			
			if (ajaxObject[i].approvalid == "0" ) {
				var app = document.createTextNode("Pending");
			} else {
				if (ajaxObject[i].approval == "1") {
					var app = document.createTextNode("Approved");
				}
				else {
					var app = document.createTextNode("Denied");
				}
			}
		
			td1.appendChild(id);
			tr.appendChild(td1);

			td2.appendChild(am);
			tr.appendChild(td2);

			td3.appendChild(rt);
			tr.appendChild(td3);

			td4.appendChild(app);
			tr.appendChild(td4);

			tbody.appendChild(tr);
			table.appendChild(tbody);

		}
	}
}

function insertReimbursement() {
	let amt = document.getElementById("ReimAmount").value;
	let rat = document.getElementById("ReimRationale").value;
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			createReimbursementRows(ajaxObject);
		}
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/insertReimbursement.ajax?amt="+amt+"&rat="+rat, true);
	xhttp.send();
}