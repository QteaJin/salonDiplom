		function createWorkerPhoto (obj) {
			
			var myNode = document.querySelectorAll('.workerPhoto'); //delete prevent nodes
      		if(myNode != null){
      		myNode.forEach(e => e.parentNode.removeChild(e));}
			//var workerProf = sendGet ();
		console.log(obj.name);
		var photo = document.getElementById('workerPhoto');
		var numOfWorkers = 5;
		for (var i = 0; i < numOfWorkers; i++) {
			var divParent = document.createElement('div');
			divParent.setAttribute('id', 'worker'+i);
			divParent.setAttribute('class', 'workerPhoto');
			//divParent.setAttribute('onclick', 'openWorkerProfile (event);')
			var divChild1 = document.createElement('div');
			//divChild1.setAttribute('class', 'col-md-8');
			var divChild2 = document.createElement('div');
			divChild2.setAttribute('class', 'thumbnail');
			var link = document.createElement('a');
			link.setAttribute('href', '#');
			var photoSrc = document.createElement('img');
			photoSrc.setAttribute('src', 'img/nofoto.png');
			photoSrc.setAttribute('alt', 'worker'+i);
			photoSrc.setAttribute('style', 'width:100%');
			photoSrc.setAttribute('myData', 'workerId : '+i)

			var divChild3 = document.createElement('div');
			//divChild3.setAttribute('class', 'caption');
			divChild3.setAttribute('myData', 'workerId : '+i)
			divChild3.innerHTML = 'Worker'+i;
			link.appendChild(photoSrc);
			link.appendChild(divChild3);
			divChild2.appendChild(link);
			divChild1.appendChild(divChild2);
			divParent.appendChild(divChild1);
			photo.appendChild(divParent);
		}
		}
		function openWorkerProfile (event) {
			
			event.stopPropagation();
			var myNode = document.querySelectorAll('.profile'); //delete prevent nodes

      		if(myNode != null){
      		myNode.forEach(e => e.parentNode.removeChild(e));

			console.log(event.target.getAttribute('myData'));
			var newDiv = document.createElement('div');
			var closeButton = document.createElement('button');
			closeButton.setAttribute('onclick', 'closeButtonProfile ();');

			closeButton.innerHTML = 'close';
			var text = document.createTextNode('Worker profile - ' + event.target.getAttribute('myData'));
			newDiv.appendChild(closeButton);
			newDiv.appendChild(text);
			newDiv.setAttribute('class', 'profile');
			document.getElementById('workerProfile').appendChild(newDiv);
			
		}
	}

	function closeButtonProfile () {
			var myNode = document.querySelectorAll('.profile'); //delete prevent nodes

      		if(myNode != null){
      		myNode.forEach(e => e.parentNode.removeChild(e));
			}
	}
		function sendGet () {
		var xhr = new XMLHttpRequest();
		var url = 'http://localhost:8080/worker/all';
		xhr.open("GET", url, true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = function () {
		    if (xhr.readyState === 4 && xhr.status === 200) {
		        var json = JSON.parse(xhr.responseText);
		        console.log(json);
		        return json;
    }
};

xhr.send();
		 
	}