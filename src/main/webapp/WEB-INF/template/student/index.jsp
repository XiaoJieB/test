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
    <script type="text/javascript" src="/js/jquery.validate.js"></script>
    <script src="/js/sweet-alert.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/sweet-alert.css">
</head>
<body>
<div class="container">
    <h1>大作业评价系统-学生操作页面</h1>
    <hr/>
    <h2>当前登陆用户：${student.name}</h2>
    <hr/>
    <h3>
        <a href="/bigWork/chooseWork" id="chooseWork" type="button" class="btn btn-primary btn-sm">选择课题</a>
        <c:if test="${student.bigWork != null}">
            <a href="/student/updateBigWork" type="button" id="upload" class="btn btn-primary btn-sm">上交作业</a>
            <a href="#" type="button" id="assessByself" class="btn btn-primary btn-sm">自评</a>
        </c:if>
        <a href="/report/export" type="button" class="btn btn-primary btn-sm">作业浏览</a>
        <a href="/student/list" type="button" class="btn btn-primary btn-sm">互评</a>
        <a href="/report/export" type="button" class="btn btn-primary btn-sm">查看</a>
        <a href="/logout" type="button" class="btn btn-primary btn-sm">退出</a>
    </h3>

        <table class="table table-bordered table-striped">
            <tr>
                <th style="display: none">ID</th>
                <th>学号</th>
                <th>姓名</th>
                <th>作业</th>
                <th>分数</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${studentList}" var="student">
                <tr>
                    <td style="display: none">${student.id}</td>
                    <td>${student.number}</td>
                    <td>${student.name}</td>
                    <td>
                        <c:if test="${student.bigWork.projectSrc != null}">
                            <a href="${student.bigWork.projectSrc}" type="button" class="btn btn-sm btn-success"  target="_blank">浏览</a>
                        </c:if>
                    </td>
                    <td>${student.bigWork.score.selfScore}</td>
                    <td>
                        <c:choose>
                            <c:when test="${student.bigWork != null}">
                                <a href="#" type="button" class="btn btn-sm btn-warning update"
                                   name="${student.bigWork.id}">评分</a>
                            </c:when>
                            <c:otherwise>
                                <a href="#" type="button" class="btn btn-sm btn-warning update"
                                   name="${student.bigWork.id}" disabled="true">评分</a>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </c:forEach>
        </table>
</div>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">评分明细</h4>
            </div>
            <div class="modal-body">
                <form action="#" method="post" id="scoreForm">
                    <div class="form-group">
                        <label>结构设计:</label>
                        <input type="text" class="form-control" id="score1" name="score1"
                               placeholder="Enter Nickname:"/>
                    </div>
                    <div class="form-group">
                        <label>代码编写:</label>
                        <textarea type="text" class="form-control" id="score2" name="score2"
                                  placeholder="Enter FirstName:"></textarea>
                    </div>
                    <div class="form-group">
                        <label>界面设计:</label>
                        <textarea type="text" class="form-control" id="score3" name="score3"
                                  placeholder="Enter FirstName:"></textarea>
                    </div>
                    <div class="form-group">
                        <label>创新性:</label>
                        <textarea type="text" class="form-control" id="score4" name="score4"
                                  placeholder="Enter FirstName:"></textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-sm btn-primary">提交</button>
                        <button id="goBack" type="button" class="btn btn-sm btn-default">返回</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script>

    var form = $("#scoreForm");
    registerEvent();
    initData();

    function initData() {
      form.validate({
        submitHandler: function () {
          var score1 = parseFloat($("#score1").val());
          var score2 = parseFloat($("#score2").val());
          var score3 = parseFloat($("#score3").val());
          var score4 = parseFloat($("#score4").val());
          var total = score1 + score2 + score3 + score4;
          var str = score1 + "," + score2 + "," + score3 + "," + score4;
          $.ajax({
            type: "POST",
            data: {"selfScore": total
              , "selfScoreStr": str,"bigWorkId":${student.bigWork.id}},
            async: false,
            url: "/score/save",
            success: function (data) {
              if (data.code == "0") {
                window.location.href = "/student/list";
              } else {
                swal("警告", data.msg, "error");
              }
            }
          });
          return false;
        },
        rules: {
          score1: {
            required: true,
          },
          score2: {
            required: true,
          },
          score3: {
            required: true,
          },
          score4: {
            required: true,
          },
        },
        messages: {
          score1: {
            required: "分数不能为空",
          },
          score2: {
            required: "分数不能为空",
          },
          score3: {
            required: "分数不能为空",
          },
          score4: {
            required: "分数不能为空",
          },
        }
      });
      if(${!student.bigWork.open}) {
        $("#upload").attr("disabled",true);
        $("#upload")[0].href="#";
      }
      if(${student.bigWork.projectSrc == null}) {
        $("#assessByself").attr("disabled",true);
        $("#assessByself")[0].href="#";
      }
    }
    function registerEvent() {
      $("#assessByself").click(function () {
        $("#myModal").modal('show');
      })
      $("#goBack").click(function () {
        $("#myModal").modal('hide');
      })
        $(".update").click(function () {
          $("#myModal").modal('show');
        })
    }
</script>
</body>
</html>

