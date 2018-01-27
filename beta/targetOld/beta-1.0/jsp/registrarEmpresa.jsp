<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="mdl-registrarEmpresa" class="modal small inmodal fade modaltext" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h1 class="m-t-none m-b">Nuevo Registro</h1>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <form id="registrarEmpresaForm" class="form-horizontal" action="registrarCuenta" role="form" onsubmit="return false;">
                        <h3> Datos Empresariales</h3>
                        <div class="row">
                            <div class="col-sm-6">
                                <label style="text-align:right;" class="control-label">Nombre de la Empresa</label>  
                                <div class="input-group">                           
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-tags"></span>
                                    </span>                     
									 <input type="text" name="empresa.empresa_nombre"  class="form-control" placeholder="Ingresar Nombre" required />								
                                </div>                     
                            </div>
                            <div class="col-sm-6">
                                <label style="text-align:right;" class="control-label">Correo Electr&oacutenico</label>        
                                <div class="input-group">
	                             <span class="input-group-addon">
	                                <span class="glyphicon glyphicon-envelope"></span>
	                             </span>
	                             <input type="email" name="empresa.empresa_correo" class="form-control"  placeholder="Ingresar correo electrónico"/>
	                           </div>
                                             
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                <label class="control-label">Tel&eacutefono</label>
                                <div class="input-group">   
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-earphone"></span>
                                    </span>                                                                         
                                    <input type="number" name="empresa.empresa_telefono" class="form-control" maxlength="10" placeholder="Ingresar teléfono"  />
                                </div>                     
                            </div>
                            <div class="col-sm-6">
                                <label class="control-label">Extensi&oacuten</label>
                                <div class="input-group">                           
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-option-horizontal"></span>
                                    </span>                        
                                    <input type="number" name="empresa.empresa_telefonoExt" class="form-control" maxlength="5" placeholder="Ingresar extensión"  />
                                </div>                     
                            </div>
                        </div>
                        <div>
                          <label class="control-label">Raz&oacuten Social</label>
                          <div class="input-group">                           
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-tags"></span>
                                    </span>                     
									 <input type="text" name="empresa.empresa_descripcion" class="form-control" placeholder="Ingresar razón social completa" />		
                                </div>
                        </div> 
                        <hr>
                        <h3> Datos del Usuario Administrador</h3>
                        
                        <div class="row">
                            <div class="col-sm-6">
                                    <label style="text-align:right;" class="control-label">Nombre(s)</label>  
                                    <div class="input-group">                           
				                      <span class="input-group-addon">
				                     	<span class="glyphicon glyphicon-user"></span>
				                      </span>                     
				                      <input type="text" class="form-control" name="usuario.usuario_nombre"  placeholder="Ingresar Nombre" required>
				                   </div> 
                            </div>

                            <div class="col-sm-6">
                                    <label class="control-label">Apellido Paterno</label>
                                    <div class="input-group">                           
				                      <span class="input-group-addon">
				                     	<span class="glyphicon glyphicon-user"></span>
				                      </span>                     
				                      <input type="text" class="form-control" name="usuario.usuario_apellidoPaterno" placeholder="Ingresar Apellido Paterno" required>
				                   </div>
                            </div>
                          
                        </div>
                        <div class="row">
                            <div class="col-sm-6">
                                    <label style="text-align:right;" class="control-label">Apellido materno</label>  
                                    <div class="input-group">                           
				                      <span class="input-group-addon">
				                     	<span class="glyphicon glyphicon-user"></span>
				                      </span>                     
				                      <input type="text" class="form-control" name="usuario.usuario_apellidoMaterno" placeholder="Ingresar Apellido Materno" required>
				                   </div> 
                            </div>

                            <div class="col-sm-6">
                                    <label class="control-label">Tel&eacutefono</label>
                                    <div class="input-group">                           
				                      <span class="input-group-addon">
				                     	<span class="glyphicon glyphicon-earphone"></span>
				                      </span>                     
				                      <input type="number" class="form-control" name="usuario.usuario_telefono" placeholder="Ingresar Teléfono" required>
				                   </div>
                            </div>
                          
                        </div>
                        <div>
                        <label class="control-label">Correo Electr&oacutenico</label>
                          <div class="input-group">
                             <span class="input-group-addon">
                                <span class="glyphicon glyphicon-envelope"></span>
                             </span>
                             <input type="email" class="form-control" name="usuario.usuario_correo" placeholder="Ingresar correo electrónico" />
                          </div>
                       </div> 
                        <div class="row">
                            <div class="col-sm-6">
                                    <label style="text-align:right;" class="control-label">Usuario</label>  
                                    <div class="input-group">                           
				                      <span class="input-group-addon">
				                     	<span class="glyphicon glyphicon-user"></span>
				                      </span>                     
				                      <input type="text" class="form-control" name="usuario.usuario_usuario" placeholder="Usuario" required>
				                   </div> 
                            </div>

                            <div class="col-sm-6">
                                    <label class="control-label">Contrase&ntildea</label>
                                    <div class="input-group">                           
				                      <span class="input-group-addon">
				                     	<span class="glyphicon glyphicon-option-horizontal"></span>
				                      </span>                     
				                      <input type="password" class="form-control" name="usuario.usuario_contrasena" placeholder="Password" required>
				                   </div>
                            </div>
                          
                        </div> 
                        <div style="text-align: center; margin-top: 20px;">
                        	<img src="imagen/usuario.png" id="fotoTomada" width="115px;">
							<div><button type="button" class="btn" id="inicarCamara" id="btn-admon-usr" onclick="iniciarCamara()" >Tomar Foto</button></div>
							<input type="hidden" id="fotoBase64">
                        </div>
                        
                    </form>        
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary block full-width m-b pull-right" id="btn-admon-usr" onclick="registrarEmpresa()" >Registrar</button>
                <canvas id="canvas" width="120" height="130" style="display: none;"></canvas>
            </div>
        </div>
    </div>
</div>


<s:include value="/jsp/ventanaModal.jsp"></s:include>