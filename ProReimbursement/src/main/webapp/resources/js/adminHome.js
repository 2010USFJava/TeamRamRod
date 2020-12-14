var test;
var title;


window.onload=function(){
	console.log("adminHome");
	
	getTitle();
	
	getAdminForm();
	getFinalApproval();
	
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

function getFinalApproval() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed final");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let final = JSON.parse(xhttp.responseText);
			console.log()
			console.log('Final: '+ final);
			tableFromJsonFinal(final);

		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/getFinalApproval.json");

	xhttp.send();
}



function getAdminForm() {
	//console.log('Array of strings '+ dates);
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed form");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let form = JSON.parse(xhttp.responseText);
			test = xhttp.responseText;
			console.log(test);
			console.log('this is test');
			//console.log('Description: '+ form[0].description);
			//console.log('FormID: ' + form[0].formID);
			tableFromJson(form);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/adminForms.json");

	xhttp.send();
}

function logout() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			
			let form = JSON.parse(xhttp.responseText);
			let test = xhttp.responseText;
			console.log(test);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/logout.json");

	xhttp.send();
}

function tableFromJson(form) {
	//console.log('Array of strings '+ dates);
	console.log('inside tableFromJson: ' + form);
	var myBooks =[];
	
	

	
	var mydates = [];
	for(i = 0; i < form.length; i++){
		myBooks[i] = form[i];
		
				
	}
	
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

	for (var i = 0; i < col.length-2; i++) {
			
		
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

			for (var j = 0; j < col.length-2; j++) {
				var tabCell = tr.insertCell(-1);
				tabCell.innerHTML = myBooks[i][col[j]];
				
				
				/*if (j == 0){
					var tabCell = tr.insertCell(-1);
					tabCell.innerHTML = mydates[i];
				}*/
		}
	}
	var adminTitle = [title];
	console.log('admin title: '+adminTitle);
	
	// Now, add the newly created table with json data, to a container.
	
		
		var divShowData = document.getElementById('showData');
		divShowData.innerHTML = "";
		divShowData.appendChild(table);
}
		
function tableFromJsonFinal(form) {
	//console.log('Array of strings '+ dates);
	console.log('inside tableFromJson: ' + form);
	var myBooks =[];
	
	

	
	var mydates = [];
	for(i = 0; i < form.length; i++){
		myBooks[i] = form[i];
		
				
	}
	
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

	for (var i = 0; i < col.length-2; i++) {
			
		
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

			for (var j = 0; j < col.length-2; j++) {
				var tabCell = tr.insertCell(-1);
				tabCell.innerHTML = myBooks[i][col[j]];
				
				
				/*if (j == 0){
					var tabCell = tr.insertCell(-1);
					tabCell.innerHTML = mydates[i];
				}*/
		}
	}
	var adminTitle = [title];
	console.log('admin title: '+adminTitle);
	
	// Now, add the newly created table with json data, to a container.
	
		
		if (adminTitle == 'benco'){ 
			console.log('title indeed is benco')
			var showFinalApproval = document.getElementById('showFinal');
			showFinalApproval.innerHTML = "";
			showFinalApproval.appendChild(table);
			document.getElementById("finalTitle").style.display = "block";
			
			
		}
	
		
	}  
	



