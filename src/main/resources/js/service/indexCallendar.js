
  var weekDay = ['Пн','Вт','Ср','Чт','Пт','Сб','Вс'];
  var calendarHead = document.querySelector('.calendar');
  var startDayofWeek = 0;
  var startDays = 0;
  var endDays = 21;

function createHead () {
	createLoginLogoutButton();
	
	var temp = document.querySelector('.dropdown');
	temp.setAttribute('salonid', '1');
	getRequestWorkersPhoto(temp);

  var myNode = document.querySelector('.calendar');
      while (myNode.firstChild) {
        myNode.removeChild(myNode.firstChild);
}
    
    for (var i = 0; i < weekDay.length; i++) {
    
     var weekDiv =  document.createElement('div');
     weekDiv.setAttribute('class', 'block');
     weekDiv.innerHTML = weekDay[i];
     calendarHead.appendChild(weekDiv);
    }
    var myBr = document.createElement('br');
    calendarHead.appendChild(myBr);
  createDate();
}

function createDate () {
  // var now = moment();
    
  //   console.log(now.format('DD/MM'));
  //   console.log(now.format('dddd'));
  
  // for (var i = 0; i < weekDay.length; i++) {
  //   var weekDiv =  document.createElement('div');
  //   weekDiv.setAttribute('class', 'block');
  //   calendarHead.appendChild(weekDiv);
  // }
  startDay();
  
  for (var i = 0; i < endDays; i++) {
    var now = moment();
    if (startDayofWeek%7 === 0 && startDayofWeek != 0) {
      var myBr = document.createElement('br');
      calendarHead.appendChild(myBr);
    }
    var weekDiv =  document.createElement('div');
     weekDiv.setAttribute('class', 'block');

     //weekDiv.setAttribute('onclick','doSomething(event);');
     now.add(i, 'days');
     weekDiv.innerHTML = now.format('DD/MM');
     weekDiv.setAttribute('currentDate', now.format('DD-MM-YYYY'));
     calendarHead.appendChild(weekDiv);
    
    startDayofWeek++;
  }
  var profile =  document.createElement('div');
  profile.setAttribute('class', 'workerprofile');
  calendarHead.appendChild(profile);
}
// function doSomething (event) {
//     var myNode = document.querySelectorAll('.chooseTime');

//       if(myNode != null){
//       myNode.forEach(e => e.parentNode.removeChild(e));

// }
//   var test = event.target.getAttribute('currentDate') ;
//   var createDiv = document.createElement('div');
//   var table = document.createElement('table');
//   table.setAttribute('id', 'tableShedule');
//   table.setAttribute('class', 'table table-striped');
//   createDiv.setAttribute('class', 'chooseTime');
//   createDiv.innerHTML = test;
//   createDiv.appendChild(table);
//   calendarHead.appendChild(createDiv);
//   console.log(test);
//   createTableShedule (table.getAttribute('id'));
// }

function startDay () { //new Date().getDay()
  switch (new Date().getDay()) {
  case 0:
    day = "Sunday";
    startDayofWeek = 6;
    createClearDiv ();
    break;
  case 1:
    day = "Monday";
    startDayofWeek = 0;
    createClearDiv ();
    break;
  case 2:
     day = "Tuesday";
     startDayofWeek = 1;
     createClearDiv ();
    break;
  case 3:
    day = "Wednesday";
    startDayofWeek = 2;
    createClearDiv ();
    break;
  case 4:
    day = "Thursday";
    startDayofWeek = 3;
    createClearDiv ();
    break;
  case 5:
    day = "Friday";
    startDayofWeek = 4;
    createClearDiv ();
    break;
  case 6:
    day = "Saturday";
    startDayofWeek = 5;
    createClearDiv ();
}

}
function createClearDiv () {
  var minusDay = -startDayofWeek;
 for (var i = 0; i < startDayofWeek; i++) {
   var now = moment();
   var clear = document.createElement('div');
   clear.setAttribute('class', 'blockpast');
    now.add(minusDay, 'days');
     clear.innerHTML = now.format('DD/MM');
   
   calendarHead.appendChild(clear);
   minusDay++;
  } 
  
}

function createTableShedule (tableId) {
  // Get a reference to the table
  var tableRef = document.getElementById(tableId);
  for (var i = 0; i < 5; i++) {
    // Insert a row in the table at row index 0
  var newRow = tableRef.insertRow(i);

  // Insert a cell in the row at index 0
  var newCell = newRow.insertCell(0);
   // Insert a cell in the row at index 1
  var newCell2 = newRow.insertCell(1);
  var formcheck = document.createElement('div');
  formcheck.setAttribute('class', 'form-check');
  var input = document.createElement('input');  
  input.setAttribute('class', 'form-check-input');
  input.setAttribute('type', 'radio');
  input.setAttribute('value', 'option'+i);
  input.setAttribute('id', 'exampleRadios'+i);
  input.setAttribute('name', 'radios');
  var label = document.createElement('label');
  label.setAttribute('class', 'form-check-label');
  label.setAttribute('for', 'exampleRadios'+i);
  label.innerHTML = 'Записаться';
  formcheck.appendChild(input);
  formcheck.appendChild(label);
  newCell2.appendChild(formcheck);
    // Append a text node to the cell
  var newText = document.createTextNode('New top row : ' + i);
  newCell.appendChild(newText);
  }
  
  }
