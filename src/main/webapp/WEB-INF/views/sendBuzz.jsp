<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Buzz</title>
</head>
<body>
    <form:form action="/sendBuzz" method="POST" modelAttribute="newBuzz">
        <form:label path="message">"Enter your message:</form:label>
        <form:input path="message"/>
        <input type="submit" value="Send"/>
    </form:form>
</body>
</html>
