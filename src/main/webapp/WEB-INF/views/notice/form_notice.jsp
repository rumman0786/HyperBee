<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
 * @author rumman
 * @since 11/22/16
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<head>
    <title>| Notice Board</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <form:form class="form-signin" method="post" action="${pageContext.request.contextPath}${action}"
                       modelAttribute="notice">
                <h2 class="form-signin-heading">${noticeHeader}</h2>

                <form:hidden path="id"/>

                <div class="form-group">
                    <label for="title">Title</label>
                    <form:input type="text" id="title" class="form-control" placeholder="Notice Title" name="title"
                                path="title" required="required" autofocus="autofocus"/>
<<<<<<< HEAD
                    <form:errors path="title" cssClass="alert alert-danger" />
||||||| merged common ancestors
                    <form:errors path="title" cssClass="error" />
=======
                    <form:errors path="title" cssClass="error"/>
>>>>>>> feature/hyperbee-duity
                </div>

                <div class="form-group">
<<<<<<< HEAD
                    <label for="description">Description</label>
                    <form:textarea class="form-control" rows="5" id="description" path="description" name="description" required="required"></form:textarea>
                    <form:errors path="description" cssClass="alert alert-danger" />
||||||| merged common ancestors
                    <label for="description" class="sr-only">Description</label>
                    <form:input type="text" id="description" class="form-control" name="description" placeholder="Description"
                                path="description" required="required"/>
                    <form:errors path="description" cssClass="error" />
=======
                    <label for="description" class="sr-only">Description</label>
                    <form:input type="text" id="description" class="form-control" name="description"
                                placeholder="Description"
                                path="description" required="required"/>
                    <form:errors path="description" cssClass="error"/>
>>>>>>> feature/hyperbee-duity
                </div>

                <div class="form-group">
<<<<<<< HEAD
                    <label for="displayStatus">Display Status</label>
                    <form:select path="displayStatus" id="displayStatus" class="form-control">
                        <form:options items="${displayStatusOptions}" itemValue="status" itemLabel="status" />
||||||| merged common ancestors
                    <label for="displayStatus" class="sr-only">Display Status</label>
                    <form:select path="displayStatus" id="displayStatus">
                        <form:options items="${displayStatusOptions}" itemValue="status" itemLabel="status" />
=======
                    <label for="displayStatus" class="sr-only">Display Status</label>
                    <form:select path="displayStatus" id="displayStatus">
                        <form:options items="${displayStatusOptions}" itemValue="status" itemLabel="status"/>
>>>>>>> feature/hyperbee-duity
                    </form:select>
                </div>

                <div class="form-group input-group date" id='datetimepicker1'>
<<<<<<< HEAD
                    <label for="dateExpired">Expiry Date</label>
                    <form:input onkeydown="return false;" type="text" id="dateExpired" class="form-control" name="dateExpired" placeholder="dd-MM-yy"
                          path="dateExpired" required="required"/>
||||||| merged common ancestors
                    <label for="dateExpired" class="sr-only">Expiry Date</label>
                    <form:input type="text" id="dateExpired" class="form-control" name="dateExpired" placeholder="dd-MM-yy"
                          path="dateExpired" required="required"/>
=======
                    <label for="dateExpired" class="sr-only">Expiry Date</label>
                    <form:input type="text" id="dateExpired" class="form-control" name="dateExpired"
                                placeholder="dd-MM-yy"
                                path="dateExpired" required="required"/>
>>>>>>> feature/hyperbee-duity
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
<<<<<<< HEAD
                    <form:errors path="dateExpired" cssClass="alert alert-danger" />
||||||| merged common ancestors
                    <form:errors path="dateExpired" cssClass="error" />
=======
                    <form:errors path="dateExpired" cssClass="error"/>
>>>>>>> feature/hyperbee-duity
                </div>

                <div class="form-group">
                    <label for="hiveList">Hives</label>
                    <form:select multiple="true" path="hiveList" id="hiveList" class="form-control">
                        <form:options items="${hiveList}" itemValue="id" itemLabel="name"/>
                    </form:select>
<<<<<<< HEAD
                    <form:errors path="hiveList" cssClass="alert alert-danger" />
||||||| merged common ancestors
                    <form:errors path="dateExpired" cssClass="error" />
=======
                    <form:errors path="dateExpired" cssClass="error"/>
>>>>>>> feature/hyperbee-duity
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
            minDate: moment(),
            format: 'DD-MM-YY'
        });
    });
</script>
</body>