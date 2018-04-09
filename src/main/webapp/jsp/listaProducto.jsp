<script src="js/listaProducto.js" charset="UTF-8"></script>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="wrapper wrapper-content animated fade in row ">
<div class="row animated fadeInRight">
    <div class="col-lg-12">
        <div class="ibox float-e-margins">
            </div>
                <table id="tablaProductosEncontrados" class="footable table table-stripped toggle-arrow-tiny text-center" data-filter=#filter>
                    <thead>
                    <tr>
                        <th class="text-center" data-toggle="true">Codigo</th>
                        <th class="text-center" data-toggle="true">Nombre</th>
                        <th class="text-center" data-toggle="true">Existencia</th>
                        <th class="text-center" data-toggle="true">Precio</th>
                        <!--  <th class="text-center" data-toggle="true">Imagen</th>-->
                        <th class="text-center">Seleccionar</th>		
                    </tr>
                    </thead>
                    <tbody>	
                    <s:if test="productos != null && productos.size>0"> 
                    	<s:iterator value="productos">
                    		<tr id="<s:property value="codigoBarras" />">
							<td ><s:property value="codigoBarras" /></td>
							<td><s:property value="name" /></td>
							<td><s:property value="quantity" /></td>
							<td ><s:property value="price" /></td>
							<!--  <td ><img width="50px" height="50px" src='https://endpoint-testdrive-192618.appspot.com/img/<s:property value="image" />'></td>-->
							<td ><div class="i-checks"><label> <input type="checkbox" value=""> <i></i></label></div></td>
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


