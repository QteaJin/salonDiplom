function changeStatusOrderClient() {
		var selected = document.getElementById("inputStateStatus").value;
		
	}

function setCurrentDateClient () {
	var arrMonth=['Январь','Февраль','Март','Апрель','Май','Июнь','Июль','Август','Сентябрь','Октябрь','Ноябрь','Декабрь'];
	var now = moment();
	// console.log(now.format('M'));
	// console.log(now.format('YYYY'));
	var month = document.getElementById("inputStateMounts");
	for (var i = 0; i < arrMonth.length; i++) {
		var select = document.createElement("option");
		select.setAttribute("value", (i+1));
		if(now.format('M').toString() == (i + 1)){
			select.setAttribute("selected", "selected");

		}
		var node = document.createTextNode(arrMonth[i]);
		select.appendChild(node);
		month.appendChild(select);
	}

	var year = document.getElementById("inputStateYear");
	var currentYear = document.createElement("option");
	currentYear.setAttribute("selected", "selected");
	currentYear.setAttribute("value", now.format('YYYY'));
	var yearNode = document.createTextNode(now.format('YYYY'));
	var lastYear = document.createElement("option");
	lastYear.setAttribute("value", moment().subtract(1, 'year').format('YYYY'));
	var lastYearNode = document.createTextNode(moment().subtract(1, 'year').format('YYYY'));


	currentYear.appendChild(yearNode);
	lastYear.appendChild(lastYearNode);
	year.appendChild(currentYear);
	year.appendChild(lastYear);
	
	sendRequestClientHistory();

}

function sendRequestClientHistory(){
	
	var month = document.getElementById('inputStateMounts').value;
	var year = document.getElementById('inputStateYear').value;
	var status = document.getElementById('inputStateStatus').value;
	var clientRequest ='&year='+year+'&month='+month+'&status='+status;
	console.log(clientRequest);
	//clientRequest = 'year=1970&month=1&status=all';							//test DB
	var clientHistoryRequest = Object.create(Request);
	clientHistoryRequest = Request.GetClientOrders(clientRequest);
	
}

function createTable (jsonIncome) {
    		
		if(jsonIncome.length>0 && jsonIncome[0].token == "error"){ //redirect if token error to login page
			
			location.href = '/login';  
			return;
		}
		
		if(jsonIncome.length == 0){
			var textBlockInfo = "Нет заказов за указанный период";
			infoBlock(textBlockInfo);
		}
		
    	var table = document.getElementById("tbodyTable");

		while (table.firstChild) {
    	table.removeChild(table.firstChild);
		}
			
		
		//console.log(Object.keys(jsonIncome[0]).length);
		for (var i = 0; i < jsonIncome.length; i++) {

			var tr = document.createElement('tr');
			//var keys = Object.keys(jsonIncome[i]).length;
			var tdNum = document.createElement("td");
			tdNum.appendChild(document.createTextNode(i + 1));

			var tdDate = document.createElement("td");
			var normalDate = moment(jsonIncome[i].dateAppointment);
			//tdDate.appendChild(document.createTextNode(normalDate.format('YYYY-MM-DD')));
			//tdDate.appendChild(document.createTextNode(normalDate.format("HH : mm")));
			tdDate.innerHTML = normalDate.format('YYYY-MM-DD') + "<br>" + "<b>" + normalDate.format("HH : mm") + "</b>";

			var tdSalon = document.createElement("td");
			tdSalon.appendChild(document.createTextNode(jsonIncome[i].salon));

			var tdWorker = document.createElement("td");
			tdWorker.appendChild(document.createTextNode(jsonIncome[i].worker));

			var catalogs =  document.createElement("td");
			var ul = document.createElement('ul');
			if (jsonIncome[i].catalogs == null) {
				catalogs.appendChild(document.createTextNode('НЕТ ДАННЫХ'));
			} else {
				var tempSet = new Set();
				console.log(jsonIncome[i].catalogs);
				for (var j = 0; j < jsonIncome[i].catalogs.length; j++) {
					var li = document.createElement('li');
					li.appendChild(document.createTextNode(jsonIncome[i].catalogs[j].description + " : " + jsonIncome[i].catalogs[j].price + " грн."));

					//jsonIncome[i].catalogs[j].description;
					ul.appendChild(li);
			}
			catalogs.appendChild(ul);
			}
			
			var tdPrice = document.createElement("td");
			tdPrice.appendChild(document.createTextNode(jsonIncome[i].price));

			var tdStatus = document.createElement("td");
			tdStatus.appendChild(document.createTextNode(translateRU (jsonIncome[i].status)));

			var tdButton = document.createElement("td");
			if(jsonIncome[i].status != "CANCELED" || jsonIncome[i].status != "DONE"){ // убрать проверку на отменту заказа
				var button = document.createElement('button');
				button.appendChild(document.createTextNode("Отменить"));
				button.setAttribute('value', jsonIncome[i].checkListId );
				button.setAttribute('class', 'neworder');
				button.setAttribute('onclick', 'cancelOrder(this);');
				tdButton.appendChild(button);

			}
			



			tr.appendChild(tdNum);
			tr.appendChild(tdDate);	
			tr.appendChild(tdSalon);
			tr.appendChild(tdWorker);
			tr.appendChild(catalogs);
			tr.appendChild(tdPrice);
			tr.appendChild(tdStatus);
			tr.appendChild(tdButton);

			table.appendChild(tr);
		}
		
    }

function cancelOrder(e) {
	console.log(e.value) ;
	var cancelOrderClient = Object.create(Request);
	cancelOrderClient = Request.CancelClientOrder(e.value);
}

function cancelOrderDone(text){
	//alert('Ваша заявка успешно отменена. Номер заявки '+ text);
	var textBlockInfo = "Ваша заявка успешно отменена. Номер заявки " + text;
	infoBlock(textBlockInfo);
	setTimeout(reloadPage, 4000);
}

function reloadPage(){
	location.reload(true);
}