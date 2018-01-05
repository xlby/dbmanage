package com.fifedu.base.web;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.ctakit.common.util.MyDateUtils;
import com.ctakit.common.util.MyNumberUtils;
import com.ctakit.common.util.MyStringUtils;
import com.ctakit.common.util.page.imp.Page;
import com.fifedu.base.vo.LoginUserInfo;
import com.fifedu.util.web.MyThreadLocalUtils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * controller 抽象类,实现基本公用方法.
 * @author liu10
 *
 */
public abstract class BaseController extends MyController {
    protected static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /*@InitBinder
    public void InitBinder(WebDataBinder dataBinder,HttpServletRequest request) {
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, false));
    }*/
    @InitBinder
    public void InitBinder(WebDataBinder dataBinder, HttpServletRequest request) {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                try {
                    if (MyStringUtils.isBlank(value)) {
                        setValue(null);
                    } else {
                        setValue(MyDateUtils.parseDateTime(value));
                    }
                } catch (ParseException e) {
                    setValue(null);
                }
            }

            public String getAsText() {
                return sdf.format((Date) getValue());
            }

        });
        dataBinder.registerCustomEditor(Boolean.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (MyStringUtils.isNotBlank(value)) {
                    if ("true".equalsIgnoreCase(value) || (MyNumberUtils.isDigits(value) && MyNumberUtils.toLong(value) > 0)) {
                        setValue(true);
                    } else {
                        setValue(false);
                    }
                }
            }

        });
        dataBinder.registerCustomEditor(Number.class, new PropertyEditorSupport() {
            public void setAsText(String value) {
                if (MyStringUtils.isNotBlank(value)) {
                    if ((MyNumberUtils.isDigits(value))) {
                        setValue(value);
                    } else {
                        setValue(null);
                    }
                }
            }

        });
    }

    /**
     * 保存查询条件到 model　中
     *
     * @param model
     */
    protected void saveQueryParamToModel(Model model) {
        HttpServletRequest request = MyThreadLocalUtils.getRequest();
        Enumeration<String> names = request.getParameterNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            //System.out.println("参数字段:" + name);
            if (name.startsWith("query")) {
                String value = MyStringUtils.trim(request.getParameter(name));
                if (MyStringUtils.isNotBlank(value)) {
                    model.addAttribute(name, value);
                }
            }
        }
    }

    /**
     * 页面采用grid 时，保存数据到页面对象中
     *
     * @param data
     */
    public void setPageData(List<?> data) {
        Page page = Page.threadLocal.get();
        if (page == null) {
            page = new Page();
            Page.threadLocal.set(page);
        }
        page.setData(data);
    }

    protected LoginUserInfo getUserInfo() {
        return (LoginUserInfo) getSession().getAttribute("LoginUserInfo");
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    protected HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected String getLoginRole(){
        return this.getSession().getAttribute("loginRole") + "";
    }

    public static void main(String[] args) {
        try {
            sdf.setLenient(false);
            System.out.println(sdf.parse("2012-01-03 11:00"));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
