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
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.google.api.server.spi.auth.common.User;
import com.google.gson.Gson;
import com.lidebeta.spi.Constants;

public class CSAction extends ActionSupport implements SessionAware{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(CSAction.class);
	protected Map<String, Object> session;
	private String actionError;
	protected User user;
	

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
	
	// Funcion: Metodo fijar la sesion del usuario
	// Parametro: Mapa clave que es un String declarado en constantes y un Objeto que es lo que se sube a la sesion
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
		user = (User) session.get(Constants.SYS_SESION_USUARIO);

	}

	public CSAction() {
		super();
	}

	
}
