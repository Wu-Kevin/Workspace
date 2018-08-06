window.onload = function() {
	document.getElementById("listEmployee").addEventListener("click",
			getEmployee);
	document.getElementById("FNsubmit").addEventListener("click", changeFN);
	document.getElementById("LNsubmit").addEventListener("click", changeLN);
	document.getElementById("EMsubmit").addEventListener("click", changeEM);
	document.getElementById("PWsubmit").addEventListener("click", changePW);
	document.getElementById("showReim").addEventListener("click",
			getReimbursements);
	document.getElementById("showReimPending").addEventListener("click",
			getAllPendingReimbursements);
	document.getElementById("showReimApproved").addEventListener("click",
			getAllApprovedReimbursements);
	document.getElementById("insertReim").addEventListener("click",
			insertReimbursement);
	document.getElementById("refreshPending").addEventListener("click", refreshPendingTable);

};

function refreshApprovedTable() {
	var tableHeaderRowCount = 1;
	var table = document.getElementById('approvedTable');
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
	getAllApprovedReimbursements();
}

function refreshPendingTable() {
	var tableHeaderRowCount = 1;
	var table = document.getElementById('pendingTable');
	var rowCount = table.rows.length;
	for (var i = tableHeaderRowCount; i < rowCount; i++) {
	    table.deleteRow(tableHeaderRowCount);
	}
	getAllPendingReimbursements();
}

function logOut() {
	if (window.confirm("Are you sure you want to logout?")) {
		sessionStorage.clear();
		window.location.href = "http://localhost:8181/ERS/html/login.html";
	} else {
		return;
	}
}

function getEmployee() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			createEmployeeRow(ajaxObject);
		}
		;
	};
	xhttp
			.open("POST", "http://localhost:8181/ERS/html/listEmployee.ajax",
					true);
	xhttp.send();
	let x = document.getElementById("listEmployee");
	x.style.display = "none";
}

function getAllEmployees() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			createAllEmployeeRows(ajaxObject);
		}
		;
	};
	xhttp.open("POST", "http://localhost:8181/ERS/html/listAllEmployees.ajax",
			true);
	xhttp.send();
	let x = document.getElementById("listAllEmployees");
	x.style.display = "none";
}

function createEmployeeRow(ajaxObject) {
	let tbody = document.createElement("tbody");
	let table = document.getElementById("employeeTable");

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
}

function createAllEmployeeRows(ajaxObject) {
	let tbody = document.createElement("tbody");
	let table = document.getElementById("employeeAllTable");
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

function getReimbursements() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			createReimbursementRows(ajaxObject);
		}
	}
	xhttp.open("POST",
			"http://localhost:8181/ERS/html/listReimbursements.ajax", true);
	xhttp.send();
	let newx = document.getElementById("showReim");
	newx.style.display = "none";
	let newy = document.getElementById("showReimForm");
	newy.style.display = "block";
}

function insertReimbursement() {
	let amt = document.getElementById("ReimAmount").value;
	let rat = document.getElementById("ReimRationale").value;
	amt = parseFloat(amt).toFixed(2);
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			createReimbursementRows(ajaxObject);
		}
	};
	xhttp.open("POST",
			"http://localhost:8181/ERS/html/insertReimbursement.ajax?amt="
					+ amt + "&rat=" + rat, true);
	xhttp.send();
}

function showReimById() {
	let reimid = document.getElementById("singleIdReim").value;
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			allReimbursementsById(ajaxObject);
		}
	}
	xhttp.open("POST",
			"http://localhost:8181/ERS/html/selectReimbursementById.ajax?reimid="+reimid, true);
	xhttp.send();
}

function allReimbursementsById(ajaxObject) {

	let tbody = document.createElement("tbody");
	let table = document.getElementById("singleIdReimbursementTable");

	if (ajaxObject.length == undefined) {
		let tr = document.createElement("tr");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		let td5 = document.createElement("td");


		let id = document.createTextNode(ajaxObject.reimbursementid);
		let eid = document.createTextNode(ajaxObject.employeeid)
		let am = document.createTextNode("$" + ajaxObject.amount);
		let rt = document.createTextNode(ajaxObject.rationale);
		if (ajaxObject.approvalid == "0") {
			var app = document.createTextNode("Pending");
		} else {
			if (ajaxObject.approval == "1") {
				var app = document.createTextNode("Approved");
			} else {
				var app = document.createTextNode("Denied");
			}
		}

		td1.appendChild(id);
		tr.appendChild(td1);

		td2.appendChild(eid);
		tr.appendChild(td2);
		
		td3.appendChild(am);
		tr.appendChild(td3);

		td4.appendChild(rt);
		tr.appendChild(td4);

		td5.appendChild(app);
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

			let id = document.createTextNode(ajaxObject[i].reimbursementid);
			let eid = document.createTextNode(ajaxObject[i].employeeId)
			let am = document.createTextNode("$" + ajaxObject[i].amount);
			let rt = document.createTextNode(ajaxObject[i].rationale);

			if (ajaxObject[i].approvalid == "0") {
				var app = document.createTextNode("Pending");
			} else {
				if (ajaxObject[i].approval == "1") {
					var app = document.createTextNode("Approved");
				} else {
					var app = document.createTextNode("Denied");
				}
			}

			td1.appendChild(id);
			tr.appendChild(td1);

			td2.appendChild(eid);
			tr.appendChild(td2);
			
			td3.appendChild(am);
			tr.appendChild(td3);

			td4.appendChild(rt);
			tr.appendChild(td4);

			td5.appendChild(app);
			tr.appendChild(td5);

			tbody.appendChild(tr);
			table.appendChild(tbody);
		}
	}
}

function getAllPendingReimbursements() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			allPendingReimbursements(ajaxObject);
		}
	}
	xhttp.open("POST",
			"http://localhost:8181/ERS/html/listReimbursements.ajax", true);
	xhttp.send();
	let newx = document.getElementById("showReimPending");
	newx.style.display = "none";
}

function changeReimbursements() {
	let changeid = document.getElementById("approveWho").value;
	let approval = document.getElementById("approveDenyForm").value;
	let xhttp = new XMLHttpRequest();
//	xhttp.onreadystatechange = function() {
//		if (xhttp.readyState == 4 && xhttp.status == 200) {
//			let ajaxObject = JSON.parse(xhttp.responseText);
//			//insert refresh here?
//			allPendingReimbursements(ajaxObject);
//		}
//	}
	xhttp.open("POST",
			"http://localhost:8181/ERS/html/changeReimbursements.ajax?changeid="+changeid+"&approval="+approval, true);
	xhttp.send();
}

function getAllApprovedReimbursements() {
	let xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
			let ajaxObject = JSON.parse(xhttp.responseText);
			allApprovedReimbursements(ajaxObject);
		}
	}
	xhttp.open("POST",
			"http://localhost:8181/ERS/html/listReimbursements.ajax", true);
	xhttp.send();
	let newx = document.getElementById("showReimApproved");
	newx.style.display = "none";
}

function createReimbursementRows(ajaxObject) {

	let tbody = document.createElement("tbody");
	let table = document.getElementById("reimbursementTable");

	if (ajaxObject.length == undefined) {
		let tr = document.createElement("tr");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");

		let id = document.createTextNode(ajaxObject.reimbursementid);
		let am = document.createTextNode("$" + ajaxObject.amount);
		let rt = document.createTextNode(ajaxObject.rationale);
		if (ajaxObject.approvalid == "0") {
			var app = document.createTextNode("Pending");
		} else {
			if (ajaxObject.approval == "1") {
				var app = document.createTextNode("Approved");
			} else {
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

			if (ajaxObject[i].approvalid == "0") {
				var app = document.createTextNode("Pending");
			} else {
				if (ajaxObject[i].approval == "1") {
					var app = document.createTextNode("Approved");
				} else {
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

function allPendingReimbursements(ajaxObject) {

	let tbody = document.createElement("tbody");
	let table = document.getElementById("pendingTable");

	if (ajaxObject.length == undefined) {
		let tr = document.createElement("tr");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");

		if ((ajaxObject.approvalid == "0")) {
			let id = document.createTextNode(ajaxObject.reimbursementid);
			let am = document.createTextNode("$" + ajaxObject.amount);
			let rt = document.createTextNode(ajaxObject.rationale);
			let app = document.createTextNode("Pending");
			
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
			return;
		}
	} else {
		for (let i = 0; i < ajaxObject.length; i++) {
			let tr = document.createElement("tr");
			let td1 = document.createElement("td");
			let td2 = document.createElement("td");
			let td3 = document.createElement("td");
			let td4 = document.createElement("td");


			if ((ajaxObject[i].approvalid == "0")) {
				let id = document.createTextNode(ajaxObject[i].reimbursementid);
				let am = document.createTextNode("$" + ajaxObject[i].amount);
				let rt = document.createTextNode(ajaxObject[i].rationale);
				let app = document.createTextNode("Pending");

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
				continue;
			}

		}
	}
}

function allApprovedReimbursements(ajaxObject) {

	let tbody = document.createElement("tbody");
	let table = document.getElementById("approvedTable");

	if (ajaxObject.length == undefined) {
		let tr = document.createElement("tr");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		let td5 = document.createElement("td");

		if ((ajaxObject.approvalid != "0")) {
			let id = document.createTextNode(ajaxObject.reimbursementid);
			let am = document.createTextNode("$" + ajaxObject.amount);
			let rt = document.createTextNode(ajaxObject.rationale);
			if (ajaxObject.approval == "1") {
				var app = document.createTextNode("Approved");
			} else {
				var app = document.createTextNode("Denied");
			}
			let man = document.createTextNode(ajaxObject.approvalid);
			
			td1.appendChild(id);
			tr.appendChild(td1);

			td2.appendChild(am);
			tr.appendChild(td2);

			td3.appendChild(rt);
			tr.appendChild(td3);

			td4.appendChild(app);
			tr.appendChild(td4);
			
			td5.appendChild(man);
			tr.appendChild(td5);

			tbody.appendChild(tr);
			table.appendChild(tbody);
		} else {
			return;
		}
	} else {
		for (let i = 0; i < ajaxObject.length; i++) {
			let tr = document.createElement("tr");
			let td1 = document.createElement("td");
			let td2 = document.createElement("td");
			let td3 = document.createElement("td");
			let td4 = document.createElement("td");
			let td5 = document.createElement("td");

			if ((ajaxObject[i].approvalid != "0")) {
				let id = document.createTextNode(ajaxObject[i].reimbursementid);
				let am = document.createTextNode("$" + ajaxObject[i].amount);
				let rt = document.createTextNode(ajaxObject[i].rationale);
				if (ajaxObject[i].approval == "1") {
					var app = document.createTextNode("Approved");
				} else {
					var app = document.createTextNode("Denied");
				}
				let man = document.createTextNode(ajaxObject[i].approvalid);
				
				td1.appendChild(id);
				tr.appendChild(td1);

				td2.appendChild(am);
				tr.appendChild(td2);

				td3.appendChild(rt);
				tr.appendChild(td3);

				td4.appendChild(app);
				tr.appendChild(td4);
				
				td5.appendChild(man);
				tr.appendChild(td5);

				tbody.appendChild(tr);
				table.appendChild(tbody);
			} else {
				continue;
			}

		}
	}
}