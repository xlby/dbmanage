package com.fifedu.oper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.thrift.TException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fifedu.base.web.BaseController;
import com.fifedu.base.web.resolver.ResultType;
import com.fifedu.mybatis.model.base.UcUser;
import com.fifedu.oper.DbParam;
import com.fifedu.oper.service.UcUserService;


@RequestMapping(value = "/db")
@Controller
public class DbOperController extends BaseController {
	

	
	
	@Resource
	private UcUserService ucUserService;
	/**
	 * 数据库列表--
	 * @param model
	 * @param examBatch
	 * @return
	 */
	@RequestMapping(value = "/dbList")
	public String getDbList(Model model,String pinyinName,String userName) {
		UcUser user = new UcUser();
		if(null!=pinyinName&&!"".equals(pinyinName)) {
			user.setPinyinName(pinyinName);
		}
		if(null!=userName&&!"".equals(userName)) {
			user.setUserName(userName);
		}
       List<UcUser>	list = new ArrayList<UcUser>();
           try {
        	   list =  ucUserService.getByExample(user);
        	  
	     	} catch (Exception e) {
			     e.printStackTrace();
		    }
		model.addAttribute("scList", list);
		return this.result(ResultType.JSON);
	}
	
	
	
	@RequestMapping(value = "/updateUserInfo")
	public String updateUserInfo(Model model,String id,String pinyinName,String idCardNo) {
		UcUser user = new UcUser();
		user.setId(id);
		if(null!=pinyinName&&!"".equals(pinyinName)) {
			user.setPinyinName(pinyinName);
		}
		if(null!=idCardNo&&!"".equals(idCardNo)) {
			user.setIdCardNo(idCardNo);
		}
		user.setUpdateTime(new Date());
		
		ucUserService.update(user);
		
		
		System.out.println("pinyinName === "+pinyinName);
		System.out.println("idCardNo === "+idCardNo);
		model.addAttribute("pinyinName", pinyinName);
		model.addAttribute("idCardNo", idCardNo);
		return this.result(ResultType.JSON);
	}
	
	
	/**
	 * 查询订单--
	 * @param model
	 * @param examBatch
	 * @return
	 */
	@RequestMapping(value = "/getDbCodeInfo")
	public String getDbCodeInfo(Model model,String userRealName,String phone,String payCode) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		
		//DbParam param = new DbParam();
		if(null!=userRealName&&!"".equals(userRealName)) {
			map.put("userRealName", userRealName);
		}
		if(null!=phone&&!"".equals(phone)) {
			map.put("phone", phone);
		}
		if(null!=payCode&&!"".equals(payCode)) {
			map.put("payCode", payCode);
		}
		
         List<DbParam>	list = new ArrayList<DbParam>();
           try {
        	   list =  ucUserService.getDbCodeInfo(map);
        	   if(list.size()==0) {
        		   list.add(new DbParam("没有查询到记录","注意","该查询非模糊查询","只会查询出完全满足搜索条件的记录","","","",""));
        	   }
	     	} catch (Exception e) {
			     e.printStackTrace();
		    }
		model.addAttribute("scList", list);
		return this.result(ResultType.JSON);
	}
	
	
	
	
}
