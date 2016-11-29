<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
</head>
<body>

<c:forEach items="${topStickyNote}" var="item">
    <div class="panel panel-warning">
        <div class="panel-heading clearfix">
            <h4 class="panel-title pull-left" style="padding-top: 7.5px;"><strong>${item.title}</strong></h4>
        </div>
        <div class="panel-body">${item.description}</div>
        <div class="panel-footer clearfix">
            <div class="pull-right">
                <small><i><cite>${item.noteType} ${item.getRemindDateFormatted()}</cite></i></small>
            </div>
        </div>
    </div>
</c:forEach>

</body>
</html>
