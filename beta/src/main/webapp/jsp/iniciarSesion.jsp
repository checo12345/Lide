<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Iniciar</title>
	<link rel="stylesheet" href="css/plugins/toastr/toastr.min.css">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
	
	<!-- Ladda style -->
    <link href="css/plugins/ladda/ladda-themeless.min.css" rel="stylesheet">
    
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- Mainly scripts -->
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/toastr/toastr.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script async defer src="https://apis.google.com/js/api.js" 
	        onload="this.onload=function(){};handleClientLoad()" 
	        onreadystatechange="if (this.readyState === 'complete') this.onload()">
	</script>
    <!-- Ladda -->
    <script src="js/plugins/ladda/spin.min.js"></script>
    <script src="js/plugins/ladda/ladda.min.js"></script>
    <script src="js/plugins/ladda/ladda.jquery.min.js"></script>
	
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
    
    <script src="js/iniciarSesion.js"></script>
</head>

<body class="gray-bg">
    <div class=" middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">SPV</h1>

            </div>
            <h3>Bienvenido al Sistema Punto de Venta Web</h3>
            <p>El unico que le ayudara a la administracion de su negocio
                <!--Continually expanded and constantly improved Inspinia Admin Them (IN+)-->
            </p>
            <p>Ingresa para comenzar </p>
            <form class="m-t" role="form" action="iniciarSesion" method="post" id="iniciarSesionForm">
                <br>
                <button type="button" style="display: none; class="ladda-button btn btn-primary block full-width m-b" onclick="irMenuPrincipal()" data-style="zoom-in">Iniciar Sesi&oacuten</button>
				<button type="button" class="ladda-button btn btn-primary block full-width m-b" id="sign-in-or-out-button" data-style="zoom-in" >Iniciar Sesi&oacuten</button>
                <a onclick="modalRecuperar()"><small>Olvidaste tu Contrase&ntildea</small></a>
                <p class="text-muted text-center"><small>No estas registrado</small></p>
                <a class="btn btn-sm btn-white btn-block" onclick="modalRegistrarEmpresa()">Crear cuenta</a>
                <input type="hidden" name="acceso.acceso_ip" id="ip" >
                <input type="hidden" name="acceso.acceso_navegador" id="navegador" >
                <input type="hidden" name="acceso.acceso_idioma" id="idioma">
                <input type="hidden" name="acceso.acceso_codigoPais" id="codigoPais">
                <input type="hidden" name="acceso.acceso_nombrePais" id="nombrePais">
                <input type="hidden" name="acceso.acceso_nombreRegion" id="nombreRegion">
                <input type="hidden" name="acceso.acceso_nombreCiudad" id="nombreCiudad">
                <input type="hidden" name="acceso.acceso_latitude" id="latitude">
                <input type="hidden" name="acceso.acceso_longuitud" id="longuitud">
            </form>
            <p class="m-t" onclick="probarGoogle()"> <small>CheckSoft &copy; 2017</small> </p>
        </div>
    </div>

<div style="text-align: center;"> <button id="revoke-access-button" style="display: none;">Revoke access</button></div>


	<div><s:include value="/jsp/recuperarCuenta.jsp"></s:include></div>
	<div><s:include value="/jsp/registrarEmpresa.jsp"></s:include></div>
</body>

</html>
