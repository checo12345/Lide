<%@ taglib prefix="s" uri="/struts-tags"%>

<tr id="<s:property value="producto.codigoBarras" />">
	<td ><s:property value="producto.codigoBarras" /></td>
	<td><s:property value="producto.description" /></td>
	<td><s:property value="producto.quantity" /></td>
	<td ><s:property value="producto.price" /></td>
	<td><input type="number" class='entrada' min='1' value='1'> </td>
	<td class="precio"><s:property value="producto.price" /></td>
	<td><a onclick="modalEliminarProducto('<s:property value="producto.codigoBarras" />')"> 
			<i class="fa fa-times fa-2x" style="color:red;" data-toggle="tooltip" title="Eliminar"></i>
		</a>
	</td>
</tr>