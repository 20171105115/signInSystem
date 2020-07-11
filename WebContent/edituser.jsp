<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<jsp:include page="top.jsp"></jsp:include>

<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 编辑信息 ${ sessionScope.user.userName}</h2>
            <p class="lead"></p>
        </div>
        
        <h2>${requestScope.errorMsg }</h2>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <form id="admin-form" name="addForm" action="userServlet?option=edit" method="post">
                <input type="hidden" name="username" value="${sessionScope.user.userName}">
                
                    <div class="panel-body bg-light">
                        <div class="section-divider mt20 mb40">
                            <span> 基本信息 </span>
                        </div>
                        
                        <div class="section row">
                            <div class="col-md-6">
                                <label for="name" class="field prepend-icon">
                                    <input id="name" name="name" class="gui-input" placeholder="姓名..." type="text" />
                                    <label for="name" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="name" class="field prepend-icon">
                                    <input id="name" name="birthday" class="gui-input" placeholder="格式：年-月-日..." type="text" />
                                    <label for="name" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                            </div>
                        </div>
                        
                        <div class="section row">
                        	<div class="col-md-6">
                                <label for="name" class="field prepend-icon">
                                    <input id="name" name="userDesc" class="gui-input" placeholder="个人简介..." type="text" />
                                    <label for="name" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="sn" class="field select">
                                    <select id="departmentSn" name="gender" class="gui-input" placeholder="性别...">
                                    <option value="0" selected="selected">男</option>
                                    <option value="1">女</option>
                                    </select>
                                    <i class="arrow double"></i>
                                </label>
                            </div>
                           
                        </div>
                        <div class="panel-footer text-right">
                            <button type="submit" class="button"> 保存 </button>
                            <button type="button" class="button" onclick="javascript:window.history.go(-1);"> 返回 </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="bottom.jsp"></jsp:include>