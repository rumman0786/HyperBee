<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>HyperBee <sitemesh:title/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../css/dashboard.css">
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script src="../js/jquery-3.1.1.js"></script>
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="topbar.jsp"/>
<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="sidebarLeft.jsp"/>
        <div class="col-sm-8 text-left top-padding">
            <sitemesh:body/>
        </div>
        <%--<jsp:include page="sidebarRight.jsp"/>--%>
    </div>
</div>
</body>
</html>