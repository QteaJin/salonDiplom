	let salonId = 1;
	var workerPhotoRequest = Object.create(Request);
    workerPhotoRequest.GetPhotoWorkerForOrder(salonId);

function sendRequestFotoWorkers(){
	let salonId = 1;
	var workerPhotoRequest = Object.create(Request);
    workerPhotoRequest.GetPhotoWorkerForOrder(salonId);
}

function setWorkerPhoto(obj){
	var myNode = document.querySelectorAll('.workerPhoto'); //delete prevent nodes
		if(myNode != null){
		myNode.forEach(e => e.parentNode.removeChild(e));}
		
		var photo = document.getElementById('workerPhoto');
		
		//console.log((Object.keys(obj).length === 0));
	if(Object.keys(obj).length === 0){
		let divParent = document.createElement('div');
		divParent.setAttribute('class', 'workerPhoto');
		divParent.innerHTML = "NO WORKERS IN THE CITY";
		photo.appendChild(divParent);
		return;
	}		

		
	var numOfWorkers = obj.length;
	for (var i = 0; i < numOfWorkers; i++) {
	var divParent = document.createElement('div');
	divParent.setAttribute('id', 'worker'+obj[i].id);
	divParent.setAttribute('class', 'workerPhoto');
	//divParent.setAttribute('onclick', 'openWorkerProfile (event);');
	var divChild1 = document.createElement('div');
	var divChild2 = document.createElement('div');
	divChild2.setAttribute('class', 'thumbnail');
	var link = document.createElement('a');
	link.setAttribute('href', '#');
	divParent.setAttribute('onclick', 'event.preventDefault(); sendRequestGetCatalog (event);');
	var photoSrc = document.createElement('img');
	photoSrc.setAttribute('src', 'img/worker/' + obj[i].id + 'workerPhoto.jpg');
	photoSrc.setAttribute('alt', 'worker'+obj[i].id);
	photoSrc.setAttribute('style', 'width:100%');
	photoSrc.setAttribute('myData', obj[i].id);

	var divChild3 = document.createElement('div');
	divChild3.setAttribute('myData', obj[i].id);
	divChild3.innerHTML = obj[i].name + '<br>'+obj[i].descripton;
	link.appendChild(photoSrc);
	link.appendChild(divChild3);
	divChild2.appendChild(link);
	divChild1.appendChild(divChild2);
	divParent.appendChild(divChild1);
	photo.appendChild(divParent);
}
}

function sendRequestGetCatalog (event){
	console.log(event.target);
	var workerCatalogsRequest = Object.create(Request);
	workerCatalogsRequest.GetWorkerCatalogForOrder(event.target.getAttribute("mydata"));
}

function createTableWorkerCatalog (json){
	var rowNumber = 0;
	var mainDiv = document.getElementById('workerprofile');
	mainDiv.setAttribute("align", "right");

	while (mainDiv.firstChild) {
		mainDiv.removeChild(mainDiv.firstChild);
	}
	var table = document.createElement("table");
		table.setAttribute("class", "table table-hover ");
		table.setAttribute("id", "catalogsTable");
		table.setAttribute("workerId", json.workerProfileId);
	for (var i = 0; i < json.listSkills.length; i++) {
		var row = table.insertRow(rowNumber);
		var cell0 = row.insertCell(0);
		var cell1 = row.insertCell(1);
		cell1.innerHTML ="<b>" + json.listSkills[i].name + "</b>";
		var cell2 = row.insertCell(2);
		var cell3 = row.insertCell(3);
		var cell4 = row.insertCell(4);

		rowNumber++;
		console.log(json.listSkills[i].catalogList[0].name);

			for (var j = 0; j < json.listSkills[i].catalogList.length; j++) {
				var rowIn = table.insertRow(rowNumber);
				//console.log(json.listSkills[i].catalogList[j].name);
				var cell0 = rowIn.insertCell(0);
				cell0.innerHTML = json.listSkills[i].catalogList[j].catalogId;
				var cell1 = rowIn.insertCell(1);
				cell1.innerHTML = json.listSkills[i].catalogList[j].name;
				var cell2 = rowIn.insertCell(2);
				cell2.innerHTML = json.listSkills[i].catalogList[j].price + "грн";
				var cell3 = rowIn.insertCell(3);
				cell3.innerHTML = json.listSkills[i].catalogList[j].timeLead + "мин.";
				var input = document.createElement("input");
				input.setAttribute("type", "checkbox");
				input.setAttribute("id", json.listSkills[i].catalogList[j].catalogId );
				var cell4 = rowIn.insertCell(4);
				cell4.appendChild(input);

				rowNumber++;
			}
	}


	mainDiv.appendChild(table);

	var button = document.createElement("button");
	button.innerHTML = "Отправить запрос";
	button.setAttribute("onclick", "sendRequestForOrder();");

	mainDiv.appendChild(button);
	}

	function sendRequestForOrder () {
	var jsonObj = {};
	var catalogs = [];
	var table = document.getElementById("catalogsTable");
	var workerId = table.getAttribute("workerId");
	var checkBoxes = document.getElementsByTagName("input");
	
	for (var i = 0; i < checkBoxes.length; i++) {
		if (checkBoxes[i].checked) {
		catalogs.push(checkBoxes[i].getAttribute("id"));

	}
		checkBoxes[i].setAttribute("disabled","true");
	}
jsonObj.workerId = workerId;
jsonObj.catalogList = catalogs;

var workerFreeDateRequest = Object.create(Request);
workerFreeDateRequest.GetFreeDateForOrder(jsonObj);
	}
	
	function createTableWithDates (json) {

		var mainDiv = document.getElementById("workerfreedates");
			while (mainDiv.firstChild) {
				mainDiv.removeChild(mainDiv.firstChild);
			}
		var table = document.createElement("table");
			table.setAttribute("class", "table-sm table-hover");
			table.setAttribute("id", "freeDatesTable");
			table.setAttribute("align", "center");

		var rowNum = 0;

		var map = new Map(Object.entries(json));
		console.log(map);

		for (var [key, value] of map) {
			var row = table.insertRow(rowNum);
			var cell = row.insertCell(0);
			var cell1 = row.insertCell(1);
			var split = key.split("T");
			cell.innerHTML = "<b>" + split[0] + "</b>";
		  	rowNum++;
		  	for (var i = 0; i < value.length; i++) {
		  		var row = table.insertRow(rowNum);
				var cell = row.insertCell(0);
				var cell1 = row.insertCell(1);
				cell.innerHTML = moment(value[i]).format("HH : mm");
				var button = document.createElement("button");
				button.appendChild(document.createTextNode("Записаться"));
				button.setAttribute("onclick", "");
				cell1.appendChild(button);
		  	rowNum++;
		  	}
		}


		mainDiv.appendChild(table);
		}