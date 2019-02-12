function openWorkerProfile (event){
	event.stopPropagation();		
	// console.log(event.target.getAttribute('myData'));
	let workerId = event.target.getAttribute('myData');
	var workerProfileRequest = Object.create(Request);
	workerProfileRequest.GetProfileWorker(workerId);
			}

function createWorkerProfile(json, workerId){
	console.log("function ---");
	console.log(JSON.stringify(json));
	console.log(workerId);
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

	let workerFoto = document.createElement('img');
	workerFoto.setAttribute('src', 'img/' + workerId + 'workerPhoto.jpg');
	workerFoto.setAttribute('width' , '30%');
	workerFoto.setAttribute('class', 'customWorkerFoto');
	workerFoto.onerror = function () {
		workerFoto.setAttribute('src', 'img/nofoto.png');
	}

	let profileDescription = document.createElement('div');
	profileDescription.appendChild(document.createTextNode(json.description));
	
	let workerSkills = document.createElement('div');

	workerSkills.setAttribute('class', 'd-flex align-content-start flex-wrap workerSkills');
	for (var i = 0; i < json.listSkills.length; i++) {
		let skill = document.createElement('div');
		skill.setAttribute('class', 'skill');
		skill.setAttribute('skillId', json.listSkills[i].skillsId);
		skill.innerHTML = json.listSkills[i].name;
		workerSkills.appendChild(skill);
	}

	closeButton.appendChild(close);
	closeProfile.appendChild(closeButton);
	profileBlock.appendChild(closeProfile);
	profileBlock.appendChild(workerFoto);
	profileBlock.appendChild(profileDescription);
	profileBlock.appendChild(workerSkills);
	parentProfile.appendChild(profileBlock);
	
	
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
	
