package com.lidebeta.spi;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.lidebeta.spi.bean.CoverageArea;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.dao.AdminDAOImpl;
import com.opensymphony.xwork2.ActionSupport;
import com.google.api.server.spi.auth.common.User;


public class CSAction extends ActionSupport {
	private static final Logger log = Logger.getLogger(CSAction.class.getName());
	private String respuestaCadena = "exitoso";
	private List<CoverageArea> areas;
	private Product producto;

	public String iniciarSesion() {
		System.out.println("\n========== ACTION: iniciarSesion()================");
		try {
			AdminApi ca = new AdminApi();
		} catch (Exception e) {
			respuestaCadena = "errorGeneral";
		}
		return respuestaCadena;
	}

	public String agregarProducto() {
		System.out.println("\n========== ACTION: agregarProducto()================");
		try {
			
			System.out.println(
					"codigo: " + getProducto().getCodigoBarras() + " area: " + getProducto().getCoverageAreaId());
			AdminApi ca = new AdminApi();
			System.out.println(
					"codigo: " + getProducto().getCodigoBarras() + " area: " + getProducto().getCoverageAreaId());
			User user = new User("roldan.a.z.p@gmail.com") ;
			setProducto(ca.fetchProductByCb(user,getProducto()));
		} catch (Exception e) {
			System.out.println("mamo: " + e.getMessage());
			respuestaCadena = "errorGeneral";
		}
		return respuestaCadena;
	}

	private String[] test;
	private ArrayList<Product> productos = new ArrayList<Product>() ;
	public String realizarVenta() {
		System.out.println("\n========== ACTION: realizarVenta()================");
		try {
				if(getProductos() != null)
				{
					AdminDAOImpl ca = new AdminDAOImpl();
					for (Product producto:getProductos()) {
						ca.actualizaInventario(producto);
					}
				}
		} catch (Exception e) {
			log.info(e.getMessage());
			log.info(e.getStackTrace().toString());
			System.out.println("mamo: " + e.getMessage());
			respuestaCadena = "errorGeneral";
		}
		return respuestaCadena;
	}

	public CSAction() {
		super();
	}

	public String execute() {
		return null;
	}

	public List<CoverageArea> getAreas() {
		return areas;
	}

	public void setAreas(List<CoverageArea> areas) {
		this.areas = areas;
	}

	public Product getProducto() {
		return producto;
	}

	public void setProducto(Product producto) {
		this.producto = producto;
	}

	public String[] getTest() {
		return test;
	}

	public void setTest(String[] test) {
		this.test = test;
	}

	public ArrayList<Product> getProductos() {
		return productos;
	}

	public void setProductos(ArrayList<Product> productos) {
		this.productos = productos;
	}
	
}
