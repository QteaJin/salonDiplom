


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

function loginFail(){
	var fail = document.getElementById('loginfail');
	fail.innerHTML = "Неправильный Логин или Пароль";
	var textBlockInfo = "Неправильный Логин или Пароль";
	infoBlock(textBlockInfo);
}

function statusNoActive(){
	var fail = document.getElementById('loginfail');
	fail.innerHTML = "Ваш аккаунт заблокирован. Обратитесь к администратору";
	var textBlockInfo = "Ваш аккаунт заблокирован. Обратитесь к администратору";
	infoBlock(textBlockInfo);
}