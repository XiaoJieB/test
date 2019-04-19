<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>大作业评价系统</title>

    <script src="/js/sweet-alert.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/sweet-alert.css">

    <link rel="stylesheet" href="/css/bootstrap.css">
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.min.js"></script>

</head>
<body>
<div class="container">
    <h1>大作业评价系统-学生操作页面</h1>
    <hr/>
    <h2>当前登陆用户：${student.name}</h2>
    <hr/>
    <a href="/student/updateBigWork" type="button" class="btn btn-primary btn-sm">上交作业</a>
        <a href="/report/export" type="button" class="btn btn-primary btn-sm">作业浏览</a>
        <a href="/student/list" type="button" class="btn btn-primary btn-sm">互评</a>
        <a href="/report/export" type="button" class="btn btn-primary btn-sm">查看</a>
        <a href="/logout" type="button" class="btn btn-primary btn-sm">退出</a>
    </h3>

    <form action="#" method="post" id="addWorkForm">
        <div class="form-group">
            <label>课题名称:</label>
            <input type="text" class="form-control" id="name"
                   name="name" value="${student.bigWork.name}" readonly/>
        </div>
        <div class="form-group">
            <label>Git链接:</label>
            <input type="text" class="form-control" id="gitSrc" name="gitSrc" placeholder="Enter Nickname:"/>
            <input type="text" id="id" name="id" value="${student.bigWork.id}" style="display: none"/>
        </div>
        <div class="form-group">
            <label>项目链接:</label>
            <input type="text" class="form-control" id="projectSrc" name="projectSrc" placeholder="Enter FirstName:"/>
        </div>
        <div class="form-group">
            <label>项目图片:</label>
            <input type="text" class="form-control" id="imgSrc" name="imgSrc" placeholder="Enter LastName:"/>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-sm btn-success">提交</button>
            <button id="goBack" type="button" class="btn btn-sm btn-default">返回</button>
        </div>
    </form>

</div>
<script>
  $('#goBack').click(function(){
    window.location.href = "/student/list";
  })

  var form = $("#addWorkForm");
  $(function () {
    form.validate({
      submitHandler: function () {
          $.ajax({
            type: "POST",
            data:{"gitSrc":$("#gitSrc").val(),"projectSrc":$("#projectSrc").val(),"imgSrc":$("#imgSrc").val(),id:$("#id").val()},
            async: false,
            url: "/bigWork/update",
            success:function (data) {
              if(data.code == "0") {
                window.location.href = "/student/list";
              } else {
                swal("警告",data.msg,"error");
              }
            }
          });
        return false;
      },
      rules: {
        gitSrc:{
          required:true,
        },
        projectSrc:{
          required:true,
        },
        imgSrc:{
          required:true,
        }
      },
      messages: {
        gitSrc:{
          required:"请填写git链接",
        },
        projectSrc:{
          required:"请填写项目链接，若无写无",
        },
        imgSrc:{
          required:"请上传图片",
        }
      }
    });

  })
</script>

</body>
</html>

