<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>   
            <a class="navbar-brand" href="/user/dashboard"><fmt:message key="dashboard.view.topbar.hyperbee"/> </a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/user/dashboard"><fmt:message key="dashboard.view.topbar.home"/></a></li>
                <li><a href="/profile/user"><fmt:message key="dashboard.view.topbar.profile"/></a></li>
                <li><a href="/user/dashboard"><fmt:message key="dashboard.view.topbar.home"/></a></li>
                <li><a href="/user/profile"><fmt:message key="dashboard.view.topbar.profile"/></a></li>
                <c:if test="${authUser.isAdmin()}">
                    <li><a href="/notice/list"><fmt:message key="dashboard.view.topbar.notice"/></a></li>
                    <li <c:if test="${page == 'notice'}"> class="active" </c:if>><a href="/notice/list"><fmt:message
                            key="dashboard.view.topbar.notice"/></a></li>
                </c:if>
                <li><a href="/user/notes"><fmt:message key="dashboard.view.topbar.note"/></a></li>
                <li><a href="/conference/list">Conference</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> <fmt:message
                        key="dashboard.view.topbar.logout"/></a></li>
            </ul>
        </div>
    </div>
</nav>
