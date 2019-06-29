function sendRegistrationForm(event){
	event.preventDefault();
	var fields = document.getElementById('register-form');
	var registrForm = Object.create(ProfileBean);
	registrForm.setLogin = fields.login.value;
	registrForm.setEmail = fields.email.value;
	registrForm.setPhone = fields.phone.value;
	registrForm.setPassword = fields.password.value;
	var passRepeat = fields.passwordconfirm.value;
	
	if(registrForm.getLogin == '' || registrForm.getEmail == '' || registrForm.getPhone == '' || registrForm.getPassword == '' ){
		//alert("Заполните все поля регистрационной формы");
		var textBlockInfo = "Заполните все поля регистрационной формы";
		infoBlock(textBlockInfo);
		return;
	}
	
	if(!validateEmail(fields.email.value)){
		var textBlockInfo = "Некорректный адресс электронной почты ";
		infoBlock(textBlockInfo);
		return;
	}
	
	if(registrForm.getPassword != passRepeat){
		//alert("Пароли не совпадают!");
		var textBlockInfo = "Пароли не совпадают!";
		infoBlock(textBlockInfo);
		return;
	}
	
	console.log(JSON.stringify(registrForm));
	
	var registerRequest = Object.create(Request);
	registerRequest.Registration(registrForm);
}

function successRegistrationClient(){
	
	document.forms['register-form']['login'].value = "";
	document.forms['register-form']['email'].value = "";
	document.forms['register-form']['phone'].value = "";
	document.forms['register-form']['password'].value = "";
	document.forms['register-form']['passwordconfirm'].value = "";
	
	var divS = document.getElementById('topform');
	var text = document.createElement('span');
	text.setAttribute('class' , 'spanSuccess');
	var messageSuccessRegister = document.createTextNode('Вы успешно зарегистрировались и можете воспользоваться Вашим личным кабинетом');
	//alert("Вы успешно зарегистрировались и можете воспользоваться Вашим личным кабинетом");
	var textBlockInfo = "Вы успешно зарегистрировались и можете воспользоваться Вашим личным кабинетом" +
			"<p>Вы автоматически перенаправитесь на страницу входа в личный кабинет</p>";
	infoBlock(textBlockInfo);
	text.appendChild(messageSuccessRegister);
	divS.appendChild(text);
	setTimeout(relocate, 5000);
	//window.location.href = "/login";
}
function relocate(){
	window.location.href = "/login";
}

function validateEmail(inputText){
	var reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
if(reg.test(inputText)){
	return true;
}
else
	{
return false;
}
}