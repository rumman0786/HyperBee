<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.firstName} ${user.lastName}</title>
</head>
<body>
<div class="col-lg-10 container"
     style="background-image: url(http://localhost:8080/profile/cover/${profile.coverImage})">
    <div class="row">
        <div class="col-lg-2 container" style="padding-top: 150px">
            <img src="/profile/image/${profile.imagePath}" class="img-thumbnail" alt="Cinque Terre"
                 width="200" height="200"/>
        </div>
    </div>
</div>
<div class="col-lg-10 container" style="padding-top: 10px">
    <h1 style="color: #269abc; font-family: 'Glyphicons Halflings'">
        <b>${user.firstName} ${user.lastName}</b>
    </h1>
</div>
<div class="row">
    <div class="col-lg-5">
        <div class="panel panel-default" style="padding: 20px">
            <div class="panel-heading">
                <h3><b>General</b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">First Name :</th>
                        <td class="form-control-static">${user.firstName}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Last Name :</th>
                        <td class="form-control-static">${user.lastName}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Designation :</th>
                        <td class="form-control-static">${profile.designation}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Email :</th>
                        <td class="form-control-static">${user.email}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Address :</th>
                        <td class="form-control-static">${profile.address}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Contact No. :</th>
                        <td class="form-control-static">${profile.contactNo}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Joining Date :</th>
                        <td class="form-control-static"><fmt:formatDate type="date" value="${profile.joiningDate.time}"
                                                                        pattern="dd-MM-yy"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="col-lg-5 panel panel-default" style="padding: 10px">
        <div class="panel-heading">
            <h3><b>Education</b></h3>
        </div>
        <div class="panel-body">
            <table>
                <tr>
                    <th class="panel-heading" style="color: #269abc">School :</th>
                    <td class="form-control-static">${profile.school}</td>
                </tr>
                <tr>
                    <th class="panel-heading" style="color: #269abc">College :</th>
                    <td class="form-control-static">${profile.college}</td>
                </tr>
                <tr>
                    <th class="panel-heading" style="color: #269abc">University :</th>
                    <td class="form-control-static">${profile.university}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-5">
        <div class="panel panel-default" style="padding: 10px">
            <div class="panel-heading">
                <h3><b>Basic Information</b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Gender :</th>
                        <td class="form-control-static">${profile.gender}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Date Of Birth :</th>
                        <td class="form-control-static"><fmt:formatDate type="date" value="${profile.dateOfBirth.time}"
                                                                        pattern="dd-MM-yy"/></td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Skills :</th>
                        <td class="form-control-static">${profile.skills}</td>
                    </tr>

                </table>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="panel panel-default" style="padding: 10px">
            <div class="panel-heading">
                <h3><b>Work Experience</b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Years of Experience :</th>
                        <td class="form-control-static">${profile.jobExperienceYears}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc">Work History :</th>
                        <td class="form-control-static">${profile.workHistory}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>