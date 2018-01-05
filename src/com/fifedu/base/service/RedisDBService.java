package com.fifedu.base.service;

import java.util.HashMap;
import java.util.Map;


import com.fifedu.mybatis.utils.CacheManagerUtil;
import com.fifedu.util.constant.RedisKeyConstants;




/**
 * 确定当前学校从Redis的哪个库读取数据
 * @author zx
 *
 */
public class RedisDBService{
	
	public static Integer getSchoolDBIdxFromRedis(String schoolId) {
		String dbIdx = CacheManagerUtil.getInstance().hget(0,
				RedisKeyConstants.CCB_SCHOOL_REDIS_DBIDX, schoolId);
		if (dbIdx != null && !dbIdx.equals("")) {
			return Integer.valueOf(dbIdx);
		} else {
			return 0;
		}
	}

	public static Map<String, Integer> getSchoolDBIdxMapFromRedis() {
		Map<String, String> ms = CacheManagerUtil.getInstance().getRedisDao()
				.hmget(0, RedisKeyConstants.CCB_SCHOOL_REDIS_DBIDX);
		Map<String, Integer> mi = new HashMap<String, Integer>();
		try {
			for (Map.Entry<String, String> sentry : ms.entrySet()) {
				mi.put(sentry.getKey(), Integer.valueOf(sentry.getValue()));
			}
		} catch (Exception e) {
		}
		return mi;
	}
}
