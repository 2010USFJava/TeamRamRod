window.onload=function(){
	console.log("window");
	getCustomerForm();
}

function getFormDates() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let dates = JSON.parse(xhttp.responseText);
			
			console.log(dates);

		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/getDates.json");

	xhttp.send();
}

function getCustomerForm() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let form = JSON.parse(xhttp.responseText);
			let test = xhttp.responseText;
			console.log(test);
			console.log('this is test');
			//console.log('Description: '+ form[0].description);
			//console.log('FormID: ' + form[0].formID);
			tableFromJson(form);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/customerHome.json");

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

function tableFromJson(form) {
	let dates = getFormDates();
	console.log('inside tableFromJson: ' + form);
	var myBooks =[];
	var formNum = [];
	var mydates = [];
	for(i = 0; i < form.length; i++){
		myBooks[i] = form[i];
		formNum[i] = form[i].formID;
				
	}
	for(i = 0; i < dates.length; i++){
		mydates[i] = dates[i];
	}
	
	// the json data. (you can change the values for output.)
	
		console.log('mybooks: '+myBooks);


	// Extract value from table header. 
	// ('Book ID', 'Book Name', 'Category' and 'Price')
	var col = [];
	for (var i = 0; i < myBooks.length; i++) {
		for (var key in myBooks[i]) {
				if (key !== 'date' && col.indexOf(key) === -1){ // take out first conditional
					 	console.log(key)
						col.push(key);
					}
			
		}
			
		}
	

	// Create a table.
	var table = document.createElement("table");

	// Create table header row using the extracted headers above.
	var tr = table.insertRow(-1);                   // table row.

	for (var i = 0; i < col.length-2; i++) {
			
		
				var th = document.createElement("th");      // table header.
				th.innerHTML = col[i];
				tr.appendChild(th);
			
	}

	// add json data to the table as rows.
	for (var i = 0; i < myBooks.length; i++) {
		
			tr = table.insertRow(-1);

			for (var j = 0; j < col.length-2; j++) {
				var tabCell = tr.insertCell(-1);
				tabCell.innerHTML = myBooks[i][col[j]];
				
				if (j == 1){
					var tabCell = tr.insertCell(-1);
					tabCell.innerHTML = 'date';
				}
		}
	}

	// Now, add the newly created table with json data, to a container.
	var divShowData = document.getElementById('showData');
	divShowData.innerHTML = "";
	divShowData.appendChild(table);

	
}
