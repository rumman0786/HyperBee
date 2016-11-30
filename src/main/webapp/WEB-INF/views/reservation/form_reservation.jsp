<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 * @author rumman
 * @since 11/29/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<head>
    <title> Notice Board</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <form:form class="form-signin" method="post" action="${pageContext.request.contextPath}${action}" modelAttribute="reservation">
                <h2 class="form-signin-heading">${pageHeader}</h2>

                <form:hidden path="id"/>

                <div class="form-group">
                    <label for="reservationStatus">Display Status</label>
                    <form:select path="reservationStatus" id="reservationStatus">
                        <form:options items="${reservationStatusOptions}" itemValue="status" itemLabel="status" />
                    </form:select>
                </div>

                <div class="form-group input-group date" id='datetimepicker1'>
                    <label for="reservationFrom">Reservation From</label>
                    <form:input type="text" id="reservationFrom" class="form-control" name="reservationFrom"
                          path="reservationFrom" required="required" value="${reservation.getFormattedFromDate()}"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    <form:errors path="reservationFrom" cssClass="error" />
                </div>

                <div class="form-group input-group date" id='datetimepicker2'>
                    <label for="reservationTo">Reservation To</label>
                    <form:input type="text" id="reservationTo" class="form-control" name="reservationTo"
                                path="reservationTo" required="required" value="${reservation.getFormattedToDate()}"/>
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    <form:errors path="reservationTo" cssClass="error" />
                </div>

                <div class="form-group">
                    <label for="conferenceRoom">Conference Room</label>
                    <form:select path="conferenceRoom" id="conferenceRoom">
                        <form:options items="${roomList}" itemValue="id" itemLabel="title"/>
                    </form:select>
                    <form:errors path="conferenceRoom" cssClass="error" />
                </div>

                <div class="form-group">
                    <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
                </div>
            </form:form>

        </div>
    </div>
</div>
<!-- /container -->

<script>
    $(function () {
        $('#datetimepicker1').datetimepicker({
            minDate: moment()
        });

        $('#datetimepicker2').datetimepicker({
            minDate: moment()
        });
    });
</script>
</body>