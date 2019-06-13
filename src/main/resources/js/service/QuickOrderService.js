
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

