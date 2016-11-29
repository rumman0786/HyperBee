<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HyperBEE</title>
</head>
<body>
<form:form action="/profile" method="post" commandName="profile" cssClass=" panel-primary"
           enctype="multipart/form-data">
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
            <td><form:input path="designation" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Address :</th>
            <td><form:input path="address" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Contact No. :</th>
            <td><form:input path="contactNo" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Joining Date :</th>
            <td><form:input path="joiningDate" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc">Education :</h2></th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">School :</th>
            <td><form:input path="school" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">College :</th>
            <td><form:input path="college" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">University :</th>
            <td><form:input path="university" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc">Basic Information :</h2></th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Gender :</th>
            <td><form:input path="gender" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Date Of Birth :</th>
            <td><form:input path="dateOfBirth" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Skills :</th>
            <td><form:input path="skills" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th><h2 class="panel-heading" style="color: #269abc">Work Experience :</h2></th>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Years of Experience :</th>
            <td><form:input path="jobExperienceYears" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Work History :</th>
            <td><form:input path="workHistory" type="text" cssClass="form-control"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Profile Picture :</th>
            <td><form:input path="imagePath" type="text" cssClass="form-control"/></td>
            <td><input type="file" name="file" id="fileName"/></td>
        </tr>
        <tr>
            <th class="panel-heading" style="color: #122b40">Cover Picture :</th>
            <td><form:input path="coverImage" type="text" cssClass="form-control"/></td>
            <td><input type="file" name="coverFile"/></td>
        </tr>
        <tr>
            <td colspan="2" align="left"><input type="submit" class="btn btn-warning btn-sm" value="Edit"></td>
        </tr>
        <tr>
            <td style="color: green">${message}</td>
        </tr>
        <tr>
            <td style="color: green">${message2}</td>
        </tr>
        <tr>
            <td style="color: green">${message3}</td>
        </tr>
    </table>
</form:form>

</body>
</html>
