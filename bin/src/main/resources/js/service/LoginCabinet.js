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
		crBut.appendChild(loginBut);
	} else {

		var cabinetButton = document.createElement('a');
		cabinetButton.setAttribute('class', "btn btn-outline-light");
		cabinetButton.setAttribute('href', "#");
		cabinetButton.setAttribute('onclick',
				"sendCabinetRequest();return false;")
		cabinetButton.innerHTML = 'КАБИНЕТ';
		crBut.appendChild(cabinetButton);

		var logoutButton = document.createElement('a');
		logoutButton.setAttribute('class', "btn btn-outline-light");
		logoutButton.setAttribute('href', "#");
		logoutButton.setAttribute('onclick', "logoutSession();return false;")
		logoutButton.innerHTML = 'ВЫХОД';
		crBut.appendChild(logoutButton);
	}
}
function logoutSession() {

	var date = new Date(0);
	document.cookie = "token=; path=/; expires=" + date.toUTCString();
	createLoginLogoutButton();
}