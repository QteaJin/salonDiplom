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
			
			console.log(jsonObj);
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
		button.setAttribute ("class", "btn btn-light");
		button.setAttribute ("data", JSON.stringify(value));
		button.setAttribute ("onclick", "createTableOrdersWorker(event);");
		button.appendChild(document.createTextNode(key));
		mainDiv.appendChild(button);
	}
}
function createTableOrdersWorker(event){
	event.stopPropagation();
	var mainDiv = document.getElementById("workersOnDutySchedule");
	
	while (mainDiv.firstChild) {
		mainDiv.removeChild(mainDiv.firstChild);
	}
	var obj = JSON.parse(event.target.getAttribute("data"));
	console.log(obj);
	
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped table-hover");
	table.setAttribute("id", "ordersWorkerOnDate");
	var rowNum = 0;
	var row = table.insertRow(rowNum);
	var cell0 = row.insertCell(0);
	cell0.appendChild(document.createTextNode("Начало"));
	var cell1 = row.insertCell(1);
	cell1.appendChild(document.createTextNode("Конец"));
	var cell2 = row.insertCell(2);
	cell2.appendChild(document.createTextNode("Статус"));
	var cell3 = row.insertCell(3);
	cell3.appendChild(document.createTextNode("Номер заказа"));
	var cell4 = row.insertCell(4);
	cell4.appendChild(document.createTextNode("Клиент"));
	var cell5 = row.insertCell(5);
	cell5.appendChild(document.createTextNode("ID клиента"));
	var cell6 = row.insertCell(6);
	cell6.appendChild(document.createTextNode("Услуги"));
	var cell7 = row.insertCell(7);
	cell7.appendChild(document.createTextNode("Общая стоимость"));
	var cell8 = row.insertCell(8);
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
			cell2.appendChild(document.createTextNode(obj[i].status));
			var cell3 = row.insertCell(3);
			cell3.appendChild(document.createTextNode(obj[i].checkListId));
			var cell4 = row.insertCell(4);
			cell4.appendChild(document.createTextNode(obj[i].name));
			var cell5 = row.insertCell(5);
			cell5.appendChild(document.createTextNode(obj[i].clientId));
			var cell6 = row.insertCell(6);
			cell6.appendChild(document.createTextNode("Услуги"));
			var cell7 = row.insertCell(7);
			cell7.appendChild(document.createTextNode(obj[i].price));
			var cell8 = row.insertCell(8);
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
		cell2.appendChild(document.createTextNode(obj[i].status));
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