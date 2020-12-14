var title;

window.onload=function(){
	console.log("adminOptions");
	
	getTitle();
	
	console.log('title in options '+ title);+
	getAdminForm();
	getOptional();
	getCustomerInfo();
	getCusEmail();
}

function getAdminForm() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed form in getAdmin");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let form = JSON.parse(xhttp.responseText);	
			let test = xhttp.responseText;
			console.log('this is test '+ test);
			
			tableFromJson(form);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/adminOptions.json");

	xhttp.send();
}

function getTitle() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed title");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			title = JSON.parse(xhttp.responseText);
			console.log()
			console.log('Title: '+ title);

		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/getTitle.json");

	xhttp.send();
}

function getCustomerInfo() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed form in getAdmin");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let customer = JSON.parse(xhttp.responseText);	
			let test = xhttp.responseText;
			console.log('this is test customer: '+ test);
			
			tableFromJson(customer);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/customerInfo.json");

	xhttp.send();
}


function getOptional() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed form in getAdmin");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let optional = JSON.parse(xhttp.responseText);	
			let test = xhttp.responseText;
			let array = [optional];
	
		
			
			console.log('this is test '+ test);
			console.log(optional);
			printOptional(optional);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/printOptional.json");

	xhttp.send();
}

function printOptional(emailOptional){
	let array = [];
	for(i = 0; i < emailOptional.length; i++){
		array[i] = emailOptional[i];
	}
	let email = array[0];
	let optional = array[1];
	
	var hasEmail;
	if(email == 'True'){
		hasEmail = 'An email may be attached.';
	} else {
		hasEmail = 'There are no emails attached.';	
	}
	
	document.getElementById('showEmail').innerHTML = hasEmail;
	console.log('hasEmail: ' + hasEmail);
	document.getElementById('showOptional').innerHTML = optional;
	console.log('optional: ' + optional);
}

function logout() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			
			let form = JSON.parse(xhttp.responseText);
			
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/logout.json");

	xhttp.send();
}

function tableFromJson(form) {

	//console.log('Array of strings '+ dates);
	console.log('inside tableFromJson: ' + form);
	
	
	var myBooks =[form];

	
		console.log('form 0 : '+ form);
		console.log('mybooks: '+myBooks);

//	var acdate = myBooks[1].date.toString();
//	
//	console.log('THIS IS THE DATE'+acdate);
	
	/*for(i = 0; i < dates.length; i++){
		mydates[i] = dates[i];
	}
	console.log('mydates: ' + mydates);*/
	// the json data. (you can change the values for output.)
	
		console.log('mybooks: '+myBooks);


	// Extract value from table header. 
	// ('Book ID', 'Book Name', 'Category' and 'Price')
	var col = [];
	for (var i = 0; i < myBooks.length; i++) {
		for (var key in myBooks[i]) {
				if (key !== 'date' && col.indexOf(key) === -1){
					 	console.log(key)
						col.push(key);
					}
			
		}
			
		}
	

	// Create a table.
	var table = document.createElement("table");

	// Create table header row using the extracted headers above.
	var tr = table.insertRow(-1);                   // table row.

	for (var i = 0; i < col.length; i++) {
			
		
				var th = document.createElement("th");      // table header.
				th.innerHTML = col[i];
				tr.appendChild(th);
				
			/*	if (i ==0){
					var th = document.createElement("th");      // table header.
					th.innerHTML = 'Date';
					tr.appendChild(th);
				}*/
	}

	// add json data to the table as rows.
	for (var i = 0; i < myBooks.length; i++) {
		
			tr = table.insertRow(-1);

			for (var j = 0; j < col.length; j++) {
				var tabCell = tr.insertCell(-1);
				tabCell.innerHTML = myBooks[i][col[j]];
				
				
				/*if (j == 0){
					var tabCell = tr.insertCell(-1);
					tabCell.innerHTML = mydates[i];
				}*/
		}
	}
	
	var adminTitle = [title];
	
	// Now, add the newly created table with json data, to a container.
	if(col[0] == 'formID'){
		var divShowData = document.getElementById('showData');
		divShowData.innerHTML = "";
		divShowData.appendChild(table);
	} else if(adminTitle == 'benco'){
	
		console.log('inside else if admintitle ' + adminTitle);
		var divShowData = document.getElementById('showCus');
		divShowData.innerHTML = "";
		divShowData.appendChild(table);
		
	
	}else {
		var divShowData = document.getElementById('showCus');
		divShowData.innerHTML = "";
		divShowData.appendChild(table);
	}
	
}

function displayBox(){
document.getElementById("forEmail").style.display = "block";
}

function displayReason(){

document.getElementById("sendEmail").style.display = "block";
}
