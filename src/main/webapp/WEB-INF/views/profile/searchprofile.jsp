<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stalk Therap</title>
</head>
<body>
<form action="/profile/search" method="post">
    <input type="text" name="search" placeholder="Enter Username">
    <button class="btn btn-warning btn-sm" type="submit">Search</button>
    <br>
    <a href="/profile/stalk/${user.username}">
        <h3><b><c:out value="${user.firstName} ${user.lastName}" escapeXml="false"/></b></h3></a>
    <h5>${profile.designation}</h5>
</form>
<br>
${message}
<h2>Users :</h2>

<div class="well well-sm">
    <table>
        <c:forEach items="${userList}" var="userList">
            <tr>
                <td>
                    <img src="/profile/${userList.profile.imagePath}" class="img-circle" alt="Cinque Terre" width="100px" height="100px"/>
                </td>
                <td>
                    <a href="/profile/stalk/${userList.username}">
                        <h3><b><c:out value="${userList.firstName} ${userList.lastName}" escapeXml="false"/></b></h3>
                    </a>
                    <h5>${userList.profile.designation}</h5>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
