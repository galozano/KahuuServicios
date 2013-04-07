//$(document).ready(function(){
//    $( "#tabs" ).tabs();
//});


if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

function disableRecommend( )
{
	$(".botton-recomendar").prop('disabled', true);
}

function error( )
{
	alert("No se pudo guardar la recomendacion");
}



