
function saveEditSkill(event){
	event.preventDefault();
	var fieldsSkill = document.getElementById('skillForm');
	var skillForm = Object.create(SkillBean);
	skillForm.setId = fieldsSkill.skillid.value;
	skillForm.setName = fieldsSkill.skillname.value;
	console.log(skillForm);
	
	var createSkillRequest = Object.create(RequestAdmin);
	createSkillRequest.CreateSkill(skillForm);
}

function sendRequestFindAllSKills(event){
	event.preventDefault();
	document.forms['skillForm']['skillid'].value = "";
	document.forms['skillForm']['skillname'].value = "";
	var createAllSKillsRequest = Object.create(RequestAdmin);
	createAllSKillsRequest.GetAllSkillsAdmin();
}

function getskill (event) {
			 event.stopPropagation();
			 let skillid = event.target.getAttribute('skillid');
			 
			 document.forms['skillForm']['skillid'].value = skillid;
			
			 document.forms['skillForm']['skillname'].value = event.target.innerText;
			
		}

function createSkillsTable (json) {
	var skillTable = document.getElementById("skilltable");
			while (skillTable.firstChild) {
			skillTable.removeChild(skillTable.firstChild);}

	var table = document.createElement("table");
	table.setAttribute("class" , "table table-hover ");
	table.setAttribute("id", "tableskill");
	table.setAttribute("onclick", "getskill(event);");
	var thead = document.createElement("thead");
	var trThread = document.createElement("tr");
	var thThread = document.createElement("th");
	thThread.appendChild(document.createTextNode("Название услуги"));
	trThread.appendChild(thThread);
	table.appendChild(trThread);
	var tbody = document.createElement("tbody");


	for (var i = 0; i < json.length; i++) {
		var tr = document.createElement("tr");
		var td = document.createElement("td");
		td.setAttribute("skillid", json[i].skillsId);
		td.innerHTML = json[i].name;
		tr.appendChild(td);
		tbody.appendChild(tr);

	}

	table.appendChild(tbody);
	skillTable.appendChild(table);
}