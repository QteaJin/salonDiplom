function openWorkerProfile (event){
	event.stopPropagation();		
	// console.log(event.target.getAttribute('myData'));
	let workerId = event.target.getAttribute('myData');
	var workerProfileRequest = Object.create(Request);
	workerProfileRequest.GetProfileWorker(workerId);
			}

function createWorkerProfile(json, workerId){
	//console.log("function ---");
	//console.log(JSON.stringify(json));
	//console.log(workerId);
	  var myNode = document.querySelector('.calendar');
    while (myNode.firstChild) {
      myNode.removeChild(myNode.firstChild);
}
    var profile =  document.createElement('div');
    profile.setAttribute('class', 'workerprofile');
    myNode.appendChild(profile);
	createProfile(json, workerId);
	function createProfile (json, workerId) {
		
		closeProfileButton();
		
		 var parentProfile = document.querySelector(".workerprofile");
		 let profileBlock = document.createElement('div');
			profileBlock.setAttribute('class', 'profileBlock');

	let closeProfile = document.createElement("div");
	closeProfile.setAttribute('class', "row float-right");
	closeProfile.setAttribute('onclick', 'closeProfileButton();')
	let close = document.createTextNode('Close');
	let closeButton = document.createElement('button');
	
	let p = document.createElement('p');

	let workerFoto = document.createElement('img');
	workerFoto.setAttribute('src', 'img/worker/' + workerId + 'workerPhoto.jpg');
	workerFoto.setAttribute('width' , '30%');
	workerFoto.setAttribute('class', 'customWorkerFoto');
	workerFoto.setAttribute('align' , 'left');
	workerFoto.onerror = function () {
		workerFoto.setAttribute('src', 'img/nofoto.png');
	}
	p.appendChild(workerFoto);
	var span = document.createElement('span');
	span.innerHTML = json.description;
	p.appendChild(span);
	//p.appendChild(document.createTextNode(json.description));

//	let profileDescription = document.createElement('span');
//	profileDescription.appendChild(document.createTextNode(json.description));
	
	let workerSkills = document.createElement('div');

	workerSkills.setAttribute('class', 'd-flex align-content-start flex-wrap workerSkills');
	for (var i = 0; i < json.listSkills.length; i++) {
		let skill = document.createElement('div');
		skill.setAttribute('class', 'skill');
		skill.setAttribute('skillId', json.listSkills[i].skillsId);
		skill.innerHTML = json.listSkills[i].name;
		let ul = document.createElement('ul');
		if(json.listSkills[i].catalogList.length !=0){
			for (var j = 0; j < json.listSkills[i].catalogList.length; j++){
				console.log(json.listSkills[i].catalogList[j].description);
				let li = document.createElement('li');
				//li.innerHTML = json.listSkills[i].catalogList[j].description;
				li.appendChild(document.createTextNode(json.listSkills[i].catalogList[j].description));
				ul.appendChild(li);
			}
		}
		skill.appendChild(ul);
		
		workerSkills.appendChild(skill);
	}

	closeButton.appendChild(close);
	closeProfile.appendChild(closeButton);
	profileBlock.appendChild(closeProfile);
	//profileBlock.appendChild(workerFoto);
	//profileBlock.appendChild(profileDescription);
	profileBlock.appendChild(p);
	profileBlock.appendChild(workerSkills);
	parentProfile.appendChild(profileBlock);
	var item = parentProfile;
	//scrollToItem(item);
	
	}
}
	
	function closeProfileButton (argument) {
		var myNode = document.querySelectorAll('.profileBlock'); // delete
																	// prevent
																	// nodes
		if(myNode != null){
			myNode.forEach(e => e.parentNode.removeChild(e));
	}
	
}
	
