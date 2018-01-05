package com.fifedu.oper.exception;

/**
 * redis异常
 * @author xlan
 *
 */
public class DbRedisException extends Exception   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DbRedisException(String msg){
		 super(msg); 
	}
	
   public DbRedisException(String message, Throwable cause){
	    super(message, cause);
	}
   
   public DbRedisException(){
	   
   }
   
   
}
