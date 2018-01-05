package com.fifedu.base.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ctakit.common.util.MyFileUtils;
import com.fifedu.util.web.MyThreadLocalUtils;
import com.fifedu.util.web.RequestUtils;
/**
 * 会话拦截器.将会话相关信息(session,及其他相关信息 )记录至ThreadLocal
 * @author liu10
 *
 */
public class ThreadLocalUtilsFilter implements Filter {

	/**
	 * SSO 访问 放行 URL 集合，给平台和其他系统调用使用
	 */
	private final static String[] filter_back_page_url = new String[] {
			"examBatchExamresultAnalysis/myScoreReportsMain",
			"examBatchExamresultAnalysis/list",
			"examBatchExamresultAnalysis/classPreviousTestScoresList" };

	private final static String LOGIN_HOME_URI = "/login/home";

	public Logger log = LoggerFactory.getLogger(getClass());

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		if (!preHandle(req, resp)) {
			return;
		}

		filterChain.doFilter(request, response);
		afterCompletion();
	}

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = null;
		MyThreadLocalUtils.setRequest(request);
		MyThreadLocalUtils.setResponse(response);

		String uri = RequestUtils.getLookupPathNoExtension(request);
		String ext = MyFileUtils.getFileNameExtension(
				RequestUtils.getLookupPath(request), "");
		MyThreadLocalUtils.setUriExt(ext);
		MyThreadLocalUtils.setUri(uri);
		session = request.getSession();
		MyThreadLocalUtils.setSession(session);
		return buildLoginInfo(request, response);
	}

	private boolean buildLoginInfo(HttpServletRequest request,
			HttpServletResponse response) {

		// 不过滤的uri
		String[] notFilter = new String[] { "sso/ssoService", "login.jsp",
				"/login/fail", "/user/pay/notify_url" };
		// 请求的uri
		String uri = request.getRequestURI();
		for (int i = 0; i < notFilter.length; i++) {
			if (uri.indexOf(notFilter[i]) != -1) {
				return true;
			}
		}

		return true;
	}

	public void afterCompletion() {
		MyThreadLocalUtils.removeRequest();
		MyThreadLocalUtils.removeResponse();
		MyThreadLocalUtils.removeSession();
		MyThreadLocalUtils.removeUri();
		MyThreadLocalUtils.removeUserType();
		MyThreadLocalUtils.removeUriExt();
		MyThreadLocalUtils.removeToken();
	}

	public void destroy() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
