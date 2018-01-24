<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1 class="m-t-none m-b" style="text-align: center;" id="tituloForm">REGISTRAR NUEVA VENTA</h1>
<form id="registrarContactoForm" class="form-horizontal" action="agregarContacto" role="form" onsubmit="return false;" style="min-height: 70px;">
	<div class="col-sm-8">
		<label class="control-label">Codigo del producto</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> 
			<input type="hidden" name="contacto.pk_contacto" value='<s:property value="contacto.pk_contacto"/>'>
			<input type="text" class="form-control" name="contacto.contacto_nombreCompleto" value='<s:property value="contacto.contacto_nombreCompleto"/>'
				placeholder="Ingresar codigo de barras del producto" required>
		</div>
	</div>
	
	
	<div class="col-sm-4">
	<label class="control-label">Acciones</label>
		<div class="input-group">
			<button type="submit" class="btn btn-primary block m-b pull-right"
		id="btn-admon-contacto" style="margin-left: 10px;">Agregar Producto</button>
		</div>
		
	</div>
	<br>
</form>
