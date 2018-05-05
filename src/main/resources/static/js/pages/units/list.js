$(document).ready(function(){	

	$.getJSON("/units/getNewChars",function(response){
		loadChars(response);
	})
	
	$.getJSON("/units/getNewShips",function(response){
		loadShips(response);
	})
	
});

function loadChars(response){
	var size = 0;
	
	response.forEach(function(unit){
		var div = createNewChar(unit);
		document.getElementById('chars').appendChild(div);
		size++;
	});
	document.getElementById("charCount").textContent = size;
}


function loadShips(response){
	var size = 0;
	
	response.forEach(function(unit){
		var div = createNewChar(unit);
		document.getElementById('ships').appendChild(div);
		size++;
	});
	
	document.getElementById("shipsCount").textContent = size;
	
}



