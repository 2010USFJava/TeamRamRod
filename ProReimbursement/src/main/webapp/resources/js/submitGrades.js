var status;

window.onload=function(){
	console.log("window");
	getCustomerForm();
	//displayGrade(session);
	customerStatus();
}
function customerStatus() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed form");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			status = JSON.parse(xhttp.responseText);
			test = xhttp.responseText;
			console.log(test);
			console.log('this is test');
			let gotStatus = [status];
			viewStatus(gotStatus);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/customerStatus.json");

	xhttp.send();
}

function viewStatus(gotStatus){
document.getElementById('showStatus').innerHTML = gotStatus;
console.log('inside viewStatus'+ gotStatus);
}
function getCustomerForm() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let session = JSON.parse(xhttp.responseText);
			console.log('first '+session);
			if(session !== null && session !== ''){
				displayGrade(session);
				console.log('this shows the box '+session);
			}
			else{	
				console.log('IS NULL '+session);
				
				
				
			}
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/getsession.json");

	xhttp.send();
}

function logout() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			
			form = JSON.parse(xhttp.responseText);
			let test = xhttp.responseText;
			console.log(test);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/logout.json");

	xhttp.send();
}


function displayGrade(session){
document.getElementById("grade").style.display = "block";
document.getElementById("gradeText").style.display = "block";

document.getElementById("customerHome").style.display = "block";
}
