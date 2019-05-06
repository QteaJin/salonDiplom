'use strict';

const URL_DEF = ""; //https://vip-salon.herokuapp.com
const URL_DEFAULT_LOCAL = "http://localhost:8080";
const QUICK_ORDER_URL = URL_DEF + "/checklist";
const GET_WORKER_PHOTO_URL = URL_DEF + "/worker/?salonId="; //get all workers by salon id
const GET_WORKER_PROFILE_URL = URL_DEF + "/worker/profile/";
const CLIENT_HISTORY_REQUEST = URL_DEF + "/checklist/client/history?";
const CANCEL_ORDER_BY_ID = URL_DEF + "/checklist/cancel/";
const LOGIN_SEND_POST = URL_DEF + "/auth/login";
const CLIENT_REGISTRATION_REQUEST = URL_DEF + "/auth/registr/client";
const SEND_MESSAGE_TO_ADMIN = URL_DEF + "/email/admin";
const GET_CLIENT_PERSONAL_PROFILE = "/client/profile";
const EDIT_CLIENT_PROFILE = "/client/profile/edit";
const GET_FREE_DATE = "/checklist/new";

var Request = {};

Request.SendMessageByEmail = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var sendMessage = JSON.parse(xhr.responseText);
            console.log(sendMessage);
            if(sendMessage == true){
            	sendEmailSuccess();
            }else{
            	alert('Что-то пошло не так. Ваше сообщение не доставлено.');
            }

        }
    };

    xhr.open("POST", SEND_MESSAGE_TO_ADMIN , true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};

Request.Registration = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var registrationMessage = JSON.parse(xhr.responseText);
            console.log(registrationMessage);
            if(registrationMessage == true){
            	successRegistrationClient();
            }else{
            	alert('Такой профиль уже существует!');
            }

        }
    };

    xhr.open("POST", CLIENT_REGISTRATION_REQUEST , true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};

Request.Login = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var loginResponce = JSON.parse(xhr.responseText);
            console.log(loginResponce);
            if(loginResponce.errorMessage == "Profile not found"){
            	console.log('Profile not found');
            	loginFail();
            	return;
            }
            if(loginResponce.errorMessage == "Status NOACTIVE"){
            	console.log("Status NOACTIVE");
            	statusNoActive();
            	return;
            }
            var date = new Date(new Date().getTime() + 60 * 60 * 1000); //1 hour
            document.cookie = "token="+loginResponce.token +"; path=/; expires=" + date.toUTCString();
            createLoginLogoutButton();
            successLogin();
        }
    };

    xhr.open("POST", LOGIN_SEND_POST , true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    console.log(LOGIN_SEND_POST);
    console.log(JSON.stringify(jsonObject));
    var params = 'login=' +jsonObject.login +'&password='+jsonObject.password;
    console.log(params);
    xhr.send(params);
};

Request.PostQuickOrder = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var quickOrder = JSON.parse(xhr.responseText);
            console.log(quickOrder);
            if(quickOrder != null){
            	succesQuickMessage();
            }

        }
    };

    xhr.open("POST", QUICK_ORDER_URL +"/quick" , true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(JSON.stringify(jsonObject));
};

Request.GetPhotoWorker = function(salonId){
	var xhr = new XMLHttpRequest();
	var url = GET_WORKER_PHOTO_URL + salonId;
	console.log(url);
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        createWorkerPhoto (json);
	    }
	};
	xhr.send();
};
Request.GetProfileWorker = function(workerId){
	
	var xhr = new XMLHttpRequest();
	var url = GET_WORKER_PROFILE_URL + workerId;
	console.log(url);
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        createWorkerProfile(json, workerId);
//	        console.log(json);
//	        console.log(json.description);
//	        console.log(json.listSkills);
//	        for (var i = 0; i < json.listSkills.length; i++) {
//	        	console.log(json.listSkills[i].name);
//			}
	    }
	};
	xhr.send();
};

Request.GetClientOrders = function(clientRequest){
	
	var xhr = new XMLHttpRequest();
	var url = CLIENT_HISTORY_REQUEST + clientRequest;
	console.log(url);
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var jsonIncome = JSON.parse(xhr.responseText);
	        console.log(jsonIncome);
	        createTable (jsonIncome);
	       

	    }
	};
	xhr.send();
};

Request.CancelClientOrder = function (orderId){
	var xhr = new XMLHttpRequest();
	var url = CANCEL_ORDER_BY_ID + orderId;
	console.log(url);
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var jsonIncome = xhr.responseText;
	        
	        cancelOrderDone(jsonIncome);
	       

	    }
	};
	xhr.send();
};

Request.SendClientProfileRequest = function(){
	var xhr = new XMLHttpRequest();
	var url = GET_CLIENT_PERSONAL_PROFILE;
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        if(json.errorMessage != null){
	        	alert('Войдите в ваш аккаунт!');
	        	logoutSessionMainPage();
	        }else{
	        	setClientProfileToForm(json);
	        	
	        }
	    }
	};
	xhr.send();
};

Request.EditClientProfile = function(jsonObject) {
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var json = JSON.parse(xhr.responseText);
			if(json.errorMessage != null){
				alert("Что-то пошло не так. Изменения не внесены");
				logoutSessionMainPage();
			}else{
				alert("Изменения прошли успешно");
				
			}
			
		}
	};

	xhr.open("POST", EDIT_CLIENT_PROFILE, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};

Request.GetPhotoWorkerForOrder = function(salonId){
	var xhr = new XMLHttpRequest();
	var url = GET_WORKER_PHOTO_URL + salonId;
	console.log(url);
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        console.log(json);
	        setWorkerPhoto(json);
	    }
	};
	xhr.send();
};
Request.GetWorkerCatalogForOrder = function(workerId){
	var xhr = new XMLHttpRequest();
	var url = GET_WORKER_PROFILE_URL + workerId;
	xhr.open("GET", url, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.onreadystatechange = function () {
	    if (xhr.readyState === 4 && xhr.status === 200) {
	        var json = JSON.parse(xhr.responseText);
	        createTableWorkerCatalog (json);
	        
	    }
	};
	xhr.send();
};

Request.GetFreeDateForOrder = function(jsonObject) {
	console.log(jsonObject);
	console.log(JSON.stringify(jsonObject));
	let xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = JSON.parse(xhr.responseText);
				console.log(result);
				createTableWithDates (result);
		}
	};

	xhr.open("POST", GET_FREE_DATE, true);
	xhr.setRequestHeader("Content-Type", "application/json");
	xhr.send(JSON.stringify(jsonObject));
};
