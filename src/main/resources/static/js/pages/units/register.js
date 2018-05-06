function changeSide(){
	var a = document.getElementById("side");
	
	var img = document.getElementsByClassName("char-portrait")[0];
	img.className = "char-portrait char-portrait-" + ( a.checked ? "light" : "dark") + "-side";

	
	var b = document.getElementById("hidden_side");
	
	if (a.checked){
		b.value="LIGHT_SIDE";
	}else{
		b.value="DARK_SIDE";
	}
	


}