<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 11/22/16
  Time: 12:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>| Conference Room</title>
</head>

<body>
<%--<c:if test="${user.isSuperuser}">--%>
<a href="${pageContext.request.contextPath}${conferenceRoomAddUrl}" class="btn btn-success pull-right">Add Conference
    Room</a>
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

<!--Modal start -->
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
<!--Modal end -->

<script>
    $('.delete-user-item').on('click', function (e) {
        var criteria_id = $(this).attr("data-id");
        $('#delete_id').val(criteria_id);
    });
</script>
</body>