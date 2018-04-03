/**========================================================================
* Sistema: LIDE
* Modulo: com.lidebeta.spi.action
* Sub modulo: PuntoVentaAction.java
* Fecha de modificación: 26/03/2018
* Descripción: Clase encargada de los metodos del punto de venta
* Autor: Sergio Rojas
=========================================================================
*/

package com.lidebeta.spi.action;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.server.spi.auth.common.User;
import com.google.appengine.repackaged.com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.appengine.repackaged.com.google.api.client.http.javanet.NetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.json.jackson.JacksonFactory;
import com.lidebeta.spi.Constants;
import com.lidebeta.spi.action.CSAction;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.business.MetodosConsultar;
import com.lidebeta.spi.dao.CSServicioRespuesta;
import com.lidebeta.spi.exception.LideException;

public class PuntoVentaAction extends CSAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(PuntoVentaAction.class);
	private String respuestaCadena = "exitoso";
	private Product producto;
	private CSServicioRespuesta respuesta;
	private MetodosConsultar consultar;
	private ArrayList<Product> productos = new ArrayList<Product>();
	String codigo;
	
	
	
	public String validarSesion() {
		System.out.println("\n========== ACTION: validarSesion()================");
		try {
			consultar= new MetodosConsultar() ;
			respuesta= consultar.validarSesion(getCodigo()) ;
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			
			session.put(Constants.SYS_SESION_USUARIO,(User)respuesta.getResultado());
			System.out.println("\n========== Se subio el user a sesion================");
		}catch (LideException e) {
			respuestaCadena = "errorLide";
			logger.error(respuesta.getMensaje());
		}catch (Exception e) {
			respuestaCadena = "errorGeneral";
			logger.info(e.getMessage()) ;
		}
		enviarObjetoJSONRespuesta(respuesta);
		return null;
	}
	 
	 
	public String obtenerProductoPorCodigo() {
		logger.info("\n========== ACTION: obtenerProductoPorCodigo()================");
		try {
			consultar= new MetodosConsultar() ;
			respuesta= consultar.obtenerProductoPorCodigo((User) session.get(Constants.SYS_SESION_USUARIO), getProducto()) ;
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			
			setProducto((Product)respuesta.getResultado());
		}catch (LideException e) {
			respuestaCadena = "errorLide";
			logger.error(respuesta.getMensaje());
			
		}catch (Exception e) {
			respuestaCadena = "errorGeneral";
			logger.error(respuesta.getMensaje());
		}
		return respuestaCadena;
	}

	public String agregarVenta() {
		logger.info("\n========== ACTION: agregarVenta()================");
		try {
			consultar= new MetodosConsultar() ;
			if(getProductos() != null) {
				for (Product producto : getProductos())
					respuesta=consultar.actualizarInventario((User) session.get(Constants.SYS_SESION_USUARIO),producto);
			}
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			
		}catch (LideException e) {
			respuestaCadena = "errorLide";
			logger.error(respuesta.getMensaje());
			
		}catch (Exception e) {
			respuestaCadena = "errorGeneral";
			logger.error(respuesta.getMensaje());
		}
		enviarObjetoJSONRespuesta(respuesta);
		return null;
	}

	


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String execute() {
		return null;
	}

	public Product getProducto() {
		return producto;
	}

	public void setProducto(Product producto) {
		this.producto = producto;
	}


	public ArrayList<Product> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Product> productos) {
		this.productos = productos;
	}

	public CSServicioRespuesta getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(CSServicioRespuesta respuesta) {
		this.respuesta = respuesta;
	}
	
}
