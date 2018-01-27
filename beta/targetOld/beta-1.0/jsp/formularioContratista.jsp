
<link href="css/plugins/select2/select2.min.css" rel="stylesheet">
<script src="js/plugins/select2/select2.full.min.js"></script>
<script src="js/plugins/datapicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" charset="utf-8" src="js/contratistas.js"></script>

<h1 class="m-t-none m-b" style="text-align: center;">REGISTRAR NUEVO CONTRATISTA</h1>
<form id="registrarContratistaForm" class="form-horizontal" action="agregarContratista" role="form" onsubmit="return false;">
	<h3>Datos del Contratista</h3>
	<div class="row">
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Nombre(s)</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-user"></span>
				</span> 
				<input type="text" class="form-control" name="contratista.contratista_nombre" placeholder="Ingresar Nombre" required>
			</div>
		</div>

		<div class="col-sm-4">
			<label class="control-label">Apellido Paterno</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-user"></span>
				</span> 
				<input type="text" class="form-control" name="contratista.contratista_paterno" placeholder="Ingresar Apellido Paterno" required>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Apellido Materno</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-user"></span>
				</span> 
				<input type="text" class="form-control" name="contratista.contratista_materno" placeholder="Ingresar Apellido Materno" required>
			</div>
		</div>
		
	</div>
	<div class="row">
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">CURP</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-inbox"></span>
				</span> 
				<input type="text" class="form-control" name="contratista.contratista_curp" placeholder="Ingresar CURP" required>
			</div>
		</div>

		<div class="col-sm-4">
			<label class="control-label">N&uacutemero de Seguro Social</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-plus-circle"></i>
				</span> 
				<input type="number" class="form-control" name="contratista.contratista_nss" placeholder="Ingresar NSS" required>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label class="control-label">Fecha de Nacimiento</label>
			<div class="input-group date">
				<span class="input-group-addon">
					<i class="fa fa-calendar"></i>
				</span>
				<input type="text" name="contratista.contratista_nacimiento" class="form-control" placeholder="Ingresar Fecha de Nacimiento" >
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Genero</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-user"></span>
				</span>
					<select class="form-control" name="contratista.contratista_genero" id="genero">
						<option></option>
						<option value="Masculino">Masculino</option>
						<option value="Femenino">Femenino</option>
					</select>
			</div>
		</div>

		<div class="col-sm-4">
			<label class="control-label">Correo Electr&oacutenico</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-envelope"></span>
				</span> 
				<input type="number" class="form-control" name="contratista.contratista_correo" placeholder="Ingresar Correo" required>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label class="control-label">Edad</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<span class="glyphicon glyphicon-user"></span>
				</span> 
				<input type="number" class="form-control" name="contratista.contratista_edad" placeholder="Ingresar Edad" required>
			</div>
		</div>
	</div>
	<h3>Datos de la Compañia</h3>
	<div class="row">
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Empresa</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-vcard"></i>
				</span>
					<select class="form-control" name="contratista.contratista_fk_empresa" id="tipoEstancia" required>
						<option></option>
						<option value="Temporal">Temporal</option>
						<option value="Residente">Residente</option>
					</select>
			</div>
		</div>

		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Tipo de Estancia</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-vcard"></i>
				</span>
					<select class="form-control" name="contratista_tipoEstancia" id="tipoEstancia">
						<option></option>
						<option value="Temporal">Temporal</option>
						<option value="Residente">Residente</option>
					</select>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Contacto</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-users"></i>
				</span>
					<select class="form-control" name="contratista_contacto" id="contacto">
						<option></option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
					</select>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Departamento</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-users"></i>
				</span>
					<select class="form-control" name="contratista.contratista_departamento" id="departamento">
						<option></option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
					</select>
			</div>
		</div>

		<div class="col-sm-4">
			<label class="control-label">Contacto de Emergencia</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-users"></i>
				</span> 
				<input type="number" class="form-control" name="contratista.contratista_emergencia" placeholder="Ingresar Contacto" required>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label class="control-label">Tel&eacutefono</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-phone"></i>
				</span> 
				<input type="number" class="form-control" name="contratista.contratista_telefonoEmergencia" placeholder="Ingresar Teléfono" required>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-4">
			<label class="control-label">Tipo de Operaci&oacuten</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-pencil"></i>
				</span> 
				<input type="number" class="form-control" name="contratista.contratista_tipoOperacion" placeholder="Ingresar Operación" required>
			</div>
		</div>

		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Puesto</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-users"></i>
				</span>
					<select class="form-control" name="contratista.contratista_puesto" id="puesto" required>
						<option></option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
					</select>
			</div>
		</div>
		
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Tipo de Contratista</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-vcard"></i>
				</span>
					<select class="form-control" name="contratista.contratista_tipoContratista" id="tipoContratista" placeholder="Seleccionar" required>
						<option></option>
						<option value="A">A</option>
						<option value="B">B</option>
						<option value="C">C</option>
					</select>
			</div>
		</div>
	</div>
	
	<div class="row">
		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Exempleado</label>
			<div class="input-group">
				<span class="input-group-addon"> 
					<i class="fa fa-user-o"></i>
				</span>
					<select class="form-control" name="contratista.contratista_exempleado" id="exEmpleado" required>
						<option></option>
						<option value="Si">Si</option>
						<option value="No">No</option>
					</select>
			</div>
		</div>

		<div class="col-sm-4">
			<label class="control-label">Fin de Actividades</label>
			<div class="input-group date">
				<span class="input-group-addon"><i class="fa fa-calendar"></i>
				</span><input type="text" name="contratista.contratista_fechaFin" class="form-control" required>
			</div>
		</div>

		<div class="col-sm-4">
			<label style="text-align: right;" class="control-label">Status</label>
			<div class="input-group">
				<span class="input-group-addon"> 	
					<i class="fa fa-circle"></i>
				</span>
					<select class="form-control" name="contratista.contratista_status" id="status">
						<option></option>
						<option value="Alta">Alta</option>
						<option value="Baja">Baja</option>
					</select>
			</div>
		</div>
	</div>
	<hr>
	<button type="submit" class="btn btn-primary block m-b pull-right" id="btn-admon-usr" onclick="agregarContratista()" style="margin-left: 10px;" >Registrar</button>
	<button type="button" class="btn block m-b pull-right" id="btn-admon-usr" onclick="limpiarForm()" >Limpiar</button>
	
</form>
