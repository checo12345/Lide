<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1 class="m-t-none m-b" style="text-align: center;" id="tituloForm">REGISTRAR NUEVO CONTACTO</h1>
<form id="registrarContactoForm" class="form-horizontal" action="agregarContacto" role="form" onsubmit="return false;" style="min-height: 70px;">
	<h3>Datos del Contacto</h3>
	<div class="col-sm-4">
		<label class="control-label">Nombre</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> 
			<input type="hidden" name="contacto.pk_contacto" value='<s:property value="contacto.pk_contacto"/>'>
			<input type="text" class="form-control" name="contacto.contacto_nombreCompleto" value='<s:property value="contacto.contacto_nombreCompleto"/>'
				placeholder="Ingresar nombre" required>
		</div>
	</div>
	
	<div class="col-sm-4">
		<label class="control-label">Apellido Paterno</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> <input type="text" class="form-control"
				name="contacto.contacto_apellidoPaterno"
				value='<s:property value="contacto.contacto_apellidoPaterno"/>'
				class="form-control" placeholder="Ingresar descripción" />
		</div>
	</div>
	
	<div class="col-sm-4">
		<label class="control-label">Apellido Materno</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> <input type="text" class="form-control"
				name="contacto.contacto_apellidoMaterno"
				value='<s:property value="contacto.contacto_apellidoMaterno"/>'
				class="form-control" placeholder="Ingresar descripción" />
		</div>
	</div>
	<br>
	<div style="margin-top: 60px;">
	<button type="submit" class="btn btn-primary block m-b pull-right"
		id="btn-admon-contacto" style="margin-left: 10px;">Guardar</button>
	<button type="button" class="btn block m-b pull-right"
		id="btn-limpiar-form" onclick="limpiarForm(registrarContactoForm.id)">Limpiar</button>
	</div>
</form>
