//When someone needs more info, they send the email to whoever needs to give that info
var cusEmail= []; 



function getCusEmail() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed form in getAdmin");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let email = JSON.parse(xhttp.responseText);	
			let test = xhttp.responseText;
			console.log('this is cus email '+ test);
			cusEmail= [email];
			console.log('cusEmail= '+cusEmail);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/getCustomerEmail.json");

	xhttp.send();
}

function sendEmail() {
	console.log('this is the body '+  document.getElementById('forEmail').value) 
	console.log('inside sendEmail cusemail: ' + cusEmail);
	Email.send({
	Host: "smtp.gmail.com",
	Username : "revmikejones13@gmail.com",
	Password : "3tyme4be",
	To : cusEmail,
	From :  "revmikejones13@gmail.com",
	Subject : "Reimbursement App",
	Body : document.getElementById('forEmail').value,
	}).then(
		message => alert("mail sent successfully")
	);
}

//To Benco Supervisor, when 3rd approval date missed 
function sendBenEmail() {
	Email.send({
	Host: "smtp.gmail.com",
	Username : "revmikejones13@gmail.com",
	Password : "3tyme4be",
	To : "mikejones1367@protonmail.com",
	From : "revmikejones13@gmail.com",
	Subject : "Reimbursement App",
	Body : "Your attention is needed! Please login to ProReimbursement",
	}).then(
		message => alert("mail sent successfully")
	);
}

//When a grade is submitted
function sendToBenco() {
	Email.send({
	Host: "smtp.gmail.com",
	Username : "revmikejones13@gmail.com",
	Password : "3tyme4be",
	To : 'revmikejones13@gmail.com',
	From : "revmikejones13@gmail.com",
	Subject : "Reimbursement App",
	Body : "Your attention is needed! Please login to ProReimbursement",
	}).then(
		message => alert("mail sent successfully")
	);
}

function change() {
    var decider = document.getElementById('grade');
    if(decider.checked){
        sendToBenco();
    } 
}