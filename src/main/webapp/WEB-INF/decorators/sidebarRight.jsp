<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="col-sm-2 sidenav top-padding text-left">

    <h2><fmt:message key="notice.sidebar.header"/></h2>

    <c:forEach var="notice" items="${cachedNoticeList}">
        <div class="panel-group">

            <div class="panel panel-info">
                <div class="panel-heading">${notice.title}</div>
                <div class="panel-body">${notice.description}</div>
                <div class="panel-footer text-right">Expires On ${notice.getRemindDateFormatted()}</div>
            </div>

        </div>
    </c:forEach>

</div>
