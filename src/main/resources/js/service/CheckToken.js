var CUSTOMER_CABINET = "/customer";
var WORKER_CABINET = "/employee";
var ADMIN_CABINET = "/admin";

function checkUserToken(){
	
	var checkToken = getCookie('token');
	console.log(checkToken);
	return checkToken;
}


function getCookie(name) {
	  var matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
	  ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
	}

function checkTokenExist(){
	
	var checkToken = getCookie('token');
	
	if(checkToken == null || checkToken === undefined){
		location.href = '/login';  
		return;
	}
	return checkToken;
}

function checkTokenExistForComments(){
	  
	  var checkToken = getCookie('token');
	  
	  if(checkToken == null || checkToken === undefined){
	    console.log("Token error");
	    return false;
	  }
	  return true;
	}

function checkErrorToken(){
	
	var checkToken = getCookie('token');
	var urlLocation = window.location.href;
	var splitUrl = urlLocation.split('\/');
	var length = splitUrl.length;
	var splitResultUrl = "\/" + splitUrl[length-1];

	if(CUSTOMER_CABINET == splitResultUrl && checkToken == undefined){
		location.href = '/login';  
		return;
	}
	if(ADMIN_CABINET == splitResultUrl && checkToken == undefined){
		location.href = '/login';  
		return;
	}
	
	if(checkToken == "error"){
		location.href = '/login';  
		return;
	}
	return checkToken;
}