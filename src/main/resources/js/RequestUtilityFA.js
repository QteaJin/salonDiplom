'use strict';

const URL_DEFAULT = ""; //https://vip-salon.herokuapp.com
const URL_DEFAULT_LOCAL = "http://localhost:8080";
const QUICK_ORDER_URL = URL_DEFAULT + "/checklist";
const GET_WORKER_PHOTO_URL = URL_DEFAULT + "/worker/?salonId=";
const GET_WORKER_PROFILE_URL = URL_DEFAULT + "/worker/profile/";
const CLIENT_HISTORY_REQUEST = URL_DEFAULT + "/checklist/client/history?";
const CANCEL_ORDER_BY_ID = URL_DEFAULT + "/checklist/cancel/";
const LOGIN_SEND_POST = URL_DEFAULT + "/auth/login";

var Request = {};

Request.Login = function (jsonObject) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            var loginResponce = JSON.parse(xhr.responseText);
            console.log(loginResponce);
            var date = new Date(new Date().getTime() + 60 * 60 * 1000); //1 hour
            document.cookie = "token="+loginResponce.token +"; path=/; expires=" + date.toUTCString();
            createLoginLogoutButton();
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

