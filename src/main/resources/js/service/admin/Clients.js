var maxNumClientsOnPage = 10;
	var pages = 0;
	var numberOfPage = 0;

	function getAllClients() {
		var getAllClientsRequest = Object.create(RequestAdmin);
		getAllClientsRequest.GetAllClientsRequestAdmin();
	}
	function createTableForClients () {

var clientsList = {};
	if (localStorage.getItem("clientsList")) {
		clientsList = JSON.parse(localStorage.getItem("clientsList"));
	}
		pages = clientsList.length / maxNumClientsOnPage;
		if(pages%1 != 0){
			pages = Math.trunc(pages) + 1;
		}
		var tableDiv = document.getElementById("clientsTable");
		while (tableDiv.firstChild) {
		tableDiv.removeChild(tableDiv.firstChild);
	}
		var table = document.createElement("table");
		table.setAttribute("class", "table table-hover ");
		table.setAttribute("id", "tableclients");
		var row0 = table.insertRow(0);

		var cell0 = row0.insertCell(0);
		cell0.innerHTML = "№";
		var cell1 = row0.insertCell(1);
		cell1.innerHTML = "Имя";
		var cell2 = row0.insertCell(2);
		cell2.innerHTML = "Телефон";
		var cell3 = row0.insertCell(3);
		cell3.innerHTML = "Email";
		var cell4 = row0.insertCell(4);
		cell4.innerHTML = "Статус";
		var cell5 = row0.insertCell(5);

		for (var i = 0; i < maxNumClientsOnPage; i++) {
			if(numberOfPage*maxNumClientsOnPage + i == clientsList.length){
				break;
			}
		var element = numberOfPage*maxNumClientsOnPage + i;
		var row = table.insertRow(i+1);
		var cell0 = row.insertCell(0);
		cell0.innerHTML = clientsList[element].id;
		var cell1 = row.insertCell(1);
		cell1.innerHTML = clientsList[element].name;
		var cell2 = row.insertCell(2);
		cell2.innerHTML = clientsList[element].phone;
		var cell3 = row.insertCell(3);
		cell3.innerHTML = clientsList[element].email;
		var cell4 = row.insertCell(4);
		cell4.innerHTML = clientsList[element].status;
		var cell5 = row.insertCell(5);
		var button = document.createElement("button");
			button.innerHTML = "Подробнее";
			button.setAttribute("clientId", clientsList[element].id);
			button.setAttribute("onclick", "createClientForm(event);");
		cell5.appendChild(button);
			//clientsList[numberOfPage*maxNumClientsOnPage + i];
			//console.log(clientsList[numberOfPage*maxNumClientsOnPage + i].phone);
		}

		tableDiv.appendChild(table);


		var pagins = document.getElementById("clientsPagination");

		if(!pagins.firstChild){

		for (var i = 0; i < pages; i++) {
			var button = document.createElement("button");
			button.setAttribute("page", i);
			button.setAttribute("onclick", "changePage(event);");
			//button.setAttribute("class", "defaultButton");
			button.innerHTML = i+1;
			pagins.appendChild(button);
		}
		}
	}

	function changePage (event) {
		event.stopPropagation();
		var buttonClass = document.querySelector(".chosenButton");
		if(buttonClass){
			buttonClass.setAttribute("class", "defaultButton");
		}
		console.log(event.target.getAttribute("page"));
		numberOfPage = event.target.getAttribute("page");
		event.target.setAttribute("class", "chosenButton");
		createTableForClients ();
	}
	
	function createClientForm (event){
		var mainDiv = document.getElementById("clientformcreate");
		while (mainDiv.firstChild) {
			mainDiv.removeChild(mainDiv.firstChild);
		}
		var inpMain = document.createElement("input");
		inpMain.setAttribute("class", "form-control");
		inpMain.setAttribute("type", "text");
		inpMain.setAttribute("placeholder", "Имя клиента");
		inpMain.setAttribute("readonly", "");
		inpMain.setAttribute("id", "clientName");
		
		var form = document.createElement("form");
		form.setAttribute("id", "clientform");

		if(event.target.getAttribute("clientId") != "new"){
			
			var clientId = event.target.getAttribute("clientId");
			var getClientRequest = Object.create(RequestAdmin);
			getClientRequest.GetClientRequestAdmin(clientId);
		}

		var inp2 = document.createElement("input");
		inp2.setAttribute("class", "form-control");
		inp2.setAttribute("type", "text");
		inp2.setAttribute("placeholder", "clientId");
		inp2.setAttribute("readonly", "");
		inp2.setAttribute("id", "clientId");
		
		var selectMain = document.createElement("select");
		selectMain.setAttribute("id", "selectedClientStatus");
		selectMain.setAttribute("class", "form-control");

			var selectActive = document.createElement("option");
			selectActive.setAttribute("value", "ACTIVE");
			selectActive.setAttribute("id", "ACTIVE");
			selectActive.innerHTML = "ACTIVE";
			//selectActive.setAttribute("selected", "selected");
			var selectNoActive = document.createElement("option");
			selectNoActive.setAttribute("value", "NOACTIVE");
			selectNoActive.setAttribute("id", "NOACTIVE");
			selectNoActive.innerHTML = "NOACTIVE";

			selectMain.appendChild(selectActive);
			selectMain.appendChild(selectNoActive);

		var lable1 = document.createElement("lable");
		lable1.appendChild(document.createTextNode("Имя:"));
		lable1.setAttribute("for", "client_name");
	
		var inp3 = document.createElement("input");
		inp3.setAttribute("class", "form-control");
		inp3.setAttribute("type", "text");
		inp3.setAttribute("id", "client_name");
		inp3.setAttribute("name", "client_name");
		inp3.setAttribute("placeholder", "Имя клиента");
		
		var lable2 = document.createElement("lable");
		lable2.appendChild(document.createTextNode("Телефон:"));
		lable2.setAttribute("for", "clientphone");
	
		var inp4 = document.createElement("input");
		inp4.setAttribute("class", "form-control");
		inp4.setAttribute("type", "text");
		inp4.setAttribute("id", "clientphone");
		inp4.setAttribute("name", "clientphone");
		inp4.setAttribute("placeholder", "Телефон клиента");
		
		var lable3 = document.createElement("lable");
		lable3.appendChild(document.createTextNode("Email:"));
		lable3.setAttribute("for", "clientemail");
	
		var inp5 = document.createElement("input");
		inp5.setAttribute("class", "form-control");
		inp5.setAttribute("type", "text");
		inp5.setAttribute("id", "clientemail");
		inp5.setAttribute("name", "clientemail");
		inp5.setAttribute("placeholder", "Email клиента");
		
		var lable4 = document.createElement("lable");
		lable4.appendChild(document.createTextNode("Login:"));
		lable4.setAttribute("for", "clientlogin");
	
		var inp6 = document.createElement("input");
		inp6.setAttribute("class", "form-control");
		inp6.setAttribute("type", "text");
		inp6.setAttribute("id", "clientlogin");
		inp6.setAttribute("name", "clientlogin");
		inp6.setAttribute("placeholder", "Логин клиента");
		
		var lable5 = document.createElement("lable");
		lable5.appendChild(document.createTextNode("Password:"));
		lable5.setAttribute("for", "clientpassword");
		
		var inp7 = document.createElement("input");
		inp7.setAttribute("class", "form-control");
		inp7.setAttribute("type", "text");
		inp7.setAttribute("id", "clientpassword");
		inp7.setAttribute("name", "clientpassword");
		inp7.setAttribute("placeholder", "Пароль клиента");
		
		var lable6 = document.createElement("lable");
		lable6.appendChild(document.createTextNode("Описание:"));
		lable6.setAttribute("for", "clienttextarea");
		
		var textar = document.createElement("textarea");
		textar.setAttribute("class", "form-control");
		textar.setAttribute("rows", "3");
		textar.setAttribute("id", "clienttextarea");
		textar.setAttribute("name", "clienttextarea");
		
			
		var buttonCreateEdit = document.createElement("button");
		buttonCreateEdit.setAttribute("onclick", "createEditClient(event);");
		buttonCreateEdit.setAttribute("class", "btn btn-primary");
		buttonCreateEdit.appendChild(document.createTextNode("Изменить/Создать"));

		
		form.appendChild(inpMain);
		form.appendChild(inp2);
		form.appendChild(selectMain);
		form.appendChild(lable1);
		form.appendChild(inp3);
		form.appendChild(lable2);
		form.appendChild(inp4);
		form.appendChild(lable3);
		form.appendChild(inp5);
		form.appendChild(lable4);
		form.appendChild(inp6);
		form.appendChild(lable5);
		form.appendChild(inp7);
		form.appendChild(lable6);
		form.appendChild(textar);
		


		mainDiv.appendChild(form);
		mainDiv.appendChild(buttonCreateEdit);
	}
	
function setClientDataToForm(json){
	
	document.forms['clientform']['clientId'].value = json.id;
	document.forms['clientform']['clientName'].value = json.name;
	document.forms['clientform']['client_name'].value = json.name;
	document.forms['clientform']['clientphone'].value = json.phone;
	document.forms['clientform']['clientemail'].value = json.email;
	document.forms['clientform']['clientlogin'].value = json.login;
	document.forms['clientform']['clientpassword'].value = json.password;
	document.forms['clientform']['clienttextarea'].value = json.description;
	var status = document.forms['clientform']['selectedClientStatus'].value
	if(status != json.status){
		var onStatus = document.getElementById(json.status + "");
		onStatus.setAttribute("selected", "selected");
	}

	
}

function createEditClient(event){
	event.stopPropagation();
	//send req for changes
	var jsonObj = {};
	jsonObj.id = document.forms['clientform']['clientId'].value;
	jsonObj.name = document.forms['clientform']['client_name'].value;
	jsonObj.phone = document.forms['clientform']['clientphone'].value;
	jsonObj.email = document.forms['clientform']['clientemail'].value;
	jsonObj.login = document.forms['clientform']['clientlogin'].value;
	jsonObj.password = document.forms['clientform']['clientpassword'].value;
	jsonObj.description = document.forms['clientform']['clienttextarea'].value;
	jsonObj.status = document.forms['clientform']['selectedClientStatus'].value;
	
	if(jsonObj.id == ""){
		var sendClientCreateRequest = Object.create(RequestAdmin);
		sendClientCreateRequest.RegistrationNewClient(jsonObj);
	}else{
		var sendClientChangeRequest = Object.create(RequestAdmin);
		sendClientChangeRequest.SendClientChangeRequestAdmin(jsonObj);
	}
}