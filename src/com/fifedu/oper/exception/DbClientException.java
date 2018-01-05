package com.fifedu.oper.exception;

/**
 * 自定义客户端请求异常
 * @author xlan
 *
 */
public class DbClientException extends Exception   {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DbClientException(String msg){
		 super(msg); 
	}
	
   public DbClientException(){
		
	}
}
