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
				$('#storeNos').val('');
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
				var general = res.general;
				var agents = res.agents;
				var enterList = res.enterList;
				
				if(general != null){
					$('#editGeneral').html(general.companyName);
				}
				$('#editAgents').html(agents.companyName);
				
				var html = "";
			
				if(enterList != null){
					for (var i = 0; i < enterList.length; i++) {
						var enterprise = enterList[i];
						var date = new Date(enterprise.creatorDate);
						var month = date.getMonth()+1;
						var status = "";
						//帳號狀態 1：未使用 10：審核中 20：不通過 30：試用 40：無效 50：停權 60：有效
						if(enterprise.status == 1){
							status = "未使用";
		                }else if(enterprise.status == 10){
		                	status = "審核中";
		                }else if(enterprise.status == 20){
		                	status = "不通過";
		                }else if(enterprise.status == 30){
		                	status = "試用";
		                }else if(enterprise.status == 40){
		                	status = "無效";
		                }else if(enterprise.status == 50){
		                	status = "停權";
		                }else if(enterprise.status == 60){
		                	status = "有效";
		                }
						html += "<tr>";
						html += "<td>" + date.getFullYear()+"/"+month+"/"+date.getDate()+ "</td>";
						html += "<td>" + enterprise.storeNo + "</td>";
						html += "<td>" + status + "</td>";
						html += "</tr>";
					}
				}
				
				$("#editTable").html(html);
				$("#editModal").modal('show');
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
																<button type="button" class="btn btn-info" data-id="${agents.guid}" data-toggle="modal" id="edit">編輯</button>
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
					<hr class="divider1">
					<div class="input-group">
						<div class="form-group row">
							<label class="col-sm-4 control-label">總代理</label>
							<div class="col-sm-7">
								<label id="editGeneral"></label>
							</div>
						</div>
						<div class="form-group row">
							<label class="col-sm-4 control-label">代理商</label>
							<div class="col-sm-7"><!-- style="border-style:solid; width: 50%" -->
								<label id="editAgents"></label>
							</div>
						</div>
						<table style="position: relative; width: 100%; top: 5px;">
							<tr>
								<td>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th>設定日期</th>
												<th>特店編號</th>
												<th>狀態</th>
											</tr>
										</thead>
										<tbody id="editTable">
											<tr><c:out value="${enterList}"/></tr>
											<c:forEach items="${enterList}" var="enterprise">
												<tr>
													<td>enterprise.creatorDate</td>
													<td>enterprise.storeNo</td>
													<td>enterprise.status</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</td>
							</tr>
						</table>
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