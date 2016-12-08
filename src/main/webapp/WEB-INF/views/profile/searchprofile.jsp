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

    <div class="container">
        <table>
            <tr>
                <td>
                    <c:if test="${profile.imagePath != null}">
                        <c:choose>
                            <c:when test="${empty profile.imagePath}">
                                <img src="/images/dummyprofilepic.png" class="img-circle"
                                     alt="Cinque Terre"
                                     width="80px" height="80px"/>
                            </c:when>
                            <c:otherwise>
                                <img src="/profile/image/${profile.imagePath}" class="img-circle"
                                     alt="Cinque Terre"
                                     width="80px" height="80px"/>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${profile != null && profile.imagePath == null}">
                        <img src="/images/dummyprofilepic.png" class="img-circle"
                             alt="Cinque Terre"
                             width="80px" height="80px"/>
                    </c:if>
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
                    <c:if test="${userList.username != authUser.username}">
                        <li class="list-group-item">
                            <table>
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty userList.profile.imagePath}">
                                                <img src="/images/dummyprofilepic.png" class="img-circle"
                                                     alt="Cinque Terre"
                                                     width="80px" height="80px"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img src="/profile/image/${userList.profile.imagePath}"
                                                     class="img-circle"
                                                     alt="Cinque Terre"
                                                     width="80px" height="80px"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <a href="/profile/stalk/${userList.username}">
                                            <h4><b><c:out value="${userList.firstName} ${userList.lastName}"
                                                          escapeXml="false"/></b></h4>
                                        </a>

                                        <p>${userList.profile.designation}</p>
                                        <c:choose>
                                            <c:when test="${userList.displayStatus == 'ACTIVE'}">
                                                <p style="font-family: 'lucida grande';
                                                          color: green;
                                                          font-size: 15">
                                                ${userList.displayStatus}</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p style="font-family: 'lucida grande';
                                                          color: #ff0000;
                                                          font-size: 15">
                                                ${userList.displayStatus}</p>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:forEach items="${userList.roleList}" var="role" varStatus="loopStatus">
                                            <c:if test="${loopStatus.index == 1}">
                                                <p style="font-family: 'lucida grande'">${role.roleType}</p>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                            </table>
                        </li>
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:if test="${!authUser.isAdmin()}">
        <div class="panel-body">
            <ul class="list-group">
                <c:forEach items="${userList}" var="userList">
                    <c:if test="${userList.username != authUser.username}">
                        <li class="list-group-item">
                            <table>
                                <tr>
                                    <td>
                                        <c:choose>
                                            <c:when test="${empty userList.profile.imagePath}">
                                                <img src="/images/dummyprofilepic.png" class="img-circle"
                                                     alt="Cinque Terre"
                                                     width="80px" height="80px"/>
                                            </c:when>
                                            <c:otherwise>
                                                <img src="/profile/image/${userList.profile.imagePath}"
                                                     class="img-circle"
                                                     alt="Cinque Terre"
                                                     width="80px" height="80px"/>
                                            </c:otherwise>
                                        </c:choose>
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
                    </c:if>
                </c:forEach>
            </ul>
        </div>
    </c:if>
</div>
</body>
</html>
