
<link href="css/plugins/select2/select2.min.css" rel="stylesheet">
<script src="js/plugins/select2/select2.full.min.js"></script>
<script src="js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script src="js/contratistas.js"></script>

<h1 class="m-t-none m-b" style="text-align: center;">REGISTRAR NUEVA EMPRESA</h1>
<form id="registrarEmpresaForm" class="form-horizontal" action="registrarCuenta" role="form" onsubmit="return false;">
	<h3>Datos de la Compañia</h3>
	<div class="row">
		<div class="col-sm-4">
			<label class="control-label">Nombre</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-building-o"></i>
				</span> 
				<input type="text" class="form-control" name="usuario.usuario_telefono" placeholder="Ingresar Nombre" required>
			</div>
		</div>

		<div class="col-sm-4">
			<label class="control-label">RFC</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-pencil"></i>
				</span> <input type="text" class="form-control" name="usuario.usuario_telefono" placeholder="Ingresar RFC" required>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label class="control-label">Registro Patronal</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-pencil"></i>
				</span> 
				<input type="number" class="form-control" name="usuario.usuario_telefono" placeholder="Ingresar Registro P" required>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<label class="control-label">Correo Electrónico</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-envelope"></i>
				</span> 
					<input type="number" class="form-control" name="usuario.usuario_telefono" placeholder="Ingresar Correo" required>
			</div>
		</div>

		<div class="col-sm-4">
			<label class="control-label">SPOC</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-pencil"></i>
				</span> 
				<input type="number" class="form-control" name="usuario.usuario_telefono" placeholder="Ingresar SPOC" required>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label class="control-label">Tipo de Operaci&oacuten</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-pencil"></i>
				</span> 
				<input type="number" class="form-control" name="usuario.usuario_telefono" placeholder="Ingresar Tipo" required>
			</div>
		</div>
	</div>
	
	
	<hr>
	<button type="submit" class="btn btn-primary block m-b pull-right" id="btn-admon-usr" onclick="limpiarForm()" style="margin-left: 10px;" >Registrar</button>
	<button type="submit" class="btn block m-b pull-right" id="btn-admon-usr" onclick="registrarEmpresa()" >Limpiar</button>
	
</form>
