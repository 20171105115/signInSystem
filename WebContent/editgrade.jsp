<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="top.jsp"/>
<section id="content" class="table-layout animated fadeIn">
    <div class="tray tray-center">
        <div class="content-header">
            <h2> 编辑班级信息 </h2>
            <p class="lead"></p>
            <h2> ${requestScope.errorMsg } </h2>
        </div>
        <div class="admin-form theme-primary mw1000 center-block" style="padding-bottom: 175px;">
            <div class="panel heading-border">
                <form id="admin-form" name="addForm" action="gradeServlet?option=edit" method="post">
                <input type="hidden" name="gradeId" value="${requestScope.grade.gradeId }">
                    <div class="panel-body bg-light">
                        <div class="section-divider mt20 mb40">
                            <span> 基本信息 </span>
                        </div>
                        <div class="section row">
                            <div class="col-md-6">
                                <label for="sn" class="field prepend-icon">
                                    <input id="sn" name="gradeName" class="gui-input" placeholder="班级名称：${requestScope.grade.gradeName }" type="text"/>
                                    <label for="sn" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                            </div>
                            <div class="col-md-6">
                                <label for="name" class="field prepend-icon">
                                    <input id="name" name="gradeDesc" class="gui-input" placeholder="描述：${requestScope.grade.gradeDesc }" type="text"/>
                                    <label for="name" class="field-icon">
                                        <i class="fa fa-user"></i>
                                    </label>
                                </label>
                            </div>
                        </div>
                        <!--  <div class="section">
                            <label for="address" class="field prepend-icon">
                                <input id="address" name="address" class="gui-input" placeholder="地址..." type="text" value="星星大厦E座1201"/>
                                <label for="address" class="field-icon">
                                    <i class="fa fa-lock"></i>
                                </label>
                            </label>
                         </div>  -->
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

<jsp:include page="bottom.jsp"/>