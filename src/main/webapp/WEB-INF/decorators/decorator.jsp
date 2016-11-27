<%@taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="sitemesh" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>HyperBee::<sitemesh:title/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <script src="../js/jquery-3.1.1.js"></script>
    <script src="../js/bootstrap-collapse.js"></script>
    <script src="../js/bootstrap-transition.js"></script>
    <link rel="stylesheet" href="../css/dashboard.css">


    <script src="../js/jquery-2.1.1.min.js"></script>
    <script src="../js/moment.2.9.o.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/bootstrap-datetimepicker.js"></script>

    <link href="../css/bootstrap.min.css" rel="stylesheet"/>
    <link href="../css/bootstrap-datetimepicker.css" rel="stylesheet"/>

    <sitemesh:head/>
</head>
<body>

<jsp:include page="topbar.jsp"/>
<div class="container-fluid text-center">
    <div class="row content">
        <jsp:include page="sidebarLeft.jsp"/>
        <div class="col-sm-8 text-left top-padding">
            <sitemesh:body/>
        </div>
        <jsp:include page="sidebarRight.jsp"/>
    </div>
</div>
</body>
</html>