
function getSalonsToChooseWorker(){
	
	var createAllSalonRequest = Object.create(RequestAdmin);
	createAllSalonRequest.GetAllSalonsAdmin();
}

function displayAllSalonsToChoseWorker(json){
	
	var selectMain = document.createElement("select");
	selectMain.setAttribute("onchange", "requestWorkers(event);");
	selectMain.setAttribute("id", "selectedsalon");
	selectMain.setAttribute("class", "form-control");

	var skillSelect = document.getElementById("salonsforworkers");
	while (skillSelect.firstChild) {
		skillSelect.removeChild(skillSelect.firstChild);
	}
	var selectDefault = document.createElement("option");
	selectDefault.setAttribute("value", "Выберите салон");
	selectDefault.setAttribute("selected", "selected");
	selectMain.appendChild(selectDefault);

	for (var i = 0; i < json.length; i++) {
		var select = document.createElement("option");
		select.setAttribute("value", json[i].id);
		var node = document.createTextNode(json[i].name);
		select.appendChild(node);
		selectMain.appendChild(select);
		skillSelect.appendChild(selectMain);
	}

}

function requestWorkers(event){
	
	
	var buttonExist = document.getElementById("createNewWorkerButton");
	if (buttonExist) {
		buttonExist.parentNode.removeChild(buttonExist);
		
	}
	var workerFormDiv = document.getElementById('workerform'); // delete form on change salon
	while (workerFormDiv.firstChild) {
		workerFormDiv.removeChild(workerFormDiv.firstChild);
	}
	
	var salonId = document.getElementById('selectedsalon').value;
	var createNewWorker = document.getElementById('createnewworker');
	var info = {id: 0};
		
	var buttonNew = document.createElement("button");
	buttonNew.setAttribute("class", "btn btn-primary");
	buttonNew.setAttribute("onclick", "createWorkerForm(event);");
	buttonNew.appendChild(document.createTextNode("Создать нового работника"));
	buttonNew.setAttribute("selectedSalon", salonId);
	buttonNew.setAttribute("id", "createNewWorkerButton");
	buttonNew.setAttribute("fullInfo", JSON.stringify(info));
	
	createNewWorker.appendChild(buttonNew);
	
	var createAllWorkersBySalonRequest = Object.create(RequestAdmin);
	createAllWorkersBySalonRequest.GetWorkerBySalon(salonId);
}
function createTableForWorkers(json) {
	console.log(json);
	var workersTableDiv = document.getElementById('createtableforworkers');
	while (workersTableDiv.firstChild) {
		workersTableDiv.removeChild(workersTableDiv.firstChild);
	}
	
	var table = document.createElement("table");
	table.setAttribute("class", "table table-hover ");
	table.setAttribute("id", "tableworkers");
	var thead = document.createElement("thead");
	var trThread = document.createElement("tr");
	var thThread1 = document.createElement("th");
	var thThread2 = document.createElement("th");
	var thThread3 = document.createElement("th");

	thThread1.appendChild(document.createTextNode("ID сотрудника"));
	thThread2.appendChild(document.createTextNode("Имя сотрудника"));

	trThread.appendChild(thThread1);
	trThread.appendChild(thThread2);
	trThread.appendChild(thThread3);

	table.appendChild(trThread);
	
	var tbody = document.createElement("tbody");
	for (var i = 0; i < json.length; i++) {
	var row = tbody.insertRow(i);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	cell1.innerHTML = json[i].id;
	cell2.innerHTML = json[i].name;
	
	var button = document.createElement("button");
	button.setAttribute("onclick", "createWorkerForm(event);");
	button.setAttribute("fullInfo", JSON.stringify(json[i]));
	button.appendChild(document.createTextNode("Подробнее"));
	
	cell3.appendChild(button);
	}
	table.appendChild(tbody);
	workersTableDiv.appendChild(table);
}

function createWorkerForm(event){
	event.preventDefault();
	var workerFormDiv = document.getElementById('workerform');
	while (workerFormDiv.firstChild) {
		workerFormDiv.removeChild(workerFormDiv.firstChild);
	}
	var infoText = event.target.getAttribute("fullInfo");
	var info = JSON.parse(infoText);
	console.log(info);
	
	var inpMain = document.createElement("input");
	inpMain.setAttribute("class", "form-control");
	inpMain.setAttribute("type", "text");
	inpMain.setAttribute("placeholder", "Имя сотрудника");
	inpMain.setAttribute("readonly", "");
	inpMain.setAttribute("id", "workerName");
	
	workerFormDiv.appendChild(inpMain);
	
	var workerForm = document.createElement("form");
	workerForm.setAttribute("id", "formworker");
	

	var buttonProfileColloapse = document.createElement("button"); //profile collapse button
	buttonProfileColloapse.setAttribute("data-toggle", "collapse");
	buttonProfileColloapse.setAttribute("data-target", "#workerprofile");
	buttonProfileColloapse.setAttribute("class", "btn btn-outline-dark btn-block");
	buttonProfileColloapse.setAttribute("type", "button");
	buttonProfileColloapse.appendChild(document.createTextNode("Профиль сотрудника"));
	
	var divProfileColloapse = document.createElement("div"); //profile div start
	divProfileColloapse.setAttribute("id", "workerprofile");
	divProfileColloapse.setAttribute("class", "collapse");
	
	
	var inp1 = document.createElement("input");
	inp1.setAttribute("class", "form-control");
	inp1.setAttribute("type", "text");
	inp1.setAttribute("placeholder", "salonId");
	inp1.setAttribute("readonly", "");
	inp1.setAttribute("id", "workerSalonId");

	var inp2 = document.createElement("input");
	inp2.setAttribute("class", "form-control");
	inp2.setAttribute("type", "text");
	inp2.setAttribute("placeholder", "workerId");
	inp2.setAttribute("readonly", "");
	inp2.setAttribute("id", "workerId");
	
	var lable1 = document.createElement("lable");
	lable1.appendChild(document.createTextNode("Имя:"));
	lable1.setAttribute("for", "workername");

	var inp3 = document.createElement("input");
	inp3.setAttribute("class", "form-control");
	inp3.setAttribute("type", "text");
	inp3.setAttribute("id", "workername");
	inp3.setAttribute("name", "workername");
	inp3.setAttribute("placeholder", "Имя сотрудника");
	
	var lable2 = document.createElement("lable");
	lable2.appendChild(document.createTextNode("Телефон:"));
	lable2.setAttribute("for", "workerphone");

	var inp4 = document.createElement("input");
	inp4.setAttribute("class", "form-control");
	inp4.setAttribute("type", "text");
	inp4.setAttribute("id", "workerphone");
	inp4.setAttribute("name", "workerphone");
	inp4.setAttribute("placeholder", "Телефон сотрудника");
	
	var lable3 = document.createElement("lable");
	lable3.appendChild(document.createTextNode("Email:"));
	lable3.setAttribute("for", "workeremail");

	var inp5 = document.createElement("input");
	inp5.setAttribute("class", "form-control");
	inp5.setAttribute("type", "text");
	inp5.setAttribute("id", "workeremail");
	inp5.setAttribute("name", "workeremail");
	inp5.setAttribute("placeholder", "Email сотрудника");
	
	var lable4 = document.createElement("lable");
	lable4.appendChild(document.createTextNode("Login:"));
	lable4.setAttribute("for", "workerlogin");

	var inp6 = document.createElement("input");
	inp6.setAttribute("class", "form-control");
	inp6.setAttribute("type", "text");
	inp6.setAttribute("id", "workerlogin");
	inp6.setAttribute("name", "workerlogin");
	inp6.setAttribute("placeholder", "Логин сотрудника");
	
	var lable5 = document.createElement("lable");
	lable5.appendChild(document.createTextNode("Password:"));
	lable5.setAttribute("for", "workerpassword");
	
	var inp7 = document.createElement("input");
	inp7.setAttribute("class", "form-control");
	inp7.setAttribute("type", "text");
	inp7.setAttribute("id", "workerpassword");
	inp7.setAttribute("name", "workerpassword");
	inp7.setAttribute("placeholder", "Пароль сотрудника");
	
	var lable6 = document.createElement("lable");
	lable6.appendChild(document.createTextNode("Описание:"));
	lable6.setAttribute("for", "workertextarea");
	
	var textar = document.createElement("textarea");
	textar.setAttribute("class", "form-control");
	textar.setAttribute("rows", "3");
	textar.setAttribute("id", "workertextarea");
	textar.setAttribute("name", "workertextarea");
	
	var lable7 = document.createElement("lable");
	lable7.appendChild(document.createTextNode("Краткое описание:"));
	lable7.setAttribute("for", "workertextareashort");
	
	var textar1 = document.createElement("textarea");
	textar1.setAttribute("class", "form-control");
	textar1.setAttribute("rows", "2");
	textar1.setAttribute("id", "workertextareashort");
	textar1.setAttribute("name", "workertextareashort");
	
	var buttonCreateEdit = document.createElement("button");
	buttonCreateEdit.setAttribute("onclick", "createEditWorker(event);");
	buttonCreateEdit.setAttribute("class", "btn btn-primary");
	buttonCreateEdit.appendChild(document.createTextNode("Изменить/Создать"));
	
	divProfileColloapse.appendChild(inp1);
	divProfileColloapse.appendChild(inp2);
	divProfileColloapse.appendChild(lable1);
	divProfileColloapse.appendChild(inp3);
	divProfileColloapse.appendChild(lable2);
	divProfileColloapse.appendChild(inp4);
	divProfileColloapse.appendChild(lable3);
	divProfileColloapse.appendChild(inp5);
	divProfileColloapse.appendChild(lable4);
	divProfileColloapse.appendChild(inp6);
	divProfileColloapse.appendChild(lable5);
	divProfileColloapse.appendChild(inp7);
	divProfileColloapse.appendChild(lable6);
	divProfileColloapse.appendChild(textar);
	divProfileColloapse.appendChild(lable7);
	divProfileColloapse.appendChild(textar1);
	divProfileColloapse.appendChild(buttonCreateEdit);
	
	workerForm.appendChild(buttonProfileColloapse);
	workerForm.appendChild(divProfileColloapse); // profile div end
	
	//workerForm.appendChild(buttonCreateEdit);
	workerFormDiv.appendChild(workerForm);
	
	var salonId = document.getElementById('selectedsalon').value;
	document.forms['formworker']['workerSalonId'].value = salonId;
	
	if(info.id != 0){ //if worker exist
		var getWorkerEditRequest = Object.create(RequestAdmin);
		getWorkerEditRequest.GetWorkerRequest(info.id);
	}
}

function setWorkerDataToForm(json){
	var salonId = document.getElementById('selectedsalon').value;	
	var inputName = document.getElementById('workerName');
	inputName.value = json.name;
	document.forms['formworker']['workerSalonId'].value = salonId;
	document.forms['formworker']['workerId'].value = json.workerId;
	document.forms['formworker']['workername'].value = json.name;
	document.forms['formworker']['workerphone'].value = json.phone;
	document.forms['formworker']['workeremail'].value = json.email;
	document.forms['formworker']['workerlogin'].value = json.login;
	document.forms['formworker']['workerpassword'].value = json.password;
	document.forms['formworker']['workertextarea'].value = json.description;
	document.forms['formworker']['workertextareashort'].value = json.shortDescription;
	
	createWorkerSkills(json);
	
}

function createWorkerSkills(json){
	
	var mainDiv = document.getElementById("workerform");
	
	var buttonSkillsCollapse = document.createElement("button"); //skills collapse button
	buttonSkillsCollapse.setAttribute("data-toggle", "collapse");
	buttonSkillsCollapse.setAttribute("data-target", "#workerskills");
	buttonSkillsCollapse.setAttribute("class", "btn btn-outline-dark btn-block");
	buttonSkillsCollapse.setAttribute("type", "button");
	buttonSkillsCollapse.appendChild(document.createTextNode("Умения сотрудника"));
	
	var divSkillsCollapse = document.createElement("div"); //skills div
	divSkillsCollapse.setAttribute("id", "workerskills");
	divSkillsCollapse.setAttribute("class", "collapse");
	
	mainDiv.appendChild(buttonSkillsCollapse);
	mainDiv.appendChild(divSkillsCollapse);
	
	var divskills = document.getElementById("workerskills");
	
	var table = document.createElement("table");
	table.setAttribute("class", "table table-hover ");
	table.setAttribute("id", "tableworkerskills");
	var row = table.insertRow(0);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);

	// Add some text to the new cells:
	cell1.innerHTML = "Id услуги";
	cell2.innerHTML = "Название услуги";


	for (var i = 0; i < json.usedSkills.length; i++) {
		var data = {};
		data.workerId = json.workerId;
		data.skillsId = json.usedSkills[i].skillsId;
		data.change = "del";

		var row = table.insertRow(i+1);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);

		cell1.innerHTML = json.usedSkills[i].skillsId;
		cell2.innerHTML = json.usedSkills[i].name;

		var buttonDel = document.createElement("button");
		buttonDel.setAttribute("info", JSON.stringify(data));
		buttonDel.appendChild(document.createTextNode("Отключить"));
		buttonDel.setAttribute("onclick", "sendRequestToChangeSkill(event);");
		
		cell3.appendChild(buttonDel);

	}
	var row = table.insertRow(json.usedSkills.length+1);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	cell2.innerHTML = "<b>Неподключенные умения</b>";

	for (var i = 0; i < json.notUsedSkills.length; i++) {

		var data = {};
		data.workerId = json.workerId;
		data.skillsId = json.notUsedSkills[i].skillsId;
		data.change = "add";

		var row = table.insertRow(json.usedSkills.length+2+i);
		var cell1 = row.insertCell(0);
		var cell2 = row.insertCell(1);
		var cell3 = row.insertCell(2);

		cell1.innerHTML = json.notUsedSkills[i].skillsId;
		cell2.innerHTML = json.notUsedSkills[i].name;

		var buttonAdd = document.createElement("button");
		buttonAdd.setAttribute("info", JSON.stringify(data));
		buttonAdd.appendChild(document.createTextNode("Подключить"));
		buttonAdd.setAttribute("onclick", "sendRequestToChangeSkill(event);");
		cell3.appendChild(buttonAdd);

	}

	divskills.appendChild(table);
}

function sendRequestToChangeSkill(event){
	event.stopPropagation();
	var jsonObj = event.target.getAttribute("info");
	
	var updWorkerListSkillRequest = Object.create(RequestAdmin);
	updWorkerListSkillRequest.UpdateWorkerSkillList(jsonObj);
	
}

function createEditWorker(event){
	event.preventDefault();
	
	var workerFormBean = Object.create(WorkerBean); //get data from worker form
	workerFormBean.salonId = document.forms['formworker']['workerSalonId'].value;
	workerFormBean.workerId = document.forms['formworker']['workerId'].value;
	workerFormBean.name = document.forms['formworker']['workername'].value;
	workerFormBean.phone = document.forms['formworker']['workerphone'].value;
	workerFormBean.email = document.forms['formworker']['workeremail'].value;
	workerFormBean.login = document.forms['formworker']['workerlogin'].value;
	workerFormBean.password = document.forms['formworker']['workerpassword'].value;
	workerFormBean.description = document.forms['formworker']['workertextarea'].value;
	workerFormBean.shortDescription = document.forms['formworker']['workertextareashort'].value;	//get data from worker form
	
	if(workerFormBean.workerId == ""){
		console.log("send request new worker");
		var createWorkerData = Object.create(RequestAdmin);
		createWorkerData.CreateNewWorker(workerFormBean);
	}else{
		console.log("send request update worker");
		var updateWorkerData = Object.create(RequestAdmin);
		updateWorkerData.UpdateWorkerInfo(workerFormBean);
	}
}

