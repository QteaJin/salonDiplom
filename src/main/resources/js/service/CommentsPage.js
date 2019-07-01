function checkUserTokenForComment () {
    console.log(true);
    if(checkTokenExistForComments()){ //проверка токена
      authorizationTrue ();
    }
 }
 
  function authorizationTrue () {
    var mainDiv = document.getElementById("commentform");
    while (mainDiv.firstChild) {
      mainDiv.removeChild(mainDiv.firstChild);
    }
    var divSecond = document.createElement("div");
    divSecond.className = "col-sm-12 col-lg-6";
    divSecond.style.align="right";
    var form = document.createElement("form");
    var textarea  = document.createElement("textarea");
    textarea.className = "form-control";
    textarea.setAttribute("rows", 5);
    textarea.setAttribute("id", "commentText");
    textarea.setAttribute("placeholder", "Введите сообщение");
    var div = document.createElement("div");
    div.className = "d-flex flex-row-reverse";

    var button = document.createElement("input");
    button.setAttribute("type", "submit");
    button.setAttribute("id", "sendCommentsButton");
    button.setAttribute("onclick", "publishNewComment(event);");
    button.className = "btn";
    button.innerHTML = "Отправить";

    div.appendChild(button);

    form.appendChild(textarea);
    form.appendChild(div);
    divSecond.appendChild(form);

    mainDiv.appendChild(divSecond);
  }

  function publishNewComment (event) {
    event.preventDefault();
    var comment = document.getElementById("commentText");
    var text = comment.value;
    if(text == ""){
      console.log("in");
      	var text = "Введите сообщение";
		infoBlock(text);
      return;
    }
	var messageForm = {};
	messageForm.description = text;
	var messageRequest = Object.create(Request);
	messageRequest.SendNewComment(messageForm);

    comment.value = "";
  }