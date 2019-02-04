		    
			function getRequestWorkersPhoto(obj){
			let salonId = obj.getAttribute('salonid');	
			var workerPhotoRequest = Object.create(Request);
		    workerPhotoRequest.GetPhotoWorker(salonId);
			}

			function createWorkerPhoto (obj) {
			console.log(obj[0].name);
			var myNode = document.querySelectorAll('.workerPhoto'); //delete prevent nodes
      		if(myNode != null){
      		myNode.forEach(e => e.parentNode.removeChild(e));}
			
		
      		var photo = document.getElementById('workerPhoto');
      		var numOfWorkers = obj.length;
      		for (var i = 0; i < numOfWorkers; i++) {
			var divParent = document.createElement('div');
			divParent.setAttribute('id', 'worker'+obj[i].id);
			divParent.setAttribute('class', 'workerPhoto');
			var divChild1 = document.createElement('div');
			var divChild2 = document.createElement('div');
			divChild2.setAttribute('class', 'thumbnail');
			var link = document.createElement('a');
			link.setAttribute('href', '#');
			var photoSrc = document.createElement('img');
			photoSrc.setAttribute('src', 'img/nofoto.png');
			photoSrc.setAttribute('alt', 'worker'+obj[i].id);
			photoSrc.setAttribute('style', 'width:100%');
			photoSrc.setAttribute('myData', 'workerId : '+obj[i].id);

			var divChild3 = document.createElement('div');
			divChild3.setAttribute('myData', 'workerId : '+obj[i].id);
			divChild3.innerHTML = obj[i].name + '<br>'+obj[i].descripton;
			link.appendChild(photoSrc);
			link.appendChild(divChild3);
			divChild2.appendChild(link);
			divChild1.appendChild(divChild2);
			divParent.appendChild(divChild1);
			photo.appendChild(divParent);
		}
		}
