<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<h1 class="m-t-none m-b" style="text-align: center;" id="tituloForm">REGISTRAR NUEVO DEPARTAMENTO</h1>
<form id="registrarDepartamentoForm" class="form-horizontal"
	action="agregarDepartamento" role="form" onsubmit="return false;">
	<h3>Datos del Departamento</h3>
	<div>
		<label class="control-label">Nombre</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> <input type="hidden" name="departamento.pk_departamento"
				value='<s:property value="departamento.pk_departamento"/>'>
			<input type="text" class="form-control"
				name="departamento.departamento_nombre"
				value='<s:property value="departamento.departamento_nombre"/>'
				placeholder="Ingresar nombre" required>
		</div>
	</div>
	<div>
		<label class="control-label">Descripción</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> <input type="text" class="form-control"
				name="departamento.departamento_descripcion"
				value='<s:property value="departamento.departamento_descripcion"/>'
				class="form-control" placeholder="Ingresar descripción" />
		</div>
	</div>
	<br>
	<button type="submit" class="btn btn-primary block m-b pull-right"
		id="btn-admon-departamento" style="margin-left: 10px;">Guardar</button>
	<button type="button" class="btn block m-b pull-right"
		id="btn-limpiar-form" onclick="limpiarForm(registrarDepartamentoForm.id)">Limpiar</button>
</form>
