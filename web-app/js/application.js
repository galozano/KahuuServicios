$(document).ready(function(){
	$(".formularioContacto").colorbox({inline:true, href:"#formularioHtml",onLoad:function()
		{
			$("#formularioHtml").css('display','inline');
		}, onCleanup: function()
		{
			$("#formularioHtml").css('display','none');
		}});
});

if (typeof jQuery !== 'undefined') {
	(function($) {
		$('#spinner').ajaxStart(function() {
			$(this).fadeIn();
		}).ajaxStop(function() {
			$(this).fadeOut();
		});
	})(jQuery);
}

function disableRecommend(data)
{
	if(data == 'agregado')
	{
		$("#botton-recomendar").attr('class', 'botton-recomendar-disabled');
	}
	else if(data == 'eliminado')
	{
		$("#botton-recomendar").attr('class', 'botton-recomendar-enable');
	}
	fetchInfoRecomendados();
}

function error( )
{
	alert("No se pudo guardar la recomendacion");
}





