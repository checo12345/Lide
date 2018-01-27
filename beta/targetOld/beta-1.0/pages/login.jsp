<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head></head>
<body>
	<h1>Struts 2 Hello World Example</h1>

	<table>
		<thead>
			<tr>
				<th class="text-center" data-toggle="true" data-field="id">ID Puesto</th>
				<th class="text-center" data-toggle="true">Nombre</th>
				<th class="text-center" data-toggle="true">Descripci&oacuten</th>
				<th class="text-center">Acciones</th>
			</tr>
		</thead>
		<tbody>

			<s:if test="areas != null && areas.size>0">
				<s:iterator value="areas">
					<tr>
						<td><s:property value="name" /></td>
						<td><s:property value="description" /></td>
						<td><s:property value="center" /></td>
					</tr>
				</s:iterator>
			</s:if>
		</tbody>
	</table>
</body>
</html>