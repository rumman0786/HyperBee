<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<div class="navbar navbar-inverse">
    <div class="nav navbar-header">
        <a class="navbar-brand">Test</a>
    </div>

    <ul class="nav navbar-nav">
        <li><a href="/user/create">Create new user</a></li>
        <li><a href="/user/notes">Notes</a></li>
        <li><a href="/buzz">Buzz!</a></li>
        <li><a href="/notice/list">Notice</a></li>
        <li><a href="/hive">Hive</a></li>
    </ul>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <h3>Sidebar Left</h3>
        </div>
        <div class="col-sm-8">
            <h3>
                Welcome to HyperBee!
            </h3>
        </div>
        <div class="col-sm-2">
            <h3>Sidebar Right</h3>
        </div>
    </div>
</div>

</body>
</html>
</body>
</html>
