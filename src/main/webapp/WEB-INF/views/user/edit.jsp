<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<head>
    <title><fmt:message key="user.edit.title"/></title>
</head>
<body>
    <h4>Edit your account info:</h4>
    <form:form action="/user/edit" method="post" modelAttribute="userEdit">
        <form:errors element="div" path="*" cssClass="alert alert-danger fade in"/>
        <div class="form-group">
            <label for="username">Username</label>
            <form:input type="text" class="form-control" id="username" path="username"/>
        </div>
        <div class="form-group">
            <label for="firstName">First Name</label>
            <form:input type="text" class="form-control" id="firstName" path="firstName"/>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name</label>
            <form:input type="text" class="form-control" id="lastName" path="lastName"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <form:input type="email" class="form-control" id="email" path="email"/>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <form:input type="password" class="form-control" id="password" path="password"/>
        </div>
        <button type="submit" class="btn btn-default">Submit</button>
    </form:form>
</body>