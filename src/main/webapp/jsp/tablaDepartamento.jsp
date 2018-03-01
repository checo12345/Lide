<script src="js/departamento.js"></script>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="wrapper wrapper-content animated fade in row ">
<div class="row animated fadeInRight">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
            <h1 class="m-t-none m-b" style="text-align: center;">CATALOGO DE DEPARTAMENTOS</h1>
            </div>
                <input type="text" class="form-control input-sm m-b-xs" id="filter"
                       placeholder="Buscar en la tabla">
                <table id="tablaDepartamentos" class="footable table table-stripped toggle-arrow-tiny text-center" data-filter=#filter>
                    <thead>
                    <tr>
                        <th class="text-center" data-toggle="true" data-field="id">ID Departamento</th>
                        <th class="text-center" data-toggle="true">Nombre</th>
                        <th class="text-center" data-toggle="true">Descripci&oacuten</th>
                        <th class="text-center">Acciones</th>		
                    </tr>
                    </thead>
                    <tbody>	
					<s:if test="listaDepartamentos != null && listaDepartamentos.size>0">
                    	<s:iterator value="listaDepartamentos">
	                        <tr id="departamento<s:property value="pk_departamento" />">
	                            <td><s:property value="pk_departamento" /></td>
								<td><s:property value="departamento_nombre" /></td>
	                            <td><s:property value="departamento_descripcion" /></td>
	                            <td>			
	                                <a class="col-sm-3 btn btn-sm btn-primary btn-xs"  onclick="editarDepartamento('<s:property value="pk_departamento" />')">
	                                    <i class="fa fa-pencil fa-2x" data-toggle="tooltip" title="Editar"></i>
	                                </a>
	                                 	
	                                <a class="col-sm-3 btn btn-sm btn-primary btn-xs" onclick="eliminarDepartamento('<s:property value="pk_departamento" />')" style="margin-left:5px;">
	                                    <i class="fa fa-trash-o fa-2x" data-toggle="tooltip" title="Editar"></i>
	                                </a>
	                            </td>
	                        </tr>
                    	</s:iterator>
					</s:if>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="8">
                            <ul class="pagination pull-right"></ul>
                        </td>
                    </tr>
                    </tfoot>
                </table>
        </div>
    </div>
</div>
</div>

<div id="mdl-departamento" class="modal small inmodal fade modaltext" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h1 class="m-t-none m-b" id="tituloDepartamentoForm">Editar Departamento</h1>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" id="modalDinamico">       
            </div>
            <div class="modal-footer">
 
            </div>
        </div>
    </div>
</div>
