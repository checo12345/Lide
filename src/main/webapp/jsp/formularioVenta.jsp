<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<script src="js/puntoVenta.js" charset="UTF-8"></script>
<%@ taglib prefix="s" uri="/struts-tags"%>


<h1 class="m-t-none m-b" style="text-align: center;" id="tituloForm">REGISTRAR NUEVA VENTA</h1>
<form id="buscarProductoForm" class="form-horizontal" action="agregarProducto" role="form" onsubmit="return false;" style="min-height: 70px;">
	<div class="col-sm-4">
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
		<label class="control-label">Nombre del producto</label>
		<div class="input-group">
			<span class="input-group-addon"> <span
				class="glyphicon glyphicon-tags"></span>
			</span> 
			<input type="text" class="form-control" name="keyword.name"  id="nombreProducto"	placeholder="Ingresar nombre del producto">
			<input type="hidden"  name="keyword.coverage_area_id" value="5066549580791808">
			<input type="hidden"  name="keyword.store_id" value="5629499534213120">
		</div>
	</div>
	
	<div class="col-sm-4">
	<label class="control-label">Acciones</label>
		<div class="input-group">
			<button onclick="obtenerProductoPorNombre()" type="button" class="btn btn-primary block m-b pull-right"  data-toggle="modal" data-target="#myModal"> Agregar Producto</button>
		</div>
		
	</div>
	<br>
</form>
<div id="contenidoError"></div>
<div id="contenidoDinamico2"><s:include value="/jsp/tablaProducto.jsp"></s:include></div>
<div id="contenidoDinamico3"><s:include value="/jsp/cobrarVenta.jsp"></s:include></div>


<div id="mdl-productos" class="modal inmodal fade" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h1 class="m-t-none m-b" id="tituloRolForm">Productos Encontrados</h1>
            </div>
            <div class="modal-body" id="modalDinamico">
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-white" data-dismiss="modal">Cerrar</button>
				<button type="button" class="btn btn-primary">Agregar</button>
            </div>
        </div>
    </div>
</div>
