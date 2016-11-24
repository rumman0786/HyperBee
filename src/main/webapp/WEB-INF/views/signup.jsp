<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="header.jsp" %>
<div class="container">
    <form:form class="form-signin" action="/signup" method="POST" modelAttribute="user">
        <h2 class="form-signin-heading">Please sign up</h2>
        <form:input type="text" class="form-control" path="username" placeholder="Username"/>
        <form:input type="email" class="form-control" path="email" placeholder="Email"/>
        <form:input type="password" class="form-control"  path="password" placeholder="Password"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
    </form:form>
</div>
<%@ include file="footer.jsp" %>