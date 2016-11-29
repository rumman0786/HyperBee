<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HyperBEE</title>
</head>
<body>
<form action="/profile/edit" method="post">
    <table>
        <tr>
            <th><h1>${authUser.username}::Profile</h1></th>
        </tr>
        <tr>
            <th>Designation :</th>
            <td>${profile.designation}</td>
        </tr>
        <tr>
            <th>Address :</th>
            <td>${profile.address}</td>
        </tr>
        <tr>
            <th>Contact No. :</th>
            <td>${profile.contactNo}</td>
        </tr>
        <tr>
            <th>Joining Date :</th>
            <td><fmt:formatDate type="date" value="${profile.joiningDate.time}" pattern="dd-MM-yy"/></td>
        </tr>
        <tr>
            <th><h2>Education :</h2></th>
        </tr>
        <tr>
            <th>School :</th>
            <td>${profile.school}</td>
        </tr>
        <tr>
            <th>College :</th>
            <td>${profile.college}</td>
        </tr>
        <tr>
            <th>University :</th>
            <td>${profile.university}</td>
        </tr>
        <tr>
            <th><h2>Basic Information :</h2></th>
        </tr>
        <tr>
            <th>Gender :</th>
            <td>${profile.gender}</td>
        </tr>
        <tr>
            <th>Date Of Birth :</th>
            <td><fmt:formatDate type="date" value="${profile.dateOfBirth.time}" pattern="dd-MM-yy"/></td>
        </tr>
        <tr>
            <th>Skills :</th>
            <td>${profile.skills}</td>
        </tr>
        <tr>
            <th><h2>Work Experience :</h2></th>
        </tr>
        <tr>
            <th>Years of Experience :</th>
            <td>${profile.jobExperienceYears}</td>
        </tr>
        <tr>
            <th>Work History :</th>
            <td>${profile.workHistory}</td>
        </tr>
        <tr>
            <th>Image :</th>
            <td>${profile.imagePath}</td>
        </tr>
        <tr>
            <td><input type="submit" value="Edit Profile"/></td>
        </tr>
    </table>
</form>
</body>
</html>
