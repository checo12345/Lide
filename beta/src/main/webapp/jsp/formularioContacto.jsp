<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1 class="m-t-none m-b" style="text-align: center;" id="tituloForm">REGISTRAR NUEVA VENTA</h1>
<form id="registrarContactoForm" class="form-horizontal" action="agregarProducto" role="form" onsubmit="return false;" style="min-height: 70px;">
	<div class="col-sm-8">
		<label class="control-label">Codigo del producto</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> 
			<input type="text" class="form-control" name="producto.codigoBarras"  id="codigoProducto"	placeholder="Ingresar codigo de barras del producto">
			<input type="hidden"  name="producto.coverageAreaId" value="5066549580791808">
			<input type="hidden"  name="producto.storeId" value="5629499534213120">
		</div>
	</div>
	
	
	<div class="col-sm-4">
	<label class="control-label">Acciones</label>
		<div class="input-group">
			<button onclick="agregarProducto()" type="button" class="btn btn-primary block m-b pull-right" id="btn-admon-contacto">Agregar Producto</button>
		</div>
		
	</div>
	<br>
</form>
