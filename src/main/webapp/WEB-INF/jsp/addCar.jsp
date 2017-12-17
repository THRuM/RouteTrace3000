<%--
  Created by IntelliJ IDEA.
  User: maciek
  Date: 25.07.17
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="./includes/common.jsp" %>
<c:url value="/" var="home"/>
<c:url value="/addCar" var="addCar"/>
<c:url value="/allCars" var="allCars"/>
<c:url value="/allEvents" var="allEvents"/>
<c:url value="/resources/theme.css" var="theme"/>
<html>
<head>
    <title><spring:message code="car.add"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="${theme}">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
          integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
            integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
            crossorigin="anonymous"></script>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"><a href="${addCar}"><spring:message code="banner.link.addCar"/></a></span>
                <span class="icon-bar"><a href="${allCars}"><spring:message code="banner.link.allCars"/></a></span>
                <span class="icon-bar"><a href="${allEvents}"><spring:message code="banner.link.allEvents"/></a></span>
            </button>
            <a class="navbar-brand" href="#"><spring:message code="banner.awesome.title"/></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${home}"><spring:message code="banner.link.home"/></a></li>
                <li class="active"><a href="${addCar}"><spring:message code="banner.link.addCar"/></a></li>
                <li><a href="${allCars}"><spring:message code="banner.link.allCars"/></a></li>
                <li><a href="${allEvents}"><spring:message code="banner.link.allEvents"/></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container" role="main">
    <div class="page-header">
        <h1><spring:message code="car.add"/></h1>
        <form:form modelAttribute="car">

            <form:errors path="*" element="div" cssClass="alert alert-danger"/>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-4">
                        <label for="make"><spring:message code="car.make"/></label>
                    </div>
                    <div class="col-md-4">
                        <form:input path="make" id="make" cssClass="form-control"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-4">
                        <label for="model"><spring:message code="car.model"/></label>
                    </div>
                    <div class="col-md-4">
                        <form:input path="model" id="model" cssClass="form-control"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-4">
                        <label for="registrationNumber"><spring:message code="car.registration"/></label>
                    </div>
                    <div class="col-md-4">
                        <form:input path="registrationNumber" id="registrationNumber" cssClass="form-control"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="row">
                    <div class="col-md-4">
                        <label for="type"><spring:message code="car.typ"/></label>
                    </div>
                    <div class="col-md-4">
                        <form:select id="type" path="type" items="${carTypes}" itemLabel="description"
                                     cssClass="form-control"/>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md4 col-md-offset-4">
                    <button class="btn btn-primary"><spring:message code="add"/></button>
                </div>
            </div>
        </form:form>
    </div>
</div>

</body>
</html>
