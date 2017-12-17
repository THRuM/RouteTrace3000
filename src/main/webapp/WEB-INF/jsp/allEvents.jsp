<%--
  Created by IntelliJ IDEA.
  User: maciek
  Date: 29.07.17
  Time: 17:04
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
    <title><spring:message code="banner.link.allEvents"/></title>
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
                <li><a href="${allCars}"><spring:message code="banner.link.allCars"/></a></li>
                <li class="active"><a href="${allEvents}"><spring:message code="banner.link.allEvents"/></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container" role="main">
    <div class="page-header">
        <table class="table">
            <caption><spring:message code="event.all.list"/></caption>
            <thead>
            <tr>
                <th>#</th>
                <th><spring:message code="car.registration"/></th>
                <th><spring:message code="event.start"/></th>
                <th><spring:message code="event.end"/></th>
                <th><spring:message code="event.type"/></th>
                <th><spring:message code="more"/></th>
                <th><spring:message code="delete"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event">
                <c:url value="/showEvent/${event.id}" var="eventMore"/>
                <c:url value="/deleteEvent/${event.id}" var="deleteEvent"/>
                <tr>
                    <td><c:out value="${event.id}"/></td>
                    <td><c:out value="${event.car.registrationNumber}"/></td>
                    <td><c:out value="${event.startAddress}"/></td>
                    <td><c:out value="${event.endAddress}"/></td>
                    <td><c:out value="${event.type.description}"/></td>
                    <td><a href="${eventMore}"><spring:message code="more"/></a></td>
                    <td><a href="${deleteEvent}"><spring:message code="delete"/></a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>