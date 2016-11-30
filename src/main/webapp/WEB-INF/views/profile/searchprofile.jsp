<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="stalk.profile.title"/></title>
</head>
<body>
<form action="/profile/search" method="post">
    <input type="text" name="search" placeholder="Enter Username">
    <button class="btn btn-warning btn-sm" type="submit"><fmt:message key="search.button"/></button>
    <br>

    <div class="well well-sm">
        <table>
            <tr>
                <td>
                    <img src="/profile/image/${profile.imagePath}" class="img-circle" alt="Cinque Terre" width="100px"
                         height="100px"/>
                </td>
                <td>
                    <a href="/profile/stalk/${user.username}">
                        <h3><b><c:out value="${user.firstName} ${user.lastName}" escapeXml="false"/></b></h3></a>
                    </a>
                    <h5>${profile.designation}</h5>
                </td>
            </tr>
        </table>
    </div>
</form>
<br>
${message}
<div class="well well-sm">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3>User</h3>
        </div>
    </div>
    <c:if test="${authUser.isAdmin()}">
        <div class="panel-body">
            <ul class="list-group">
                <c:forEach items="${userList}" var="userList">
                    <li class="list-group-item">
                        <table>
                            <tr>
                                <td>
                                    <img src="/profile/image/${userList.profile.imagePath}" class="img-circle"
                                         alt="Cinque Terre"
                                         width="80px" height="80px"/>
                                </td>
                                <td>
                                    <a href="/profile/stalk/${userList.username}">
                                        <h4><b><c:out value="${userList.firstName} ${userList.lastName}"
                                                      escapeXml="false"/></b></h4>
                                    </a>

                                    <p>${userList.profile.designation}</p>

                                    <p>${userList.displayStatus}</p>
                                </td>
                            </tr>
                        </table>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:if test="${!authUser.isAdmin()}">
        <div class="panel-body">
            <ul class="list-group">
                <c:forEach items="${userList}" var="userList">
                    <li class="list-group-item">
                        <table>
                            <tr>
                                <td>
                                    <img src="/profile/image/${userList.profile.imagePath}" class="img-circle"
                                         alt="Cinque Terre"
                                         width="80px" height="80px"/>
                                </td>
                                <td>
                                    <a href="/profile/stalk/${userList.username}">
                                        <h4><b><c:out value="${userList.firstName} ${userList.lastName}"
                                                      escapeXml="false"/></b></h4>
                                    </a>

                                    <p>${userList.profile.designation}</p>
                                </td>
                            </tr>
                        </table>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</div>
</body>
</html>
