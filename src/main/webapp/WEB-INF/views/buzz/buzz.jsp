<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>HyperBee::Buzz!</title>
    <script>
        function refreshList() {
            $("#buzzList").load("/buzz/buzzList")
        }

        $(document).ready(function () {
            setInterval(refreshList, 500);
        });
    </script>
</head>

<body>
<div id="buzzMain" class="panel panel-primary">
    <div class="panel-heading"><h3 style="font-family:'Helvetica Neue'"><b><i>Buzz!</i></b></h3></div>
    <div class="panel-body">
        <div id="buzzList"></div>
        <form:form id="buzzMessageForm" action="/buzz/sendBuzz" method="POST" modelAttribute="newBuzz">
            <form:input path="message" placeholder="Enter your message..." cssStyle="width: 93%"/>
            <input type="submit" class="btn btn-primary" value="Send"/>
        </form:form>
    </div>
</div>
</body>
</html>
