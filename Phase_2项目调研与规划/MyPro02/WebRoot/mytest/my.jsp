<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'login.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>	
	<s:form name="findForm" action="my" method="post">
		<s:textfield name="keyword" label="输入查询关键词" value="Nacy" />
		<tr>
			<td><s:submit name="submit" value="查询" method="findByField"
					theme="simple" /></td>
			<td><s:reset name="reset" value="取消" theme="simple" /></td>
		</tr>
	</s:form>
	<s:label>my!list执行结果：</s:label>
	<s:action name="my!list" executeResult="true" />
</body>
</html>
