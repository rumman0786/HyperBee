<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><fmt:message key="hive.title"/></title>
</head>
<body>
<div class="container-fluid">
<div class="container" style="background-image: url(/user/hive/image/${hive.imagePath})">
    <div class="row">
        <div class="col-lg-10" style="padding-top: 200; padding-bottom: 50; padding-left: 25">
            <h1>${hive.name}</h1>
        </div>
    </div>
</div>
<br>

<div class="container">
    <ul class="nav nav-tabs">
        <li class="active"><a data-toggle="tab" href="#post"><fmt:message key="hive.discussion"/></a></li>
        <li><a data-toggle="tab" href="#notice"><fmt:message key="hive.notice"/></a></li>
        <li><a data-toggle="tab" href="#member"><fmt:message key="hive.member"/></a></li>
        <li><a data-toggle="tab" href="#about"><fmt:message key="hive.about"/></a></li>
    </ul>
</div>
<br>

<div class=" col-sm-10 tab-content">
<div id="post" class="tab-pane in active">
    <form:form action="/user/hive/post/${hive.id}" method="POST" commandName="discussion">
        <div class="panel panel-success">
            <div class="panel-heading clearfix">
                <h4 class="panel-title pull-left" style="padding-top: 7.5px;"><fmt:message
                        key="hive.post.form"/></h4>
            </div>
            <div class="panel-body">
                <form:textarea type="text" placeholder="Description" class="form-control" path="description"/>
            </div>
            <div class="panel-footer clearfix">
                <div class="btn-group pull-right">
                    <button class="btn btn-warning btn-sm" type="submit"><fmt:message
                            key="hive.post.button"/></button>
                </div>
                <form:errors path="description" cssClass="alert-danger"/>
            </div>
        </div>
    </form:form>
    <c:forEach items="${hive.postList}" var="item">
        <div class="panel panel-warning">
            <div class="panel-heading clearfix">
                <table>
                    <tr>
                        <td style="padding: 8px">
                            <c:choose>
                                <c:when test="${empty item.user.profile.imagePath}">
                                    <img src="/images/dummyprofilepic.png" class="img-circle"
                                         alt="Cinque Terre"
                                         width="40px" height="40px"/>
                                </c:when>
                                <c:otherwise>
                                    <img src="/profile/image/${item.user.profile.imagePath}"
                                         class="img-circle"
                                         alt="Cinque Terre"
                                         width="40px" height="40px"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="padding: 8px">
                            <h4 class="panel-title pull-left" style="padding-top: 7.5px;">
                                <b>${item.user.firstName} ${item.user.lastName}</b></h4>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="panel-body">${item.description}</div>
            <div class="panel-footer clearfix">
                <div class="pull-right">
                    <fmt:formatDate type="date" value="${item.dateCreated.time}" pattern="dd-MM-yy [hh:mm]"/>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div id="notice" class="tab-pane">
    <c:forEach items="${noticeList}" var="item">
        <div class="panel panel-warning">
            <div class="panel-heading clearfix">
                <h4 class="panel-title pull-left" style="padding-top: 7.5px;">
                    <strong>${item.title}</strong></h4>
            </div>
            <div class="panel-body">${item.description}</div>
            <div class="panel-footer clearfix">
                <div class="pull-right">
                    <fmt:formatDate type="date" value="${item.dateCreated.time}" pattern="dd-MM-yy"/>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<div id="member" class="tab-pane">
    <c:choose>
        <c:when test="${hive.creator.id==authUser.id}">
            <div class="col-sm-6 table-responsive">
                <div class="panel panel-info">
                    <div class="panel-heading"><h3><fmt:message key="hive.member"/> ${hive.userList.size()} </h3>
                        <h4><fmt:message key="hive.admin"/>
                            <small>${hive.creator.firstName} ${hive.creator.lastName}</small>
                        </h4>
                    </div>
                    <form:form method="POST" action="/user/hive/removeuser/${hive.id}" commandName="userIdInfo">
                        <div class="panel-body">
                            <ul class="list-group">
                                <c:forEach var="user" items="${userListToRemove}" varStatus="loop">
                                    <li class="list-group-item">
                                        <table>
                                            <tr style="border: 1">
                                                <td style="padding: 8px">
                                                    <form:checkbox path="userIdList" value="${user.id}"/>
                                                </td>
                                                <td style="padding: 8px">
                                                    <c:choose>
                                                        <c:when test="${empty user.profile.imagePath}">
                                                            <img src="/images/dummyprofilepic.png" class="img-circle"
                                                                 alt="Cinque Terre"
                                                                 width="40px" height="40px"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="/profile/image/${user.profile.imagePath}"
                                                                 class="img-circle"
                                                                 alt="Cinque Terre"
                                                                 width="40px" height="40px"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td style="padding: 8px">${user.firstName} ${user.lastName}</td>
                                            </tr>
                                        </table>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="panel-footer clearfix">
                            <div class="pull-right">
                                <form:errors path="userIdList" cssClass="alert-danger"/>
                                <button class="btn btn-warning btn-sm" type="submit"><fmt:message
                                        key="hive.remove.button"/></button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
            <div class="col-sm-6 table-responsive">
                <div class="panel panel-info">
                    <div class="panel-heading"><h3><fmt:message key="hive.add.member"/></h3>
                    </div>
                    <form:form method="POST" action="/user/hive/insertuser/${hive.id}" commandName="userIdInfo">
                        <div class="panel-body">
                            <ul class="list-group">
                                <c:forEach var="user" items="${userList}" varStatus="loop">
                                    <li class="list-group-item">
                                        <table>
                                            <tr style="border: 1">
                                                <td style="padding: 8px">
                                                    <form:checkbox path="userIdList" value="${user.id}"/>
                                                </td>
                                                <td style="padding: 8px">
                                                    <c:choose>
                                                        <c:when test="${empty user.profile.imagePath}">
                                                            <img src="/images/dummyprofilepic.png" class="img-circle"
                                                                 alt="Cinque Terre"
                                                                 width="40px" height="40px"/>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <img src="/profile/image/${user.profile.imagePath}"
                                                                 class="img-circle"
                                                                 alt="Cinque Terre"
                                                                 width="40px" height="40px"/>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td style="padding: 8px">${user.firstName} ${user.lastName}</td>
                                            </tr>
                                        </table>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="panel-footer clearfix">
                            <div class="pull-right">
                                <form:errors path="userIdList" cssClass="alert-danger"/>
                                <button class="btn btn-warning btn-sm" type="submit"><fmt:message
                                        key="hive.add.button"/></button>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div class="col-sm-12 table-responsive">
                <div class="panel panel-info">
                    <div class="panel-heading"><h3><fmt:message key="hive.member"/> ${hive.userList.size()} </h3>
                        <h4><fmt:message key="hive.admin"/>
                            <small>${hive.creator.firstName} ${hive.creator.lastName}</small>
                        </h4>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">
                            <c:forEach items="${hive.userList}" var="user">
                                <li class="list-group-item">
                                    <table>
                                        <tr style="border: 1">
                                            <td style="padding: 8px">
                                                <c:choose>
                                                    <c:when test="${empty user.profile.imagePath}">
                                                        <img src="/images/dummyprofilepic.png" class="img-circle"
                                                             alt="Cinque Terre"
                                                             width="40px" height="40px"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="/profile/image/${user.profile.imagePath}"
                                                             class="img-circle"
                                                             alt="Cinque Terre"
                                                             width="40px" height="40px"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td style="padding: 8px"><c:out
                                                    value="${user.firstName} ${user.lastName}"/></td>
                                        </tr>
                                    </table>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<div id="about" class="tab-pane">
    <div class="panel panel-info">
        <div class="panel-heading clearfix">
        </div>
        <div class="panel-body">${hive.description}</div>
    </div>
</div>
</div>
</div>
</body>
</html>
