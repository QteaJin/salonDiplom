function infoBlock(text){
		var infoBlock = document.getElementById("infoblock");
		var infotext = document.getElementById("infotext");
		infotext.innerHTML = text;
		var op = 0.1;  // initial opacity
    	infoBlock.style.visibility = 'visible';
    	infoBlock.style.display = 'block';
    	var timer = setInterval(function () {
        if (op >= 1){
            clearInterval(timer);
        }
        infoBlock.style.opacity = op;
        infoBlock.style.filter = 'alpha(opacity=' + op * 100 + ")";
        op += op * 0.1;
    	}, 10);
    	setTimeout(fade, 3000, infoblock); 
    	//fade(infoBlock);
	}
	function fade(element) {
    var op = 1;  // initial opacity
    var timer = setInterval(function () {
        if (op <= 0.1){
            clearInterval(timer);
            element.style.display = 'none';
        }
        element.style.opacity = op;
        element.style.filter = 'alpha(opacity=' + op * 100 + ")";
        op -= op * 0.1;
    }, 50);
}