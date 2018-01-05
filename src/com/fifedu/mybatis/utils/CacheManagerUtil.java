package com.fifedu.mybatis.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.exceptions.JedisException;

import com.fifedu.base.service.RedisDBService;
import com.fifedu.mybatis.base.RedisDao;
import com.fifedu.util.common.DateUtil;
import com.fifedu.util.constant.RedisKeyConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * 缓存操作类 负责从缓存中取业务数据
 * 
 * @author
 */
public class CacheManagerUtil {
	private Gson gson;
	private RedisDao redisDao;
	public static CacheManagerUtil instance = null;

	private Logger log = LoggerFactory.getLogger(CacheManagerUtil.class);
	



	/**
	 * 保存对象到redis缓存
	 * 
	 * @param key
	 * @param mapObj
	 */
	public void saveInforToCatch(String key, Map mapObj) {
		String objJsonStr = new GsonBuilder().create().toJson(mapObj);
		redisDao.set(key, objJsonStr);
	}
	
	/**
	 * 缓存map
	 * @param key
	 * @param map
	 */
	public void hmset(String key ,Map<String,String> map) {
		redisDao.hmset(key,map);
	}

	/**
	 * 根据key删除缓存中的对象
	 * 
	 * @param key
	 * @param mapObj
	 */
	public static void delInfoFromCatch(String key) {
		RedisDao redisDao = new RedisDao();
		redisDao.del(key);
	}

	
	
	
	/**
	 * @param tableName String 设置有效期的表名，也就是RedisKeyConstants类中的关于redis的常量
	 * @param keyName  String tableName下的key值
	 * @param days	int 此key值的有效天数
	 * @return
	 * 设置redis的有效周期
	 * 方便以后进行redis的数据管理
	 * 用于记录 当前插入的数据的创建时间的，为以后数据清理提供时间依据，
	 * 如果没有 此数据的截止日期可以将 days 设置为 小于等于 0的整数，- days此属性 已废弃
	 * 否则 days 为 多少天后过期的一个整数 -- days此属性 已废弃
	 * 
	 * 删除：lrem(String key, long count, String value) 
	 * 添加：public Long lpush(String key, String... strings) 
	 */
	public boolean setRedisKeyPeriod(String tableName , String keyName) {
		Calendar cal=Calendar.getInstance();
		String values = DateUtil.formatDate(cal.getTime(), "yyyy-MM-dd");
		StringBuilder sb = new StringBuilder();
		sb.append(tableName).append("&|&");
		sb.append(keyName).append("&|&");
		sb.append(values);
		redisDao.lpush(RedisKeyConstants.REDIS_KEY_PERIOD, sb.toString());
		return true;
	}
	public boolean setRedisKeyPeriod(String dbIndex,String tableName , String keyName) {
		Calendar cal=Calendar.getInstance();
		String values = DateUtil.formatDate(cal.getTime(), "yyyy-MM-dd");
		StringBuilder sb = new StringBuilder();
		sb.append(tableName).append("&|&");
		sb.append(keyName).append("&|&");
		sb.append(values);
		redisDao.lpush(dbIndex , RedisKeyConstants.REDIS_KEY_PERIOD, sb.toString());
		return true;
	}
	


	
	public void hset(String key, String field, String value) {
		redisDao.hset(key, field, value);
	}
	
	public void hsetVobj(String key, String field, Object value) {
		try{
			redisDao.hset(key, field, new GsonBuilder().create().toJson(value));
		}catch(JedisException e){
			log.error("redis请求失败!");
		}
	}
	
	public void hsetVobj(Integer dbIdx,String key, String field, Object value) {
		try{
		   redisDao.hset(dbIdx, key, field, new GsonBuilder().create().toJson(value));
		}catch(JedisException e){
			log.error("redis请求失败!");
		}
	}
	
	public void hsetVmap(String key, String field, Map<?,?> map) {
		redisDao.hset(key, field, new GsonBuilder().create().toJson(map));
	}
	
	public void hsetVmap(Integer dbIdx,String key, String field, Map<?,?> map) {
		redisDao.hset(dbIdx,key, field, new GsonBuilder().create().toJson(map));
	}
	
	public String hget(String key, String field) {
		return redisDao.hget(key, field);
	}
	public String hget(Integer dbIdx,String key, String field) {
		return redisDao.hget(dbIdx, key, field);
	}
	
	public <T> T hgetVobj(String key, String field,Class<T> clsss) {
		return gson.fromJson(redisDao.hget(key, field),clsss);
	}
	
	public <T> T hgetVobj(Integer dbIdx,String key, String field,Class<T> clsss) {
		return new GsonBuilder().create().fromJson(redisDao.hget(dbIdx,key, field),clsss);
	}
	
	public Map<?,?> hgetVmap(String key, String field,Type typeOfT) {
		return new GsonBuilder().create().fromJson(redisDao.hget(key, field), typeOfT);
	}
	
	public Map<?,?> hgetVmap(Integer dbIdx,String key, String field,Type typeOfT) {
		return new GsonBuilder().create().fromJson(redisDao.hget(dbIdx,key, field), typeOfT);
	}
	
	public List<?> hget2List(String key, String field,Type typeOfT) {
		return new GsonBuilder().create().fromJson(redisDao.hget(key, field), typeOfT);
	}
	
	public Long hdel(String key, String... field) {
		return redisDao.hdel(key, field);
	}
	
	public Long hdel(Integer dbIdx,String key, String field) {
		return redisDao.hdel(dbIdx,key, field);
	}
	
	private CacheManagerUtil() {
		gson = new Gson();
		redisDao = new RedisDao();
	}

	
	public RedisDao getRedisDao() {
		return redisDao;
	}

	/**
	 * 处理多线程导致性能问题，要比不处理更严重，暂且不处理
	 * @return
	 */
	public final static CacheManagerUtil getInstance() {
		if (instance == null) {
			instance = new CacheManagerUtil();
		}
		return instance;
	}

	
}
