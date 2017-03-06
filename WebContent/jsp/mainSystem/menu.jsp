<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h5><span>企業帳號管理：</span></h5>
	<div class="list-group">
		<a href="${pageContext.request.contextPath}/MainSystem/verify" class="list-group-item">企業申請審核</a>
		<a href="${pageContext.request.contextPath}/MainSystem/tryon" class="list-group-item">企業試用帳號</a>
		<a href="${pageContext.request.contextPath}/MainSystem/effective" class="list-group-item">企業正式帳號</a>
		<a href="${pageContext.request.contextPath}/MainSystem/invalid" class="list-group-item">企業無效帳號</a>
		<a href="${pageContext.request.contextPath}/MainSystem/fail" class="list-group-item">企業不通過帳號</a>
	</div>
	<h5><span>代理商管理：</span></h5>
	<div class="list-group">
		<a href="${pageContext.request.contextPath}/MainSystem/general" class="list-group-item">總代理</a>
		<a href="${pageContext.request.contextPath}/MainSystem/agents" class="list-group-item">代理商</a>
		<a href="${pageContext.request.contextPath}/MainSystem/agentsStore" class="list-group-item">特店編號管理</a>
	</div>
</body>
</html>