<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${page}</title>
</head>
<body>
<div class="pre-scrollable set-activity-height">
    <c:forEach items="${selectedNoteList}" var="item">
        <form:form action="/note/delete/${item.getNoteTypeAsString()}/${item.id}" method="post">
            <div class="panel panel-warning" id="${item.id}">
                <div class="panel-heading clearfix">
                    <h4 class="panel-title pull-left" style="padding-top: 7.5px;"><strong>${item.title}</strong></h4>

                    <div class="btn-group pull-right">
                        <button class="btn btn-default btn-sm" type="submit"><fmt:message
                                key="notes.view.label.delete"/></button>
                    </div>
                </div>
                <div class="panel-body">${item.description}</div>
                <div class="panel-footer clearfix">
                    <div class="pull-right">
                        <small><i><cite>${item.noteType} ${item.getRemindDateFormatted()}</cite></i></small>
                    </div>
                </div>
            </div>
        </form:form>
    </c:forEach>
</div>
</body>
</html>
