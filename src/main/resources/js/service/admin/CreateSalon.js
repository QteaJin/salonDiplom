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
	
	var textBlockInfo = "Изменения внесены";
	infoBlock(textBlockInfo);
}

function getsalons(event){ //get all salons
	event.preventDefault();
	
	var createAllSalonRequest = Object.create(RequestAdmin);
	createAllSalonRequest.GetAllSalonsAdmin();
	
}

function displayAllSalons(json){
	
	localStorage.setItem("salons", JSON.stringify(json)); //save object to storage
	
	var allSalonDiv = document.getElementById("salonslist"); //remove previous nodes
	while (allSalonDiv.firstChild) {
		allSalonDiv.removeChild(allSalonDiv.firstChild);
	}
	allSalonDiv.setAttribute('onclick', 'event.preventDefault(); choosesalon (event);');
	

	for (var i = 0; i < json.length; i++) {
		var div = document.createElement("div");
		div.setAttribute("salonid", json[i].id);
		div.setAttribute("class", "shadow p-4 mb-4 bg-white"); //
		var spanSalon = document.createElement("span");
		spanSalon.setAttribute("class", "spansalons");
		spanSalon.setAttribute("salonid", json[i].id);
		var textName = document.createTextNode(json[i].name);
		var textAddress = document.createTextNode(json[i].city + " , " + json[i].street);
		spanSalon.appendChild(textName);
		spanSalon.appendChild(document.createElement("br"));
		spanSalon.appendChild(textAddress);
		div.appendChild(spanSalon);
		allSalonDiv.appendChild(div);
	}
}

function choosesalon(event) {
	event.stopPropagation();		
	var salons = {};
	if (localStorage.getItem("salons")) {
		salons = JSON.parse(localStorage.getItem("salons"));
	}
	console.log(salons);
let salonId = event.target.getAttribute('salonid') - 1;
console.log(salonId);
document.forms['salonForm']['salonid'].value = salons[salonId].id;
document.forms['salonForm']['salonname'].value = salons[salonId].name;
document.forms['salonForm']['salondescr'].value = salons[salonId].description;
document.forms['salonForm']['saloncountry'].value = salons[salonId].country;
document.forms['salonForm']['saloncity'].value = salons[salonId].city;
document.forms['salonForm']['salonstreet'].value = salons[salonId].street;
}