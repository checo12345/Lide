package com.lidebeta.spi.config;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

public class iniciarBitacora extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	/** Funcion: Servlet para iniciar configuracion del registro de bitacora de cada accion del sistema
	 * @param config :ServletConfig con la configuracion del sistema
	 */
	public void init(ServletConfig config) {

        try{

        	System.out.println("**********************************************************************");
			System.out.println("					SISTEMA LIDE - Iniciando iniciarBitacora");
			System.out.println("**********************************************************************");

            System.out.println("iniciarBitacora esta inicializando");
            String log4jLocation = config.getInitParameter("log4j-init-file");

            ServletContext sc = config.getServletContext();

            if (log4jLocation == null) {
                System.err.println("*** No se encuentra log4j-properties-location, entonces no puede inicializar log4j con BasicConfigurator");
                BasicConfigurator.configure();
            } else {
                String webAppPath = sc.getRealPath("/");
                String log4jProp = webAppPath + "/WEB-INF/"+log4jLocation;
                File archivo = new File(log4jProp);
                if (archivo.exists()) {
                    System.out.println("Inicializando log4j con: " + log4jProp);
                    PropertyConfigurator.configure(log4jProp);
                } else {
                    System.err.println("*** " + log4jProp + " no se enceuntra el archivo, entonces no puede incializar log4j con BasicConfigurator");
                    BasicConfigurator.configure();
                }
            }
            super.init(config);
			
            System.out.println("**********************************************************************");
            System.out.println("			Finaliza la carga de iniciarBitacora(SISTEMA DE LIDE)");
            System.out.println("**********************************************************************");

        }catch (Exception R) {
            R.printStackTrace();
            System.out.println("Ocurrio un error al cargar los parametros iniciales" + R.getMessage());
        }


    }
}
