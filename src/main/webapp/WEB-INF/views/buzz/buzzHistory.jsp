<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title><fmt:message key="buzzHistory.view.title"/></title>
    <link href="/css/bootstrap.min.css" rel="stylesheet"/>
</head>

<body>
<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h2><fmt:message key="buzzHistory.view.title"/></h2>
        </div>

        <div class="panel-body">
            <div class="alert alert-info" style="display:inline-block">
                <fmt:message key="buzzHistory.view.label.pinned"/>
            </div>

            <div class="alert alert-warning" style="display:inline-block">
                <fmt:message key="buzzHistory.view.label.flagged"/>
            </div>

            <div class="alert alert-danger" style="display:inline-block">
                <fmt:message key="buzzHistory.view.label.inactive"/>
            </div>

            <table class="table table-striped">
                <c:forEach items="${buzzList}" var="buzz">
                    <c:choose>
                        <c:when test="${buzz.isFlagged()}">
                            <tr class="warning">
                        </c:when>

                        <c:when test="${buzz.isPinned()}">
                            <tr class="info">
                        </c:when>

                        <c:when test="${!buzz.isActive()}">
                            <tr class="danger">
                        </c:when>
                    </c:choose>

                    <td>
                        <c:out value="${buzz.user.username} [${buzz.buzzTime.getTime()}]: ${buzz.message}"/>
                    </td>

                    </tr>
                </c:forEach>
            </table>

            <div class="panel-footer">
                <ul class="pager">
                    <c:if test="${prev > 0}">
                        <li class="previous">
                            <a href="/buzz/buzzHistory?prev=${prev-20}&next=${prev}">
                                <fmt:message key="buzzHistory.view.label.previous"/>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${next == prev+20}">
                        <li class="next">
                            <a href="/buzz/buzzHistory?prev=${next}&next=${next+20}">
                                <fmt:message key="buzzHistory.view.label.next"/>
                            </a>
                        </li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>
</body>
</html>
