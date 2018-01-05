/*
 * 文件名：JaxbUtil.java
 * Copyright: 2014-2018 FiF. All Rights Reserved. 
 * @author: 
 * 0 
 * @since: -出题平台
 * 创建时间： 2013-9-2
 * 静态方法，完成xml与javaBean的转换
 */

package com.fifedu.util.xml;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 静态方法，完成xml与javaBean的转换
 * 
 */
public class JaxbUtil {
    
    /**
     * 
     * 转换java对象为xml字符串
     * @param obj
     * @return
     * @throws JAXBException String
     * [如果有异常，异常的注释必须说明该异常的含义及什么条件下抛出该异常]
     */
    public static <T> String java2XML(T obj) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());   
        Marshaller marshaller = context.createMarshaller();   
        marshaller.setProperty(Marshaller.JAXB_ENCODING,"UTF-8");//编码格式
        //我在实际开发中重新封装了JAXB基本类，这里就使用到了该属性。不过默认的编码格式UTF-8
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//是否格式化生成的xml串   
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);//是否省略xml头信息（<?xml version="1.0" encoding="gb2312" standalone="yes"?>）   
           
        //输出到字符串中
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);   
       
        return writer.toString();
    }
    
    
    /**
     * 
     * 转化xml字符串为javaBean
     * @param xmlString
     * @param obj
     * @return
     * @throws JAXBException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException T
     * [如果有异常，异常的注释必须说明该异常的含义及什么条件下抛出该异常]
     */
    @SuppressWarnings("unchecked")
    public static <T> T  XML2Java(String xmlString, Class<T> cls) throws JAXBException
    {
        JAXBContext context = JAXBContext.newInstance(cls);   
        Unmarshaller unmarshaller = context.createUnmarshaller();   
        
        StringReader reader = new StringReader(xmlString);
        Object object = unmarshaller.unmarshal(reader);
	return (T)object;  
    }
}
