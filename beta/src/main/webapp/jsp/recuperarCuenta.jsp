<div id="mdl-recuperar-pwd" class="modal fade" tabindex="-1" role="dialog">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content">
	          <div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            <h4>Recuperar contrase&ntildea</h4>
	          </div>
	          <div class="modal-body">
	            <div class="form-group">
	                <div class="col-sm-10">
	                	<form id="recuperarCuentaForm" role="form" action="recuperarCuenta" onsubmit="return false;">
	                    	<input name="usuario.usuario_usuario" id="email" type="text" class="form-control" maxlength="35" placeholder="Ingrese su nombre de usuario"/>
	                	</form>
	                </div>
	            </div>
	          </div><br>
	          <div class="modal-footer">
	          	<div class="enviar">
	            	<button class="btn btn-primary btn-lg btn-block" onclick="recuperarCuenta();">Enviar</button>
	        	</div>
	          </div>
	        </div>
	    </div>
	</div>