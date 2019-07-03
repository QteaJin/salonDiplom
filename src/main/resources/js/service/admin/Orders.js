var saveWorker;

function chooseDate(){
var arrMonth=['Январь','Февраль','Март','Апрель','Май','Июнь','Июль','Август','Сентябрь','Октябрь','Ноябрь','Декабрь'];
		var now = moment();
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
		setDaysOfMonth();

		
		
		var day = document.getElementById("inputStateDays");
}

		function setDaysOfMonth() {
			var day = document.getElementById("inputStateDays");
			var year = document.getElementById("inputStateYear").value;
			var month = document.getElementById("inputStateMounts").value;
			var dateParse = year + "-0" + month;
			var numDays = moment(dateParse, "YYYY-MM").daysInMonth();

			for (var i = 0; i < numDays; i++) {
				var currentDay = document.createElement("option");
				currentDay.setAttribute("value", i+1);
				currentDay.appendChild(document.createTextNode(i+1));
				var dateNow = moment().format("DD");
				if(dateNow.toString() == i+1){
					currentDay.setAttribute("selected", "selected");
				}
				day.appendChild(currentDay);
			}

		}

		function findOrdersByDateStatus() {
			var textBlockInfo = "Ваш запрос отправлен";
			infoBlock(textBlockInfo);
			var mainDiv = document.getElementById("workersOnDutySchedule");
			
			while (mainDiv.firstChild) {
				mainDiv.removeChild(mainDiv.firstChild);
			}
			var mainDiv2 = document.getElementById("workersOnDuty");
			while (mainDiv2.firstChild) {
				mainDiv2.removeChild(mainDiv2.firstChild);
			}
			var jsonObj = {};
			var status = document.getElementById("inputStateStatus").value;
			var day = document.getElementById("inputStateDays").value;
			var year = document.getElementById("inputStateYear").value;
			var month = document.getElementById("inputStateMounts").value;
			var dateParse = year + "-" + month + "-" + day;
			var date = moment(dateParse, "YYYY-MM-DD").valueOf();
			
			jsonObj.date = date;
			jsonObj.status = status;
			
			var request = Object.create(RequestAdmin);
			request.GetWorkerOrdersRequest(jsonObj);
		}
function createListWorkers(json){
	var mainDiv = document.getElementById("workersOnDuty");
	while (mainDiv.firstChild) {
		mainDiv.removeChild(mainDiv.firstChild);
	}
	var map = new Map(Object.entries(json));
	for (var [key, value] of map){
		var button = document.createElement("button");
		button.setAttribute ("class", "btn btn-light workers");
		button.setAttribute ("id", "workButton");
		button.setAttribute ("data", JSON.stringify(value));
		button.setAttribute ("onclick", "createTableOrdersWorker(event);");
		button.appendChild(document.createTextNode(key));
		mainDiv.appendChild(button);
	}
	if(saveWorker != undefined){
		var elements = document.querySelectorAll(".workers");
		for(var i=0; i<elements.length; i++) {
		    if(saveWorker == elements[i].innerHTML ){
		    	elements[i].click();
		    	
		    }
		}
	}
}
function createTableOrdersWorker(event){
	event.stopPropagation();
	var element = document.querySelector(".btn-info");
	if(element){
		element.className = "btn btn-light workers";
	}
	event.target.className = "btn btn-info workers";
	var workerName = event.target.innerHTML;
	
	var mainDiv = document.getElementById("workersOnDutySchedule");
	
	while (mainDiv.firstChild) {
		mainDiv.removeChild(mainDiv.firstChild);
	}
	var obj = JSON.parse(event.target.getAttribute("data"));
	//console.log(obj);
	
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped table-hover");
	table.setAttribute("id", "ordersWorkerOnDate");
	var rowNum = 0;
	var row = table.insertRow(rowNum);
	var cell0 = row.insertCell(0);
	cell0.appendChild(document.createTextNode("Начало"));
	var cell1 = row.insertCell(1);
	cell1.innerHTML = "Конец" + "&nbsp";
	//cell1.appendChild(document.createTextNode("Конец"));
	var cell2 = row.insertCell(2);
	cell2.appendChild(document.createTextNode("Статус"));
	var cell3 = row.insertCell(3);
	cell3.appendChild(document.createTextNode("Номер заказа"));
	var cell4 = row.insertCell(4);
	cell4.appendChild(document.createTextNode("Имя клиента"));
	var cell5 = row.insertCell(5);
	cell5.appendChild(document.createTextNode("Контакты"));
	var cell6 = row.insertCell(6);
	cell6.appendChild(document.createTextNode("ID клиента"));
	var cell7 = row.insertCell(7);
	cell7.appendChild(document.createTextNode("Услуги"));
	var cell8 = row.insertCell(8);
	cell8.appendChild(document.createTextNode("Общая стоимость"));
	var cell9 = row.insertCell(9);
	rowNum++;
	for (var i = 0; i < obj.length; i++) {
		
		if(obj[i].status != "FREE"){
			var row = table.insertRow(rowNum);
			var cell0 = row.insertCell(0);
			var start = moment(obj[i].dateAppointment).format("HH : mm");
			cell0.appendChild(document.createTextNode(start));
			var cell1 = row.insertCell(1);
			var finish = moment(obj[i].finish).format("HH : mm");
			cell1.appendChild(document.createTextNode(finish));
			var cell2 = row.insertCell(2);
			cell2.innerHTML = "<font color='#FF5733'>" + translateRU (obj[i].status) + "</font>";
			//cell2.appendChild(document.createTextNode(translateRU (obj[i].status)));
			var cell3 = row.insertCell(3);
			cell3.appendChild(document.createTextNode(obj[i].checkListId));
			var cell4 = row.insertCell(4);
			if(obj[i].name != null){
				cell4.appendChild(document.createTextNode(obj[i].name));
			}else{
				cell4.appendChild(document.createTextNode("НЕТ ИМЕНИ"));
			}
			//cell5
			var cell5 = row.insertCell(5);
			cell5.innerHTML = obj[i].clientContacts;
			var cell6 = row.insertCell(6);
			cell6.appendChild(document.createTextNode(obj[i].clientId));
			var cell7 = row.insertCell(7);
			//cell6.appendChild(document.createTextNode("Услуги"));
			var data = "";
			for (var j = 0; j < obj[i].catalogs.length; j++){
				data = data + obj[i].catalogs[j].name + "<br>";
			}
			cell7.innerHTML = data;
			var cell8 = row.insertCell(8);
			cell8.appendChild(document.createTextNode(obj[i].price));
			var cell9 = row.insertCell(9);
			var select = document.createElement("select");
				select.setAttribute("id","statusSelect");
				
				
			var optionNull = document.createElement("option");
			optionNull.setAttribute("selected","selected");
			var option = document.createElement("option");
			option.setAttribute("value", "ACTIVE");
			option.innerHTML = "АКТИВНЫЙ";
			var option1 = document.createElement("option");
			option1.setAttribute("value", "CANCELED");
			option1.innerHTML = "ОТМЕНЕН";
			var option2 = document.createElement("option");
			option2.setAttribute("value", "DONE");
			option2.innerHTML = "ВЫПОЛНЕН";
			var option3 = document.createElement("option");
			option3.setAttribute("value", "NEW");
			option3.innerHTML = "НОВЫЙ";
			
			var buttonOK = document.createElement("button");
			buttonOK.setAttribute("orderNum", obj[i].checkListId);
			buttonOK.setAttribute("workerName", workerName);
			buttonOK.setAttribute("onclick", "sendRequestChangeOrderStatus(event);");
			buttonOK.innerHTML = "OK";
			
			select.appendChild(optionNull);
			select.appendChild(option);
			select.appendChild(option1);
			select.appendChild(option2);
			select.appendChild(option3);
			cell9.appendChild(select);
			cell9.appendChild(buttonOK);
			
			
			rowNum++;
	}else{
		var row = table.insertRow(rowNum);
		var cell0 = row.insertCell(0);
		var start = moment(obj[i].dateAppointment).format("HH : mm");
		cell0.appendChild(document.createTextNode(start));
		var cell1 = row.insertCell(1);
		var finish = moment(obj[i].finish).format("HH : mm");
		cell1.appendChild(document.createTextNode(finish));
		var cell2 = row.insertCell(2);
		cell2.innerHTML = "<font color='#0E9E09'>" + translateRU (obj[i].status) + "</font>";
		//cell2.appendChild(document.createTextNode(translateRU (obj[i].status)));
		var cell3 = row.insertCell(3);
		//cell3.appendChild(document.createTextNode(obj[i].checkListId));
		var cell4 = row.insertCell(4);
		//cell4.appendChild(document.createTextNode(obj[i].name));
		var cell5 = row.insertCell(5);
		//cell5.appendChild(document.createTextNode(obj[i].clientId));
		var cell6 = row.insertCell(6);
		//cell6.appendChild(document.createTextNode("Услуги"));
		var cell7 = row.insertCell(7);
		//cell7.appendChild(document.createTextNode(obj[i].price));
		var cell8 = row.insertCell(8);
		var cell9 = row.insertCell(9);
		rowNum++;
	}
		
//		var row = table.insertRow(rowNum);
//		var cell0 = row.insertCell(0);
//		var start = moment(obj[i].dateAppointment).format("HH : mm");
//		cell0.appendChild(document.createTextNode(start));
//		var cell1 = row.insertCell(1);
//		var finish = moment(obj[i].finish).format("HH : mm");
//		cell1.appendChild(document.createTextNode(finish));
//		var cell2 = row.insertCell(2);
//		cell2.appendChild(document.createTextNode(obj[i].status));
////		if(obj[i].status == "FREE"){
////			continue;
////		}
//		var cell3 = row.insertCell(3);
//		cell3.appendChild(document.createTextNode(obj[i].checkListId));
//		var cell4 = row.insertCell(4);
//		cell4.appendChild(document.createTextNode(obj[i].name));
//		var cell5 = row.insertCell(5);
//		cell5.appendChild(document.createTextNode(obj[i].clientId));
//		var cell6 = row.insertCell(6);
//		cell6.appendChild(document.createTextNode("Услуги"));
//		var cell7 = row.insertCell(7);
//		cell7.appendChild(document.createTextNode(obj[i].price));
//		var cell8 = row.insertCell(8);
//		rowNum++;
	}
	
	mainDiv.appendChild(table);
}

function sendRequestChangeOrderStatus(event){
	event.stopPropagation();
	saveWorker = event.target.getAttribute("workerName");
	var orderId = event.target.getAttribute("orderNum");
	var selectStatus = document.getElementById("statusSelect").value;
	var request = Object.create(RequestAdmin);
	request.RequestChangeOrderStatus(orderId, selectStatus);
}