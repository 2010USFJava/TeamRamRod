window.onload=function(){
	console.log("window");
	getCustomerForm();
}

function getCustomerForm() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let session = xhttp.responseText;
			console.log(session);
			if(session != null){
				displayGrade(session);
			}
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/getsession.json");

	xhttp.send();
}

function displayGrade(session){
document.getElementById("grade").style.display = "block";
document.getElementById("gradeText").style.display = "block"
}
