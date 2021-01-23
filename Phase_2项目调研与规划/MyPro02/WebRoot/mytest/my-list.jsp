<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>My JSP 'my-list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
</head>

<body>

	<s:div>
		<a href="mytest/my!loading.action">新增信息</a>
		<hr>
		<table>
			<tr>
				<td>MID</td>
				<td>Name</td>
				<td>Age</td>
				<td>详细信息</td>
				<td>修改信息</td>
				<td>删除信息</td>
			</tr>
			<s:iterator value="list">
				<tr>
					<td><s:property value="mid" /></td>
					<td><s:property value="name" /></td>
					<td><s:property value="age" /></td>
					<td><a
						href="mytest/my!loading.action?flag=<s:property value="mid"/>">详细信息</a></td>
					<td><a
						href="mytest/my!loading.action?flag=<s:property value="mid"/>">修改信息</a></td>
					<td><a
						href="mytest/my!delete.action?flag=<s:property value="mid"/>">删除信息</a>
					</td>
				</tr>
			</s:iterator>
		</table>

		<table>
			<tr>
				<td>MID</td>
				<td>Name</td>
				<td>Age</td>
				<td>详细信息</td>
				<td>修改信息</td>
				<td>删除信息</td>
			</tr>
			<c:forEach items="${list}" var="m">
				<tr>
					<td>${ m.mid}</td>
					<td>${ m.name}</td>
					<td>${ m.age}</td>
					<td><a href="mytest/my!loading.action?flag=${ m.mid}">详细信息</a></td>
					<td><a href="mytest/my!loading.action?flag=${ m.mid}">修改信息</a></td>
					<td><a href="mytest/my!delete.action?flag=${ m.mid}">删除信息</a></td>
				</tr>
			</c:forEach>
		</table>
	</s:div>
</body>
</html>
