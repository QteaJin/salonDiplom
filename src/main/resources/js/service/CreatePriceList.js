
function getRequestPriceList(){
	var getAllPricesRequest = Object.create(Request);
	getAllPricesRequest.GetPriceList();
}

function publishPriceList(json){
	var mainDiv = document.getElementById("pricelist");

	for (var i = 0; i < json.length; i++) {
		var div = document.createElement("div");
		div.setAttribute("class", "mt-5 mb-5");
		div.setAttribute("style", "background-color:lightblue");
		div.setAttribute("align", "center");

		div.innerHTML = "<h4>" + json[i].name + "</h4>" + "<img src=\"../img/venzel1.png\" width=\"200px\">";
		
		var rowCount = 0;
		var table = document.createElement("table");
		table.setAttribute("class", "table table-striped");
		table.setAttribute("style", "width:100%");
		for (var j = 0; j < json[i].catalogList.length; j++) {
			
			 var row = table.insertRow(rowCount);
			 var cell0 = row.insertCell(0);
			 cell0.setAttribute("width", "30%");
			 cell0.innerHTML = json[i].catalogList[j].name;
			 var cell1 = row.insertCell(1);
			 cell1.innerHTML = json[i].catalogList[j].description;
			 var cell2 = row.insertCell(2);
			 cell2.setAttribute("width", "10%");
			 cell2.innerHTML = json[i].catalogList[j].price + " грн.";
			 var cell3 = row.insertCell(3);
			 cell3.setAttribute("width", "10%");
			 cell3.innerHTML = json[i].catalogList[j].timeLead + " мин.";

			 rowCount++;
		}
		mainDiv.appendChild(div);
		mainDiv.appendChild(table);
	}
}