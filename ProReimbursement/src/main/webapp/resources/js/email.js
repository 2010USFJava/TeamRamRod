

function sendEmail() {
	Email.send({
	Host: "smtp.gmail.com",
	Username : "revmikejones13@gmail.com",
	Password : "3tyme4be",
	To : document.getElementById('emailFromBenco'),
	From : "revmikejones13@gmail.com",
	Subject : "Reimbursement App",
	Body : "Your attention is needed! Please login to ProReimbursement",
	}).then(
		message => alert("mail sent successfully")
	);
}

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