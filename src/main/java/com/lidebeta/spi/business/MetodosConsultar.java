package com.lidebeta.spi.business;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.api.server.spi.auth.common.User;
import com.lidebeta.spi.AdminApi;
import com.lidebeta.spi.bean.Product;
import com.lidebeta.spi.dao.CSServicioRespuesta;
import com.lidebeta.spi.exception.LideException;

/*========================================================================

 * Sistema: LIDE
 * Modulo: com.lidebeta.spi.business
 * Sub modulo: MetodosConsultar.java
 * Fecha de modificacion: 27/03/2018
 * Descripcion: Clase encargada de realizar el negocio en cuanto a consultas a la base de datos
 * Autor: Sergio Rojas
=========================================================================
 */

public class MetodosConsultar {
	private static final Logger logger = Logger.getLogger(MetodosConsultar.class);
	private CSServicioRespuesta respuesta;
	
	
	/** Funcion: Metodo comprobar si es correcto usuario y contrasena en la base de datos
	 * @param usuario
	 * @return CSServicioRespuesta: Objeto con el producto obtenido
	 */
	public CSServicioRespuesta obtenerProductoPorCodigo(User usuario,Product producto) {
		logger.info("\n********** EJECUTAR: obtenerProductoPorCodigo():**********");
		CSServicioRespuesta respuesta = new CSServicioRespuesta();
		AdminApi adminApi = new AdminApi();
		try {
			respuesta = new CSServicioRespuesta(false, "No ejecutado", null);
			
			Product productoObtenido= adminApi.fetchProductByCb(usuario, producto) ;
			
			if(productoObtenido != null)
				respuesta = new CSServicioRespuesta(true, "Producto encontrado exitosamente", productoObtenido);
			else
				respuesta = new CSServicioRespuesta(true, "Producto no encontrado", null);

			logger.info(respuesta.getMensaje());

		} catch (Exception e) {
			logger.error(e.getMessage());
			respuesta = new CSServicioRespuesta(false, "Ocurrio una Excepcion", null);
		}
		logger.info("************************Saliendo obtenerProductoPorCodigo************************");
		return respuesta;
	}
	
	/** Funcion: Metodo actualizar el inventario
	 * @return ArrayList<Product>: Objeto con el producto obtenido
	 */
	public CSServicioRespuesta actualizarInventario(User usuario,Product producto) {
		logger.info("\n********** EJECUTAR: actualizarInventario():**********");
		CSServicioRespuesta respuesta = new CSServicioRespuesta();
		AdminApi adminApi = new AdminApi();
		try {
			respuesta = new CSServicioRespuesta(false, "No ejecutado", null);
			
			Product productoActualizado= adminApi.updateProduct(usuario, producto) ;
			
			if(productoActualizado != null)
				respuesta = new CSServicioRespuesta(true, "Producto actualizado exitosamente", productoActualizado);
			else
				respuesta = new CSServicioRespuesta(true, "Producto no actualizado", null);

			logger.info(respuesta.getMensaje());

		} catch (Exception e) {
			logger.error(e.getMessage());
			respuesta = new CSServicioRespuesta(false, "Ocurrio una Excepcion", null);
		}
		logger.info("************************Saliendo actualizarInventario************************");
		return respuesta;
	}
}
