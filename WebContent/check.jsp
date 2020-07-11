<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="top.jsp"></jsp:include>

<section id="content" class="table-layout animated fadeIn">
	<div class="tray tray-center">
		<div class="content-header">
			<h2>进行签到</h2>
			<p class="lead"></p>
		</div>
		<div class="admin-form theme-primary mw1000 center-block"
			style="padding-bottom: 175px;">
			<div class="panel heading-border">
				<form id="admin-form" name="addForm"
					action="checkServlet?option=check" method="post">
					<div class="panel-body bg-light">
						<div class="section-divider mt20 mb40">
							<span> 填写内容 </span>
						</div>
						<span> ${errorMsg } </span>
						<c:if test="${check.checkStatus==0 }">
							<div class="section row">
								<div class="col-md-6">
									<label for="sn" class="field prepend-icon"> <input
										id="sn" name="pwd" class="gui-input" placeholder="签到码"
										type="text" value="" /> <label for="sn" class="field-icon">
											<i class="fa fa-user"></i>
									</label>
									</label>
								</div>
								<div class="panel-footer text-right">
									<button type="submit" class="button">提交</button>
									<button type="button" class="button"
										onclick="javascript:window.history.go(-1);">返回</button>
								</div>
							</div>
						</c:if>
				</form>
			</div>
		</div>
	</div>
</section>

<jsp:include page="bottom.jsp"></jsp:include>