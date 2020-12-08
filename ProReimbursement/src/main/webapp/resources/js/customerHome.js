var formContainer = document.getElementById("form-info");

window.onload=function(){
	console.log("window");
	getCustomerForm();
}

let cusNum = 1010;

function getCustomerForm() {
	let xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {
		console.log("the ready state has changed");
		if (xhttp.readyState == 4 && xhttp.status== 200) {
			let form = JSON.parse(xhttp.responseText);
			console.log('Description: '+ form[0].description);
			console.log('FormID: ' + form[0].formID);
			tableFromJson(form);
		}
	}
	xhttp.open("GET", "http://localhost:8080/ProReimbursement/customerHome.json");

	xhttp.send();
}

function tableFromJson(form) {
	console.log('inside tableFromJson: ' + form);
	var htmlString = [];
	for(i = 0; i < form.length; i++){
		//htmlString += form[i].formID + form[i].time + form[i].cost + form[i].gradeFormat;
		htmlString = [form[i].formID + form[i].time + form[i].cost + form[i].gradeFormat];
	}
	formContainer.insertAdjacentHTML('beforeend', htmlString);
	// the json data. (you can change the values for output.)
	var myBooks = [];


	// Extract value from table header. 
	// ('Book ID', 'Book Name', 'Category' and 'Price')
	var col = [];
	for (var i = 0; i < myBooks.length; i++) {
		for (var key in myBooks[i]) {
			if (col.indexOf(key) === -1) {
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
	}

	// add json data to the table as rows.
	for (var i = 0; i < myBooks.length; i++) {

		tr = table.insertRow(-1);

		for (var j = 0; j < col.length; j++) {
			var tabCell = tr.insertCell(-1);
			tabCell.innerHTML = myBooks[i][col[j]];
		}
	}

	// Now, add the newly created table with json data, to a container.
	var divShowData = document.getElementById('showData');
	divShowData.innerHTML = "";
	divShowData.appendChild(table);

	document.getElementById('msg').innerHTML = '<br />You can later <a href="https://www.encodedna.com/javascript/dynamically-add-remove-rows-to-html-table-using-javascript-and-save-data.htm" target="_blank" style="color:#1464f4;text-decoration:none;">get all the data from table and save it in a database.</a>';
}