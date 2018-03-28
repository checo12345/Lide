package com.lidebeta.spi.dao;

/*========================================================================
* Sistema: LIDE
* Modulo: com.lidebeta.spi.dao
* Sub modulo: CSServicioRespuesta.java
* Fecha de modificaco�n: 11/08/2017
* Descripcion: Clase para encapsular la respuesta del servidor hacia el cliente
* Autor: Sergio Rojas
=========================================================================
*/
public class CSServicioRespuesta {

	private boolean exito;
	private String mensaje;
	private Object resultado;		
	
	public CSServicioRespuesta() {
		super();
	}

	public CSServicioRespuesta(boolean success, String mensaje, Object resultado) {
		super();
		this.exito = success;
		this.mensaje = mensaje;
		this.resultado = resultado;
	}

	/**
	 * @return el exito
	 */
	public boolean isExito() {
		return exito;
	}

	/**
	 * @param exito el exito a establecer
	 */
	public void setExito(boolean exito) {
		this.exito = exito;
	}

	/**
	 * @return el mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje el mensaje a establecer
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return el resultado
	 */
	public Object getResultado() {
		return resultado;
	}

	/**
	 * @param resultado el resultado a establecer
	 */
	public void setResultado(Object resultado) {
		this.resultado = resultado;
	}
	
	

}
