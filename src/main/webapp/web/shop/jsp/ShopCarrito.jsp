<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<!--le decimos a nuestra pÃ¡gina que vamos a utilizar el mÃ³dulo app que hemos creado-->
<html ng-app="app">
<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <title>Carrito de compras con AngularJS</title>
  <link rel="stylesheet"  href="/web/shop/css/normalize.css"  media="screen" />
  <link rel="stylesheet"  href="/web/shop/css/foundation.min.css" media="screen" />  
  <script type="text/javascript" src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
  <!--cdn con la version 1.2.4 de angular.js-->
  <script type='text/javascript' src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.4/angular.min.js"></script>
  <!--cdn con el modulo ngRoute de angular-->
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.0rc1/angular-route.min.js"></script>
  <!--cargamos el modulo cart-->
  <script type="text/javascript" src="/web/shop/js/cart.js"></script>
  <!--archivo app.js, donde hemos definido nuestro modulo app-->
  <script type='text/javascript' src="/web/shop/js/app.js"></script>
</head>
    <body>
      <!--directiva ng-view, aquÃ­ cargara todo el contenido-->
        <div ng-view></div>
    </body>
</html>