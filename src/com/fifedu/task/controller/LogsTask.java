package com.fifedu.task.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 日志定时持久化到数据库
 * 
 * @author Jason
 * 
 */
@Service
public class LogsTask {
	private final Logger log = LoggerFactory.getLogger(LogsTask.class);
	//@Resource
	//LogsQueueUtil logsQueueUtil;

	public void runLogsTask() {
//		log.info("定时器kaishi 执行.");
//		String downloadurl = "http://h5.xf-yun.com/webtts/%e5%90%88%e6%88%90%e9%9f%b3%e9%a2%91%e6%96%87%e6%9c%ac%e5%86%85%e5%ae%b9%e9%98%bf%e6%96%af%e8%92%82%e8%8a%ac/vcn=xiaoyan&ent=aisound";
//		int bytesum = 0;
//	int byteread = 0;
//	try {
//		java.net.URL url = new java.net.URL(downloadurl);
//
//
//		java.net.URLConnection conn = url.openConnection();
//		java.io.InputStream inStream = conn.getInputStream();
//		java.io.FileOutputStream fs = new java.io.FileOutputStream(new File("aa.mp3"));
//
//		byte[] buffer = new byte[1204];
//		int length;
//		while ((byteread = inStream.read(buffer)) != -1) {
//			bytesum += byteread;
//			fs.write(buffer, 0, byteread);
//		}
//		fs.close();
//		inStream.close();
//		System.out.println("下载挖成");
//		
//	} catch (java.io.FileNotFoundException e) {
//		e.printStackTrace();
//	} catch (java.io.IOException e) {
//		e.printStackTrace();
//	}
//		log.info("定时器执行.");
		
	}

}
