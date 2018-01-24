package com.lidebeta.spi;

import java.util.List;

import com.lidebeta.spi.bean.CoverageArea;
import com.opensymphony.xwork2.ActionSupport;

public class CSAction extends ActionSupport{
	private String respuestaCadena = "exitoso";
	private List<CoverageArea> areas ;
	
	public String iniciarSesion() {
		System.out.println("\n========== ACTION: iniciarSesion()================");
		try {
			AdminApi ca = new AdminApi() ;
			}
		catch (Exception e) {
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
	
	
}
