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
<title>特店編號管理</title>
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

.row {
    position: relative;
}

.bottom-align-text {
   position: absolute;
   bottom: -1px;
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
var guid;
	
$(function() {
	$("body").on("click", ".btn-info", function() {
		guid = $(this).attr('data-id'); //抓取當前點擊元件的data-id
	});

	$("body").on("click", "#new", function() {
		$.ajax({
			type : "get",
			dataType : 'json',
			url : 'newAgentsStoreDetail/' + guid,
			success : function(res) {
				var general = res.general;
				var agents = res.agents;

				if(general != null){
					$('#general').html(general.companyName);
				}
				$('#agents').html(agents.companyName);
				$('#newSerialNo').val(agents.serialNo);
				$('#newBAgentsGuid').val(agents.guid);
			},
			error : function(res) {
				console.log(res);
			}
		});
	});

	$("body").on("click", "#edit", function() {
		$.ajax({
			type : "get",
			dataType : 'json',
			url : 'editAgentsStoreDetail/' + guid,
			success : function(res) {
				var agents = res.agents;

				$('#guid').val(agents.guid);
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
	});
});

function newModalDone() {
	var data = {
			'serialNo' : $('#newSerialNo').val(),
			'bAgentsGuid' : $('#newBAgentsGuid').val(),
			'storeNos' : $('#storeNos').val(),
		};
	
	$.ajax({
		type : "post",
		dataType : 'json',
		contentType : "application/json",
		url : 'newAgentsStore',
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
		'guid' : guid,
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
		url : 'updateAgentsStore',
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
												<th>管理</th>
												<th>級別</th>
												<th>名稱</th>
												<th>未開通</th>
												<th>試用中</th>
												<th>正式</th>
												<th>無效</th>
												<th>不通過</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${agentsStoreMap}" var="getAgents">
												<c:if test="${getAgents.key == 'agentsList'}">
													<c:forEach items="${getAgents.value}" var="agents">
														<tr>
															<td>
																<button type="button" class="btn btn-info" data-id="${agents.guid}" data-toggle="modal" data-target="#newModal" id="new">新增</button> /
																<button type="button" class="btn btn-info" data-id="${agents.guid}" data-toggle="modal" data-target="#editModal" id="edit">編輯</button>
															</td>
															
<!-- 															級別 -->
															<c:choose>
																<c:when test="${agents.bAgentsGuid == null}">
																	<td>總代理</td>
																</c:when>
																<c:otherwise>
																	<td>代理商</td>
																</c:otherwise>
															</c:choose>
															<c:if test="${agents.bAgentsGuid == null}">
															
															</c:if>
															
															<td>${agents.companyName}</td>
															
<!-- 														未開通帳號數量 -->
															<c:forEach items="${agentsStoreMap}" var="getCount">
																<c:if test="${getCount.key == 'nouseCountMap'}">
																	<c:forEach items="${getCount.value}" var="count">
																		<c:if test="${count.key == agents.guid}">
																			<td>${count.value}</td>
																		</c:if>
																	</c:forEach>
																</c:if>
															</c:forEach>
															
<!-- 														試用帳號數量 -->
															<c:forEach items="${agentsStoreMap}" var="getCount">
																<c:if test="${getCount.key == 'tryonCountMap'}">
																	<c:forEach items="${getCount.value}" var="count">
																		<c:if test="${count.key == agents.guid}">
																			<td>${count.value}</td>
																		</c:if>
																	</c:forEach>
																</c:if>
															</c:forEach>
															
<!-- 														正式帳號數量 -->
															<c:forEach items="${agentsStoreMap}" var="getCount">
																<c:if test="${getCount.key == 'effectiveCountMap'}">
																	<c:forEach items="${getCount.value}" var="count">
																		<c:if test="${count.key == agents.guid}">
																			<td>${count.value}</td>
																		</c:if>
																	</c:forEach>
																</c:if>
															</c:forEach>
															
<!-- 														無效帳號數量 -->
															<c:forEach items="${agentsStoreMap}" var="getCount">
																<c:if test="${getCount.key == 'invalidCountMap'}">
																	<c:forEach items="${getCount.value}" var="count">
																		<c:if test="${count.key == agents.guid}">
																			<td>${count.value}</td>
																		</c:if>
																	</c:forEach>
																</c:if>
															</c:forEach>
															
<!-- 														不通過帳號數量 -->
															<c:forEach items="${agentsStoreMap}" var="getCount">
																<c:if test="${getCount.key == 'failCountMap'}">
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
					<h4 class="modal-title">新增特店編號組</h4>
					<label id="exist"></label>
				</div>
				<div class="modal-body">
					<div class="input-group">
						<form>
							<button type="button" class="btn btn-default" data-dismiss="modal" onclick="">返回</button>
							<button type="button" class="btn btn-default" onclick="newModalDone()">儲存</button>
							<button style="display: none" type="button" class="btn btn-default" onclick="newAgentsView()">代理商</button>
							<hr class="divider1">
							<input id="newSerialNo" style="display: none"/>
							<input id="newBAgentsGuid" style="display: none"/>
							<div class="form-group row">
								<label class="col-sm-4 control-label">總代理</label>
								<div class="col-sm-7">
									<label id="general"></label>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-4 control-label">代理商</label>
								<div class="col-sm-7">
									<label id="agents"></label>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-sm-4 control-label">特店編號</label>
								<div class="col-sm-5">
									<input id="storeNos" type="text" class="form-control" placeholder="數量">
								</div>
								<label class="bottom-align-text">組</label>
							</div>
						</form>
					</div>
				</div>
<!-- 				<div class="modal-footer"></div> -->
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
					<button type="button" class="btn btn-default" data-dismiss="modal">返回</button>
					<button type="button" class="btn btn-default" onclick="editModalDone()">儲存</button>
					<hr class="divider1">
					<div class="input-group">
						<form class="form-horizontal" role="form">
							<input id="guid" style="display: none"/>
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
				<div class="modal-footer"></div>
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
					<center><button type="button" class="btn btn-default" data-dismiss="modal" onclick="">取消</button></center>
				</div>
			</div>
		</div>
	</div>
</body>
</html>