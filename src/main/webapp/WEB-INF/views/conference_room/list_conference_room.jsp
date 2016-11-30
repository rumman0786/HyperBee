<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
 * @author rumman
 * @since 11/22/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>| Conference Room</title>
</head>

<body>
<<<<<<< HEAD
<c:if test="${authUser.isAdmin()}">
    <a href="${pageContext.request.contextPath}${conferenceRoomAddUrl}" class="btn btn-success pull-right">Add Conference Room</a>
</c:if>
||||||| merged common ancestors
<%--<c:if test="${user.isSuperuser}">--%>
    <a href="${pageContext.request.contextPath}${conferenceRoomAddUrl}" class="btn btn-success pull-right">Add Conference Room</a>
<%--</c:if>--%>
=======
<%--<c:if test="${user.isSuperuser}">--%>
<a href="${pageContext.request.contextPath}${conferenceRoomAddUrl}" class="btn btn-success pull-right">Add Conference
    Room</a>
<%--</c:if>--%>
>>>>>>> feature/hyperbee-duity
<div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
            <th>Title</th>
            <th>Capacity</th>

            <c:if test="${authUser.isAdmin()}">
                <th>Edit</th>
                <th>Delete</th>
            </c:if>

        </tr>
        </thead>
        <tbody>

        <c:forEach var="conferenceRoom" items="${conferenceRoomList}">
            <tr>
                <td>${conferenceRoom.title}</td>
                <td>${conferenceRoom.capacity}</td>

                <c:if test="${authUser.isAdmin()}">
                    <td><a href="${pageContext.request.contextPath}/conference/${conferenceRoom.id}/"><span
                            class="glyphicon glyphicon-edit"></span></a></td>
                    <td><a href="#" data-id="${conferenceRoom.id}"
                           data-toggle="modal" data-target="#confirm-delete"
                           class="delete-user-item"><span
                            class="glyphicon glyphicon-trash"></span></a></td>
                </c:if>

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