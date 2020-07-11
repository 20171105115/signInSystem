<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="top.jsp"/>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 个人信息 </h2>
            <p class="lead"></p>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <div class="panel-body bg-light">
                    <div class="section-divider mt20 mb40">
                        <span> 基本信息 </span>
                    </div>
                    <div class="section row">
                        <div class="col-md-2">姓名</div>
                        <div class="col-md-4">${sessionScope.user.name}</div>
                        <div class="col-md-2">性别</div>
                        <c:if test="${sessionScope.user.gender==0 }">
                        	<div class="col-md-4">男</div>
                        </c:if>
                        <c:if test="${sessionScope.user.gender==1 }">
                        	<div class="col-md-4">女</div>
                        </c:if>
                    </div>
                    <div class="section row">
                        <div class="col-md-2">生日</div>
                        <div class="col-md-4">${sessionScope.user.birthday}</div>
                        <div class="col-md-2">个人简介</div>
                        <div class="col-md-4">${sessionScope.user.userDesc}</div>
                    </div>
                    <div class="panel-footer text-right">
                        <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="bottom.jsp"/>