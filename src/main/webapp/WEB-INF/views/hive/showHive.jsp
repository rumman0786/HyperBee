<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HypeerBee-Hive Page</title>
</head>
<body>
<div class="container">
    <div class="jumbotron" style="background: image('/images/image.jpg')">
        <img src="localhost:8080/Web-INF/images/images.jpg">

        <h3>Welcome to ${hive.name}</h3>

        <p>${hive.description}</p>
    </div>
    <div>
        <ul class="nav nav-tabs">
            <li class="active"><a data-toggle="tab" href="post">Discussion</a></li>
            <li><a data-toggle="tab" href="#">Notice</a></li>
            <li><a data-toggle="tab" href="member">Member</a></li>
        </ul>
    </div>
    <br>

    <div class=" col-sm-8 tab-content">
        <div id="post" class="tab-pane fade in active">
            <form:form action="/user/hive/post/${hiveId}" method="POST" commandName="post">
                <div class="panel panel-success">
                    <div class="panel-heading clearfix">
                        <h4 class="panel-title pull-left" style="padding-top: 7.5px;">Add Post</h4>
                    </div>
                    <div class="panel-body">
                        <form:textarea type="text" placeholder="Description" class="form-control" path="description"/>
                    </div>
                    <div class="panel-footer clearfix">
                        <div class="btn-group pull-right">
                            <button class="btn btn-warning btn-sm" type="submit">Post</button>
                        </div>
                    </div>
                </div>
            </form:form>

            <c:forEach items="${postList}" var="item">
                <div class="panel panel-warning">
                    <div class="panel-heading clearfix">
                        <h4 class="panel-title pull-left" style="padding-top: 7.5px;">
                            <strong>${item.user.username}</strong></h4>
                    </div>
                    <div class="panel-body">${item.description}</div>
                    <div class="panel-footer clearfix">
                        <div class="pull-right">
                            <fmt:formatDate type="date" value="${item.dateCreated.time}" pattern="dd-MM-yy"/>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>


    <div class=" col-sm-8 tab-content">
        <div id="member" class="tab-pane fade">
            <div class="col-sm-4 table-responsive">
                <table class="table" border="1">
                    <thead>
                    <tr>
                        <th><b>Dish Name</b></th>
                    </tr>
                    </thead>

                    <c:forEach items="${enlistedUser}" var="user">
                        <tr>
                            <td><c:out value="${user.username}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            </div>
        </div>
</div>
</body>
</html>
