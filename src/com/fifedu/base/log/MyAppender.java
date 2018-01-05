package com.fifedu.base.log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.AppenderBase;
  
  
  
public class MyAppender extends AppenderBase<LoggingEvent> {
	 @Override  
	    protected void append(LoggingEvent eventObject) {  
		 
		 
		 Properties properties = new Properties();  
	        properties.put("zookeeper.connect", "192.168.71.211:2181");//声明zk  
	        properties.put("serializer.class", StringEncoder.class.getName());  
	        properties.put("metadata.broker.list", "192.168.71.211:9092");// 声明kafka broker  
	        Producer a =  new   Producer<Integer,String>(new ProducerConfig(properties));
	        KeyedMessage msg =new KeyedMessage<Integer, String>("test", "message: "+eventObject.getMessage());
//	        List list =new ArrayList();
//	        list.add(msg);
	        a.send(msg);
//	        
	    } 
	 public static void main (String args[]){
		 int bytesum = 0;
			int byteread = 0;
	        try { 
	        	String aa ="Is there anyone who hasn't suffered for the secret love? We always think that love is very heavy, heavy and could be the heaviest thing in the world. But one day, when you look back, you suddenly realize that it's always light, light. We all thought love was very deep, but in fact it's very thin. The deepest and heaviest love must grow up with the time.";
	        	String bb =URLEncoder.encode(aa, "UTF-8");	        
	        	String ss = "http://h5.xf-yun.com/webtts/"+bb+"/vcn=henry&ent=aisound";
				URL url = new URL(ss);
			
 				URLConnection conn = url.openConnection();
				InputStream inStream = conn.getInputStream();
				FileOutputStream fs = new FileOutputStream(
						"C:/test.mp3");

				byte[] buffer = new byte[1204];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread;
					fs.write(buffer, 0, byteread);
				}
				fs.close();
				inStream.close();
				 
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
}
