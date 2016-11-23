<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: azim
  Date: 11/22/16
  Time: 11:53 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HyperBee</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div>
    <form:form action="/hive/post" method="post" commandName="hive" enctype="multipart/form-data">
        <h2>Create Hive</h2>
        <form:input path="name" type="text" name="hiveName" placeholder="Hive Name"/>
        <form:input path="description" type="text" placeholder="Hive Description"/>
        <input path="imagePath" type="file" name="fileUpload" size="50"/>
        <button type="submit" value="Upload">Create</button>
    </form:form>
</div>
</body>
</html>
