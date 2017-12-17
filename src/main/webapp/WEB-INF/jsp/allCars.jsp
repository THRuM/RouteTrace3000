<%--
  Created by IntelliJ IDEA.
  User: maciek
  Date: 25.07.17
  Time: 20:36
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
    <title><spring:message code="banner.link.allCars"/></title>
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
                <li><a href="${addCar}"><spring:message code="banner.link.addCar"/></a></li>
                <li class="active"><a href="${allCars}"><spring:message code="banner.link.allCars"/></a></li>
                <li><a href="${allEvents}"><spring:message code="banner.link.allEvents"/></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>
<div class="container" role="main">
    <div class="page-header">
        <table class="table">
            <caption><spring:message code="car.all.list"/></caption>
            <thead>
            <tr>
                <th>#</th>
                <th><spring:message code="car.make"/></th>
                <th><spring:message code="car.model"/></th>
                <th><spring:message code="car.typ"/></th>
                <th><spring:message code="car.registration"/></th>
                <th><spring:message code="event.add.moveEvent"/></th>
                <th><spring:message code="event.add.event"/></th>
                <th><spring:message code="event.add.specyficEvent"/></th>
                <th><spring:message code="delete"/></th>
                <th><spring:message code="more"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${cars}" var="car">
                <c:url value="/addEvent/MOVE/${car.id}" var="addMoveEventLink"/>
                <c:url value="/addEvent/WORK/${car.id}" var="addWorkEventLink"/>
                <c:url value="/addEvent/SPECIFIC/${car.id}" var="addSpecificEventLink"/>
                <c:url value="/deleteCar/${car.id}" var="deleteCar"/>
                <c:url value="/showCar/${car.id}" var="showCar"/>

                <tr>
                    <td scope="row"><c:out value="${car.id}"/></td>
                    <td><c:out value="${car.make}"/></td>
                    <td><c:out value="${car.model}"/></td>
                    <td><c:out value="${car.type.description}"/></td>
                    <td><c:out value="${car.registrationNumber}"/></td>
                    <td><a href="${addMoveEventLink}"><spring:message code="event.add.moveEvent"/></a></td>
                    <td><a href="${addWorkEventLink}"><spring:message code="event.add.event"/></a></td>
                    <td><a href="${addSpecificEventLink}"><spring:message code="event.add.specyficEvent"/></a></td>
                    <td><a href="${deleteCar}"><spring:message code="delete"/></a></td>
                    <td><a href="${showCar}"><spring:message code="more"/></a></td>
                </tr>

            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
