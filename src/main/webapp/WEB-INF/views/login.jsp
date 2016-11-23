<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ include file="header.jsp" %>
<div class="container">
    <div class="page-header">
        <h1>Welcome to Meal Planner</h1>
    </div>
    <form:form class="form-signin" action="/user/login" method="POST" modelAttribute="user">
        <h2 class="form-signin-heading">Please log in</h2>
        <form:errors path="*" element="div" cssClass="alert alert-danger fade in"/>
        <label for="inputEmail" class="sr-only">Username</label>
        <form:input type="text" id="inputEmail" name="email" class="form-control" placeholder="Email address"
                    path="username"/>
        <label for="inputPassword" class="sr-only">Password</label>
        <form:input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password"
                    path="password"/>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
        <br>

        <div class="center-block">
            <a href="/user/signup">Sign up?</a>
        </div>
    </form:form>
</div>
<%@ include file="footer.jsp" %>