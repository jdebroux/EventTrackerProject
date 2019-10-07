window.addEventListener('load', function(e) {
	console.log('document loaded');
	init();
});

function init(e) {
	let tableBody = document.getElementById('tablebody');
	let tr = document.createElement('tr');
	let td = document.createElement('td');
	td.textContent = "ALL WEDDINGS"
	tr.appendChild(td);
	tableBody.appendChild(tr);
	tableBody.appendChild(document.createElement('hr'));

	document.getElementById('weddingMaker').firstElementChild.textContent = 'Add Wedding: ';

	showAllWeddings();

	document.newWedding.persistWedding.addEventListener('click', createWedding);
}

function showAllWeddings() {
	// TODO:
	var xhr = new XMLHttpRequest();

	xhr.open('GET', 'api/weddings/')

	xhr.onreadystatechange = function() {
		// console.log(xhr.readyState + " " + xhr.status + xhr.responseText);
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				let weddingsJSON = xhr.responseText;
				let weddings = JSON.parse(weddingsJSON);
				displayAllWeddings(weddings);
			} else {
				let div = document.getElementById('allWeddings');
				div.textContent = 'Weddings Not Found';
			}
		}
	};
	xhr.send(null);
	// * Use XMLHttpRequest to perform a GET request to "api/films/"
	// with the filmId appended.
	// * On success, if a response was received parse the film data
	// and pass the film object to displayFilm().
	// * On failure, or if no response text was received, put "Film not found"
	// in the filmData div.
}

function displayAllWeddings(weddings) {
	let tableBody = document.getElementById('tablebody');
	tableBody.textContent = '';
	for (let i = 0; i < weddings.length; i++) {
		let tr = document.createElement('tr');
		let td = document.createElement('td');
		td.textContent = weddings[i].celebrationDate.substring(10, 0);
		tr.appendChild(td);
		tr.weddingId = weddings[i].id;

		tr.addEventListener('click', function(evt) {
			this.weddingId = weddings[i].id;
			getWeddingFromList(this.weddingId);
		});

		tableBody.appendChild(tr);
		tableBody.appendChild(document.createElement('hr'));
	}
	let seeTotalRevenue = document.createElement('input');
	seeTotalRevenue.value = 'See Total Revenue Contracted';
	seeTotalRevenue.type = 'button';
	seeTotalRevenue.id = 'seeTotal';
	tableBody.appendChild(seeTotalRevenue);
	seeTotalRevenue.allWeddings = weddings;
	seeTotalRevenue.addEventListener('click', showTotalRevenue);
	
}

function showTotalRevenue(){
	let totalRevenue = 0;
	for(let i = 0; i < this.allWeddings.length; i++){
		totalRevenue += this.allWeddings[i].totalCost;
	}
	let seeTotal = document.getElementById('seeTotal');
	seeTotal.parentElement.removeChild(seeTotal);
	
	let tableBody = document.getElementById('tablebody');
	let tr = document.createElement('tr');
	let td = document.createElement('td');
	td.textContent = "Total Revenue Contracted: $" + totalRevenue;
	tr.appendChild(td);
	tableBody.appendChild(tr);
}

function getWeddingFromList(wid) {
	console.log("displayWedding(): " + wid);

	var xhr = new XMLHttpRequest();

	xhr.open('GET', 'api/weddings/' + wid);

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status === 200) {
				console.log("Success");
				var wedding = JSON.parse(xhr.responseText);
				displayWedding(wedding);
			}
		}
	};
	xhr.send();
}

function displayWedding(wedding) {
	let form = document.newWedding;
	form.bookDate.value = wedding.bookingDate;
	form.celebrationDate.value = wedding.celebrationDate;
	form.totalCost.value = wedding.totalCost;
	form.upLighting.value = wedding.upLighting;
	form.notes.value = wedding.notes;

	let editButton = form.persistWedding;
	editButton.value = 'Edit This Wedding';
	editButton.removeEventListener('click', createWedding);
	editButton.wedding = wedding;
	editButton.addEventListener('click', editWedding);

	if (!form.deleteButton) {
		let deleteButton = document.createElement("input");
		deleteButton.type = 'button';
		deleteButton.name = 'deleteButton';
		deleteButton.value = 'Delete This Wedding';
		deleteButton.wedding = wedding;
		deleteButton.addEventListener('click', deleteWedding);
		form.appendChild(deleteButton);
	}

	let tableBody = document.getElementById('tablebody');
	tableBody.textContent = '';
	let div = document.getElementById('weddingMaker');
	div.textContent = '';
	let h3 = document.createElement('h3'); // need to edit this so add wedding
											// becomes edit wedding
	h3.textContent = 'Edit Wedding: ';

	div.appendChild(h3);
	div.appendChild(form);

	displayClientNames(wedding);
	createWeddingInformationTable(wedding);

	div.appendChild(document.createElement('hr'));
}

function deleteWedding(evt) {
	evt.preventDefault();

	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', 'api/weddings/' + this.wedding.id);

	xhr.setRequestHeader("Content-type", "application/json");

	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4) {
			if (xhr.status == 200 || xhr.status == 204) { // Ok or Deleted
				console.log("DELETE WENT THROUGH");
				let form = document.newWedding;
				let addButton = form.persistWedding
				addButton.removeEventListener('click', editWedding);
				addButton.value = "Add A Wedding";
				addButton.addEventListener('click', createWedding);
				form.deleteButton.parentElement.removeChild(form.deleteButton);
				form.reset();
				init(this.wedding);
			} else {
				console.log("DELETE request failed.");
				console.error(xhr.status + ': ' + xhr.responseText);
			}
		}
	};
	xhr.send(null);
}

function editWedding(evt) {
	evt.preventDefault();
	let form = document.newWedding;

	if (form.celebrationDate.value !== '' && form.totalCost.value !== ''
			&& form.upLighting.value !== '' && form.bookDate.value !== '') {
		let wedding = {
			bookingDate : form.bookDate.value,
			celebrationDate : form.celebrationDate.value,
			totalCost : form.totalCost.value,
			upLighting : form.upLighting.value,
			notes : form.notes.value
		};

		let xhr = new XMLHttpRequest();
		xhr.open('PUT', 'api/weddings/' + this.wedding.id);

		xhr.setRequestHeader("Content-type", "application/json");

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
					let wedding = JSON.parse(xhr.responseText);
					console.log("PUT WENT THROUGH");
					document.newWedding.reset();
					displayWedding(wedding);
				} else {
					console.log("PUT request failed.");
					console.error(xhr.status + ': ' + xhr.responseText);
				}
			}
		};
		let weddingJSON = JSON.stringify(wedding); // Convert JS object
		xhr.send(weddingJSON);
	}
}

function displayClientNames(wedding) {
	if (wedding.clients.length > 0) {
		let div = document.getElementById('weddingDisplay');
		let h3 = document.createElement('h3');
		h3.textContent = "Clients: ";
		for (let i = 0; i < wedding.clients.length; i++) {
			h3.textContent += wedding.clients[i].firstName + " "
					+ wedding.clients[i].lastName;
			if (i !== wedding.clients.length - 1) {
				h3.textContent += ", ";
			}
		}
		div.appendChild(h3);
	}
}

function createWeddingInformationTable(wedding) {
	let tr;
	let td;
	let tableBody = document.getElementById('tablebody');
	tr = document.createElement('tr');
	tableBody.appendChild(tr);
	td = document.createElement('td');
	td.textContent = "ID: " + wedding.id;
	tr.appendChild(td);

	tr = document.createElement('tr');
	td = document.createElement('td');
	td.textContent = "Booking Date: " + wedding.bookingDate.substring(10, 0);
	tr.appendChild(td);
	tableBody.appendChild(tr);

	tr = document.createElement('tr');
	td = document.createElement('td');
	td.textContent = "Celebration Date: "
			+ wedding.celebrationDate.substring(10, 0);
	tr.appendChild(td);
	tableBody.appendChild(tr);

	tr = document.createElement('tr');
	td = document.createElement('td');
	td.textContent = "Total Cost: $" + wedding.totalCost;
	tr.appendChild(td);
	tableBody.appendChild(tr);

	tr = document.createElement('tr');
	td = document.createElement('td');
	td.textContent = "Up Lights Included: " + wedding.upLighting + " up lights";
	tr.appendChild(td);
	tableBody.appendChild(tr);

	tr = document.createElement('tr');
	td = document.createElement('td');
	td.textContent = "Notes: " + wedding.notes;
	tr.appendChild(td);
	tableBody.appendChild(tr);

	if (wedding.djs.length > 0) {
		let div = document.getElementById('weddingDisplay');
		let h3 = document.createElement('h3');
		h3.textContent = "DJs: ";
		for (let i = 0; i < wedding.djs.length; i++) {
			h3.textContent += wedding.djs[i].firstName + " "
					+ wedding.djs[i].lastName;
			if (i !== wedding.clients.length - 1) {
				h3.textContent += ", ";
			}
		}
		div.appendChild(h3);
	}

	if (wedding.venue) {
		let div = document.getElementById('weddingDisplay');
		let h3 = document.createElement('h3');
		h3.textContent = "Venue: " + wedding.venue.name;
		div.appendChild(h3);
	}
}

function createWedding(evt) {
	evt.preventDefault();
	let form = document.newWedding;

	if (form.bookDate.value !== '' && form.celebrationDate.value !== ''
			&& form.totalCost.value !== '' && form.upLighting.value !== '') {
		let wedding = {
			bookingDate : form.bookDate.value,
			celebrationDate : form.celebrationDate.value,
			totalCost : form.totalCost.value,
			upLighting : form.upLighting.value,
			notes : form.notes.value
		};

		let xhr = new XMLHttpRequest();
		xhr.open('POST', 'api/weddings');

		xhr.setRequestHeader("Content-type", "application/json");

		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status == 200 || xhr.status == 201) { // Ok or Created
					let wedding = JSON.parse(xhr.responseText);
					console.log("POST WENT THROUGH");
					document.newWedding.reset();
					showAllWeddings();
				} else {
					console.log("POST request failed.");
					console.error(xhr.status + ': ' + xhr.responseText);
				}
			}
		};
		console.log("createWedding():");
		console.log(wedding);
		let weddingJSON = JSON.stringify(wedding); // Convert JS object
		xhr.send(weddingJSON);
	}
}
