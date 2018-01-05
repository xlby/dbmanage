package com.fifedu.util.common;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 全局配置类,读取系统核心配置
 * 
 * @author feizhao
 * @version 2014-02-10
 */
public class GlobalConfig {
	private static Properties SYS_CONFIG;
	public static final String DEFAULT_PROPERTIES = "classpath:application.properties";
	private static Map<String, Properties> properties = new HashMap<String, Properties>();

	/**
	 * 默认上传文件目录
	 */
	public static final String DEFAULT_PATH = "upload";

	/**
	 * 默认上传文件大小限制
	 */
	// public static final int MAX_FILE_SIZE;

	static {
		SYS_CONFIG = loadProperties();
	}

	private static Properties loadProperties() {
		if (SYS_CONFIG == null) {
			try {
				return loadProperties(DEFAULT_PROPERTIES);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return SYS_CONFIG;
	}

	private synchronized static Properties loadProperties(String propPath) {
		return new PropertiesLoader(propPath).getProperties();
	}

	/**
	 * 读取核心配置文件属性
	 * 
	 * @param key
	 * @return
	 */
	public static String getConfig(String key) {
		return getConfig(key, null);
	}

	/**
	 * 读取核心配置文件中配置的属性
	 * 
	 * @param key
	 * @param def
	 * @return
	 */
	public static String getConfig(String key, String def) {
		if (SYS_CONFIG == null) {
			loadProperties();
		}
		return StringUtils.isBlank(SYS_CONFIG.getProperty(key)) ? def : SYS_CONFIG.getProperty(key);
	}

	/**
	 * 读取核心配置文件中配置的属性
	 * 
	 * @param propPath
	 *            要读取的配置文件位置
	 * @param key
	 * @param def
	 * @return
	 */
	public static String getConfig(String propPath, String key, String def) {
		Properties prop = null;
		if (StringUtils.isBlank(propPath)) {
			prop = loadProperties();
		} else {
			prop = properties.get(propPath);
			if (prop == null) {
				prop = loadProperties(propPath);
				properties.put(propPath, prop);
			}
		}
		return StringUtils.isBlank(prop.getProperty(key)) ? def : prop.getProperty(key);
	}

	/**
	 * 普通文件 NORMAL_FILE，默认 头像文件 PHOTO_FILE
	 */
	public static enum Basepath {
		NORMAL_FILE, NET_FILE
	}

}
