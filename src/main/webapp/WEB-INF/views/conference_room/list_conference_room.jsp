<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 11/22/16
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <%--<link rel="icon" href="../../favicon.ico">--%>

    <title>HyperBee | Notice Board</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <%--<link href="../../assets/css/ie10-viewport-bug-workaround.css" rel="stylesheet">--%>

    <!-- Custom styles for this template -->
    <%--<link href="/statics/css/dashboard.css" rel="stylesheet">--%>

    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><!--<script src="../../assets/js/ie8-responsive-file-warning.js"></script>--><![endif]-->
    <%--<script src="../../assets/js/ie-emulation-modes-warning.js"></script>--%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%--<c:if test="${user.isSuperuser}">--%>
    <a href="${pageContext.request.contextPath}${conferenceRoomAddUrl}" class="btn btn-success pull-right">Add Conference Room</a>
<%--</c:if>--%>
<div class="table-responsive">
    <table class="table table-striped" border="1">
        <thead>
        <tr>
            <th>Title</th>
            <th>Capacity</th>

            <%--<c:if test="${user.isSuperuser}">--%>
            <th>Edit</th>
            <th>Delete</th>
            <%--</c:if>--%>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="conferenceRoom" items="${conferenceRoomList}">
            <tr>
                <td>${conferenceRoom.title}</td>
                <td>${conferenceRoom.capacity}</td>

                    <%--<c:if test="${user.isSuperuser}">--%>
                <td><a href="${pageContext.request.contextPath}/conference/${conferenceRoom.id}/"><span
                        class="glyphicon glyphicon-edit"></span></a></td>
                <td><a href="#" data-id="${conferenceRoom.id}"
                       data-toggle="modal" data-target="#confirm-delete"
                       class="delete-user-item"><span
                        class="glyphicon glyphicon-trash"></span></a></td>
                    <%--</c:if>--%>

            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
</body>

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
            </div>

            <div class="modal-body">
                <p>You are about to delete a Conference Room, this procedure is irreversible.</p>

                <p>Do you want to proceed?</p>

                <p class="debug-url"></p>
            </div>

            <div class="modal-footer">
                <%--<a href="#" class="btn btn-danger btn-ok">Delete</a>--%>
                <form action="${deleteUrl}" method="post" id="form">
                    <input type="hidden" id="delete_id" name="id" value="#"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <input type="submit" class="btn btn-danger btn-ok" value="Delete"/>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="<%= request.getContextPath() %>/js/bootstrap.min.js"></script>

<script>
    $('.delete-user-item').on('click', function (e) {
        var criteria_id = $(this).attr("data-id");
        $('#delete_id').val(criteria_id);
    });
</script>


</html>
