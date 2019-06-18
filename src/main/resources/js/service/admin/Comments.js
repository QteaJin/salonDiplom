function getAllComments(){
	
	var request = Object.create(RequestAdmin);
	request.GetAllCommentsRequest();
	
}

function createTableForComments(jsonObj){
	var mainDiv = document.getElementById("commentstable");
	while (mainDiv.firstChild) {
    	mainDiv.removeChild(mainDiv.firstChild);
		}
	var rowCount = 0;
	var table = document.createElement("table");
	table.setAttribute("class", "table table-striped");
	table.setAttribute("style", "width:100%");
	
	var row = table.insertRow(rowCount);
	var cell1 = row.insertCell(0);
		cell1.innerHTML = "ID";
	var cell2 = row.insertCell(1);
		cell2.innerHTML = "ID клиента";
	var cell3 = row.insertCell(2);
		cell3.innerHTML = "Дата";
	var cell4 = row.insertCell(3);
		cell4.innerHTML = "Комментарий";
		cell4.setAttribute("width", "40%");
	var cell5 = row.insertCell(4);
		cell5.innerHTML = "Статус";
	var cell6 = row.insertCell(5);
	rowCount++;
	for (var i = 0; i < jsonObj.length; i++) {
		var row = table.insertRow(rowCount);
		var cell1 = row.insertCell(0);
			cell1.innerHTML = jsonObj[i].id;
		var cell2 = row.insertCell(1);
			cell2.innerHTML = jsonObj[i].client.id;
		var cell3 = row.insertCell(2);
			cell3.innerHTML = moment(jsonObj[i].date).format('YYYY-MM-DD');
		var cell4 = row.insertCell(3);
			cell4.innerHTML = jsonObj[i].description;
			cell4.setAttribute("width", "40%");
		var cell5 = row.insertCell(4);
			cell5.innerHTML = jsonObj[i].status;
		var cell6 = row.insertCell(5);
		var button = document.createElement("button");
			button.appendChild(document.createTextNode("Изменить"));
			button.setAttribute("commentid", jsonObj[i].id);
			button.setAttribute("onclick", "changeStatusComment(event);");
			cell6.appendChild(button);


		rowCount++;
	}


	mainDiv.appendChild(table);
}
function changeStatusComment(event){
	event.stopPropagation();
	var commentId = event.target.getAttribute("commentid");
	var request = Object.create(RequestAdmin);
	request.ChangeCommentStatus(commentId);
}