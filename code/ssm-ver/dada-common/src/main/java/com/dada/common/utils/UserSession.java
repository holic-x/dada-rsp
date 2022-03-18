package com.dada.common.utils;

import javax.servlet.http.HttpSession;

public class UserSession {
	
	 private static final ThreadLocal<HttpSession> userSession = new ThreadLocal<HttpSession>();  
     
	 public static void setUserSession(HttpSession session) {  
		 userSession.set(session);  
	}  
	    
	public static HttpSession getUserSession() {  
		return userSession.get();  
	}  
	      
	/*
	public static UserInfo getUser(){  
	    HttpSession session = userSession.get();
	    if(session!=null) {
	        return (UserInfo)session.getAttribute("loginUser");  
	    }
		return null;
	}  
	*/
	      
	public static void setAttribute(String key, Object value){  
		userSession.get().setAttribute(key, value);  
	}  
	      
	public static Object getAttribute(String key){  
		return userSession.get().getAttribute(key);  
	}  

}
