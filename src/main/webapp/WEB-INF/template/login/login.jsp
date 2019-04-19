<%--
  Created by IntelliJ IDEA.
  User: 19414
  Date: 2019/3/11
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>login.jsp</title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script src="js/sweet-alert.js"></script>
    <link rel="stylesheet" type="text/css" href="css/sweet-alert.css">
    <script src="js/select2.js"></script>
    <link rel="stylesheet" type="text/css" href="css/select2.css">
    <script type="text/javascript" src="js/jquery.form.js"></script>
    <script type="text/javascript" src="js/jquery.validate.min.js"></script>
    <style>
        .error {
            color:red;
        }
        #container {
            margin: 0 auto;
            width: 60%;
            padding: 125px 20px;
        }

        span {
            font-size: 20px;
        }
        #container .login {
            width: 404px;
            margin: 0 auto;
        }

        #container .login h1 {
            text-align: center;
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
            color: #444;
        }

        h1 {
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
            color: #444;
            margin-bottom: 30px;
        }

        .textbox {
            width: 100%;
        }

        .textbox input[type=text], input[type=password] {
            display: block;
            margin-bottom: 16px;
            width: 380px;
            padding: 10px;
            font-size: 18px;
            border: 1px solid #e1e8ed;
            border-radius: 3px;
        }

        .textbox .signUp {
            width: 384px;
            padding: 12px;
            font-size: 18px;
            display: block;
            color: white;
            font-size: 18px;
            text-align: center;
            background-color: #8cc3ea;
            border-radius: 5px;
            cursor: pointer;
            letter-spacing: 5px;
            font-family: "Arial", "Microsoft YaHei", "黑体", "宋体", sans-serif;
        }
    </style>
</head>
<body>
<div id="container">
    <form action="login.action" method="post" id="loginForm">
        <div class="login">
            <h1>大作业评价系统</h1>
            <div class="textbox">
                <select id="type">
                    <option value ="0">学生</option>
                    <option value ="1">老师</option>
                </select>
                <input type="text" name="number" id="number" placeholder="学号/教师号"/><br>
                <input type="password" name="password" id="password" placeholder="密码"/>
                <input type="submit" class="signUp" value="提交"/>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
  var form = $("#loginForm");
  $(function () {
    $("#type").select2({
      width: "380px"
    });
    form.validate({
      submitHandler: function () {
        if ($('#type').val() == "0") {
          $.ajax({
            type: "POST",
            data: {"number": $("#number").val(), "password": $("#password").val()},
            async: false,
            url: "/student/login.action",
            success: function (data) {
              if (data.code == "0") {
                window.location.href = "/student/list";
              } else {
                swal("警告", data.msg, "error");
              }
            }
          });
        } else {
          $.ajax({
            type: "POST",
            data: {"number": $("#number").val(), "password": $("#password").val()},
            async: false,
            url: "/teacher/login.action",
            success: function (data) {
              if (data.code == "0") {
                window.location.href = "/teacher/list";
              } else {
                swal("警告", data.msg, "error");
              }
            }
          });
        }
        return false;
      },
      rules: {
        number: {
          required: true,
          minlength: 3,
          maxlength: 3
        },
        password: {
          required: true,
        }
      },
      messages: {
        number: {
          required: "学号/教师号不能为空",
          minlength: "请输入正确的学号/教师号",
          maxlength: "请输入正确的学号/教师号",
        },
        password: {
          required: "必填",
          // isMobile : "请正确填写手机号码"
        }
      }
    });

  })

</script>
</body>
</html>
