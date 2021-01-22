<%@page import="dashow.model.Mytable"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<s:if test="flag ==null ">
	<s:text name="创建对象" id="title" />
</s:if>
<s:else>
	<s:text name="编辑对象" id="title" />
</s:else>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title><s:property value="#title" /></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>
<body>
	<s:property value="#title" />
	<s:actionmessage />
	<s:form name="myForm" action="my" method="post">
		<s:token />
		<!-- 防重复提交 -->
		<s:hidden name="mytable.mid" value="%{flag}" />
		<s:textfield name="mytable.name" label="姓名" value="%{mytable.name}" />
		<s:textfield name="mytable.age" label="年龄" value="%{mytable.age}" />
		<tr>
			<td><s:submit name="submit" value="保存" method="update"
					theme="simple" /></td>
			<td><s:reset name="reset" value="取消" theme="simple" /></td>
		</tr>
	</s:form>
	<s:if test="mytable==null">mytable==null</s:if>
	<s:else>mytable!=null</s:else>
</body>
</html>
