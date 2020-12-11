window.onload=function(){
	console.log("window");
	getCustomerForm();
	//displayGrade(session);
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



function displayGrade(session){
document.getElementById("grade").style.display = "block";
document.getElementById("gradeText").style.display = "block";

document.getElementById("customerHome").style.display = "block";
}
