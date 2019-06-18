<script>
var DisplayPass = true;

function clientLogin(menu, win) {
	menu.style.display = "none";
	if (DisplayPass) {
		$('#register').css("display","block");
		DisplayPass = false;
	}
}
function techLogin(menu, win) {
	menu.style.display = "none";
	if (DisplayPass) {
		$('#registertn').css("display","block");
		DisplayPass = false;
	}
}

function MenuOver(obj, classname) {
	HideAll(classname);
	document.getElementById(obj).style.display = 'block';
}

function HideAll(classname) {
	$("."+classname).hide();
}

function closeWindow(win) {
	win.style.display = "none";
	DisplayPass = true;
}
</script>
