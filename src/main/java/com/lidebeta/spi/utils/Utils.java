package com.lidebeta.spi.utils;

import java.util.Map;

import com.google.api.server.spi.auth.common.User;
import com.lidebeta.spi.Constants;

public class Utils {

	public static User obtenerUsuario(Map<String, Object> session) {
		
		return (User) session.get(Constants.SYS_SESION_USUARIO) ;
	}
}
