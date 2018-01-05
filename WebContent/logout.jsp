<%@ page language="java" import="java.util.*,org.jasig.cas.client.authentication.AttributePrincipal,org.jasig.cas.client.validation.Assertion,org.jasig.cas.client.util.AbstractCasFilter" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <% 
if(session!=null) {
	session.invalidate();
	String host = request.getRequestURL().toString().replaceAll(request.getRequestURI(), "");
	response.sendRedirect(host+"/SSO/logout?service="+host+"/usercenter");
}
%>
