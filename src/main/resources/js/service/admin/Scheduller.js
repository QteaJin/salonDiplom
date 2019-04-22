	var weekDay = ['Пн','Вт','Ср','Чт','Пт','Сб','Вс'];
	var startDayofWeek = 0;
	var startDays = 0;
	var endDays = 21;
	var scheduleDiv = document.getElementById("schedulecalendar");
	
	
	function createschedulecalendar () {
		var scheduleDiv = document.getElementById("schedulecalendar");
		while (scheduleDiv.firstChild) {
			scheduleDiv.removeChild(scheduleDiv.firstChild);}
			var table = document.createElement("table");
			table.setAttribute("id", "scheduletable");
			table.setAttribute("class", "table table-bordered");
			table.setAttribute("onclick", "choseCell(event);");

			var row0 = table.insertRow(0);
			for (var i = 0; i < weekDay.length; i++) {
				var cell = row0.insertCell(i);
				cell.appendChild(document.createTextNode(weekDay[i]));
			} 
			scheduleDiv.appendChild(table);
			startDay();
		}

function startDay () { //new Date().getDay()
	switch (new Date().getDay()) {
		case 0:
		day = "Sunday";
		startDayofWeek = 6;
		createClearCell ();
		break;
		case 1:
		day = "Monday";
		startDayofWeek = 0;
		createClearCell ();
		break;
		case 2:
		day = "Tuesday";
		startDayofWeek = 1;
		createClearCell ();
		break;
		case 3:
		day = "Wednesday";
		startDayofWeek = 2;
		createClearCell ();
		break;
		case 4:
		day = "Thursday";
		startDayofWeek = 3;
		createClearCell ();
		break;
		case 5:
		day = "Friday";
		startDayofWeek = 4;
		createClearCell ();
		break;
		case 6:
		day = "Saturday";
		startDayofWeek = 5;
		createClearCell ();
	}
}

function createClearCell () {
	var scheduleDiv = document.getElementById("schedulecalendar");
	var table = document.getElementById("scheduletable");
	var numRow = 1;
	var minusDay = -startDayofWeek;
	var row = table.insertRow(numRow);
	var numDay = 0;
	var startDay = 0;
	for (var i = 0; i < endDays; i++) {
		var now = moment();
		if(startDayofWeek != 0){
			for (var j = 0; j < startDayofWeek; j++) {
				
				var now1 = moment();
				now1.add(minusDay, 'day');
				var startDayDate = now1 - (now1%86400000);
				var cellpast = row.insertCell(j);
				cellpast.setAttribute("class", "pasttime");
				cellpast.setAttribute('currentDate', startDayDate.valueOf());
				cellpast.innerHTML = now1.format('DD/MM');
				minusDay++;
				i++;
			}
			numDay = startDayofWeek;
			startDayofWeek = 0;
		}

		if (i%7 == 0) {
			numRow++;
			row = table.insertRow(numRow);
			numDay = 0;

		}

		var cell = row.insertCell(numDay);
		cell.setAttribute("class", "futuretime");
		now.add(startDay, 'days');
		var startDayDate = now - (now%86400000);
		cell.setAttribute('currentDate', startDayDate.valueOf());
		cell.innerHTML = now.format('DD/MM');
		numDay++;
		startDay++;

	} 



	scheduleDiv.appendChild(table);
	createButtonsForSchedule ();
}

function choseCell(event) {
	
	var chosenCell = event.target;
	if(chosenCell.getAttribute("class") != "selectedDate"){
		chosenCell.setAttribute("class", "selectedDate");
	}else{
		chosenCell.setAttribute("class", "futuretime");
	}
	
}
function createButtonsForSchedule () {
	var scheduleDiv = document.getElementById("schedulecalendar");
	var addButton = document.createElement("button");
	addButton.appendChild(document.createTextNode("Добавить"));
	addButton.setAttribute("onclick", "getChosenDatesAdd();");


	var delButton = document.createElement("button");
	delButton.appendChild(document.createTextNode("Удалить"));
	delButton.setAttribute("onclick", "deleteChosenDates();");

	scheduleDiv.appendChild(addButton);
	scheduleDiv.appendChild(delButton);
	
	var workerId = document.forms['formworker']['workerId'].value;
	
	var createGetDaysRequest = Object.create(RequestAdmin);
	createGetDaysRequest.GetWorkingDays(workerId);
}

function getChosenDatesAdd () {
	var scheduleObj = {};
	var masDates = [];
	var dates = document.querySelectorAll(".selectedDate");
	for (var i = 0; i < dates.length; i++) {
		if(dates[i].getAttribute("id") == "isWorking"){ continue ;}
		masDates.push(dates[i].getAttribute("currentDate"));
		
	}
	var worker = document.forms['formworker']['workerId'].value;
	scheduleObj.workerId = worker;
	scheduleObj.datesList = masDates;
	console.log(scheduleObj);
	console.log(JSON.stringify(scheduleObj));
	var createAddDaysRequest = Object.create(RequestAdmin);
	createAddDaysRequest.AddWorkingDays(scheduleObj);
	
}
function deleteChosenDates(){
	var scheduleObj = {};
	var masDates = [];
	var dates = document.querySelectorAll(".selectedDate");
	for (var i = 0; i < dates.length; i++) {
		if(dates[i].getAttribute("id") != "isWorking"){ continue ;}
		masDates.push(dates[i].getAttribute("currentDate"));
		
	}
	var worker = document.forms['formworker']['workerId'].value;
	scheduleObj.workerId = worker;
	scheduleObj.datesList = masDates;
	if(masDates.length !=0){
		var createDelDaysRequest = Object.create(RequestAdmin);
		createDelDaysRequest.DelWorkingDays(scheduleObj);
	}
	
}

function setWorkingDaysToCallendar (json){
	var allTd =  document.querySelectorAll("[currentdate]");
	var jsonArr = json.datesList.length;
	var j = 0;
	for (var i = 0; i < allTd.length; i++) {
		if(allTd[i].getAttribute("currentDate") == json.datesList[j]){
			allTd[i].setAttribute("class", "isWorking");
			allTd[i].setAttribute("id", "isWorking");
			j++;
			if(j == jsonArr){
				break;
			}
		}
	}
}