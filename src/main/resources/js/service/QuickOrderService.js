
function sendQuickOrder(event) {
	event.preventDefault();
	var fields = document.getElementById('contact-form');
	var qOrder = Object.create(QuickOrderBean);
	qOrder.setName = fields.name.value;
	qOrder.setPhone = fields.phone.value;
	qOrder.setEmail = fields.email.value;
	qOrder.setDescription = fields.description.value;
	console.log(JSON.stringify(qOrder));
	
	var qOrderRequest = Object.create(Request);
	qOrderRequest.PostQuickOrder(qOrder);
	
}

