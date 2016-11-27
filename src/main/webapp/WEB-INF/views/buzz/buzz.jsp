<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>HyperBee::Buzz!</title>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <script src="/js/jquery-3.1.1.js"></script>
    <script src="/js/bootstrap.min.js"></script>

    <script>
        function refreshList() {
            $("#buzzList").load("/buzz/buzzList")
        }

        $(document).ready(function () {
            setInterval(refreshList, 500);

            $("#buzzMessageForm").submit(function() {
                $.ajax({
                  success: function(response) {
                      $("#buzzMain").append(response);
                  }
                });
            });
        });
    </script>
</head>

<body>
<div id = "buzzMain" class="panel panel-primary">
    <div class="panel-heading"><h4><b><i>Buzz!</i></b></h4></div>
    <div class="panel-body">
        <div id="buzzList"></div>

        <div id="buzzBar">
            <form:form id="buzzMessageForm" action="/buzz/sendBuzz" method="POST" modelAttribute="newBuzz">
                <form:input path="message" placeholder="Enter your message..."/>
                <input type="submit" value="Send"/>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
