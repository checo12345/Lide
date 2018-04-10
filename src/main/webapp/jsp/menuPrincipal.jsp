<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!--
========================================================================
* Sistema: LIDE
* Modulo: jsp
* Sub modulo: menuPrincipal.jsp
* Fecha de modificación: 09/04/2018
* Descripción:  JSP para pintar el menu principal
* Autor: Sergio Rojas
=========================================================================
-->

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	
    <title>CONTRATISTAS</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link href="css/plugins/footable/footable.core.css" rel="stylesheet">
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
    <link href="css/venta.css" rel="stylesheet">
    <link href="css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css" rel="stylesheet">
    
    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.js"></script>
    <script src="js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="js/plugins/flot/jquery.flot.pie.js"></script>
    <script src="js/plugins/peity/jquery.peity.min.js"></script>
    <script src="js/demo/peity-demo.js"></script>
    <script src="js/inspinia.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
    <script src="js/plugins/jquery-ui/jquery-ui.min.js"></script>
    <script src="js/plugins/gritter/jquery.gritter.min.js"></script>
    <script src="js/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="js/demo/sparkline-demo.js"></script>
    <script src="js/plugins/chartJs/Chart.min.js"></script>
    <script src="js/plugins/toastr/toastr.min.js"></script>
    <!-- JS que se usaran en mas de una plantilla jsp -->
    <script src="js/plugins/validate/jquery.validate.min.js"></script>
    <script src="js/plugins/footable/footable.all.min.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    
    <script  type="text/javascript" charset="utf-8" src="js/menuPrincipal.js" ></script>
    <script  type="text/javascript" charset="utf-8" src="js/funcionesGenericas.js" ></script>
    
    
</head>

<body class="md-skin">
    <div id="wrapper">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav metismenu" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element"> <span>
                            <img alt="image" width="100px" class="img-circle" src= 
                            <s:if test="usuario.usuario_fotoBase64Regreso != null ">'<s:property value="usuario.usuario_fotoBase64Regreso"/>' </s:if> 
                            <s:else>'imagen/usuario.png' </s:else> />
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold"><s:property value="usuario.usuario_usuario"/></strong>
                             </span> <span class="text-muted text-xs block">Cajero<b class="caret"></b></span> </span> </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="profile.html">Cuenta</a></li>
                                <li class="divider"></li>
                                <li><a href="cerrarSesion.action">Salir</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            SPV
                        </div>
                    </li>
                    
                    <li>
						<a onclick="obtenerPuntoDeVenta()">
							<i class="fa fa-shopping-cart"></i> 
							<span class="nav-label">Punto de Venta</span>
						</a>
					</li>
					<li>
						<a href="index.html">
							<i class="fa fa-user-circle"></i> 
							<span class="nav-label">Clientes</span> 
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level collapse">
							<li><a href="index.html">Actualizar clientes</a></li>
						</ul>
					</li>
					<li>
						<a href="index.html">
							<i class="fa fa-barcode"></i> 
							<span class="nav-label">Productos</span> 
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level collapse">
							<li><a onclick="obtenerActualizacionProducto()">Actualizar productos</a></li>
						</ul>
					</li>
					<li>
						<a href="index.html">
							<i class="fa fa-file-text-o"></i> 
							<span class="nav-label">Inventario</span> 
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level collapse">
							<li><a href="index.html">Exportar inventario</a></li>
						</ul>
					</li>
					<li>
						<a href="index.html">
							<i class="fa fa-gear"></i> 
							<span class="nav-label">Configuracion</span> 
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level collapse">
							<li><a href="index.html">Cuenta</a></li>
							<li><a href="index.html">Cajeros</a></li>
						</ul>
					</li>
					<li>
						<a href="index.html"> 
							<i class="fa fa-list-alt"></i> 
							<span class="nav-label">Reportes</span> 
							<span class="fa arrow"></span>
						</a>
						<ul class="nav nav-second-level collapse">
							<li><a href="index.html">Reporte de ventas</a></li>
							<li><a href="index.html">Reporte de inventario</a></li>
							<li><a href="index.html">Reporte de cajeros</a></li>
						</ul>
					</li>
				</ul>
            </div>
        </nav>

        <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
        <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
        </div>
            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <span class="m-r-sm text-muted welcome-message"> SISTEMA PUNTO DE VENTA WEB </span>
                </li>
                <li>
                    <span class="m-r-sm text-muted welcome-message">Empresa: Tienda de Abarrotes<s:property value="usuario.empresa_descripcion"/></span>
                </li>
                <li>
                    <a href="cerrarSesion.action">
                        <i class="fa fa-sign-out"></i> Cerrar Sesi&oacuten
                    </a>
                </li>
            </ul>

        </nav>
        </div>
			<div class="wrapper wrapper-content animated">
				<div class="ibox-content">
					<div class="sk-spinner sk-spinner-double-bounce">
						<div class="sk-double-bounce1"></div>
						<div class="sk-double-bounce2"></div>
					</div>
					<div id="contenidoDinamico">
						<h1 class="m-t-none m-b" style="text-align: center;" id="tituloForm">BIENVENIDO A LIDE</h1>
					</div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>
