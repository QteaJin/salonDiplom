


function sendLoginForm(event){
	event.preventDefault();
	var fields = document.getElementById('login-form');
	var loginForm = Object.create(LoginFormBean);
	loginForm.setLogin = fields.login.value;
	loginForm.setPassword = fields.password.value;
	
	console.log(JSON.stringify(loginForm));
	
	var loginRequest = Object.create(Request);
	loginRequest.Login(loginForm);
}