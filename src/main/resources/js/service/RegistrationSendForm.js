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
		alert("Заполните все поля регистрационной формы");
		return;
	}
	
	if(registrForm.getPassword != passRepeat){
		alert("Пароли не совпадают!");
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
	text.appendChild(messageSuccessRegister);
	divS.appendChild(text);
}