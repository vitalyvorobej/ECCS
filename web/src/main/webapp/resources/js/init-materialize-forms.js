$(document).ready(function() {
	$('select').material_select();

	$('.datepicker').pickadate({
		selectMonths : true, // Creates a dropdown to control month
		selectYears : 15, // Creates a dropdown of 15 years to control year,
		today : 'Today',
		clear : 'Clear',
		close : 'Ok',
		closeOnSelect : false,
		format : 'yyyy-mm-dd'
	// Close upon selecting a date,
	});

});

/*
 * document.addEventListener('DOMContentLoaded', function() { var elems =
 * document.querySelectorAll('.dropdown-trigger'); var instances =
 * M.Dropdown.init(elems, options); });
 */

$(document).ready(function() {
	$(".dropdown-trigger").dropdown({
		hover : false,
		coverTrigger : false
	});
});

$(document).ready(function() {
	$('.button-collapse').sideNav();

});

$(document).ready(function() {
	$('.parallax').parallax();
});
