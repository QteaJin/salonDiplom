function sendEmail(event){
	event.preventDefault();
	var fields = document.getElementById('message-form');
	var messageForm = Object.create(EmailBean);
	messageForm.setSubject = fields.subject.value;
	messageForm.setMessage = fields.message.value;
	
	console.log(JSON.stringify(messageForm));
	
	if(messageForm.getMessage == ''){
		//alert("Невозможно отправить пустое сообщение");
		var textBlockInfo = "Невозможно отправить пустое сообщение";
		infoBlock(textBlockInfo);
		return;
	}
	
	var messageRequest = Object.create(Request);
	messageRequest.SendMessageByEmail(messageForm);
}
function sendEmailSuccess(){
	document.forms['message-form']['subject'].value = "";
	document.forms['message-form']['message'].value = "";
	//alert("Сообщение отправлено");
	var textBlockInfo = "Сообщение отправлено";
	infoBlock(textBlockInfo);
}