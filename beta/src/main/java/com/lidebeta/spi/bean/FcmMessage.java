package com.lidebeta.spi.bean;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import com.lidebeta.spi.Constants;

public class FcmMessage {

	////////////////MAIN KEYS
	private static final String NOTIFICATION = "notification";
	private static final String TO = "to";
	private static final String DATA = "data";
	////////////////MAIN KEYS
	
	////////////////NOTIFICATION KEYS
	private static final String BODY = "body";
	private static final String TITLE = "title";
	private static final String ICON = "icon";
	////////////////NOTIFICATION KEYS
	
	////////////////DATA ENTRY KEYS
	public static final String DATA_MESSAGE_KEY 		=  "lidebeta.message";
	public static final String DATA_ORDER_ID_KEY 		=  "lidebeta.orderId";
	public static final String DATA_NEW_ORDER	 		=  "lidebeta.newOrder";
	public static final String DATA_ORDER_CLOSED	 	=  "lidebeta.orderClosed";
	public static final String DATA_ORDER_UPDATED	 	=  "lidebeta.orderUpdated";
	public static final String DATA_ORDER_STATUS		=  "lidebeta.orderClosedStatus";
	////////////////DATA ENTRY KEYS
	
	////////////////CONSTANTS
	public static final String TITLE_MESSENGER_NOTIFICATION =  "Nuevo Mensaje";
	public static final String TITLE_NEW_ORDER 				=  "Nueva Orden";
	public static final String TITLE_SUCCESS_ORDER 			=  "Gracias Por Su Compra";
	public static final String TITLE_FAIL_ORDER				=  "La Orden Fue Cerrada";
	public static final String TITLE_ICON_NOTIFICATION 		=  "back";
	public static final String TITLE_UPDATE_ORDER 			=  "Productos actualizados";
	////////////////CONSTANTS
	
	private String body;
	private String title;
	private String icon;
	
	private String to;
	
	private String data;
	
	private Map<String, String> dataList;
	
	public byte[] getBytes(){
		JSONObject message 		= new JSONObject();
		JSONObject data 		= new JSONObject();
		JSONObject notification = new JSONObject();
		
		if(to!=null){
			message.put(TO, to);
		}
		
		if(body!=null && title !=null && icon !=null){
			notification.put(TITLE, title);
			notification.put(BODY, body);
			notification.put(ICON, icon);
			message.put(NOTIFICATION, notification);
		}
		
		if(dataList!=null && !dataList.isEmpty()){
			Set<String> keys = dataList.keySet();
			for(String key: keys){
				data.put(key, dataList.get(key));
			}
			message.put(DATA, data);
		}
		return message.toString().getBytes();	
		
	}
	
	public BooleanWrapper send(){
		URL url;
		try {
			url = new URL("https://android.googleapis.com/gcm/send");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			 conn.setRequestProperty("Authorization", "key="+Constants.FCM_SERVER_KEY);
			 conn.setRequestProperty("Content-Type", "application/json");
			 conn.setRequestMethod("POST");
			 conn.setDoOutput(true);
			
			 // Send GCM message content.
			 OutputStream outputStream = conn.getOutputStream();
			 outputStream.write(getBytes());
			
			 // Read GCM response.
			 InputStream inputStream = conn.getInputStream();
			 String resp = IOUtils.toString(inputStream);

			 return new BooleanWrapper(true, resp);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return new BooleanWrapper(true, e.getMessage());
		} catch (IOException e){
			e.printStackTrace();
			return new BooleanWrapper(true, e.getMessage());
		}
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public void addDataEntry(String key, String value){
		if(dataList==null){
			dataList = new HashMap<>();
		}
		dataList.put(key, value);
	}
	
	@Override
	public String toString() {
		JSONObject message 		= new JSONObject();
		JSONObject data 		= new JSONObject();
		JSONObject notification = new JSONObject();
		
		if(to!=null){
			message.put(TO, to);
		}
		
		if(body!=null && title !=null && icon !=null){
			notification.put(TITLE, title);
			notification.put(BODY, body);
			notification.put(ICON, icon);
			message.put(NOTIFICATION, notification);
		}
		
		if(dataList!=null && !dataList.isEmpty()){
			Set<String> keys = dataList.keySet();
			for(String key: keys){
				data.put(key, dataList.get(key));
			}
			message.put(DATA, data);
		}
		return message.toString();
	}
	
}
