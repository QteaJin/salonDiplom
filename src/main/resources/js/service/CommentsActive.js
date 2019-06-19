function createRequestComments(){
	var commentsRequest = Object.create(Request);
	commentsRequest.GetAllActiveComments();
}

function createCommentsBlocks (jsonObj) {
		var mainDiv = document.getElementById("commentblock");
		while (mainDiv.firstChild) {
    	mainDiv.removeChild(mainDiv.firstChild);
		}
		for (var i = 0; i < jsonObj.length; i++) {
			var container = document.createElement("div");
			container.className = "container";
			if(i%2!=0){
				container.setAttribute("align", "right");
			}
		var divCol8 = document.createElement("div");
			divCol8.className = "col-8 comment";

		var commenthead = document.createElement("div");
			commenthead.className = "d-flex justify-content-between commenthead";
		var name = document.createElement("div");
			name.className = "name";
		var hasName = jsonObj[i].client.profile.name;
			if(hasName == null){ hasName = "Клиент салона" }
			name.innerHTML = "<b>" + hasName + "</b>";
		var dateDiv = document.createElement("div");
			dateDiv.className = "date";
			dateDiv.innerHTML = moment(jsonObj[i].date).format('YYYY-MM-DD');

		var descr = document.createElement("div");
			descr.className = "descr"; 
			descr.innerHTML = jsonObj[i].description;

			commenthead.appendChild(name);
			commenthead.appendChild(dateDiv);
			divCol8.appendChild(commenthead);
			divCol8.appendChild(descr);
			container.appendChild(divCol8);
			mainDiv.appendChild(container);
		}
	}