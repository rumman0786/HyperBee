<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HyperBee-Hive</title>
</head>
<body>
<p>Hive List</p>
<c:forEach items="${hiveList}" var="item">
    <a href="/user/hive/show/${item.id}"> <c:out value="${item.name}"/></a><br>
    <br>
    <br>
</c:forEach>

<div>
    <form:form action="/user/hive/create" method="post" commandName="hive" enctype="multipart/form-data">
        <h2>Create Hive</h2>
        <form:input path="name" type="text" name="hiveName" placeholder="Hive Name"/>
        <form:input path="description" type="text" placeholder="Hive Description"/>
        <input path="imagePath" type="file" name="fileUpload" size="50"/>
        <button type="submit" value="Upload">Create</button>
    </form:form>
</div>

</body>
</html>
