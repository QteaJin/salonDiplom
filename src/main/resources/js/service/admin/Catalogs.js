function getSkillsForCatalog() {

	var createAllSKillsRequest = Object.create(RequestAdmin);
	createAllSKillsRequest.GetAllSkillsAdmin();

}

function createSelectForCatalog(json) {

	var selectMain = document.createElement("select");
	selectMain.setAttribute("class", "form-control");
	selectMain.setAttribute("onchange", "requestCatalogs();");
	selectMain.setAttribute("id", "selectedskill");

	var skillSelect = document.getElementById("catalogskillselect");
	while (skillSelect.firstChild) {
		skillSelect.removeChild(skillSelect.firstChild);
	}
	var selectDefault = document.createElement("option");
	selectDefault.setAttribute("value", "Выберите навык");
	selectDefault.setAttribute("selected", "selected");
	selectMain.appendChild(selectDefault);

	for (var i = 0; i < json.length; i++) {
		var select = document.createElement("option");
		select.setAttribute("value", json[i].skillsId);

		// if(i == 0){
		// select.setAttribute("selected", "selected");
		//
		// }
		var node = document.createTextNode(json[i].name);
		select.appendChild(node);
		selectMain.appendChild(select);
		skillSelect.appendChild(selectMain);
	}
}

function requestCatalogs(argument) {

	var skillId = document.getElementById('selectedskill').value;
	var buttonExist = document.getElementById("createNewCatalogButton");
	if (buttonExist) {
		buttonExist.parentNode.removeChild(buttonExist);

	}
	var createAllCatalogsBySkillRequest = Object.create(RequestAdmin);
	createAllCatalogsBySkillRequest.GetAllCatalogsBySkillAdmin(skillId);
}

function createCatalogTable(json) {
	var catalogTable = document.getElementById("catalogTable");
	while (catalogTable.firstChild) {
		catalogTable.removeChild(catalogTable.firstChild);
	}

	var skillId = document.getElementById('selectedskill').value;
	var buttonNew = document.createElement("button");
	buttonNew.setAttribute("class", "btn btn-primary");
	buttonNew.setAttribute("onclick", "createCatalogForm(event);");
	buttonNew.appendChild(document.createTextNode("Создать услугу"));
	buttonNew.setAttribute("selectedSkill", skillId);
	buttonNew.setAttribute("id", "createNewCatalogButton");

	var table = document.createElement("table");
	table.setAttribute("class", "table table-hover ");
	table.setAttribute("id", "tablecatalog");
	// table.setAttribute("onclick", "getcatalog(event);");
	var thead = document.createElement("thead");
	var trThread = document.createElement("tr");
	// trThread.setAttribute("onclick", "getcatalog(this);");
	var thThread1 = document.createElement("th");
	var thThread2 = document.createElement("th");
	var thThread3 = document.createElement("th");
	var thThread4 = document.createElement("th");
	thThread1.appendChild(document.createTextNode("Название услуги"));
	thThread2.appendChild(document.createTextNode("Стоимость"));
	thThread3.appendChild(document.createTextNode("Длительность, мин"));

	trThread.appendChild(thThread1);
	trThread.appendChild(thThread2);
	trThread.appendChild(thThread3);
	trThread.appendChild(thThread4);
	table.appendChild(trThread);
	var tbody = document.createElement("tbody");

	for (var i = 0; i < json.length; i++) {
		var tr = document.createElement("tr");
		tr.setAttribute("catalogId", json[i].catalogId)
		var td1 = document.createElement("td");
		var td2 = document.createElement("td");
		var td3 = document.createElement("td");
		td1.setAttribute("catalogname", json[i].name);
		td1.innerHTML = json[i].name;
		td2.setAttribute("cataloprice", json[i].price);
		td2.innerHTML = json[i].price;
		td3.setAttribute("catalotimeLead", json[i].timeLead);
		td3.innerHTML = json[i].timeLead;
		var button = document.createElement("button");
		button.setAttribute("onclick", "createCatalogForm(event);");
		button.setAttribute("fullInfo", JSON.stringify(json[i]));
		button.appendChild(document.createTextNode("Подробнее"));
		var td4 = document.createElement("td");
		td4.appendChild(button);

		tr.appendChild(td1);
		tr.appendChild(td2);
		tr.appendChild(td3);
		tr.appendChild(td4);

		tbody.appendChild(tr);

	}

	table.appendChild(tbody);
	catalogTable.appendChild(buttonNew);
	catalogTable.appendChild(table);
}

function createCatalogForm(event) {
	event.preventDefault();

	// var setButtonEnabled = document.getElementById("createNewCatalogButton");
	// setButtonEnabled.setAttribute("disabled", false);

	var infoText = event.target.getAttribute("fullInfo");
	var info = JSON.parse(infoText);

	var mainDiv = document.getElementById("changecatalog");

	while (mainDiv.firstChild) {
		mainDiv.removeChild(mainDiv.firstChild);
	}

	var catalogForm = document.createElement("form");
	catalogForm.setAttribute("id", "formcatalog");

	var inp1 = document.createElement("input");
	inp1.setAttribute("class", "form-control");
	inp1.setAttribute("type", "text");
	inp1.setAttribute("placeholder", "skillId");
	inp1.setAttribute("readonly", "");
	inp1.setAttribute("id", "catalogSkillId");

	var inp = document.createElement("input");
	inp.setAttribute("class", "form-control");
	inp.setAttribute("type", "text");
	inp.setAttribute("placeholder", "catalogId");
	inp.setAttribute("readonly", "");
	inp.setAttribute("id", "catalogId");

	var lable1 = document.createElement("lable");
	lable1.appendChild(document.createTextNode("Наименование:"));
	lable1.setAttribute("for", "catalogname");

	var inp2 = document.createElement("input");
	inp2.setAttribute("class", "form-control");
	inp2.setAttribute("type", "text");
	inp2.setAttribute("id", "catalogname");
	inp2.setAttribute("name", "catalogname");

	var lable2 = document.createElement("lable");
	lable2.appendChild(document.createTextNode("Описание:"));
	var textar = document.createElement("textarea");
	textar.setAttribute("class", "form-control");
	textar.setAttribute("rows", "3");
	textar.setAttribute("id", "catalogtextarea");
	textar.setAttribute("name", "catalogtextarea");

	var lable3 = document.createElement("lable");
	lable3.appendChild(document.createTextNode("Цена:"));
	var inp3 = document.createElement("input");
	inp3.setAttribute("class", "form-control");
	inp3.setAttribute("type", "text");
	inp3.setAttribute("id", "catalogprice");
	inp3.setAttribute("name", "catalogprice");

	var lable4 = document.createElement("lable");
	lable4.appendChild(document.createTextNode("Время, мин"));
	var inp4 = document.createElement("input");
	inp4.setAttribute("class", "form-control");
	inp4.setAttribute("type", "text");
	inp4.setAttribute("id", "catalogtime");
	inp4.setAttribute("name", "catalogtime");

	var button = document.createElement("button");

	// button.setAttribute("type", "submit");
	button.setAttribute("class", "btn btn-primary");
	button.setAttribute("onclick", "createEditCataolg(event);");
	button.appendChild(document.createTextNode("Добавить / Изменить"));

	catalogForm.appendChild(inp1);
	catalogForm.appendChild(inp);
	catalogForm.appendChild(lable1);
	catalogForm.appendChild(inp2);
	catalogForm.appendChild(lable2);
	catalogForm.appendChild(textar);
	catalogForm.appendChild(lable3);
	catalogForm.appendChild(inp3);
	catalogForm.appendChild(lable4);
	catalogForm.appendChild(inp4);
	catalogForm.appendChild(button);

	mainDiv.appendChild(catalogForm);

	if (info != null || info != undefined) {
		document.forms['formcatalog']['catalogSkillId'].value = info.skillId;
		document.forms['formcatalog']['catalogId'].value = info.catalogId;
		document.forms['formcatalog']['catalogname'].value = info.name;
		document.forms['formcatalog']['catalogtextarea'].value = info.description;
		document.forms['formcatalog']['catalogprice'].value = info.price;
		document.forms['formcatalog']['catalogtime'].value = info.timeLead;
	} else {

		var infoText = event.target.getAttribute("selectedSkill");

		document.forms['formcatalog']['catalogSkillId'].value = infoText;
	}

}

function createEditCataolg(event) {
	event.preventDefault();
	var fieldsCatalog = document.getElementById('formcatalog');
	var catalogForm = Object.create(CatalogBean);
	catalogForm.setCatalogId = fieldsCatalog.catalogId.value;
	catalogForm.setName = fieldsCatalog.catalogname.value;
	catalogForm.setDescription = fieldsCatalog.catalogtextarea.value;
	catalogForm.setPrice = fieldsCatalog.catalogprice.value;
	if(fieldsCatalog.catalogtime.value%30 == 0){
		catalogForm.setTimeLead = fieldsCatalog.catalogtime.value;
	}else{
		alert("Время должно быть кратным 30 минутам! Установлено значение по умолчанию 60 мин.");
		catalogForm.setTimeLead = 60;
	}
	
	catalogForm.setSkillId = fieldsCatalog.catalogSkillId.value;
	console.log(JSON.stringify(catalogForm));

	var createEditCatalogRequest = Object.create(RequestAdmin);
	createEditCatalogRequest.CreateEditCatalogAdmin(catalogForm);
}
function successCreateEditCatalog() {
	document.forms['formcatalog']['catalogSkillId'].value = "";
	document.forms['formcatalog']['catalogId'].value = "";
	document.forms['formcatalog']['catalogname'].value = "";
	document.forms['formcatalog']['catalogtextarea'].value = "";
	document.forms['formcatalog']['catalogprice'].value = "";
	document.forms['formcatalog']['catalogtime'].value = "";
}