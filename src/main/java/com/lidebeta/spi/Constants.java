package com.lidebeta.spi;

import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming your API.
 */
public class Constants {
  public static final String WEB_CLIENT_ID = "299646937934-044postf14bnq484tthl88cnfo9f9379.apps.googleusercontent.com";
//  public static final String ANDROID_CLIENT_ID = "347485897175-q7gccknelit5m4p814bm6fha5u5bi9kp.apps.googleusercontent.com";
  
  
//  public static final String ANDROID_CUSTOMER_CLIENT_ID = "347485897175-sogc1j5q7dbjgapkjbd30rj09rhqjl0p.apps.googleusercontent.com";
  public static final String ANDROID_CUSTOMER_CLIENT_ID = "299646937934-p45gchbmkd6ck1fne34vu4tjk99560i1.apps.googleusercontent.com";
  
//  public static final String ANDROID_CLIENT_ID = "347485897175-p8fdbb289tcpua7mfjc8nhvofas3rohk.apps.googleusercontent.com";
  public static final String ANDROID_CLIENT_ID = "299646937934-p45gchbmkd6ck1fne34vu4tjk99560i1.apps.googleusercontent.com";
  
//public static final String ANDROID_MANAGER_CLIENT_ID = "347485897175-p8fdbb289tcpua7mfjc8nhvofas3rohk.apps.googleusercontent.com";
  public static final String ANDROID_MANAGER_CLIENT_ID = "299646937934-cut3mi8kqla2prfomrmdbmpkhc1nf7gd.apps.googleusercontent.com";
  
  
  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
  public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;
  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
  
  public static final String FCM_SERVER_KEY = "AAAARcRZa04:APA91bFa5GGa0ss0bc03DfipeQT2Am5Juwfxe3b5KGdFh8ec5hJfn9AT9J0NlzCI1FNqbgAMnabGm-8xbzKu5bJoO6NzsGyYUIixuByUu2phaVXLYWCzdqrvNjVs1_DPdunv5luwnhZw";
  
  public static final String SYS_SESION_USUARIO="SSU";
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
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
