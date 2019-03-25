function savesalon(event){
	event.preventDefault();
	var fieldsSalon = document.getElementById('salonForm');
	var salonForm = Object.create(SalonBean);
	salonForm.setId = fieldsSalon.salonid.value;
	salonForm.setName = fieldsSalon.salonname.value;
	salonForm.setDescription = fieldsSalon.salondescr.value;
	salonForm.setCountry = fieldsSalon.saloncountry.value;
	salonForm.setCity = fieldsSalon.saloncity.value;
	salonForm.setStreet = fieldsSalon.salonstreet.value;
	
	console.log(JSON.stringify(salonForm));
	
	var createSalonRequest = Object.create(RequestAdmin);
	createSalonRequest.CreateSalon(salonForm);
}

function successCreateSalon(){
	document.forms['salonForm']['salonid'].value = "";
	document.forms['salonForm']['salonname'].value = "";
	document.forms['salonForm']['salondescr'].value = "";
	document.forms['salonForm']['saloncountry'].value = "";
	document.forms['salonForm']['saloncity'].value = "";
	document.forms['salonForm']['salonstreet'].value = "";
	
	alert("Изменения прошли успешно");
}

function getsalons(event){ //get all salons
	event.preventDefault();
	
	var createAllSalonRequest = Object.create(RequestAdmin);
	createAllSalonRequest.GetAllSalonsAdmin();
	
}

function displayAllSalons(json){
	
	var allSalonDiv = document.getElementById("salonslist"); //remove previous nodes
	while (allSalonDiv.firstChild) {
		allSalonDiv.removeChild(allSalonDiv.firstChild);
	}
	allSalonDiv.setAttribute('onclick', 'event.preventDefault(); choosesalon (event);');
	

	for (var i = 0; i < json.length; i++) {
		var div = document.createElement("div");
		div.setAttribute("salonid", json[i].id);
		div.setAttribute("class", "shadow p-4 mb-4 bg-white"); //
		var textName = document.createTextNode(json[i].name);
		var textAddress = document.createTextNode(json[i].city + " , " + json[i].street);
		div.appendChild(textName);
		div.appendChild(document.createElement("br"));
		div.appendChild(textAddress);

		allSalonDiv.appendChild(div);
	}
}

function choosesalon(event) {
	event.stopPropagation();		

let blabla = event.target.getAttribute('salonid');
console.log(blabla);
}