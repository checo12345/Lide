<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1 class="m-t-none m-b" style="text-align: center;" id="tituloForm">REGISTRAR NUEVO PUESTO</h1>
<form id="registrarPuestoForm" class="form-horizontal" action="agregarPuesto" role="form" onsubmit="return false;">
	<h3>Datos del Puesto</h3>
	<div>
		<label class="control-label">Nombre</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> <input type="hidden" name="puesto.pk_puesto"
				value='<s:property value="puesto.pk_puesto"/>'>
			<input type="text" class="form-control"
				name="puesto.puesto_nombre"
				value='<s:property value="puesto.puesto_nombre"/>'
				placeholder="Ingresar nombre" required>
		</div>
	</div>
	<div>
		<label class="control-label">Descripción</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> <input type="text" class="form-control"
				name="puesto.puesto_descripcion"
				value='<s:property value="puesto.puesto_descripcion"/>'
				class="form-control" placeholder="Ingresar descripción" />
		</div>
	</div>
	<br>
	<button type="submit" class="btn btn-primary block m-b pull-right"
		id="btn-admon-puesto" style="margin-left: 10px;">Guardar</button>
	<button type="button" class="btn block m-b pull-right"
		id="btn-limpiar-form" onclick="limpiarForm(registrarPuestoForm.id)">Limpiar</button>
</form>
