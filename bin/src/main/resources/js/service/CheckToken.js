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