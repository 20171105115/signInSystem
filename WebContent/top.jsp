<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>


<!-- Mirrored from admindesigns.com/demos/absolute/1.1/admin_forms-validation.html by HTTrack Website Copier/3.x [XR&CO'2014], Thu, 06 Aug 2015 02:56:15 GMT -->
<head>
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">

<title>欢迎使用签到系统</title>

<link rel="stylesheet" type="text/css"
	href="assets/skin/default_skin/css/theme.css">
<link rel="stylesheet" type="text/css"
	href="assets/admin-tools/admin-forms/css/admin-forms.css">
<link rel="shortcut icon" href="assets/img/favicon.ico">
</head>

<body class="admin-validation-page" data-spy="scroll"
	data-target="#nav-spy" data-offset="200">
	<div id="main">
		<header class="navbar navbar-fixed-top navbar-shadow">
			<div class="navbar-branding">
				<a class="navbar-brand" href="dashboard.html"> <b>Sign</b>
				</a> <span id="toggle_sidemenu_l" class="ad ad-lines"></span>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown menu-merge"><a href="#"
					class="dropdown-toggle fw600 p15" data-toggle="dropdown"> <img
						src="assets/img/avatars/5.jpg" alt="avatar" class="mw30 br64">
						<span class="hidden-xs pl15"> ${sessionScope.user.name} </span> <span
						class="caret caret-tp hidden-xs"></span>
				</a>
					<ul class="dropdown-menu list-group dropdown-persist w250"
						role="menu">
						<li class="list-group-item"><a href="userServlet?option=self"
							class="animated animated-short fadeInUp"> <span
								class="fa fa-user"></span> 个人信息 <span
								class="label label-warning"></span>
						</a></li>
						<li class="list-group-item"><a
							href="userServlet?option=toChangePassword"
							class="animated animated-short fadeInUp"> <span
								class="fa fa-gear"></span> 设置密码
						</a></li>
						<li class="dropdown-footer"><a href="userServlet?option=quit"
							class=""> <span class="fa fa-power-off pr5"></span> 退出
						</a></li>
					</ul></li>
			</ul>
		</header>
		<aside id="sidebar_left" class="nano nano-light affix">
			<div class="sidebar-left-content nano-content">
				<header class="sidebar-header">
					<div class="sidebar-widget author-widget">
						<div class="media">
							<a class="media-left" href="#"> <img
								src="assets/img/avatars/3.jpg" class="img-responsive">
							</a>
							<div class="media-body">
								<div class="media-author">${sessionScope.user.name}---
									<c:if test="${sessionScope.user.identity==0 }">
                            	学生
                            </c:if>
									<c:if test="${sessionScope.user.identity==1 }">
                           	    教师
                           </c:if>
								</div>
								<div class="media-links">
									<a href="userServlet?option=quit">退出</a>
								</div>
							</div>
						</div>
					</div>
					<div class="sidebar-widget search-widget hidden">
						<div class="input-group">
							<span class="input-group-addon"> <i class="fa fa-search"></i>
							</span> <input type="text" id="sidebar-search" class="form-control"
								placeholder="Search...">
						</div>
					</div>
				</header>
				<ul class="nav sidebar-menu">
					<li class="sidebar-label pt20">日常管理</li>
					<c:if test="${sessionScope.user.identity==1}">
						<li><a href="thingsServlet?option=findMineThings"> <span
								class="glyphicon glyphicon-book"></span> <span
								class="sidebar-title">待处理班级申请</span> <span
								class="sidebar-title-tray"> <span
									class="label label-xs bg-primary">New</span>
							</span>
						</a></li>
						<li class="active"><a
							href="checkServlet?option=findCheckList"> <span
								class="glyphicon glyphicon-home"></span> <span
								class="sidebar-title">签到管理</span>
						</a></li>
						<li><a href="checkServlet?option=toAdd"> <span
								class="fa fa-calendar"></span> <span class="sidebar-title">发起签到</span>
						</a></li>
					</c:if>
					<c:if test="${sessionScope.user.identity==0}">
						<li><a href="checkServlet?option=toCheck"> <span
								class="glyphicon glyphicon-book"></span> <span
								class="sidebar-title">待处理签到</span> <span
								class="sidebar-title-tray"> <span
									class="label label-xs bg-primary">New</span>
							</span>
						</a></li>

					</c:if>
					<li class="sidebar-label pt15">基础信息管理</li>
					<li><a class="accordion-toggle" href="#"> <span
							class="glyphicon glyphicon-check"></span> <span
							class="sidebar-title">个人信息管理</span> <span class="caret"></span>
					</a>
						<ul class="nav sub-nav">
							<li><a href="userServlet?option=toEdit"> <span
									class="glyphicon glyphicon-calendar"></span> 修改信息
							</a></li>
						</ul></li>
					<li><a class="accordion-toggle" href="#"> <span
							class="fa fa-columns"></span> <span class="sidebar-title">班级管理</span>
							<span class="caret"></span>
					</a>
						<ul class="nav sub-nav">
							<c:if test="${sessionScope.user.identity==1 }">
								<li><a href="gradeServlet?option=findGradeList"> <span
										class="glyphicon glyphicon-calendar"></span> 所创班级
								</a></li>
								<li class="active"><a href="gradeServlet?option=toAdd">
										<span class="glyphicon glyphicon-check"></span> 添加班级
								</a></li>
							</c:if>
							<c:if test="${sessionScope.user.identity==0 }">
								<li class="active"><a href="thingsServlet?option=toAdd">
										<span class="glyphicon glyphicon-check"></span> 加入班级
								</a></li>
								<li><a
									href="userServlet?option=findUserList&gradeId=${sessionScope.user.gradeId }">
										<span class="glyphicon glyphicon-calendar"></span> 我的班级
								</a></li>
							</c:if>
						</ul></li>
				</ul>
				<div class="sidebar-toggle-mini">
					<a href="#"> <span class="fa fa-sign-out"></span>
					</a>
				</div>
			</div>
		</aside>
		<section id="content_wrapper">