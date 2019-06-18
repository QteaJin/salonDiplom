function sendComment(event){
	event.preventDefault();
	var textBlockInfo = "Сохранение комментария";
	infoBlock(textBlockInfo);
	var fields = document.getElementById('message-form');
	var messageForm = {};
	
	messageForm.description = fields.message.value;
	
	console.log(JSON.stringify(messageForm));
	
	if(messageForm.description == ''){
		//alert("Невозможно отправить пустое сообщение");
		var textBlockInfo = "Невозможно отправить пустое сообщение";
		infoBlock(textBlockInfo);
		return;
	}
	
	var messageRequest = Object.create(Request);
	messageRequest.SendNewComment(messageForm);
}
function sendCommentSuccess(){
	document.forms['message-form']['message'].value = "";
	//alert("Сообщение отправлено");
//	var textBlockInfo = "Сообщение отправлено";
//	infoBlock(textBlockInfo);
}