<%@ taglib prefix="s" uri="/struts-tags"%>

<tr>
	<td class="precio"><s:property value="producto.codigoBarras" /></td>
	<td><s:property value="producto.description" /></td>
	<td class="precio"><s:property value="producto.price" /></td>
	<td><s:property value="producto.quantity" /></td>
	<td><a onclick="modalEliminarContacto('<s:property value="pk_contacto" />')"> 
			<i class="fa fa-times fa-2x" style="color:red;" data-toggle="tooltip" title="Eliminar"></i>
		</a>
	</td>
</tr>