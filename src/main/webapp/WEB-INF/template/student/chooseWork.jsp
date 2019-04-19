<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>大作业评价系统</title>

    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.js"></script>
</head>
<body>
<div class="container">
    <h1>大作业评价系统-学生操作页面</h1>
    <hr/>
    <h2>当前登陆用户：${student.name}</h2>
    <hr/>
    <h3>
        <a href="#" id="chooseWork" type="button" class="btn btn-primary btn-sm">选择课题</a>
        <c:if test="${student.bigWork != null}">
            <a href="/student/updateBigWork" type="button" id="upload" class="btn btn-primary btn-sm">上交作业</a>
            <a href="/work/list" type="button" id="assessByself" class="btn btn-primary btn-sm">自评</a>
        </c:if>
        <a href="/report/export" type="button" class="btn btn-primary btn-sm">作业浏览</a>
        <a href="/work/list" type="button" class="btn btn-primary btn-sm">互评</a>
        <a href="/report/export" type="button" class="btn btn-primary btn-sm">查看</a>
        <a href="/logout" type="button" class="btn btn-primary btn-sm">退出</a>
    </h3>

    <table class="table table-bordered table-striped">
        <tr>
            <th style="display: none">ID</th>
            <th>课题名称</th>
            <th>导师</th>
            <th>操作</th>
        </tr>

        <c:forEach items="${workList}" var="work">
            <tr>
                <td style="display: none">${work.id}</td>
                <td>${work.name}</td>
                <td>${work.teacher.name}</td>
                <td>
                    <a href="#" type="button" class="btn btn-sm btn-success view"
                       name="${work.id}">详情</a>
                    <c:if test="${work.studentId == null && student.bigWork == null}">
                        <a href="#" type="button" class="btn btn-sm btn-warning update"
                           name="${work.id}">选题</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<script>
  registerEvent();

  function registerEvent() {
    $(".view").on('click', function () {
      var id = this.name;
      $.ajax({
        type: "POST",
        data: {"workId": this.name},
        async: false,
        url: "/bigWork/view",
        success: function (data) {
          if (data.code == "0") {
            $("#name").val(data.name);
            $("#remark").val(data.remark);
            $("#myModalLabel").html("查看课题");
            $("button[type]:submit").attr("disabled","disabled");
            $("#myModal").modal('show');
          } else {
            swal("警告", data.msg, "error");
          }
        }
      });
    })
    $(".update").on('click', function () {
      var id = this.name;
      $.ajax({
        type: "POST",
        data: {"id": this.name,"studentId":${student.id}},
        async: false,
        url: "/bigWork/updateWorkBindStudent",
        success: function (data) {
          if (data.code == "0") {
             window.location.href = "/student/list";
          } else {
            swal("警告", data.msg, "error");
          }
        }
      });
    })
    if(${!student.bigWork.open}) {
      $("#upload").attr("disabled",true);
      $("#upload")[0].href="#";
    }
    if(${student.bigWork.projectSrc == null}) {
      $("#assessByself").attr("disabled",true);
      $("#assessByself")[0].href="#";
    }
  }
</script>
</body>
</html>

