
function sendQuickOrder(event) {
	event.preventDefault();
	var fields = document.getElementById('contact-form');
	var date = moment().format('DD-MM-YYYY');	
	var qOrder = Object.create(QuickOrderBean);
	qOrder.setName = fields.name.value;
	qOrder.setPhone = fields.phone.value;
	qOrder.setEmail = fields.email.value;
	qOrder.setDescription = fields.description.value;
	qOrder.setDateCreate = date;
	console.log(JSON.stringify(qOrder));
	
	var qOrderRequest = Object.create(Request);
	Request.PostQuickOrder(qOrder);
	
}

