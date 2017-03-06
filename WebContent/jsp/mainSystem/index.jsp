<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/resources/css/base.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-switch.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.11.1.min.js" /></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" /></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-switch.min.js" /></script>
<title>企業帳號管理</title>
<style type="text/css">
	body {
	    padding-top: 5px;
	}
</style>
<script type="text/javascript"></script>
<jsp:include page="constant.jsp"/>
</head>
<body>
	<div>
		<center>
			<h3><span class="label label-info">企業帳號管理系統</span></h3>
			<table class="table" style="width:100%">
				<tr>
					<td style='width:15%'>
						<jsp:include page="${requestScope.menu}"/>
					</td>
					<td>
						<table style='position:relative;top:20px;'>
							<tr>
								<td>            
									<center><h3><span class="text-muted">首頁</span></h3></center>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</center>
	</div>
</body>
</html>