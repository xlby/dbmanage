<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%
if(request.getAttribute("javax.servlet.error.request_uri")!=null) {
	String error = request.getAttribute("javax.servlet.error.request_uri")+"";
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="refresh" content="0.1;url=/testcenter">
<title>没有找到页面</title>
</head>
<body>
<p align="center" style="font-size: 40px;padding-top: 200px;display:none" >对不起，没有找到你的页面!!</p>
<p></p>
<p></p><p></p><p></p><p></p><p></p>
<p></p><p></p><p></p><p></p><p></p>
<p></p><p></p><p></p><p></p>
<p></p><p></p><p></p>
<p></p><p></p><p></p>
<div style="display:none">这里是404页面，欢迎使用本系统，本系统出现了404错误了！</div>
</body>
</html>