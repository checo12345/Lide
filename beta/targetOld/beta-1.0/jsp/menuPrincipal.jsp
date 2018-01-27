<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!--
========================================================================
* CHECKSOFT
* Sistema: Contratistas
* Modulo: jsp
* Sub modulo: menuPrincipal.jsp
* Fecha de modificación: 14/08/2017
* Descripción:  JSP para pintar el menu principal validando los permisos del usuario
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
                             </span> <span class="text-muted text-xs block">Puesto<b class="caret"></b></span> </span> </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="profile.html">Perfil</a></li>
                                <li><a href="contacts.html">Contactos</a></li>
                                <li class="divider"></li>
                                <li><a href="cerrarSesion.action">Salir</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            SPV
                        </div>
                    </li>
                    <s:if test="listaModulos!=null">
						<s:iterator value="listaModulos">
							<li>
		                        <a href="index.html"><i class="<s:property value="modulo_icono"/>"></i> <span class="nav-label"><s:property value="modulo_nombre"/></span> <span class="fa arrow"></span></a>
		                        <s:if test="listaSubModulos!=null">
		                        <ul class="nav nav-second-level">
								<s:iterator value="listaSubModulos">
			                            <li><a onclick='<s:property value="subModulo_accion"/>'><s:property value="subModulo_nombre"/></a></li>
								</s:iterator>
								</ul>
							</s:if>
		                    </li>
						</s:iterator>
					</s:if>
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
					<div id="contenidoDinamico"><s:include value="/jsp/formularioContacto.jsp"></s:include></div>
					<div id="contenidoDinamico2"><s:include value="/jsp/tablaContacto.jsp"></s:include></div>
					<div id="contenidoDinamico3"><s:include value="/jsp/cobrarVenta.jsp"></s:include></div>
				</div>
			</div>
		</div>
    </div>
</body>
</html>
