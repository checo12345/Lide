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
import com.lidebeta.spi.Constants;
import com.lidebeta.spi.action.CSAction;
import com.lidebeta.spi.bean.Keyword;
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
	private String codigo;
	private Keyword keyword ;
	
	
	public String validarSesion() {
		System.out.println("\n========== ACTION: validarSesion()================");
		try {
			consultar= new MetodosConsultar() ;
			respuesta= consultar.validarSesion(getCodigo()) ;
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			
			session.put(Constants.SYS_SESION_USUARIO,(User)respuesta.getResultado());

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
			respuesta= consultar.obtenerUsuario(session) ;
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			user=(User)respuesta.getResultado();
			respuesta= consultar.obtenerProductoPorCodigo(user, getProducto()) ;
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
	
	@SuppressWarnings("unchecked")
	public String obtenerProductoPorNombre() {
		logger.info("\n========== ACTION: obtenerProductoPorNombre()================"+getKeyword().getName());
		try {
			
			consultar= new MetodosConsultar() ;
			respuesta= consultar.obtenerUsuario(session) ;
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			user=(User)respuesta.getResultado();
			respuesta= consultar.obtenerProductoPorNombre(user, getKeyword()) ;
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			
			setProductos((ArrayList<Product>)respuesta.getResultado());
		}catch (LideException e) {
			respuestaCadena = "errorLide";
			logger.error(respuesta.getMensaje());
			
		}catch (Exception e) {
			respuestaCadena = "errorGeneral";
			logger.error(respuesta.getMensaje());
		}
		return respuestaCadena;
	}
	public String actualizarProducto() {
		logger.info("\n========== ACTION: actualizarProducto()================");
		try {
			consultar= new MetodosConsultar() ;
			respuesta= consultar.obtenerUsuario(session) ;
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			user=(User)respuesta.getResultado();
			
			respuesta=consultar.actualizarInventario(user,getProducto());

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

	
	
	public String agregarVenta() {
		logger.info("\n========== ACTION: agregarVenta()================");
		try {
			consultar= new MetodosConsultar() ;
			/*respuesta= consultar.obtenerUsuario(session) ;
			if(!respuesta.isExito() || respuesta.getResultado()== null)
				throw new LideException();
			user=(User)respuesta.getResultado();*/
			User user = new User("checo.que@gmail.com") ;
			if(getProductos() != null)
				for (Product producto : getProductos())
					respuesta=consultar.actualizarInventario(user,producto);

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


	public Keyword getKeyword() {
		return keyword;
	}


	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}
	
	
}
