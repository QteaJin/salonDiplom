/**
 * 
 */
document.cookie = "userName=Vasya";
document.cookie = "userName=dima";

var checkToken = getCookie('userName');

console.log(checkToken);

function getCookie(name) {
	  var matches = document.cookie.match(new RegExp(
	    "(?:^|; )" + name.replace(/([\.$?*|{}\(\)\[\]\\\/\+^])/g, '\\$1') + "=([^;]*)"
	  ));
	  return matches ? decodeURIComponent(matches[1]) : undefined;
	}