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
import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.google.api.server.spi.auth.common.User;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.appengine.repackaged.com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.appengine.repackaged.com.google.api.client.http.javanet.NetHttpTransport;
import com.google.appengine.repackaged.com.google.api.client.json.jackson2.JacksonFactory;
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
		System.out.println("\n========== ACTION: iniciarSesion()================");
		try {
			
			String authCode=getCodigo();
			
			GoogleTokenResponse tokenResponse =
			          new GoogleAuthorizationCodeTokenRequest(
			              new NetHttpTransport(),
			              JacksonFactory.getDefaultInstance(),
			              "https://www.googleapis.com/oauth2/v4/token",
			              "299646937934-044postf14bnq484tthl88cnfo9f9379.apps.googleusercontent.com",
			              "L1iahWYUdR6GT6qHMVqbegfs",
			              authCode,
			              "").execute();

			String accessToken = tokenResponse.getAccessToken();


			// Get profile info from ID token
			GoogleIdToken idToken = tokenResponse.parseIdToken();
			GoogleIdToken.Payload payload = idToken.getPayload();
			String userId = payload.getSubject();  // Use this value as a key to identify a user.
			String email = payload.getEmail();
			boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
			String name = (String) payload.get("name");
			String pictureUrl = (String) payload.get("picture");
			String locale = (String) payload.get("locale");
			String familyName = (String) payload.get("family_name");
			String givenName = (String) payload.get("given_name");
			
			logger.info("ESTE ES EL CODIGO: "+authCode) ;
		} catch (Exception e) {
			respuestaCadena = "errorGeneral";
		}
		return respuestaCadena;
	}

	
	public String obtenerProductoPorCodigo() {
		logger.info("\n========== ACTION: obtenerProductoPorCodigo()================");
		try {
			consultar= new MetodosConsultar() ;
			respuesta= consultar.obtenerProductoPorCodigo(new User("roldan.a.z.p@gmail.com"), getProducto()) ;
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
					respuesta=consultar.actualizarInventario(new User("roldan.a.z.p@gmail.com"),producto);
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
