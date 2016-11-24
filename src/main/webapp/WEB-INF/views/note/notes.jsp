<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>All Notes</title>
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>

<div class="container">
    <div class="panel panel-success">
        <div class="panel-heading clearfix">
            <h4 class="panel-title pull-left" style="padding-top: 7.5px;">Add Note</h4>
        </div>
        <div class="panel-footer">
            <form:form action="/user/note/save" method="post" commandName="noteCommand">
                <div class="input-group">
                    <form:input id="address" type ="text" placeholder="Add Note" class="form-control"
                            path="description"/>
                <span class="input-group-btn">
                    <button class="btn btn-warning" type="submit" id="addressSearch">Save</button>
                </span>
                </div>
            </form:form>
        </div>
    </div>

    <c:forEach items="${noteList}" var="item">

        <form:form action="/note/delete/${item.id}" method="post">
        <div class="panel panel-warning">
            <div class="panel-heading clearfix">
                <h4 class="panel-title pull-left" style="padding-top: 7.5px;">${item.title}</h4>

                <div class="btn-group pull-right">
                    <button class="btn btn-default btn-sm" type="submit">Delete</button>
                </div>
            </div>
            <div class="panel-body">${item.description}</div>
        </div>
        </form:form>

    </c:forEach>
</div>
<body>

</body>
</html>
