$(document).ready(function() {
	$('.parallax').parallax();
	$('select').material_select();
	$(".button-collapse").sideNav();
	$('.loader').hide();
	$('.dropdown-button').dropdown({
		belowOrigin:true
	});
});

function mostrarLoader() {
	$('.loader').show();
}

function ocultarLoader() {
	$('.loader').hide();
}