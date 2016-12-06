<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${type} <fmt:message key="buzzToday.view.title"/></title>
</head>
<body>
<div class="container">
    <div class="panel panel-success">
        <div class="panel-heading">
            <h2>${type} <fmt:message key="buzzToday.view.title"/></h2>
            <h4><span class="label label-success"><fmt:message key="buzz.count.label"/> ${buzzList.size()}</span></h4>
        </div>

        <div class="panel-body">
            <table class="table table-bordered table-striped">
                <c:choose>
                    <c:when test="${buzzList.isEmpty()}">
                        <tr>
                            <td><fmt:message key="buzzToday.view.no.message"/></td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${buzzList}" var="buzz">
                            <tr>
                                <fmt:formatDate pattern="MM/dd/yyyy hh:mm a" value="${buzz.getBuzzTime()}"
                                                var="buzzDateTime"/>
                                <td>
                                    <c:out value="${buzz.user.username} [${buzzDateTime}]: ${buzz.message}"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>

        <div class="panel-footer">
            <ul class="pager">
                <li>
                    <a href="/user/dashboard"><span class="glyphicon glyphicon-home"/></a>
                </li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
