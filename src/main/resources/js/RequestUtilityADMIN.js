'use strict';

const URL_DEFAULT = ""; //https://vip-salon.herokuapp.com
const URL_DEFAULT_LOCAL = "http://localhost:8080";
const CREATE_SALON = "/salon/create";
const GET_ALL_SALONS = "/salon/admin/all";



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




