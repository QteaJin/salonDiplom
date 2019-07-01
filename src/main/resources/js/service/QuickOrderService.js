var errorInformForPhoneNumber;

function sendQuickOrder(event) {
	event.preventDefault();
	var fields = document.getElementById('contact-form');
	var qOrder = Object.create(QuickOrderBean);
	qOrder.setName = fields.name.value;
	qOrder.setPhone = fields.phone.value;
	qOrder.setEmail = fields.email.value;
	qOrder.setDescription = fields.description.value;
	console.log(JSON.stringify(qOrder));
	if( qOrder.name == "" || qOrder.description == ""){
		var textBlockInfo = "Заполните обязательные поля для отправки сообщения!";
		infoBlock(textBlockInfo);
		return;
	}
	if(!validMobileNumber(fields.phone.value)){
		
		infoBlock(errorInformForPhoneNumber);
		return;
	}
	
	var qOrderRequest = Object.create(Request);
	qOrderRequest.PostQuickOrder(qOrder);
	
}

function succesQuickMessage() {
	var insertText = document.querySelector(".messages");
	insertText.style.background = '#51F966';
	insertText.style.textAlign = 'center';
	insertText.innerHTML = "Сообщение отправлено";
	var textBlockInfo = "Сообщение отправлено";
	infoBlock(textBlockInfo);
	
}

function validMobileNumber(number) { // validation 12 digits mobile phone +380XXXXXXXX
	let checkDigits = number;
	if(checkDigits.replace(/\D/g,'').length == 12){
		  let regex = /^[+]*[(]{0,1}[0-9]{1,3}[)]{0,1}[-\s\./0-9]*$/g,
		  result = regex.test(number);
		  if(!result){
			  errorInformForPhoneNumber = "Введите номер в формате +380ХХХХХХХХ";  
		  }
		  return result;
	}else{
		errorInformForPhoneNumber = "Проверьте количество цифр в номере";
		
		return false;
	}
	

	}
