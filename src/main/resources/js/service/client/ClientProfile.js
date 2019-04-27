


function createClientPersonalForm (){
		
		
		var mainDiv = document.getElementById("myprofile");
		while (mainDiv.firstChild) {
			mainDiv.removeChild(mainDiv.firstChild);
		}
		
		var form = document.createElement("form");
		form.setAttribute("id", "clientcustomform");

		var lable1 = document.createElement("lable");
		lable1.appendChild(document.createTextNode("Имя:"));
		lable1.setAttribute("for", "clientname");
	
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
		buttonCreateEdit.appendChild(document.createTextNode("Изменить"));

		
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
		
		var getProfileRequest = Object.create(Request);
		getProfileRequest.SendClientProfileRequest();
	}