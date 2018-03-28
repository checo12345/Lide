package com.lidebeta.spi.exception;

/*========================================================================
* Sistema: LIDE
* Modulo: com.lidebeta.spi.exception.exception
* Sub modulo: LideException.java
* Fecha de modificacion: 26/03/2018
* Descripcion: Clase para tratar excepciones generales del sistema
* Autor: Sergio Rojas
=========================================================================
*/
public class LideException extends Exception{
	private static final long serialVersionUID = 1L;
	/**
	 *Default Constructor 
	 */
	public LideException() {
	}

	/**
	 * @param error Texto de la nueva excepcion
	 */
	public LideException(String error) {
		super(error);
	}
}

