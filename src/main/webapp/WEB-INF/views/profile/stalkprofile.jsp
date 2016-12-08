<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ftm" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${user.firstName} ${user.lastName}</title>
</head>
<body>
<div class="col-sm-10 container"
     style="background-image: url(/profile/cover/${profile.coverImage})">
    <div class="row">
        <div class="col-lg-2 container" style="padding-top: 150px">
            <c:choose>
                <c:when test="${empty profile.imagePath}">
                    <img src="/images/dummyprofilepic.png" class="img-thumbnail" alt="Cinque Terre"
                         width="200" height="200"/>
                </c:when>
                <c:otherwise>
                    <img src="/profile/image/${profile.imagePath}" class="img-thumbnail" alt="Cinque Terre"
                         width="200" height="200"/>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<div class="col-sm-3 container" style="padding-top: 10px">
    <h2 style="color: #269abc; font-family: 'fontawesome'">
        <b>${user.firstName} ${user.lastName}</b>
    </h2>
</div>
<div class="col-sm-10 container pull-left" style="padding-left: 900px">
    <c:if test="${authUser.isAdmin() && !user.isAdmin()}">
        <c:choose>
            <c:when test="${user.getDisplayStatus() == 'ACTIVE'}">
                <div class="pull-right">
                    <form action="/user/deactivate/<c:out value="${user.getId()}"/>/<c:out value="${user.getUsername()}"/>"
                          method="post">
                        <button type="submit" class="btn btn-danger">
                            <ftm:message key="user.deactivate"/>
                        </button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="pull-right">
                    <form action="/user/activate/<c:out value="${user.getId()}"/>/<c:out value="${user.getUsername()}"/>"
                          method="post">
                        <button type="submit" class="btn btn-success">
                            <ftm:message key="user.activate"/>
                        </button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
        <c:choose>
            <c:when test="${user.isAdmin()}">
                <div class="pull-right">
                    <form action="/user/make/user/<c:out value="${user.getId()}"/>"
                          method="post">
                        <button type="submit" class="btn btn-primary">
                            <ftm:message key="user.make.user"/>
                        </button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <div class="pull-right">
                    <form action="/user/make/admin/<c:out value="${user.getId()}"/>"
                          method="post">
                        <button type="submit" class="btn btn-warning">
                            <ftm:message key="user.make.admin"/>
                        </button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </c:if>
</div>
<div class="row">
    <div class="col-sm-5">
        <div class="panel panel-default" style="padding: 20px">
            <div class="panel-heading">
                <h3><b><fmt:message key="profile.show.general"/></b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="user.first.name"/></th>
                        <td class="form-control-static">${user.firstName}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="user.last.name"/></th>
                        <td class="form-control-static">${user.lastName}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.designation"/></th>
                        <td class="form-control-static">${profile.designation}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="user.email"/></th>
                        <td class="form-control-static">${user.email}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.address"/></th>
                        <td class="form-control-static">${profile.address}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.contact"/></th>
                        <td class="form-control-static">${profile.contactNo}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.joining.date"/></th>
                        <td class="form-control-static"><fmt:formatDate type="date" value="${profile.joiningDate.time}"
                                                                        pattern="dd-MM-yy"/></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
    <div class="col-sm-5 panel panel-default" style="padding: 10px">
        <div class="panel-heading">
            <h3><b><fmt:message key="profile.show.education"/></b></h3>
        </div>
        <div class="panel-body">
            <table>
                <tr>
                    <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.school"/></th>
                    <td class="form-control-static">${profile.school}</td>
                </tr>
                <tr>
                    <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.college"/></th>
                    <td class="form-control-static">${profile.college}</td>
                </tr>
                <tr>
                    <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.university"/></th>
                    <td class="form-control-static">${profile.university}</td>
                </tr>
            </table>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-5">
        <div class="panel panel-default" style="padding: 10px">
            <div class="panel-heading">
                <h3><b><fmt:message key="profile.show.basic.info"/></b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.gender"/></th>
                        <td class="form-control-static">${profile.gender}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.dateofbirth"/></th>
                        <td class="form-control-static"><fmt:formatDate type="date" value="${profile.dateOfBirth.time}"
                                                                        pattern="dd-MM-yy"/></td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.skills"/></th>
                        <td class="form-control-static">${profile.skills}</td>
                    </tr>

                </table>
            </div>
        </div>
    </div>
    <div class="col-sm-6">
        <div class="panel panel-default" style="padding: 10px">
            <div class="panel-heading">
                <h3><b><fmt:message key="profile.show.workexperience"/></b></h3>
            </div>
            <div class="panel-body">
                <table>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message
                                key="profile.yearsofexperience"/></th>
                        <td class="form-control-static">${profile.jobExperienceYears}</td>
                    </tr>
                    <tr>
                        <th class="panel-heading" style="color: #269abc"><fmt:message key="profile.workhistory"/></th>
                        <td class="form-control-static">${profile.workHistory}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
