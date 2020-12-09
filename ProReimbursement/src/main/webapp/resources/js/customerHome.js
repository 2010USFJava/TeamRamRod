window.onload=function(){
	console.log("window");
	getCustomerForm();
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

function tableFromJson(form) {
	console.log('inside tableFromJson: ' + form);
	var myBooks =[];
	var formNum = [];
	for(i = 0; i < form.length; i++){
		myBooks[i] = form[i];
		formNum[i] = form[i].formID;
		

		
	}
	
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
			
	}

	// add json data to the table as rows.
	for (var i = 0; i < myBooks.length; i++) {
		
			tr = table.insertRow(-1);

			for (var j = 0; j < col.length-2; j++) {
				var tabCell = tr.insertCell(-1);
				tabCell.innerHTML = myBooks[i][col[j]];
				
				if (j == col.length-3){
					var tabCell = tr.insertCell(-1);
					tabCell.innerHTML = 'link';
				}
		}
	}

	// Now, add the newly created table with json data, to a container.
	var divShowData = document.getElementById('showData');
	divShowData.innerHTML = "";
	divShowData.appendChild(table);

	
}
