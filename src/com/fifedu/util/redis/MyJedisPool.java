package com.fifedu.util.redis;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

 /**
  * redis链接池
  * @author liu10
  *
  */
public class MyJedisPool {
	/**
	 * jedis 池
	 */
	private static JedisPool pool;
	 
	/**
	 * shardedJedis池
	 */
	private static ShardedJedisPool shardPool;
	// 静态代码初始化池配置
	static {
		// 加载redis配置文件
		ResourceBundle bundle = ResourceBundle.getBundle("redis");
		if (bundle == null) {
			throw new IllegalArgumentException("[redis.properties] is not found!");
		} 
		String strTimeout = bundle.getString("jedis.pool.connectionTimeout");

		// 创建jedis池配置实例
		final JedisPoolConfig config = new JedisPoolConfig();
		// 设置池配置项值
		config.setMaxTotal(Integer.valueOf(bundle.getString("jedis.pool.maxActive")));
		config.setMaxIdle(Integer.valueOf(bundle.getString("jedis.pool.maxIdle")));
		config.setMinIdle(Integer.valueOf(bundle.getString("jedis.pool.minIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("jedis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("jedis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("jedis.pool.testOnReturn")));

		// 根据配置实例化jedis池,带auth
		pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")),
				Integer.valueOf(strTimeout),bundle.getString("redis.auth"));
        // 不带auth
		/*pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));*/
		// 创建多个redis共享服务
		/*
		 * JedisShardInfo jedisShardInfo1 = new JedisShardInfo(
		 * bundle.getString("redis.ip"),
		 * Integer.valueOf(bundle.getString("redis.port"))); JedisShardInfo
		 * jedisShardInfo2 = new JedisShardInfo( bundle.getString("redis.ip"),
		 * Integer.valueOf(bundle.getString("redis.port")));
		 * 
		 * List<JedisShardInfo> list = new LinkedList<JedisShardInfo>();
		 * list.add(jedisShardInfo1); list.add(jedisShardInfo2);
		 */

		// 根据配置文件,创建shared池实例
		// shardPool = new ShardedJedisPool(config, list);

	}

	//jedis池部分
	public static Jedis getResource() {
		Jedis jedis = pool.getResource();
		try {
			Integer dbIdx = 0;
			jedis.select(dbIdx);
			return jedis;
		} catch(Exception e) {
			return jedis;
		}
	}
	
	public static Jedis getResource(Integer dbIdx) {
		Jedis jedis = pool.getResource();
		if(dbIdx==null) {
			dbIdx = 0;
		}
		jedis.select(dbIdx);
		return jedis;
	}

	public static void returnResource(Jedis resource) {
		pool.returnResource(resource);
	}

	public static void destory() {
		pool.destroy();
	}

	public static JedisPool getPool() {
		return pool;
	}

	public static void returnBrokenResource(Jedis resource) {
		pool.returnBrokenResource(resource);
	}

	// shardedJedis池部分
	public static ShardedJedis getSharedJedisResource() {
		return shardPool.getResource();
	}

	public static void returnResource(ShardedJedis resource) {
		shardPool.returnResource(resource);
	}

	public static void shardPooldestory() {
		shardPool.destroy();
	}

	public static ShardedJedisPool geShardPool() {
		return shardPool;
	}

	public static void returnBrokenResource(ShardedJedis resource) {
		shardPool.returnBrokenResource(resource);

	}

	/**
	 * setJedisCatch jedis池方法
	 */
	public static void setJedisCatch(String key, String value) {
		// 从jedis池中获取一个jedis实例
		Jedis jedis = pool.getResource();

		// 获取jedis实例后可以对redis服务进行一系列的操作
		jedis.set(key, value);
		System.out.println(jedis.get("name"));
		// jedis.del("name");
		// System.out.println(jedis.exists("name"));

		// 释放对象池，即获取jedis实例使用后要将对象还回去
		pool.returnResource(jedis);
	}

	/**
	 * 测试shardedJedis池方法
	 */
	public static void test2() {
		// 从shard池中获取shardJedis实例
		ShardedJedis shardJedis = shardPool.getResource();

		// 向redis服务插入两个key-value对象
		shardJedis.set("lizhen", "xmong_aaa");
		System.out.println(shardJedis.get("aaa"));
		shardJedis.set("zzz", "xmong_zzz");
		System.out.println(shardJedis.get("zzz"));

		// 释放资源
		shardPool.returnResource(shardJedis);
	}

	public static void test3() {
	 
	}

	public static void main(String[] args) {
		//Jedis jedis = getResource(3);
		//jedis.keys("user*");
		test4();
	}

	private static void test4() {
		// 从jedis池中获取一个jedis实例
		Jedis jedis = pool.getResource();
		System.out.println(jedis.get("hh"));
		jedis.flushAll();
		// jedis.del("name");
		// System.out.println(jedis.exists("name"));

		// 释放对象池，即获取jedis实例使用后要将对象还回去
		pool.returnResource(jedis);

	}

}