<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>欢迎使用签到管理系统</title>
    <meta name="keywords" content="HTML5 Bootstrap 3 Admin Template UI Theme" />
    <meta name="description" content="AbsoluteAdmin - A Responsive HTML5 Admin UI Framework">
    <meta name="author" content="AbsoluteAdmin">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="assets/skin/default_skin/css/theme.css">
    <link rel="stylesheet" type="text/css" href="assets/admin-tools/admin-forms/css/admin-forms.css">
    <link rel="shortcut icon" href="assets/img/favicon.ico">
</head>
<body class="external-page external-alt sb-l-c sb-r-c">
<div id="main" class="animated fadeIn">
    <section id="content_wrapper">
        <section id="content">
            <div class="admin-form theme-info mw500" id="login">
                <div class="content-header">
                    <h1> 注册OA</h1>
                    <p class="lead">欢迎注册签到自动化管理系统</p>
                </div>
                <div class="panel mt30 mb25">
                    <form method="post" action="" id="contact">
                        <div class="panel-body bg-light p25 pb15">
                            <div class="section">
                                <label for="sn" class="field-label text-muted fs18 mb10">账号</label>
                                <label for="sn" class="field prepend-icon">
                                    <input type="text" name="username" id="username" class="gui-input" placeholder="请输入您的邮箱地址...">
                                    <label for="sn" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                            </div>
                            <div class="section">
                                <label for="sn" class="field-label text-muted fs18 mb10">昵称</label>
                                <label for="sn" class="field prepend-icon">
                                    <input type="text" name="name" id="name" class="gui-input" placeholder="请输入昵称（建议使用真实姓名)...">
                                    <label for="password" class="field-icon">
                                        <i class="fa fa-lock"></i>
                                    </label>
                                </label>
                            </div>
                            <div class="section">
                                <label for="sn" class="field-label text-muted fs18 mb10">生日</label>
                                <label for="sn" class="field prepend-icon">
                                    <input type="text" name="birthday" id="birthday" class="gui-input" placeholder="请输入格式为年-月-日...">
                                    <label for="password" class="field-icon">
                                        <i class="fa fa-lock"></i>
                                    </label>
                                </label>
                            </div>
                            <div class="section">
                                <label for="sn" class="field-label text-muted fs18 mb10">性别</label>
                                <label for="sn" class="field prepend-icon">
                                    <!--  <input type="password" name="gender" id="password" class="gui-input" placeholder="请输入密码...">-->
                                    <select id="gender" name="gender" class="gui-input" placeholder="性别...">
                                    <option value="0" selected="selected">男</option>
                                    <option value="1">女</option>
                                    </select>
                                    <label for="password" class="field-icon">
                                        <!-- <i class="fa fa-lock"></i> -->
                                    </label>
                                </label>
                            </div>
                            <div class="section">
                                <label for="sn" class="field-label text-muted fs18 mb10">个人简介</label>
                                <label for="sn" class="field prepend-icon">
                                    <input type="text" name="userDesc" id="userDesc" class="gui-input" placeholder="请输入个人简介...">
                                    <label for="password" class="field-icon">
                                        <i class="fa fa-lock"></i>
                                    </label>
                                </label>
                            </div>
                        </div>
                        <div class="panel-footer clearfix">
                            <button type="button" onclick="sign()" class="button btn-primary mr10 pull-right">注册</button>
                            <label class="switch ib switch-primary mt10">
                                <input type="checkbox" name="remember" id="remember" checked="true">
                                <label for="remember" data-on="是" data-off="否"></label>
                                <span>记住我</span>
                            </label>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </section>
</div>
<script type="text/javascript">
		
		function sign() {
			var username = $("#username").val();
			var name = $("#name").val();
			var birthday = $("#birthday").val();
			var gender = $("#gender").val();
			var userDesc = $("#userDesc").val();
			var jsonData = {
					"username" : username,
					"name" : name,
					"birthday" : birthday,
					"gender" : gender,
					"userDesc" : userDesc
				};
			$.ajax({
				type : "post",
				//dataType : "text",
				url : "registerServlet",
				data : jsonData,
				success : function(res) {
					if (res==-1) {
						alert("注册失败，该账号已被注册，请重新输入账号")
						 window.location.href="register.jsp"
					}else if(res==1){
						alert("注册成功，将跳转到登录页面，初始密码为123456");
						 window.location.href="login.jsp"
					}
				},
				error : function(res) {
					alert("error")
				}
			})
		}
</script>
<script src="vendor/jquery/jquery-1.11.1.min.js"></script>
<script src="vendor/jquery/jquery_ui/jquery-ui.min.js"></script>
<script src="assets/js/utility/utility.js"></script>
<script src="assets/js/demo/demo.js"></script>
<script src="assets/js/main.js"></script>
</body>
</html>
