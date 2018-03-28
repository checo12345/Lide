/*========================================================================
* Sistema: LIDE
* Modulo: com.mx.contratistas.acciones
* Sub modulo: CSAction.java
* Fecha de modificacin: 26/03/2018
* Descripcion: Clase que hereda de ActionSupport para implementar framework struts2
* Autor: Sergio Rojas
=========================================================================
*/

package com.lidebeta.spi.action;

import java.io.IOException;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.google.gson.Gson;

public class CSAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CSAction.class);
	protected Map<String, Object> session;
	private String actionError;

	

	/** Funcion: Metodo para convertir el resultado en un objeto json y devolverlo al cliente
	 * @param objToSend :Objeto con el resultado, mensaje y si fue Exitoso
	 */
	protected void enviarObjetoJSONRespuesta(Object objToSend) {
		Gson gson = new Gson();
		String jsonResult = gson.toJson(objToSend);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json");
		try {
			response.getWriter().write(jsonResult);
		} catch (IOException e) {
			LOG.info("Imposible generar la respuesta json: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public CSAction() {
		super();
	}

	
}
