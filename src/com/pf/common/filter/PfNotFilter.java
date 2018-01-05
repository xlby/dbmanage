/*
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.pf.common.filter;

import com.alibaba.fastjson.JSON;
import com.fifedu.base.vo.LoginUserInfo;
import com.fifedu.util.constant.CasRedisDBConstants;
import com.fifedu.util.constant.RedisKeyConstants;
import com.fifedu.util.redis.MyJedisPool;

import org.jasig.cas.client.validation.Assertion;

import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpSession;


/**
 * Filter implementation to intercept all requests and attempt to authenticate
 * the user by redirecting them to CAS (unless the user has a ticket).
 * <p>
 * This filter allows you to specify the following parameters (at either the
 * context-level or the filter-level):
 * <ul>
 * <li><code>casServerLoginUrl</code> - the url to log into CAS, i.e.
 * https://cas.rutgers.edu/login</li>
 * <li><code>renew</code> - true/false on whether to use renew or not.</li>
 * <li><code>gateway</code> - true/false on whether to use gateway or not.</li>
 * </ul>
 * <p>
 * <p>
 * Please see AbstractCasFilter for additional properties.
 * </p>
 *
 * @author Scott Battaglia
 * @author Misagh Moayyed
 * @since 3.0
 */
public class PfNotFilter {
	/**
	 * 
	 * @return 返回非登录请求.cas不会进行拦截.
	 */
    public static String[] getNotFilter() {
        return new String[]{
                "/door/workVoteList/",
                "/door/newsList/",
                "/door/worksInfo",
                "/door/worksRank/",
                "/door/newsDetail/",
                "/door/provinceStat",
                "/door/totalCount",
                "/vote/index",
                "/contribute/c_view",
                "/contribute/c_previewResource",
                "/collect/"
        };

    }

    /**
     * CAS登陆成功回调方法
     * @param assertion
     * @param session
     */
    public static void onSuccessfulValidation(Assertion assertion, HttpSession session) {
        String userName = assertion.getPrincipal().getName();
        String token = (String) assertion.getPrincipal().getAttributes().get("token");
        LoginUserInfo userInfo = getLoginUserInfoRedis(CasRedisDBConstants.CAS_DATA, userName);
        session.setAttribute("LoginUserInfo", userInfo);
    }

    /**
     * 查询redis用户信息
     * @param dbIdx
     * @param loginUserName
     * @return
     */
    public static LoginUserInfo getLoginUserInfoRedis(int dbIdx, String loginUserName) {
        LoginUserInfo info = null;
        Jedis jedis = null;
        try {
            jedis = MyJedisPool.getResource(dbIdx);
            String jsonStr = jedis.get(RedisKeyConstants.KYE_USER_INFO
                    + loginUserName);
            info = JSON.parseObject(jsonStr, LoginUserInfo.class);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            MyJedisPool.returnResource(jedis);
        }
        return info;
    }
}
