<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: rumman
  Date: 10/25/16
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="false" %>
<head>
    <title>| Notice Board</title>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <%--@ManyToMany(mappedBy = "noticeList")--%>
            <%--private List<Hive> hiveList;--%>

            <form:form class="form-signin" method="post" action="${pageContext.request.contextPath}${action}" modelAttribute="notice">
                <h2 class="form-signin-heading">${noticeHeader}</h2>

                <form:hidden path="id"/>

                <div class="form-group">
                    <label for="title" class="sr-only">Title</label>
                    <form:input type="text" id="title" class="form-control" placeholder="Notice Title" name="title"
                                path="title" required="required" autofocus="autofocus"/>
                    <%--<form:errors path="name" cssClass="error" />--%>
                </div>

                <div class="form-group">
                    <label for="description" class="sr-only">Description</label>
                    <form:input type="text" id="description" class="form-control" name="description" placeholder="Description"
                                path="description" required="required"/>
                    <%--<form:errors path="calories" cssClass="error" />--%>
                </div>

                <div class="form-group">
                    <label for="displayStatus" class="sr-only">Display Status</label>
                    <form:select path="displayStatus" id="displayStatus">
                        <form:options items="${displayStatusOptions}" itemValue="status" itemLabel="status" />
                    </form:select>
                </div>

                <div class="form-group">
                    <label for="dateExpired" class="sr-only">Expiry Date</label>
                    <form:input type="text" id="dateExpired" class="form-control" name="dateExpired" placeholder="dd-MM-yy"
                          path="dateExpired" required="required"/>
                </div>

                <div class="form-group">
                    <input class="btn btn-lg btn-primary btn-block" value="Save" type="submit"/>
                </div>
            </form:form>

        </div>
    </div>
</div>
<!-- /container -->
</body>
</html>
