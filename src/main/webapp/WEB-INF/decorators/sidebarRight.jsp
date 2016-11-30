<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<div class="col-sm-2 sidenav top-padding text-left">
    <div class="well">
        <c:forEach var="notice" items="${cachedNoticeList}">
                <div>${notice.title}</div>
        </c:forEach>
    </div>
</div>
</html>
