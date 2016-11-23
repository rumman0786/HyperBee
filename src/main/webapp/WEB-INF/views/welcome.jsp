<%@ page import="net.therap.hyperbee.web.security.AuthUser" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title></title>
</head>
<body>
<%
    AuthUser authUser = (AuthUser) request.getSession().getAttribute("authUser");
    response.getWriter().println(authUser.getUsername() + " " + authUser.getUserRole());
%>

    <a href="/user/create">Create new user</a>
    <a href="/user/all">Display all users</a>
    <a href="/user/find">Search for user</a>

    <a href="/buzz">Buzz!</a>
</body>
</html>
