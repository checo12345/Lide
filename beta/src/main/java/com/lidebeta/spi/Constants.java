package com.lidebeta.spi;

import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming your API.
 */
public class Constants {
  public static final String WEB_CLIENT_ID = "347485897175-9dc1rkes6auj36o6f6utjv3e0k5ha5cj.apps.googleusercontent.com";
//  public static final String ANDROID_CLIENT_ID = "347485897175-q7gccknelit5m4p814bm6fha5u5bi9kp.apps.googleusercontent.com";
  
  
//  public static final String ANDROID_CUSTOMER_CLIENT_ID = "347485897175-sogc1j5q7dbjgapkjbd30rj09rhqjl0p.apps.googleusercontent.com";
  public static final String ANDROID_CUSTOMER_CLIENT_ID = "347485897175-sogc1j5q7dbjgapkjbd30rj09rhqjl0p.apps.googleusercontent.com";
  
//  public static final String ANDROID_CLIENT_ID = "347485897175-p8fdbb289tcpua7mfjc8nhvofas3rohk.apps.googleusercontent.com";
  public static final String ANDROID_CLIENT_ID = "347485897175-7h91tno2c79qlrcf0lgbhk2eq2p6375j.apps.googleusercontent.com";
  
//public static final String ANDROID_MANAGER_CLIENT_ID = "347485897175-p8fdbb289tcpua7mfjc8nhvofas3rohk.apps.googleusercontent.com";
  public static final String ANDROID_MANAGER_CLIENT_ID = "347485897175-kf18ekkqs2d1t30r25u4sdqogts17gv9.apps.googleusercontent.com";
  
  
  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
  public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;
  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
  
  public static final String FCM_SERVER_KEY = "AAAAfTmSzT8:APA91bFT-LRHghEnny73vAPaACveSmbIurTQ2mnEjCGjiA_t7zU42NHirS7jcn5mmwq1Fdj5u50vvbTmrGIxCYpOyu3xQU60k9oz4myqD-NnGLLQSxpfbFq0AspSfYsLv3feJhWhC_CN";
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  public static final String LOCAL_DATABASE_URL 		= System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")
		  												  ?System.getProperty("ae-cloudsql.cloudsql-database-url")
		  											      :System.getProperty("ae-cloudsql.local-database-url");
		  												  
  public static final String LOCAL_DATABASE_USER 		= System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")
			  											  ?System.getProperty("ae-cloudsql.cloudsql-database-user")
			  											  :System.getProperty("ae-cloudsql.local-database-user");
			  											  
  public static final String LOCAL_DATABASE_PASSWORD 	= System.getProperty("com.google.appengine.runtime.version").startsWith("Google App Engine/")
			  											  ?System.getProperty("ae-cloudsql.cloudsql-database-password")
			  										      :System.getProperty("ae-cloudsql.local-database-password");
}
