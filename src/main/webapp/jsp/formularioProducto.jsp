<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<h1 class="m-t-none m-b" style="text-align: center;" id="tituloForm">Datos del Producto</h1>
<form id="registrarProductoForm" class="form-horizontal"  role="form" onsubmit="return false;" style="min-height: 70px;">

	
	<div class="row"> 
		<div class="col-sm-4">
			<label class="control-label">Nombre</label>
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-tags"></span>
				</span> 
				<input type="hidden" name="producto.codigoBarras" value='<s:property value="producto.codigoBarras"/>'>
				<input type="hidden"  name="producto.coverageAreaId" value="5066549580791808">
				<input type="hidden"  name="producto.storeId" value="5629499534213120">
				<input type="text" class="form-control" name="producto.name" value='<s:property value="producto.name"/>'
					placeholder="Ingresar nombre" required>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label class="control-label">Existencia</label>
			<div class="input-group">
				<span class="input-group-addon"> <span
					class="glyphicon glyphicon-tags"></span>
				</span> <input type="text" class="form-control"
					name="producto.quantity"
					value='<s:property value="producto.quantity"/>'
					class="form-control" placeholder="Ingresar descripción" />
			</div>
		</div>
	
	<div class="col-sm-4">
		<label class="control-label">Precio</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> <input type="text" class="form-control"
				name="producto.price"
				value='<s:property value="producto.price"/>'
				class="form-control" placeholder="Ingresar descripción" />
		</div>
	</div>
	
	</div>
	
	
	
	<br>
	<div class="row"> 
		<div style="margin-top: 60px;">
			<button type="submit" class="btn btn-primary block m-b pull-right" onclick="actualizarProducto()"  style="margin-left: 10px;">Actualizar</button>
		</div>
	</div>
</form>
