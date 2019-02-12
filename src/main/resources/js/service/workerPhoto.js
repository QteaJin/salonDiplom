		    
			function getRequestWorkersPhoto(obj){
			let salonId = obj.getAttribute('salonid');	
			var workerPhotoRequest = Object.create(Request);
		    workerPhotoRequest.GetPhotoWorker(salonId);
			}

			function createWorkerPhoto (obj) {
			
			var myNode = document.querySelectorAll('.workerPhoto'); //delete prevent nodes
      		if(myNode != null){
      		myNode.forEach(e => e.parentNode.removeChild(e));}
      		
      		var photo = document.getElementById('workerPhoto');
      		
      		//console.log((Object.keys(obj).length === 0));
			if(Object.keys(obj).length === 0){
				let divParent = document.createElement('div');
				divParent.setAttribute('class', 'workerPhoto');
				divParent.innerHTML = "NO WORKERS IN THE CITY";
				photo.appendChild(divParent);
				return;
			}		
		
      		
      		var numOfWorkers = obj.length;
      		for (var i = 0; i < numOfWorkers; i++) {
			var divParent = document.createElement('div');
			divParent.setAttribute('id', 'worker'+obj[i].id);
			divParent.setAttribute('class', 'workerPhoto');
			//divParent.setAttribute('onclick', 'openWorkerProfile (event);');
			var divChild1 = document.createElement('div');
			var divChild2 = document.createElement('div');
			divChild2.setAttribute('class', 'thumbnail');
			var link = document.createElement('a');
			link.setAttribute('href', '#');
			divParent.setAttribute('onclick', 'event.preventDefault(); openWorkerProfile (event);');
			var photoSrc = document.createElement('img');
			photoSrc.setAttribute('src', 'img/nofoto.png');
			photoSrc.setAttribute('alt', 'worker'+obj[i].id);
			photoSrc.setAttribute('style', 'width:100%');
			photoSrc.setAttribute('myData', obj[i].id);

			var divChild3 = document.createElement('div');
			divChild3.setAttribute('myData', obj[i].id);
			divChild3.innerHTML = obj[i].name + '<br>'+obj[i].descripton;
			link.appendChild(photoSrc);
			link.appendChild(divChild3);
			divChild2.appendChild(link);
			divChild1.appendChild(divChild2);
			divParent.appendChild(divChild1);
			photo.appendChild(divParent);
		}
		}
			
