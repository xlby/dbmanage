package com.fifedu.util.zip;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.GZIPInputStream;  
  







import javax.servlet.ServletInputStream;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletRequestWrapper;  
  







import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
/** 
 * zip流解析
 *  
 */  
public class GzipRequestWrapper extends HttpServletRequestWrapper {  
    public static final Logger LOGGER = LoggerFactory  
            .getLogger(GzipRequestWrapper.class);  
    private HttpServletRequest request;  
  
    public GzipRequestWrapper(HttpServletRequest request) {  
        super(request);  
        this.request = request;  
    }  
  
    @Override  
    public ServletInputStream getInputStream() throws IOException {  
    	
        ServletInputStream stream = request.getInputStream();  
        String contentEncoding = request.getHeader("Content-Encoding");  
        // 如果对内容进行了压缩，则解压  
        if (null != contentEncoding && contentEncoding.indexOf("gzip") != -1) {  
            try {  
                final GZIPInputStream gzipInputStream = new GZIPInputStream(  
                        stream);  
  
                ServletInputStream newStream = new ServletInputStream() {  
  
                    @Override  
                    public int read() throws IOException {  
                        return gzipInputStream.read();  
                    }  
                };  
                return newStream;  
            } catch (Exception e) {  
                LOGGER.debug("ungzip content fail.", e);  
            }  
        }  
        return stream;  
    }  
  
} 