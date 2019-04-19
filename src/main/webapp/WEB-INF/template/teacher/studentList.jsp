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
    <link href="/css/jquery-accordion-menu.css" rel="stylesheet" type="text/css"/>
    <link href="/css/font-awesome.css" rel="stylesheet" type="text/css"/>

    <script src="/js/jquery.min.js"></script>
    <script type="text/javascript" src="/js/jquery.validate.js"></script>
    <script src="/js/bootstrap.js"></script>
    <script src="/js/sweet-alert.js"></script>
    <link rel="stylesheet" type="text/css" href="/css/sweet-alert.css">
    <script src="/js/jquery-accordion-menu.js" type="text/javascript"></script>

    <style type="text/css">
        * {
            box-sizing: border-box;
            -moz-box-sizing: border-box;
            -webkit-box-sizing: border-box;
        }

        body {
            background: #f0f0f0;
        }

        .content {
            width: 260px;
            margin: 20px auto;
        }

        #demo-list a {
            overflow: hidden;
            text-overflow: ellipsis;
            -o-text-overflow: ellipsis;
            white-space: nowrap;
            width: 100%;
        }
    </style>


</head>
<body>
<div class="row">
    <div class="col-md-4 col-md-offset-4">
        <h1>大作业评价系统-教师管理页面</h1>
        <hr/>
        <h2>当前登陆用户：${teacher.name}</h2>
        <hr/>
    </div>
</div>


<div class="row">
    <div class="col-md-3 content">
        <div id="jquery-accordion-menu" class="jquery-accordion-menu white">
            <div class="jquery-accordion-menu-header" id="form"></div>
            <ul id="demo-list">
                <li><a href="#"><i class="fa fa-file-image-o"></i>消息 </a>
                    <span class="jquery-accordion-menu-label">
                12 </span>
                </li>
                <li><a href="#"><i class="fa fa-cog"></i>学生管理</a>
                    <ul class="submenu">
                        <li><a href="/bigWork/findAllByTeacher">学生列表</a></li>
                        <li><a href="/bigWork/BigWorkControlList">上传控制</a></li>
                        <li><a href="#">评价控制</a></li>
                    </ul>
                </li>

                <li><a href="#"><i class="fa fa-cog"></i>信息管理</a>
                    <ul class="submenu">
                        <li><a href="#">发布信息</a></li>
                        <li><a href="#">修改信息</a></li>
                        <li><a href="#">删除信息</a></li>
                    </ul>
                </li>

                <li><a href="#"><i class="fa fa-cog"></i>评价统计</a>
                    <ul class="submenu">
                        <li><a href="#">评价学生</a></li>
                        <li><a href="#">统计</a></li>
                    </ul>
                </li>

                <li><a href="/logout"><i class="fa fa-file-image-o"></i>退出</a>
                </li>
            </ul>
            <div class="jquery-accordion-menu-footer">
                Footer
            </div>
        </div>
    </div>
    <div class="col-md-8  col-md-offset-1 container">
        <table class="table table-bordered table-striped">
            <tr>
                <th style="display: none">ID</th>
                <th>学生名称</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${studentList}" var="student">
                <tr>
                    <td style="display: none">${student.id}</td>
                    <td>${student.name}</td>
                    <td>
                        <a href="#" type="button" class="btn btn-sm btn-success update"
                           name="${student.bigWork.id}" open="false">评分</a>

                </tr>
            </c:forEach>
        </table>
    </div>
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

<script type="text/javascript">
  jQuery("#jquery-accordion-menu").jqueryAccordionMenu();
</script>

<script type="text/javascript">
  $(function () {
    var form = $("#addWorkForm");
    init();
    var url;

    function init() {
      initData();
      registerEvent();
    }

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
              , "selfScoreStr": str,"bigWorkId":this.name},
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
    }
    function registerEvent() {
      //顶部导航切换
      $("#demo-list li").click(function () {
        $("#demo-list li.active").removeClass("active")
        $(this).addClass("active");
      })

      $("#goBack").click(function () {
        $("#myModal").modal('hide');
      })
      $(".update").click(function () {
        $("#myModal").modal('show');
      })

    }

  })
</script>
</body>
</html>

