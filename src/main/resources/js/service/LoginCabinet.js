function createLoginLogoutButton() {
	
	
	var crBut = document.getElementById('logbutton');

	while (crBut.firstChild) {
		crBut.removeChild(crBut.firstChild); // delete buttons
	}

	var uToken = checkUserToken();
	console.log(uToken);
	if (uToken == null || uToken == undefined || uToken == "null") {
		var loginBut = document.createElement('a');
		loginBut.setAttribute('class', "btn btn-outline-light");
		loginBut.setAttribute('href', "/login");
		loginBut.innerHTML = 'ВОЙТИ';
		var registrBut = document.createElement('a');
		registrBut.setAttribute('class', "btn btn-outline-light");
		registrBut.setAttribute('href', "/registration");
		registrBut.innerHTML = 'РЕГИСТРАЦИЯ';
		crBut.appendChild(loginBut);
		crBut.appendChild(registrBut);
	} else {

		var cabinetButton = document.createElement('a');
		cabinetButton.setAttribute('class', "btn btn-outline-light");
		cabinetButton.setAttribute('href', "/cabinet");
//		cabinetButton.setAttribute('onclick',
//				"sendCabinetRequest();return false;")
		cabinetButton.innerHTML = 'КАБИНЕТ';
		crBut.appendChild(cabinetButton);

		var logoutButton = document.createElement('a');
		logoutButton.setAttribute('class', "btn btn-outline-light");
		logoutButton.setAttribute('href', "#");
		logoutButton.setAttribute('onclick', "logoutSessionMainPage();return false;")
		logoutButton.innerHTML = 'ВЫХОД';
		crBut.appendChild(logoutButton);
		
	}
	checkErrorToken();
}
function logoutSession() {

	var date = new Date(0);
	document.cookie = "token=; path=/; expires=" + date.toUTCString();
	createLoginLogoutButton();
	
	
}
function logoutSessionMainPage() {

	var date = new Date(0);
	document.cookie = "token=; path=/; expires=" + date.toUTCString();
	createLoginLogoutButton();
	location.href = '/';
	
}


function successLogin(){
	var fail = document.getElementById('loginfail');
	fail.innerHTML = "";
	var message = document.getElementById('successLogin');
	document.forms['login-form']['login'].value = "";
	document.forms['login-form']['password'].value = "";
	while (message.firstChild) {
		message.removeChild(message.firstChild); // delete link
	}
	var uToken = checkUserToken();
	if (uToken == null || uToken == undefined || uToken == "null") {return;}
	else{
//		var formCabinet = document.createElement('a');
//		formCabinet.setAttribute('href', "/cabinet");
//		formCabinet.setAttribute('class', "btn btn-warning");
//		formCabinet.setAttribute('onclick',
//		"sendCabinetRequest();return false;")
//		formCabinet.innerHTML = 'Перейти в личный кабинет';
//		message.appendChild(formCabinet);
		document.getElementById('cabinet').disabled = true;
		var text = "Переход в личный кабинет";
		infoBlock(text);
		setTimeout(redirectToCabinet, 1000);
		
		
	}
	function redirectToCabinet(){
		window.location.href = "/cabinet";
	}
	
}