//creamos el modulo y le inyectamos el modulo ngRoute y el modulo cart que hemos creado
var app = angular.module("app", ['ngRoute','ng-Shop']);
 
//las rutas que vamos a utilizar para nuestro ejemplo
app.config(function($routeProvider)
{
    $routeProvider.when("/", {
        templateUrl : "/web/shop/home.html",
        controller : "homeController"
    })
    .when("/pay", {
        templateUrl : "/web/shop/pay.html",
        controller : "homeController"
    })
    .otherwise({ reditrectTo : "/" });
});
 
 
app.controller("homeController", function($scope, $shop)
{
    /**
    * @desc - establecemos los datos para el formulario de paypal
    * @return - object
    */
    function userDataPayPal()
    {
        var userData = {};
        userData.cmd = "_cart";
        userData.upload = "1";
        userData.business = "correo_business_paypal";
        userData.currencyCode = "EUR";
        userData.lc = "EU";
        userData.rm = 2;
        //url retorno paypal lado server, envia data post
        userData.successUrl = "http://localhost/cartAngularServer/return.php";
        userData.cancelUrl = "http://localhost/cartAngular/#/";
        userData.cbt = "Volver a la tienda";
        userData.formClass = "#formPaypal";
        return userData;
    }
 
    /**
    * @desc - a�ade x cantidad de un producto al carrito
    * @return - object - si es nueva inserci�n devuelve insert, en otro caso update
    */
    $scope.add = function(producto)
    {
        //alert(producto.total); return;
        var product = {};
        product.id = producto.id;
        product.price = producto.price;
        product.name = producto.name;
        product.category = producto.category;
        product.qty = parseInt(producto.total || 1,10);
        $shop.add(product);
    }
 
    /**
    * @desc - elimina un producto del carrito por su id
    */
    $scope.remove = function(id)
    {
        if($shop.remove(id))
        {
            alert("Producto eliminado correctamente");
            return;
        }
        else
        {
            alert("Ha ocurrido un error eliminando el producto, seguramente porqu� no existe");
            return;
        }
    }
    
    /**
    * @desc - elimina el contenido del carrito
    */
    $scope.destroy = function()
    {
        $shop.destroy();
    }
 
    /**
    * @desc - redondea el precio que le pasemos con dos decimales
    */
    $scope.roundCurrency = function(total)
    {
        return total.toFixed(2);
    }
 
    /**
    * @desc - formulario de paypal preparado para printar
    */
    $scope.paypalData = function()
    {
        $shop.dataPayPal(userDataPayPal());
    }
 
    /**
    * @desc - array de objetos con productos para el ejemplo
    */
    $scope.productosTienda =
    [
    {"id": 1, "category": "Detalles", "name": "Campanas", "price": 0.9, "picture": "/web/shop/img/carrito/campanas.jpg"},
    {"id": 2, "category": "Detalles", "name": "Carrito", "price": 1, "picture": "/web/shop/img/carrito/carrito.jpg"},
    {"id": 3, "category": "Detalles", "name": "Carrito con chupetes", "price": 1.2, "picture": "/web/shop/img/carrito/carrito_chupetes.jpg"},
    {"id": 4, "category": "Detalles", "name": "Cesta", "price": 1.6, "picture": "/web/shop/img/carrito/cesta.jpg"},
    {"id": 5, "category": "Detalles", "name": "Mini cesta", "price": 2, "picture": "/web/shop/img/carrito/cestita.jpg"},
    {"id": 6, "category": "Detalles", "name": "Enfermera", "price": 3, "picture": "/web/shop/img/carrito/enfermera.jpg"},
    {"id": 7, "category": "Detalles", "name": "Gatitos", "price": 2.5, "picture": "/web/shop/img/carrito/gatitos.jpg"},
    {"id": 8, "category": "Detalles", "name": "Perritos", "price": 2.5, "picture": "/web/shop/img/carrito/perritos.jpg"},
    {"id": 9, "category": "Detalles", "name": "Profesoras", "price": 2.5, "picture": "/web/shop/img/carrito/profesora.jpg"},
    {"id": 10, "category": "Detalles", "name": "Vestido", "price": 1.8, "picture": "/web/shop/img/carrito/vestido.jpg"},
    {"id": 11, "category": "Detalles", "name": "Otros", "price": 0.5, "picture": "/web/shop/img/carrito/otros.jpg"}
    ];
});