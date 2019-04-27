'use strict';

const URL_DEFAULT = ""; // https://vip-salon.herokuapp.com
const URL_DEFAULT_LOCAL = "http://localhost:8080";
const CREATE_SALON = "/salon/create";
const GET_ALL_SALONS = "/salon/admin/all"; 
const CREATE_EDIT_SKILL = "/skill/create";
const GET_ALL_SKILLS = "/skill/admin/all";
const GET_CATALOGS_BY_SKILL = "/catalog/admin/";
const CREATE_EDIT_CATALOG = "/catalog/create";
const GET_WORKER_URL = URL_DEFAULT + "/worker/?salonId="; //get all workers by salon id
const GET_WORKER_BY_ID = URL_DEFAULT + "/worker/admin/"; //get worker by ID
const UPDATE_WORKER_DATA = URL_DEFAULT + "/worker/admin/update";
const CREATE_NEW_WORKER = URL_DEFAULT + "/auth/registr/worker";
const UPDATE_WORKER_SKILL_LIST = URL_DEFAULT + "/worker/admin/skill";
const ADD_WORKING_DAYS = URL_DEFAULT + "/worker/admin/addDays";
const GET_WORKING_DAYS = URL_DEFAULT + "/worker/admin/getDays/";
const DEL_WORKING_DAYS = URL_DEFAULT + "/worker/admin/delDays/";
const GET_ALL_CLIENTS = URL_DEFAULT + "/client/admin/all";
const GET_CLIENT_PROFILE = URL_DEFAULT + "/client/admin/";
const CHANGE_CLIENT_PROFILE = URL_DEFAULT + "/client/admin/changeProfile";
const CLIENT_REGISTRATION_REQUEST = URL_DEFAULT + "/auth/registr/client";

var RequestAdmin = {};

RequestAdmin.CreateSalon = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var salon = JSON.parse(xhr.responseText);
			console.log(salon);
			successCreateSalon();

		}
	};

	xhr.open("POST", CREATE_SALON, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.GetAllSalonsAdmin = function() {
	var xhr = new XMLHttpRequest();
	var url = GET_ALL_SALONS;

	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);
			displayAllSalons(json);
			displayAllSalonsToChoseWorker(json);
		}
	};
	xhr.send();
};

RequestAdmin.CreateSkill = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var salon = JSON.parse(xhr.responseText);
			console.log(salon);
			alert("Услуга создана / изменена успешно");
			sendRequestFindAllSKills(event);

		}
	};

	xhr.open("POST", CREATE_EDIT_SKILL, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.GetAllSkillsAdmin = function() {
	var xhr = new XMLHttpRequest();
	var url = GET_ALL_SKILLS;

	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);
			createSkillsTable(json);
			createSelectForCatalog(json);

		}
	};
	xhr.send();
};

RequestAdmin.GetAllCatalogsBySkillAdmin = function(skillId) {
	var xhr = new XMLHttpRequest();
	var url = GET_CATALOGS_BY_SKILL + skillId;

	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function() {
		if (xhr.readyState === 4 && xhr.status === 200) {
			var json = JSON.parse(xhr.responseText);

			if (json.length > 0 && json[0].token == "error") { // redirect if
																// token error
																// to login page

				location.href = '/login';
				return;
			} else {
				console.log(json);
				createCatalogTable(json);
			}

		}
	};
	xhr.send();
};

// CreateEditCatalogAdmin(catalogForm)
RequestAdmin.CreateEditCatalogAdmin = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var catalogBean = JSON.parse(xhr.responseText);
			console.log(catalogBean);
			alert("Создание / Изменение услуги прошло успешно");
			requestCatalogs();
			successCreateEditCatalog();

		}
	};

	xhr.open("POST", CREATE_EDIT_CATALOG, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.GetWorkerBySalon = function(salonId){
	var xhr = new XMLHttpRequest();
	var url = GET_WORKER_URL + salonId;
	console.log(url);
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        createTableForWorkers(json);
	    }
	};
	xhr.send();
};

RequestAdmin.GetWorkerRequest = function(workerId){
	var xhr = new XMLHttpRequest();
	var url = GET_WORKER_BY_ID + workerId;
	console.log(url);
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        console.log(json);
	        setWorkerDataToForm(json);
	        
	    }
	};
	xhr.send();
};

RequestAdmin.UpdateWorkerInfo = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var workerBean = JSON.parse(xhr.responseText);
			console.log(workerBean);
			if(workerBean.workerId == null){
				alert("Что-то пошло не так. Изменения не внесены");
			}else{
				alert("Создание / Изменение сотрудника прошло успешно");
			}

		}
	};

	xhr.open("POST", UPDATE_WORKER_DATA, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.CreateNewWorker = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var workerBean = JSON.parse(xhr.responseText);
			console.log(workerBean);
			
			if(!workerBean){
				alert("Что-то пошло не так. Изменения не внесены");
			}else{
				alert("Создание / Изменение сотрудника прошло успешно");
				requestWorkers(event);
			}
		}
	};

	xhr.open("POST", CREATE_NEW_WORKER, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.UpdateWorkerSkillList = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = JSON.parse(xhr.responseText);
			console.log(result);
			if(!result){
				alert("Что-то пошло не так. Изменения не внесены");
			}else{
				alert("Изменения прошли успешно");
				requestWorkers(event);
			}

		}
	};

	xhr.open("POST", UPDATE_WORKER_SKILL_LIST, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send((jsonObject));
};

RequestAdmin.AddWorkingDays = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = JSON.parse(xhr.responseText);
			console.log(result);
			createschedulecalendar ();
		}
	};

	xhr.open("POST", ADD_WORKING_DAYS, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.GetWorkingDays = function(workerId){
	var xhr = new XMLHttpRequest();
	var url = GET_WORKING_DAYS + workerId;
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        setWorkingDaysToCallendar(json);
	       
	    }
	};
	xhr.send();
};
RequestAdmin.DelWorkingDays = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = JSON.parse(xhr.responseText);
			createschedulecalendar ();
		}
	};

	xhr.open("POST", DEL_WORKING_DAYS, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.GetAllClientsRequestAdmin = function(){
	var xhr = new XMLHttpRequest();
	var url = GET_ALL_CLIENTS;
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        localStorage.setItem("clientsList", JSON.stringify(json)); // saving to local storage
	        createTableForClients ();
	    }
	};
	xhr.send();
};

RequestAdmin.GetClientRequestAdmin = function(clientId){
	var xhr = new XMLHttpRequest();
	var url = GET_CLIENT_PROFILE + clientId;
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        setClientDataToForm(json);
	    }
	};
	xhr.send();
};

RequestAdmin.SendClientChangeRequestAdmin = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = JSON.parse(xhr.responseText);
			if(!result){
				alert("Что-то пошло не так. Изменения не внесены");
			}else{
				alert("Изменения прошли успешно");
				getAllClients();
			}
			
		}
	};

	xhr.open("POST", CHANGE_CLIENT_PROFILE, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.RegistrationNewClient = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var registrationMessage = JSON.parse(xhr.responseText);
            console.log(registrationMessage);
            if(registrationMessage == true){
            	alert('Регистрация прошла успешно!');
            }else{
            	alert('Такой профиль уже существует!');
            }

        }
    };

    xhr.open("POST", CLIENT_REGISTRATION_REQUEST , true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};
// Request.Registration = function (jsonObject) {
// let xhr = new XMLHttpRequest();
// xhr.onreadystatechange = function () {
// if (xhr.readyState == 4 && xhr.status == 200) {
// var registrationMessage = JSON.parse(xhr.responseText);
// console.log(registrationMessage);
// if(registrationMessage == true){
// successRegistrationClient();
// }else{
// alert('Такой профиль уже существует!');
// }
//
// }
// };
//
// xhr.open("POST", CLIENT_REGISTRATION_REQUEST , true);
// xhr.setRequestHeader("Content-Type", "application/json");
// xhr.send(JSON.stringify(jsonObject));
// };
//
//
//
//
//
// Request.GetPhotoWorker = function(salonId){
// var xhr = new XMLHttpRequest();
// var url = GET_WORKER_PHOTO_URL + salonId;
// console.log(url);
// xhr.open("GET", url, true);
// xhr.setRequestHeader("Content-Type", "application/json");
// xhr.onreadystatechange = function () {
// if (xhr.readyState === 4 && xhr.status === 200) {
// var json = JSON.parse(xhr.responseText);
// createWorkerPhoto (json);
// }
// };
// xhr.send();
// };

