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
<title>代理商</title>
<style type="text/css">
body {
	padding-top: 5px;
	/* 	background-color: rgba(199,237,204,1.00); */
}

.customer-btn{
	width: 80px;
}

.modal-style{
/* 	水平/垂直間距 */
 	border-spacing:20px 5px;
/*  告訴瀏覽器水平/垂直分開計算 */
	border-collapse:separate;
}

.red a {
	color: black;
}

/* Modal加入scrollbar */
.modal-dialog{
    overflow-y: initial !important
}

.scrollbar{
    height: 500px;
    overflow-y: auto;
}
</style>
<script type="text/javascript">
function sendSMS() {
	var string = "";
	var i = 0;
	$(":checkbox").each(function() {
		if ($(this).prop("checked")) {
			string += $(this).val() + ",";
			i++
		}
	});
	var jsonString = {
		"tempData" : string
	}
	var json = JSON.stringify(jsonString);
	$.ajax({
		type : "POST",
		dataType : 'json',
		url : 'sendMailToSelective',
		contentType : "application/json",
		data : json,
		success : function(response) {
		},
		error : function(response) {
			console.log(response);
		}
	});
};

function selectAll() {
	if ($("#selectAll").prop("checked") == false) {
		$(":checkbox").each(function() {
			$(this).prop("checked", false);
		});
		return;
	}
	
	$(":checkbox").each(function() {
		$(this).prop("checked", true);
	});
}

var count = 0;
var string = new Array;
var currentCompanyNumber;

function selectItems() {
	count = 0;
	string = new Array;
	$(":checkbox").each(function() {
		if ($(this).val() != "selectAll" && $(this).prop("checked")) {
			string[count] = $(this).val();
			count++;
		}
	});

	if (count == 0) {
		$('#msgModal h4').html("沒有選取資料");
		$('#msgModal').modal("show");
		return;
	}
	$("#editModal").modal('show');
	if (count > 1) {
		$("#now-count").text(1);
	} else {
		$("#now-count").text(count);
	}
	$("#total-count").text(count);
	
	$.ajax({
		type : "get",
		dataType : 'json',
		url : 'editAgentsDetail/' + string[0],
		contentType : "application/json",
		success : function(res) {
			var agents = res.agents;

			$('#guid').val(agents.guid);
			$('#selectEditGeneral').val(agents.bAgentsGuid);
			$('#serialNo').val(agents.serialNo);
			$('#account').val(agents.account);
			$('#password').val(agents.passwd);
			$('#companyName').val(agents.companyName);
			$('#uniformNumber').val(agents.uniformNumber);
			$('#companyPhone').val(agents.companyPhone);
			$('#companyAddress').val(agents.companyAddress);
			$('#contactName').val(agents.contactName);
			$('#contactAddress').val(agents.contactAddress);
			$('#contactMobile').val(agents.contactMobile);
			$('#contactEamil').val(agents.contactEamil);
			$('#remittanceBank').val(agents.remittanceBank);
			$('#remittanceAccount').val(agents.remittanceAccount);
			$('#remittanceName').val(agents.remittanceName);
			$('#remark').val(agents.remark);
		},
		error : function(res) {
			console.log(res);
		}
	});
}

function next() {
	var now_count = $("#now-count").text();
	var total_count = $("#total-count").text();
	if (now_count < total_count) {
		now_count++
		$("#now-count").text(now_count);
	}

	if (count > 0 && now_count <= total_count) {
		var jsonString = {
			"companyNumber" : string[now_count - 1]
		}
		var json = JSON.stringify(jsonString);

		$.ajax({
			type : "POST",
			dataType : 'json',
			url : 'selectCustomerByNumber',
			contentType : "application/json",
			data : json,
			success : function(response) {
				$('#company_number').val(response.customer.company_number);
				$('#company_phone').val(response.customer.company_phone);
				$('#apply_email').val(response.customer.apply_email);
				$('#company_name').val(response.customer.company_name);
				$('#apply_name').val(response.customer.apply_name);
				$('#apply_phone').val(response.customer.apply_phone);
				$('#company_address')
						.val(response.customer.company_address);
				$('#store_id').val(response.customer.store_id);
				$('#business_guid').val(response.customer.business_guid);
				$('#password').val(response.customer.password);
				$('#main_account').val(response.customer.main_account);
			},
			error : function(response) {
				console.log(response);
			}
		});
	}
}

function newModalDone() {
	var data = {
			'bAgentsGuid' : $('#selectGeneral').val(),
			'serialNo' : $('#newSerialNo').val(),
			'account' : $('#newAccount').val(),
			'passwd' : $('#newPassword').val(),
			'companyName' : $('#newCompanyName').val(),
			'uniformNumber' : $('#newUniformNumber').val(),
			'companyPhone' : $('#newCompanyPhone').val(),
			'companyAddress' : $('#newCompanyAddress').val(),
			'contactName' : $('#newContactName').val(),
			'contactAddress' : $('#newContactAddress').val(),
			'contactMobile' : $('#newContactMobile').val(),
			'contactEamil' : $('#newContactEamil').val(),
			'remittanceBank' : $('#newRemittanceBank').val(),
			'remittanceAccount' : $('#newRemittanceAccount').val(),
			'remittanceName' : $('#newRemittanceName').val(),
			'remark' : $('#newRemark').val()
		};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		contentType : "application/json",
		url : 'newAgents',
		data : JSON.stringify(data),
		success : function(res) {
			if(res.result == null || res.result == ""){
				$('#msgModal .modal-body').empty();
				$.each(res.message, function(index){
					$('#msgModal .modal-body').append("<center><h4 class='modal-title'>" 
							+res.message[index].message+ "</h4></center>");
				});
				$('#newModal').modal('hide');
				$('#msgModal').modal("show");
				return;
			}
			location.reload(true);
		},
		error : function(res) {
			console.log(res);
		}
	});
};

function editModalDone() {
	var data = {
		'guid' : string[0],
		'bAgentsGuid' : $('#selectEditGeneral').val(),
		'serialNo' : $('#serialNo').val(),
		'account' : $('#account').val(),
		'passwd' : $('#password').val(),
		'companyName' : $('#companyName').val(),
		'uniformNumber' : $('#uniformNumber').val(),
		'companyPhone' : $('#companyPhone').val(),
		'companyAddress' : $('#companyAddress').val(),
		'contactName' : $('#contactName').val(),
		'contactAddress' : $('#contactAddress').val(),
		'contactMobile' : $('#contactMobile').val(),
		'contactEamil' : $('#contactEamil').val(),
		'remittanceBank' : $('#remittanceBank').val(),
		'remittanceAccount' : $('#remittanceAccount').val(),
		'remittanceName' : $('#remittanceName').val(),
		'remark' : $('#remark').val()
	};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		url : 'updateAgents',
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(res) {
			if(res.result == null || res.result == ""){
				if($.isArray(res.message)){
					$('#msgModal .modal-body').empty();
					$.each(res.message, function(index){
						$('#msgModal .modal-body').append("<center><h4 class='modal-title'>" +res.message[index].message+ "</h4></center>");
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

function cancel(action){
	$(":checkbox").each(function() {
		$(this).prop("checked", false);
	});
	
	if(action == "newAgents"){
		$('#newModal').modal("show");
		return;
	}

	if(action == "editAgents"){
		$('#editModal').modal("show");
		return;
	}
};

function newAgentsView(){
	$.ajax({
		type : "get",
		dataType : 'json',
		url : 'angentsView',
		success : function(res) {
			$.each(res, function(index){
				$('#agentsBody tbody').append("<tr><td>"+ res[index].companyName +"</td><td>"+ res[index].companyPhone +"</td>"+
						"<td>"+ res[index].contactName +"</td><td>"+ res[index].contactMobile +"</td></tr>");
			});
			$('#newModal').modal("hide");
			$('#newAgentsModal').modal("show");
		},
		error : function(res) {
			console.log(res);
		}
	});
};

function editAgentsView(){
	$.ajax({
		type : "get",
		dataType : 'json',
		url : 'angentsView/' + $('#guid').val(),
		success : function(res) {
			console.log(res);
			$.each(res, function(index){
				$('#agentsBody tbody').append("<tr><td>"+ res[index].companyName +"</td><td>"+ res[index].companyPhone +"</td>"+
						"<td>"+ res[index].contactName +"</td><td>"+ res[index].contactMobile +"</td></tr>");
			});
			$('#editModal').modal("hide");
			$('#editAgentsModal').modal("show");
		},
		error : function(res) {
			console.log(res);
		}
	});
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
						<table style="position: relative; width: 100%; top: 26px">
							<tr>
								<td>
									<button type="button" class="btn btn-default btn-sm navbar-btn  customer-btn" data-toggle="modal" data-target="#newModal">新增</button>
									<button type="button" class="btn btn-default btn-sm navbar-btn  customer-btn" onclick="selectItems()">編輯</button>
								</td>
							</tr>
							<tr>
								<td>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th><input style="display: none" type="checkbox" id="selectAll" onclick="selectAll()"></th>
												<th>總代理</th>
												<th>公司</th>
												<th>電話</th>
												<th>聯絡人</th>
												<th>行動電話</th>
												<th>正式帳號</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${agentsMap}" var="getAgentsList">
												<c:if test="${getAgentsList.key == 'agentsList'}">
													<c:forEach items="${getAgentsList.value}" var="agents">
														<tr>
															<td><input type="checkbox" id="check" name="check" value="${agents.guid}"></td>
															
<!-- 															總代理名稱 -->
															<c:forEach items="${agentsMap}" var="getGeneral">
																<c:if test="${getGeneral.key == agents.guid}">
																	<td>${getGeneral.value}</td>
																</c:if>
															</c:forEach>
															
															<td>${agents.companyName}</td>
															<td>${agents.companyPhone}</td>
															<td>${agents.contactName}</td>
															<td>${agents.contactMobile}</td>
															
<!-- 														正式帳號數量 -->
															<c:forEach items="${agentsMap}" var="getCount">
																<c:if test="${getCount.key == 'effectiveCountMap'}">
																	<c:forEach items="${getCount.value}" var="count">
																		<c:if test="${count.key == agents.guid}">
																			<td>${count.value}</td>
																		</c:if>
																	</c:forEach>
																</c:if>
															</c:forEach>
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
	
	<!-- newModal -->
	<div id="newModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">新增個人帳號</h4>
					<label id="exist"></label>
				</div>
				<div class="modal-body scrollbar">
					<div class="input-group">
						<form class="form-horizontal" role="form">
							<button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancel()">返回</button>
							<button type="button" class="btn btn-default" onclick="newModalDone()">儲存</button>
							<button style="display: none" type="button" class="btn btn-default" onclick="newAgentsView()">代理商</button>
							<hr class="divider1">
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>＊總代理</label> 
								<div class="col-sm-7">
									<select id="selectGeneral" class="form-control">
										<option value="">請選擇</option>
										<c:forEach items="${agentsMap}" var="getGeneral">
											<c:if test="${getGeneral.key == 'generalList'}">
												<c:forEach items="${getGeneral.value}" var="general">
													<option value="${general.guid}">${general.companyName}</option>
												</c:forEach>
											</c:if>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>＊代理商識別碼</label>
								<div class="col-sm-7">
									<input id="newSerialNo" type="text" class="form-control" placeholder="代理商識別碼">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>＊代理商帳號</label>
								<div class="col-sm-7">
									<input id="newAccount" type="text" class="form-control" placeholder="代理商帳號">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>密碼</label>
								<div class="col-sm-7">
									<input id="newPassword" type="text" class="form-control" placeholder="密碼">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>公司名稱</label>
								<div class="col-sm-7">
									<input id="newCompanyName" type="text" class="form-control" placeholder="公司名稱">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>統一編號</label>
								<div class="col-sm-7">
									<input id="newUniformNumber" type="text" class="form-control" placeholder="統一編號">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>公司電話</label>
								<div class="col-sm-7">
									<input id="newCompanyPhone" type="text" class="form-control" placeholder="公司電話">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>公司地址</label>
								<div class="col-sm-7">
									<input id="newCompanyAddress" type="text" class="form-control" placeholder="公司地址">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>聯絡人姓名</label>
								<div class="col-sm-7">
									<input id="newContactName" type="text" class="form-control" placeholder="聯絡人姓名">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>行動電話</label>
								<div class="col-sm-7">
									<input id="newContactMobile" type="text" class="form-control" placeholder="行動電話">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>電子郵件</label>
								<div class="col-sm-7">
									<input id="newContactEamil" type="text" class="form-control" placeholder="電子郵件">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>匯款銀行</label>
								<div class="col-sm-7">
									<input id="newRemittanceBank" type="text" class="form-control" placeholder="匯款銀行">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>匯款帳號</label>
								<div class="col-sm-7">
									<input id="newRemittanceAccount" type="text" class="form-control" placeholder="匯款帳號">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>匯款名稱</label>
								<div class="col-sm-7">
									<input id="newremittanceName" type="text" class="form-control" placeholder="匯款名稱">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>備註</label>
								<div class="col-sm-7">
									 <textarea id="newRemark" class="form-control" rows="5" style="margin-left: 0px; margin-right: 0px; width: 350px;"></textarea>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
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
				<div class="modal-body scrollbar">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancel()">返回</button>
					<button type="button" class="btn btn-default" onclick="editModalDone()">儲存</button>
					<button style="display: none" type="button" class="btn btn-default" onclick="editAgentsView()">代理商</button>
					<hr class="divider1">
					<div class="input-group">
						<form class="form-horizontal" role="form">
							<input id="guid" style="display: none"/>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>＊總代理</label> 
								<div class="col-sm-7">
									<select id="selectEditGeneral" class="form-control">
										<option value="">請選擇</option>
										<c:forEach items="${agentsMap}" var="getGeneral">
											<c:if test="${getGeneral.key == 'generalList'}">
												<c:forEach items="${getGeneral.value}" var="general">
													<option value="${general.guid}">${general.companyName}</option>
												</c:forEach>
											</c:if>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>＊代理商識別碼</label>
								<div class="col-sm-7">
									<input id="serialNo" type="text" class="form-control" placeholder="代理商識別碼">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>＊代理商帳號</label>
								<div class="col-sm-7">
									<input id="account" type="text" class="form-control" placeholder="代理商帳號">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>密碼</label>
								<div class="col-sm-7">
									<input id="password" type="text" class="form-control" placeholder="密碼">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>公司名稱</label>
								<div class="col-sm-7">
									<input id="companyName" type="text" class="form-control" placeholder="公司名稱">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>統一編號</label>
								<div class="col-sm-7">
									<input id="uniformNumber" type="text" class="form-control" placeholder="統一編號">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>公司電話</label>
								<div class="col-sm-7">
									<input id="companyPhone" type="text" class="form-control" placeholder="公司電話">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>公司地址</label>
								<div class="col-sm-7">
									<input id="companyAddress" type="text" class="form-control" placeholder="公司地址">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>聯絡人姓名</label>
								<div class="col-sm-7">
									<input id="contactName" type="text" class="form-control" placeholder="聯絡人姓名">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>行動電話</label>
								<div class="col-sm-7">
									<input id="contactMobile" type="text" class="form-control" placeholder="行動電話">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>電子郵件</label>
								<div class="col-sm-7">
									<input id="contactEamil" type="text" class="form-control" placeholder="電子郵件">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>匯款銀行</label>
								<div class="col-sm-7">
									<input id="remittanceBank" type="text" class="form-control" placeholder="匯款銀行">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>匯款帳號</label>
								<div class="col-sm-7">
									<input id="remittanceAccount" type="text" class="form-control" placeholder="匯款帳號">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>匯款名稱</label>
								<div class="col-sm-7">
									<input id="remittanceName" type="text" class="form-control" placeholder="匯款名稱">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-5 control-label" style='text-align:center'>備註</label>
								<div class="col-sm-7">
									 <textarea id="remark" class="form-control" rows="5" style="margin-left: 0px; margin-right: 0px; width: 350px;"></textarea>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="modal-footer">
				</div>
			</div>
		</div>
	</div>
	
	<!-- newAgentsModal -->
	<div id="newAgentsModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div id="agentsBody" class="modal-body scrollbar">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancel('newAgents')">返回</button>
					<hr class="divider1">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>公司</th>
								<th>電話</th>
								<th>聯絡人</th>
								<th>行動電話</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- editAgentsModal -->
	<div id="editAgentsModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div id="agentsBody" class="modal-body scrollbar">
					<button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancel('editAgents')">返回</button>
					<hr class="divider1">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>公司</th>
								<th>電話</th>
								<th>聯絡人</th>
								<th>行動電話</th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- msgModal -->
	<div id="msgModal" class="modal fade" role="dialog">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<center><h4 class="modal-title"></h4></center>
				</div>
				<div class="modal-footer">
					<center><button type="button" class="btn btn-default" data-dismiss="modal" onclick="cancel()">取消</button></center>
				</div>
			</div>
		</div>
	</div>
</body>
</html>