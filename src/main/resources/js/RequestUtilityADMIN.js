'use strict';

var URL_DEFAULT = ""; // https://vip-salon.herokuapp.com
var URL_DEFAULT_LOCAL = "http://localhost:8080";
var CREATE_SALON = "/salon/create";
var GET_ALL_SALONS = "/salon/admin/all"; 
var CREATE_EDIT_SKILL = "/skill/create";
var GET_ALL_SKILLS = "/skill/admin/all";
var GET_CATALOGS_BY_SKILL = "/catalog/admin/";
var CREATE_EDIT_CATALOG = "/catalog/create";
var GET_WORKER_URL = URL_DEFAULT + "/worker/?salonId="; //get all workers by salon id
var GET_WORKER_BY_ID = URL_DEFAULT + "/worker/admin/"; //get worker by ID
var UPDATE_WORKER_DATA = URL_DEFAULT + "/worker/admin/update";
var CREATE_NEW_WORKER = URL_DEFAULT + "/auth/registr/worker";
var UPDATE_WORKER_SKILL_LIST = URL_DEFAULT + "/worker/admin/skill";
var ADD_WORKING_DAYS = URL_DEFAULT + "/worker/admin/addDays";
var GET_WORKING_DAYS = URL_DEFAULT + "/worker/admin/getDays/";
var DEL_WORKING_DAYS = URL_DEFAULT + "/worker/admin/delDays/";
var GET_ALL_CLIENTS = URL_DEFAULT + "/client/admin/all";
var GET_CLIENT_PROFILE = URL_DEFAULT + "/client/admin/";
var CHANGE_CLIENT_PROFILE = URL_DEFAULT + "/client/admin/changeProfile";
var CLIENT_REGISTRATION_REQUEST = URL_DEFAULT + "/auth/registr/client";
var GET_WORKER_ORDERS = URL_DEFAULT + "/checklist/admin";

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
			var text = "Услуга создана / изменена успешно";
			infoBlock(text);
			//alert("Услуга создана / изменена успешно");
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
			var textBlockInfo = "Создание / Изменение услуги прошло успешно";
			infoBlock(textBlockInfo);
			//alert("Создание / Изменение услуги прошло успешно");
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
				//alert("Что-то пошло не так. Изменения не внесены");
				var textBlockInfo = "Что-то пошло не так. Изменения не внесены";
				infoBlock(textBlockInfo);
			}else{
				//alert("Создание / Изменение сотрудника прошло успешно");
				var textBlockInfo = "Создание / Изменение сотрудника прошло успешно";
				infoBlock(textBlockInfo);
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
				//alert("Что-то пошло не так. Изменения не внесены");
				var textBlockInfo = "Что-то пошло не так. Изменения не внесены";
				infoBlock(textBlockInfo);
			}else{
				//alert("Создание / Изменение сотрудника прошло успешно");
				var textBlockInfo = "Создание / Изменение сотрудника прошло успешно";
				infoBlock(textBlockInfo);
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
				//alert("Что-то пошло не так. Изменения не внесены");
				var textBlockInfo = "Что-то пошло не так. Изменения не внесены";
				infoBlock(textBlockInfo);
			}else{
				//alert("Изменения прошли успешно");
				var textBlockInfo = "Изменения прошли успешно";
				infoBlock(textBlockInfo);
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
	        console.log(json);
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
				//alert("Что-то пошло не так. Изменения не внесены");
				var textBlockInfo = "Что-то пошло не так. Изменения не внесены";
				infoBlock(textBlockInfo);
			}else{
				//alert("Изменения прошли успешно");
				var textBlockInfo = "Изменения прошли успешно";
				infoBlock(textBlockInfo);
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
            	//alert('Регистрация прошла успешно!');
				var textBlockInfo = "Регистрация прошла успешно!";
				infoBlock(textBlockInfo);
            }else{
            	//alert('Такой профиль уже существует!');
				var textBlockInfo = "Такой профиль уже существует!";
				infoBlock(textBlockInfo);
            }

        }
    };

    xhr.open("POST", CLIENT_REGISTRATION_REQUEST , true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.GetWorkerOrdersRequest = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var result = JSON.parse(xhr.responseText);
            createListWorkers(result);
         }
    };

    xhr.open("POST", GET_WORKER_ORDERS , true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.RequestChangeOrderStatus = function(orderId, selectStatus){
	var xhr = new XMLHttpRequest();
	var url = "/checklist/admin/" + orderId + "/status/" + selectStatus;
	console.log(url);
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        //var json = JSON.parse(xhr.responseText);
	        var response = xhr.responseText;
	        
	        if(response == "true"){
	        	//alert("Изменения прошли успешно");
				var textBlockInfo = "Изменения прошли успешно";
				infoBlock(textBlockInfo);
	        	findOrdersByDateStatus();
	        }else{
	        	//alert("Ошибка при внесении изменений!");
				var textBlockInfo = "Ошибка при внесении изменений!";
				infoBlock(textBlockInfo);
	        }
	    }
	};
	xhr.send();
};

RequestAdmin.GetAllCommentsRequest = function(){
	var xhr = new XMLHttpRequest();
	var url = "/comment/allSortByDate";
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        //var json = JSON.parse(xhr.responseText);
	    	var result = JSON.parse(xhr.responseText);
	        
	        if(result){
	        	//alert("Изменения прошли успешно");
//				var textBlockInfo = "Получаем список комментариев";
//				infoBlock(textBlockInfo);
				createTableForComments(result);
	        }else{
	        	//alert("Ошибка при внесении изменений!");
				var textBlockInfo = "Ошибка получении списка коммертариев";
				infoBlock(textBlockInfo);
	        }
	    }
	};
	xhr.send();
};

RequestAdmin.ChangeCommentStatus = function(commentId){
	var xhr = new XMLHttpRequest();
	var url = "/comment/change/"+commentId;
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        //var json = JSON.parse(xhr.responseText);
	    	var result = xhr.responseText;
	        
	        if(result == "true"){
	        	var textBlockInfo = "Изменения прошли успешно";
				infoBlock(textBlockInfo);
				
	        }else{
	        	//alert("Ошибка при внесении изменений!");
				var textBlockInfo = "Ошибка при изменении статуса комментария";
				infoBlock(textBlockInfo);
	        }
	        getAllComments();
	    }
	};
	xhr.send();
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

