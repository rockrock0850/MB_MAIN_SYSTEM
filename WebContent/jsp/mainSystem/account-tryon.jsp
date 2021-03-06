<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>企業試用帳號</title>
<style type="text/css">
body {
	padding-top: 5px;
}

.modal-style{
/* 	水平/垂直間距 */
 	border-spacing:20px 5px;
/*  告訴瀏覽器水平/垂直分開計算 */
	border-collapse:separate;
}
</style>
<script type="text/javascript">
var guid;
	
$(function() {
	$("body").on("click", ".btn-info", function() {
		guid = $(this).attr('data-id'); //抓取當前點擊元件的data-id
	});

	$("body").on("click", "#update", function() {
		guid = $(this).attr('data-id');
		$.ajax({
			type : "get",
			dataType : 'json',
			url : 'editTryonDetail/' + guid,
			success : function(res) {
				$('#selectGeneral').val(res.general.guid);
				$('#selectNotGeneral').empty();
				$('#selectNotGeneral').append("<option value=''>請選擇 </option>");
				if(res.notGeneralList != null){
					$.each(res.notGeneralList, function(index){
						var notGeneral = res.notGeneralList[index];
						$('#selectNotGeneral').append("<option value=" + notGeneral.guid + ">" + notGeneral.companyName + "</option>");
					});
					$('#selectNotGeneral').attr('disabled', false);
					$('#selectNotGeneral').val(res.notGeneral.guid);
				}

				$('#storeNo').val(res.enterprise.storeNo);
				$('#account').val(res.enterprise.account);
				$('#selectIndustry').val(res.enterprise.bIndustryGuid);
				$('#password').val(res.enterprisePasswordHistory.passwd);
				$('#companyName').val(res.enterprise.companyName);
				$('#uniformNumber').val(res.enterprise.uniformNumber);
				$('#companyPhone').val(res.enterprise.companyPhone);
				$('#applicantName').val(res.enterprise.applicantName);
				$('#applicantMobile').val(res.enterprise.applicantMobile);
				$('#email').val(res.enterprise.email);
				$('#companyAddress').val(res.enterprise.companyAddress);
			},
			error : function(res) {
				console.log(res);
			}
		});
	});

	$('#selectNotGeneral').attr('disabled', true);
	$('#selectGeneral').change(function(){
		$('#selectNotGeneral').attr('disabled', false);
		
		var guid = $('#selectGeneral').val();
		
		$.ajax({
			type : "get",
			dataType : 'json',
			url : 'getNotGeneralList/' + guid,
			success : function(res) {
				$('#selectNotGeneral').empty();
				$('#selectNotGeneral').append("<option value=''>請選擇 </option>");
				$.each(res, function(index){
					var notGeneral = res[index];
					$('#selectNotGeneral').append("<option value=" + notGeneral.guid + ">" + notGeneral.companyName + "</option>");
				});
			},
			error : function(res) {
				$('#selectNotGeneral').empty();
				$('#selectNotGeneral').append("<option value=''>請選擇 </option>");
			}
		});
	});
});

function effectiveModalDone() {
	var data = {
		'guid' : guid,
	};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		contentType : "application/json",
		url : 'tryonToEffective',
		data : JSON.stringify(data),
		success : function(res) {
			console.log(res);
			if(res.message != null){
				$('#msgModal h4').html(res.message);
				$('#effectiveModal').modal('hide');
				$('#msgModal').modal("show");
			}

			if(res.result != null){
				location.reload(true);
				$('#effectiveModal').modal('hide');
			}
		},
		error : function(res) {
			console.log(res);
		}
	});
};

function invalidModalDone() {
	var data = {
		'guid' : guid,
		'remark' : $('#remark').val()
	};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		contentType : "application/json",
		url : 'tryonToInvalid',
		data : JSON.stringify(data),
		success : function(res) {
			console.log(res);
			if(res.message != null){
				$('#msgModal h4').html(res.message);
				$('#effectiveModal').modal('hide');
				$('#msgModal').modal("show");
			}

			if(res.result != null){
				location.reload(true);
				$('#effectiveModal').modal('hide');
			}
		},
		error : function(res) {
			console.log(res);
		}
	});
}

function editModalDone() {
	var data = {
		'guid' : guid,
		'storeNo' : $('#storeNo').val(),
		'account' : $('#account').val(),
		'bAgentsGuid' : $('#selectGeneral').val(),
		'bAgentsGuidNotGeneral' : $('#selectNotGeneral').val(),
		'password' : $('#password').val(),
		'bIndustryGuid' : $('#selectIndustry').val(),
		'companyName' : $('#companyName').val(),
		'companyPhone' : $('#companyPhone').val(),
		'uniformNumber' : $('#uniformNumber').val(),
		'applicantName' : $('#applicantName').val(),
		'applicantMobile' : $('#applicantMobile').val(),
		'email' : $('#email').val(),
		'companyAddress' : $('#companyAddress').val()
	};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		url : 'updateTryon',
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(res) {
			if(res.result == null || res.result == ""){
				if($.isArray(res.message)){
					$('#msgModal .modal-header').empty();
					$.each(res.message, function(index){
						$('#msgModal .modal-header').append("<center><h4 class='modal-title'>" +res.message[index].message+ "</h4></center>");
					});
				}else{
					$('#msgModal h4').html(res.message);
				}
				$('#editModal').modal('hide');
				$('#msgModal').modal("show");
				return;
			}
			
			$('#editModal').modal('hide');
			location.reload(true);
		},
		error : function(res) {
			console.log(res);
		}
	});
}

function cancel(){
	$('#selectNotGeneral').empty();
	$('#selectNotGeneral').append("<option value=''>請選擇</option>");
};
</script>
<jsp:include page="constant.jsp"/>
</head>
<body>
	<div>
		<center>
			<jsp:include page="${requestScope.title}"/>
			<table class="table" style="width: 100%">
				<tr>
					<td style='width: 15%'>
						<jsp:include page="${requestScope.menu}"/>
					</td>
					<td>
						<table style="position: relative; width: 100%; top: 35px;">
							<tr>
								<td>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>類別：企業</th>
												<c:forEach items="${tryonMap}" var="object">
													<c:if test="${object.key == 'count'}">
														<th>筆數:${object.value}</th>
													</c:if>
												</c:forEach>
											</tr>
										</thead>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>管理</th>
												<th>業別</th>
												<th>統一編號</th>
												<th>公司名稱</th>
												<th>申請人</th>
												<th>行動電話</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${tryonMap}" var="object">
												<c:if test="${object.key == 'tryonList'}">
													<c:forEach items="${object.value}" var="enterprise">
														<tr>
															<td>
																<button type="button" class="btn btn-info" data-id="${enterprise.guid}" data-toggle="modal" data-target="#effectiveModal">有效</button> /
																<button type="button" class="btn btn-info" data-id="${enterprise.guid}" id="fail" data-toggle="modal" data-target="#invalidModal">無效</button> /
																<button type="button" class="btn btn-info" data-id="${enterprise.guid}" id="update" data-toggle="modal" data-target="#editModal">編輯</button>
															</td>
															<td>${enterprise.industry}</td>
															<td>${enterprise.uniformNumber}</td>
															<td>${enterprise.companyName}</td>
															<td>${enterprise.applicantName}</td>
															<td>${enterprise.applicantMobile}</td>
														</tr>
													</c:forEach>
												</c:if>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</center>
	</div>
	
	<!-- effectiveModal -->
	<div id="effectiveModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">是否改為有效帳號!?</h4>
				</div>
				<div class="modal-body">
					<center>
						<button type="button" class="btn btn-default" id="effectiveSubmit" onclick="effectiveModalDone();">確定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	<!-- invalidModal -->
	<div id="invalidModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">是否改為無效帳號!?</h4>
				</div>
				<div class="modal-body">
					<div class="input-group">
						<input type="text" class="form-control" id="remark" placeholder="無效帳號原因">
					</div>
				</div>
				<div class="modal-footer">
					<center>
						<button type="button" class="btn btn-default" id="failSubmit" onclick="invalidModalDone();">確定</button>
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					</center>
				</div>
			</div>
		</div>
	</div>
	
	<!-- editModal -->
	<div id="editModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">編輯詳細資料</h4>
					<label id="exist"></label>
				</div>
				<div class="modal-body">
					<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					<button type="button" class="btn btn-default" onclick="editModalDone();">儲存</button>
					<hr class="divider1">
					<div class="input-group">
						<form id="updateForm">
							<table class='modal-style'>
								<tr>
									<td colspan="2">
										<div class="form-group">
											<label for="sel1">＊總代理</label> 
											<select class="form-control" id="selectGeneral">
												<option>請選擇</option>
												<c:forEach items="${tryonMap}" var="obj">
													<c:if test="${obj.key == 'generalList'}">
														<c:forEach items="${obj.value}" var="general">
															<option value="${general.guid}">${general.companyName}</option>
														</c:forEach>
													</c:if>
												</c:forEach>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div class="form-group">
											<label for="sel1">＊代理商</label> 
											<select class="form-control" id="selectNotGeneral">
												<option value="">請選擇</option>
												<c:forEach items="${tryonMap}" var="obj">
													<c:if test="${obj.key == 'notGeneralList'}">
														<c:forEach items="${obj.value}" var="notGeneral">
															<option value="${notGeneral.guid}">${notGeneral.companyName}</option>
														</c:forEach>
													</c:if>
												</c:forEach>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2"><label>＊特店編號 </label> <input id="storeNo" type="text" class="form-control"></td>
								</tr>
								<tr>
									<td colspan="2"><label>＊企業帳號</label> <input id="account" type="text" class="form-control"></td>
								</tr>
								<tr>
									<td>
										<div class="form-group">
											<label for="sel1">業別</label> 
											<select class="form-control" id="selectIndustry">
												<option>請選擇</option>
												<c:forEach items="${tryonMap}" var="obj">
													<c:if test="${obj.key == 'industryList'}">
														<c:forEach items="${obj.value}" var="industry">
															<option value="${industry.guid}">${industry.name}</option>
														</c:forEach>
													</c:if>
												</c:forEach>
											</select>
										</div>
									<td>
										<label>密碼 : </label> 
										<input id="password" type="text" class="form-control">
									</td>
								</tr>
								<tr>
									<td><label>公司名稱 : </label> <input id="companyName" type="text" class="form-control"></td>
									<td><label>統一編號 : </label> <input id="uniformNumber" type="text" class="form-control"></td>
								</tr>
								<tr>
									<td><label>公司電話 : </label> <input id="companyPhone" type="text" class="form-control"></td>
									<td><label>申請人姓名 : </label> <input id="applicantName" type="text" class="form-control"></td>
								</tr>
								<tr>
									<td><label>申請人電話 : </label> <input id="applicantMobile" type="text" class="form-control"></td>
									<td><label>信箱 : </label> <input id="email" type="text" class="form-control"></td>
								</tr>
								<tr>
									<td><label>公司地址 : </label> <input id="companyAddress" type="text" class="form-control"></td>
								</tr>
							</table>
						</form>
					</div>
				</div>
				<div class="modal-footer"></div>
			</div>
		</div>
	</div>
	
	<!-- msgModal -->
	<div id="msgModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<center><h4 class="modal-title"></h4></center>
				</div>
				<div class="modal-body">
					<center><button type="button" class="btn btn-default" data-dismiss="modal">取消</button></center>
				</div>
			</div>
		</div>
	</div>
</body>
</html>