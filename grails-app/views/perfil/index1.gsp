<!doctype html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html lang="es" class="no-js">
<!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>"Kahuu-Guia de Profesional para el Hogar"</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="Portal web para buscar servicios (electricista, plomeros, pintores, etc.) en Colombia" />
	<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
	<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
	<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">	
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'colorbox.css')}" type="text/css">
	<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/themes/smoothness/jquery-ui.css" type="text/css" media="all" />
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery.css')}" type="text/css">
	<link rel="image_src" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}" />
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.8.23/jquery-ui.min.js"></script>
	<script src="${resource(dir: 'js', file: 'jquery.colorbox-min.js')}" type="text/javascript"></script>
	<script src="${resource(dir: 'js', file: 'application.js')}" type="text/javascript"></script>
	<script src="${resource(dir: 'js', file: 'bootstrap.min.js')}" type="text/javascript"></script>
	<ga:trackPageview/>
</head>
<body>
	<facebook:initJS appId="${facebookContext.app.id}" xfbml="${true}" />
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="">Kahuu</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li><a href="#">Como Funciona?</a></li>
              <li><a href="#">Contactenos</a></li>
              <li class="dropdown">
                <a href="http://getbootstrap.com/2.3.2/examples/hero.html#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                  <li><a href="http://getbootstrap.com/2.3.2/examples/hero.html#">Action</a></li>
                  <li><a href="http://getbootstrap.com/2.3.2/examples/hero.html#">Another action</a></li>
                  <li><a href="http://getbootstrap.com/2.3.2/examples/hero.html#">Something else here</a></li>
                  <li class="divider"></li>
                  <li class="nav-header">Nav header</li>
                  <li><a href="http://getbootstrap.com/2.3.2/examples/hero.html#">Separated link</a></li>
                  <li><a href="http://getbootstrap.com/2.3.2/examples/hero.html#">One more separated link</a></li>
                </ul>
              </li>
            </ul>
            <form class="navbar-form pull-right">
              <input class="span2" type="text" placeholder="Email">
              <input class="span2" type="password" placeholder="Password">
              <button type="submit" class="btn">Ingresar</button>
            </form>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
      <div class="hero-unit">
        <h1><a href="${createLink(uri: '/')}"><img src="${resource(dir: 'images', file: 'logoKahuu.png')}" /></a></h1>
        <p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
        <p>
        	<a href="" class="btn btn-primary btn-large">Registrate como profesional</a>
        	<a href="" class="btn btn-primary btn-large">Registrate como usuario</a>
        </p> 
      </div>
    </div>
    
	<div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="span6">
         <h2>Que es?</h2>
         	<script>
			$(document).ready(function(){
				//Examples of how to assign the Colorbox event to elements
				$(".ajax").colorbox();
				$(".youtube").colorbox({iframe:true, innerWidth:560, innerHeight:315});
			});
			</script>
			<a href="http://www.youtube.com/embed/vD7LkpPxwhE?rel=0" class="youtube"><img width="350px" height="200px" src="${resource(dir: 'images', file: 'video_principal.jpg')}" /></a>		
        </div>
        <div class="span6">
          <h2>Like us on Facebook!</h2>
          <div class="fb-like-box" data-href="https://www.facebook.com/pages/Kahuuco/242764315796016" data-width="320" data-height="200" data-show-faces="true" data-stream="false" data-header="true"></div>
       </div>
      </div>

      <hr>
      <footer>
        <p> Company 2013</p>
      </footer>
    </div> 
</body>
</html>