package com.fifedu.util.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);
    /** 格式化时间的string */
    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 持有ObjectMapper单例, 避免重复创建ObjectMapper消耗资源.
     */
    private static final ObjectMapper mapper = new ObjectMapper();;
    
    /**
     * fromJsonToObject<br>
     * json转换为java对象
     * 
     * <pre>
     * return Jackson.jsonToObj(this.answersJson, Jackson.class);
     * </pre>
     * 
     * @param <T>
     *            要转换的对象
     * @param json
     *            字符串
     * @param valueType
     *            对象的class
     * @return 返回对象
     */
    public static <T> T jsonToObj(String json, Class<T> valueType) {
	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;  
	try {
	    return mapper.readValue(json, valueType);
	}
	catch (JsonParseException e) {
	    logger.error("JsonParseException: ", e);
	}
	catch (JsonMappingException e) {
	    logger.error("JsonMappingException: ", e);
	}
	catch (IOException e) {
	    logger.error("IOException: ", e);
	}
	return null;
    }

    public static <T> T jsonToListWithGeneric(String json, Class<T> valueType) {
   	ObjectMapper mapper = new ObjectMapper();
   	try {
   	    return mapper.readValue(json, new TypeReference<T>(){});
   	}
   	catch (JsonParseException e) {
   	    logger.error("JsonParseException: ", e);
   	}
   	catch (JsonMappingException e) {
   	    logger.error("JsonMappingException: ", e);
   	}
   	catch (IOException e) {
   	    logger.error("IOException: ", e);
   	}
   	return null;
       }
    /**
     * fromObjectToJson<br>
     * java对象转换为json字符串
     * 
     * @param object
     *            Java对象
     * @return 返回字符串
     */
    public static String objToJson(Object object) {
	ObjectMapper mapper = new ObjectMapper();
	try {
	    return mapper.writeValueAsString(object);
	}
	catch (JsonGenerationException e) {
	    logger.error("JsonGenerationException: ", e);
	}
	catch (JsonMappingException e) {
	    logger.error("JsonMappingException: ", e);
	}
	catch (IOException e) {
	    logger.error("IOException: ", e);
	}
	return null;
    }

    /**
     * fromObjectHasDateToJson<br>
     * java对象(包含日期字段或属性)转换为json字符串
     * 
     * @param object
     *            Java对象
     * @return 返回字符串
     */
    public static String objDateToJson(Object object) {
	ObjectMapper mapper = new ObjectMapper();
	mapper.getSerializationConfig().with(
		new SimpleDateFormat(DATE_TIME_FORMAT));
	try {
	    return mapper.writeValueAsString(object);
	}
	catch (JsonGenerationException e) {
	    logger.error("JsonGenerationException: ", e);
	}
	catch (JsonMappingException e) {
	    logger.error("JsonMappingException: ", e);
	}
	catch (IOException e) {
	    logger.error("IOException: ", e);
	}
	return null;
    }

    /**
     * fromObjectHasDateToJson<br>
     * java对象(包含日期字段或属性)转换为json字符串
     * 
     * @param object
     *            Java对象
     * @param dateTimeFormatString
     *            自定义的日期/时间格式。该属性的值遵循java标准的date/time格式规范。如：yyyy-MM-dd
     * @return 返回字符串
     */
    public static String objDateToJson(Object object, String dateTimeFormatString) {
	ObjectMapper mapper = new ObjectMapper();
	mapper.getSerializationConfig().with(
		new SimpleDateFormat(dateTimeFormatString));
	try {
	    return mapper.writeValueAsString(object);
	}
	catch (JsonGenerationException e) {
	    logger.error("JsonGenerationException: ", e);
	}
	catch (JsonMappingException e) {
	    logger.error("JsonMappingException: ", e);
	}
	catch (IOException e) {
	    logger.error("IOException: ", e);
	}
	return null;
    }

       /**
	 * 反序列化复杂Collection如List<Bean>, 先使用createCollectionType()或contructMapType()构造类型, 然后调用本函数.
	 * 
	 * @see #createCollectionType(Class, Class...)
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

       /**
        * 构造Collection类型.
        */
	@SuppressWarnings("rawtypes")
	public static JavaType contructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
		return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}

       /**
        * 构造Map类型.
        */
	@SuppressWarnings("rawtypes")
	public static JavaType contructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
		return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
	}
    
    /**
     * 〈功能详细描述〉
     * 
     * @param args
     * void [如果有异常，异常的注释必须说明该异常的含义及什么条件下抛出该异常]
     */

    public static void main(String[] args) {
    	
    
    	
    
	//String str = objToJson(QuestionTypeConstant.getQuestionTypeList());
	//System.out.println(str);
	
	// 反向序列化List<Bean>类型的数据
        //String beanListString = "[{\"name\":\"A\"},{\"name\":\"B\"}]";
	//JavaType beanListType = mapper.contructCollectionType(List.class, TestBean.class);
	//List<TestBean> beanList = mapper.fromJson(beanListString, beanListType);
	//System.out.println("Bean List:");
	//for (TestBean element : beanList) {
		   //System.out.println(element);
	 //}
    }
    
	/**
	 * 获取泛型的Collection Type
	 * 
	 * @param collectionClass
	 *            泛型的Collection
	 * @param elementClasses
	 *            元素类
	 * @return JavaType Java类型
	 * @since 1.0
	 */
	/*public static JavaType getCollectionType(Class<?> collectionClass,
			Class<?>... elementClasses) {
		return mapper.getTypeFactory().constructParametricType(collectionClass,
				elementClasses);
	}
	
	
	public static void main(String[] args) throws Exception{  
		          JavaType javaType = getCollectionType(ArrayList.class, YourBean.class); 
		          List<YourBean> lst =  (List<YourBean>)mapper.readValue(jsonString, javaType); 
		     }*/  
    
    
}
