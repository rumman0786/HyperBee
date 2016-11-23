<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
    <table>
        <tbody>
        <c:forEach items="${userList}" var="user" varStatus="loop">
            <tr>
                <td><c:out value="${user.username}"/></td>
                <td><c:out value="${user.email}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
