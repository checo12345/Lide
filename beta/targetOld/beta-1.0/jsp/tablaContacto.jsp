<script src="js/contacto.js" charset="UTF-8"></script>
  
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="wrapper wrapper-content animated fade in row ">
<div class="row animated fadeInRight">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
            <h1 class="m-t-none m-b" style="text-align: center;">PRODUCTOS</h1>
            </div>
                <input type="text" class="form-control input-sm m-b-xs" id="filter"
                       placeholder="Buscar en la tabla">
                <table id="tablaContacto" class="footable table table-stripped toggle-arrow-tiny text-center" data-filter=#filter>
                    <thead>
                    <tr>
                        <th class="text-center" data-toggle="true" data-field="id">Codigo</th>
                        <th class="text-center" data-toggle="true">Nombre</th>
                        <th class="text-center" data-toggle="true">Precio</th>
                        <th class="text-center" data-toggle="true">Existencia</th>
                        <th class="text-center">Acciones</th>		
                    </tr>
                    </thead>
                    <tbody>	
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

<div id="mdl-contacto" class="modal small inmodal fade modaltext" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
                <h1 class="m-t-none m-b" id="tituloContactoForm">Editar Puesto</h1>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body" id="modalDinamico">       
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
