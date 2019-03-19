/**
 * 
 */

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
	
	if(checkToken == null || checkToken === undefined ){
		location.href = '/login';  
		return;
	}
	return checkToken;
}