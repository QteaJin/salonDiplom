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
	for (var i = 0; i < endDays; i++) {
		var now = moment();
		if(startDayofWeek != 0){
			for (var j = 0; j < startDayofWeek.length; j++) {
				var now = moment();
				now.add(minusDay, 'days');
				var cell = row.insertCell(j);
				cell.setAttribute("class", "pasttime");
				cell.setAttribute('currentDate', now.valueOf());
				cell.innerHTML = now.format('DD/MM');
				minusDay++;
				i++;
			}
		}

		if (i%7 == 0 && i !=0) {
			numRow++;
			row = table.insertRow(numRow);
			numDay = 0;

		}
		var cell = row.insertCell(numDay);
		cell.setAttribute("class", "futuretime");
		now.add(i, 'days');
		cell.setAttribute('currentDate', now.valueOf());
		cell.innerHTML = now.format('DD/MM');
		numDay++;

	}  



	scheduleDiv.appendChild(table);
	createButtonsForSchedule ();
}

function choseCell(event) {
	console.log(event.target.getAttribute("currentDate"));
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

	scheduleDiv.appendChild(addButton);
	scheduleDiv.appendChild(delButton);
}

function getChosenDatesAdd () {
	var scheduleObj = {};
	var masDates = [];
	var dates = document.querySelectorAll(".selectedDate");
	for (var i = 0; i < dates.length; i++) {
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