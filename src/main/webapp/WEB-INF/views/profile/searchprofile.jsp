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
        <div class="panel-heading" style="padding-left: 30px;font-family: 'fontawesome'">
            <h1><fmt:message key="profile.user"/></h1>
        </div>
    </div>
    <c:if test="${authUser.isAdmin()}">
        <div class="panel-body">
            <ul class="list-group">
                <c:forEach items="${userList}" var="userList">
                    <c:if test="${userList.username != authUser.username}">
                        <li class="list-group-item">

                                <div class="row">
                                    <div class="col-sm-1">
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
                                    </div>
                                    <div class="col-sm-7 pull-left" style="padding-top: 10px">
                                        <a href="/profile/stalk/${userList.username}">
                                            <h4><b><c:out value="${userList.firstName} ${userList.lastName}"
                                                          escapeXml="false"/></b></h4>
                                        </a>

                                        <p>${userList.profile.designation}</p>
                                    </div>
                                    <div class="col-sm-4 pull-right" style="padding-left: 300px;padding-top: 30px">
                                        <c:forEach items="${userList.roleList}" var="role">
                                            <c:if test="${role.roleType == 'ADMIN'}">
                                                <p style="font-family: 'lucida grande'; color: #2aabd2">
                                                ${role.roleType}</p>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${userList.roleList.size() == 1}">
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
                                        </c:if>
                                    </div>
                                </div>

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
