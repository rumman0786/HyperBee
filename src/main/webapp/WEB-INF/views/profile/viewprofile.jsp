<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HyperBEE</title>
</head>
<body>
<div class="container">
    <img src="/profile/${profile.imagePath}" class="img-circle" alt="Cinque Terre" width="200px" height="200px"/>
</div>
<form action="/profile/edit" method="post">
    <table>
        <tr>
            <th class="panel-heading">
                <h1 style="color: #269abc; font-family: 'Glyphicons Halflings'">
                    <b>${user.firstName} ${user.lastName}</b>
                </h1>
            </th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Designation :</th>
            <td class="form-control">${profile.designation}</td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Address :</th>
            <td class="form-control">${profile.address}</td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Contact No. :</th>
            <td class="form-control">${profile.contactNo}</td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Joining Date :</th>
            <td class="form-control"><fmt:formatDate type="date" value="${profile.joiningDate.time}" pattern="dd-MM-yy"/></td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc">Education :</h2></th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">School :</th>
            <td class="form-control">${profile.school}</td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">College :</th>
            <td class="form-control">${profile.college}</td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">University :</th>
            <td class="form-control">${profile.university}</td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc">Basic Information :</h2></th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Gender :</th>
            <td class="form-control">${profile.gender}</td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Date Of Birth :</th>
            <td class="form-control"><fmt:formatDate type="date" value="${profile.dateOfBirth.time}" pattern="dd-MM-yy"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Skills :</th>
            <td class="form-control">${profile.skills}</td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc">Work Experience :</h2></th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Years of Experience :</th>
            <td class="form-control">${profile.jobExperienceYears}</td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Work History :</th>
            <td class="form-control">${profile.workHistory}</td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Image :</th>
            <td class="form-control">${profile.imagePath}</td>
        </tr>
        <tr>
            <td><input type="submit" class="btn btn-warning btn-sm" value="Edit Profile"/></td>
        </tr>
    </table>
</form>
</body>
</html>
