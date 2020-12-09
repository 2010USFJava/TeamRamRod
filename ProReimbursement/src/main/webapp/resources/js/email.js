//When someone needs more info, they send the email to whoever needs to give that info

function sendEmail() {
	Email.send({
	Host: "smtp.gmail.com",
	Username : "revmikejones13@gmail.com",
	Password : "3tyme4be",
	To : document.getElementById('receipient'),
	From : document.getElementById('sender'),
	Subject : "Reimbursement App",
	Body : document.getElementById('emailMessage'),
	}).then(
		message => alert("mail sent successfully")
	);
}

//To Benco Supervisor, when 3rd approval date missed 
function sendEmail() {
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