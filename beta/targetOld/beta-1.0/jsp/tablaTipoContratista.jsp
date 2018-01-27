<script src="js/tipocontratista.js" charset="UTF-8"></script>
  
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="wrapper wrapper-content animated fade in row ">
<div class="row animated fadeInRight">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
            <h1 class="m-t-none m-b" style="text-align: center;">CATALOGO DE TIPOS DE CONTRATISTA</h1>
            </div>
                <input type="text" class="form-control input-sm m-b-xs" id="filter"
                       placeholder="Buscar en la tabla">
                <table id="tablaTipoContratista" class="footable table table-stripped toggle-arrow-tiny text-center" data-filter=#filter>
                    <thead>
                    <tr>
                        <th class="text-center" data-toggle="true" data-field="id">ID</th>
                        <th class="text-center" data-toggle="true">Tipo de Contratista</th>
                        <th class="text-center" data-toggle="true">Descripci&oacuten</th>
                        <th class="text-center">Acciones</th>		
                    </tr>
                    </thead>
                    <tbody>	
					<s:if test="listaTiposContratista != null && listaTiposContratista.size>0">
                    	<s:iterator value="listaTiposContratista">
	                        <tr id="contacto<s:property value="pk_tipoContratista" />">
	                        	<td><s:property value="pk_tipoContratista" /></td>
	                            <td><s:property value="tipoContratista_tipo" /></td>
								<td><s:property value="tipoContratista_descripcion" /></td>
	                            <td>			
	                                <a class="col-sm-3 btn btn-sm btn-primary btn-xs"  onclick="obtenerTipoContratista('<s:property value="pk_tipoContratista" />')">
	                                    <i class="fa fa-pencil fa-2x" data-toggle="tooltip" title="Editar"></i>
	                                </a>
	                                 	
	                                <a class="col-sm-3 btn btn-sm btn-primary btn-xs" onclick="modalEliminarTipoContratista('<s:property value="pk_tipoContratista" />')" style="margin-left:5px;">
	                                    <i class="fa fa-trash-o fa-2x" data-toggle="tooltip" title="Eliminar"></i>
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

<div id="mdl-tipo-contratista" class="modal small inmodal fade modaltext" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h1 class="m-t-none m-b" id="tituloTipoContratistaForm">Editar Tipo de Contratista</h1>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" id="modalDinamico">       
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
