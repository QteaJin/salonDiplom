'use strict';

const URL_DEFAULT = ""; //https://vip-salon.herokuapp.com
const URL_DEFAULT_LOCAL = "http://localhost:8080";
const CREATE_SALON = "/salon/create";
const GET_ALL_SALONS = "/salon/admin/all";
const CREATE_EDIT_SKILL = "/skill/create";
const GET_ALL_SKILLS = "/skill/admin/all";
const GET_CATALOGS_BY_SKILL ="/catalog/admin/";



var RequestAdmin = {};

RequestAdmin.CreateSalon = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var salon = JSON.parse(xhr.responseText);
            console.log(salon);
            successCreateSalon();
          

        }
    };

    xhr.open("POST", CREATE_SALON , true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.GetAllSalonsAdmin = function(){
var xhr = new XMLHttpRequest();
var url = GET_ALL_SALONS;

xhr.open("GET", url, true);
xhr.setRequestHeader("Content-Type", "application/json");
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        var json = JSON.parse(xhr.responseText);
        displayAllSalons(json);
    }
};
xhr.send();
};

RequestAdmin.CreateSkill = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var salon = JSON.parse(xhr.responseText);
            console.log(salon);
            alert("Услуга создана / изменена успешно");
            sendRequestFindAllSKills(event);
          

        }
    };

    xhr.open("POST", CREATE_EDIT_SKILL , true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};

RequestAdmin.GetAllSkillsAdmin = function(){
	var xhr = new XMLHttpRequest();
	var url = GET_ALL_SKILLS;

	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        createSkillsTable(json);
	        createSelectForCatalog(json);
	        
	    }
	};
	xhr.send();
	};
	
	
	
	RequestAdmin.GetAllCatalogsBySkillAdmin = function(skillId){
		var xhr = new XMLHttpRequest();
		var url = GET_CATALOGS_BY_SKILL + skillId;

		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		        var json = JSON.parse(xhr.responseText);
		        
				if(json.length>0 && json[0].token == "error"){ //redirect if token error to login page
					
					location.href = '/login';  
					return;
				}else{
				console.log(json);
		        createCatalogTable (json);
		        }
		        
		        
		    }
		};
		xhr.send();
		};

//Request.Registration = function (jsonObject) {
//    let xhr = new XMLHttpRequest();
//    xhr.onreadystatechange = function () {
//        if (xhr.readyState == 4 && xhr.status == 200) {
//            var registrationMessage = JSON.parse(xhr.responseText);
//            console.log(registrationMessage);
//            if(registrationMessage == true){
//            	successRegistrationClient();
//            }else{
//            	alert('Такой профиль уже существует!');
//            }
//
//        }
//    };
//
//    xhr.open("POST", CLIENT_REGISTRATION_REQUEST , true);
//    xhr.setRequestHeader("Content-Type", "application/json");
//    xhr.send(JSON.stringify(jsonObject));
//};
//
//
//
//
//
//Request.GetPhotoWorker = function(salonId){
//	var xhr = new XMLHttpRequest();
//	var url = GET_WORKER_PHOTO_URL + salonId;
//	console.log(url);
//	xhr.open("GET", url, true);
//	xhr.setRequestHeader("Content-Type", "application/json");
//	xhr.onreadystatechange = function () {
//	    if (xhr.readyState === 4 && xhr.status === 200) {
//	        var json = JSON.parse(xhr.responseText);
//	        createWorkerPhoto (json);
//	    }
//	};
//	xhr.send();
//};




