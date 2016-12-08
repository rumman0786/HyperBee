<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<body>
<div class="pre-scrollable">
    <table class="table table-striped table-bordered">
        <c:forEach items="${pinnedBuzzList}" var="buzz">
            <fmt:formatDate pattern="MM/dd/yyyy hh:mm a" value="${buzz.getBuzzTime()}" var="buzzDateTime"/>
            <c:choose>
                <c:when test="${buzz.isFlagged()}">
                    <tr class="info">
                        <td>
                            <c:if test="${authUser.isAdmin()}">
                                <form action="/buzz/flagBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-flag"/>
                                    </button>
                                </form>

                                <form action="/buzz/pinBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-pushpin"/>
                                    </button>
                                </form>
                            </c:if>
                            <font color="#191970"><i><fmt:message key="buzz.view.label.flagMessage"/></i></font>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr class="info">
                        <td>
                            <c:if test="${authUser.isAdmin()}">
                                <form action="/buzz/flagBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-flag"/>
                                    </button>
                                </form>

                                <form action="/buzz/pinBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-pushpin"/>
                                    </button>
                                </form>
                            </c:if>
                            <i><font color="#191970"><fmt:message key="buzz.view.label.pinnedTag"/></font></i>
                            <c:out value="${buzz.user.username} [${buzzDateTime}]: ${buzz.message}"/>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:forEach items="${buzzList}" var="buzz">
            <fmt:formatDate pattern="MM/dd/yyyy hh:mm a" value="${buzz.getBuzzTime()}" var="buzzDateTime"/>
            <c:choose>
                <c:when test="${buzz.isFlagged()}">
                    <tr>
                        <td>
                            <c:if test="${authUser.isAdmin()}">
                                <form action="/buzz/flagBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-flag"/>
                                    </button>
                                </form>

                                <form action="/buzz/deactivateBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-remove"/>
                                    </button>
                                </form>
                            </c:if>
                            <font color="#191970"><i><fmt:message key="buzz.view.label.flagMessage"/></i></font>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            <c:if test="${authUser.isAdmin()}">
                                <form action="/buzz/flagBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-flag"/>
                                    </button>
                                </form>

                                <form action="/buzz/pinBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-pushpin"/>
                                    </button>
                                </form>

                                <form action="/buzz/deactivateBuzz?id=${buzz.id}" method="post" style="display:inline-block">
                                    <button type="submit" class="btn btn-link" style="display:inline-block">
                                        <span class="glyphicon glyphicon-remove"/>
                                    </button>
                                </form>
                            </c:if>
                            <c:out value="${buzz.user.username} [${buzzDateTime}]: ${buzz.message}"/>
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </table>
</div>
</body>
</html>
