<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.firstName} ${user.lastName}</title>
</head>
<body>
<div class="container">
    <img src="/profile/image/${profile.imagePath}" class="img-circle" alt="Cinque Terre" width="200px" height="200px"/>
</div>
<table>
    <tr>
        <th class="panel-heading">
            <h1 style="color: #269abc; font-family: 'Glyphicons Halflings'">
                <b>${user.firstName} ${user.lastName}</b>
            </h1>
        </th>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Firstname :</th>
        <td>${user.firstName}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Lastname :</th>
        <td>${user.lastName}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Designation :</th>
        <td>${profile.designation}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Email :</th>
        <td>${user.email}}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Address :</th>
        <td>${profile.address}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Contact No. :</th>
        <td>${profile.contactNo}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Joining Date :</th>
        <td><fmt:formatDate type="date" value="${profile.joiningDate.time}" pattern="dd-MM-yy"/></td>
    </tr>
    <tr>
        <th><h2 class="panel-heading" style="color: #269abc">Education :</h2></th>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">School :</th>
        <td>${profile.school}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">College :</th>
        <td>${profile.college}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">University :</th>
        <td>${profile.university}</td>
    </tr>
    <tr>
        <th><h2 class="panel-heading" style="color: #269abc">Basic Information :</h2></th>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Gender :</th>
        <td>${profile.gender}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Date Of Birth :</th>
        <td><fmt:formatDate type="date" value="${profile.dateOfBirth.time}" pattern="dd-MM-yy"/></td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Skills :</th>
        <td>${profile.skills}</td>
    </tr>
    <tr>
        <th><h2 class="panel-heading" style="color: #269abc">Work Experience :</h2></th>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Years of Experience :</th>
        <td>${profile.jobExperienceYears}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Work History :</th>
        <td>${profile.workHistory}</td>
    </tr>
    <tr>
        <th class="panel-heading" style="color: #122b40">Image :</th>
        <td>${profile.imagePath}</td>
    </tr>
</table>
</body>
</html>
