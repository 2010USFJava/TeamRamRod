

function sendEmail() {
	Email.send({
	Host: "smtp.gmail.com",
	Username : "revmikejones13@gmail.com",
	Password : "3tyme4be",
	To : 'mikejones1367@protonmail.com',
	From : "revmikejones13@gmail.com",
	Subject : "Reimbursement App",
	Body : "Your attention is needed! Please login to ProReimbursement",
	}).then(
		message => alert("mail sent successfully")
	);
}