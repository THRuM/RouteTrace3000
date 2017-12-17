<%--
  Created by IntelliJ IDEA.
  User: maciek
  Date: 30.07.17
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="./includes/common.jsp" %>

<c:url value="/" var="home"/>
<c:url value="/addCar" var="addCar"/>
<c:url value="/allCars" var="allCars"/>
<c:url value="/allEvents" var="allEvents"/>
<c:url value="/resources/theme.css" var="theme"/>
<c:url value="/resources/map.js" var="mapJs"/>
<c:url value="/resources/map.css" var="mapCss"/>
<c:url value="/deleteEvent/${event.id}" var="deleteEvent"/>
<c:url value="/rest/event/download/${event.id}" var="downloadEvent"/>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="event.show.title"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="${theme}">

    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.1.0/dist/leaflet.css"
          integrity="sha512-wcw6ts8Anuw10Mzh9Ytw4pylW8+NAD4ch3lqm9lzAsTxg0GFeJgoAtxuCLREZSC5lUXdVyo/7yfsqFjQ4S+aKw=="
          crossorigin=""/>

    <!-- Make sure you put this AFTER Leaflet's CSS -->
    <script src="https://unpkg.com/leaflet@1.1.0/dist/leaflet.js"
            integrity="sha512-mNqn2Wg7tSToJhvHcqfzLMU6J4mkOImSPTxVZAdo+lcPlk+GhZmYgACEe0x35K7YzW1zJ7XyJV/TT1MrdXvMcA=="
            crossorigin=""></script>

    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

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

    <link rel="stylesheet" href="${mapCss}">

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
                <li><a href="${allEvents}"><spring:message code="banner.link.allEvents"/></a></li>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container" role="main">
    <div class="page-header">
        <h1><spring:message code="event.show.header"/> ${event.id}</h1>

        <div class="row" style="padding-bottom: 40px; padding-top: 40px">
            <div class="col-md-12" style="z-index: 0">
                <div id="mapid"></div>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <h4><spring:message code="event.desctiption"/></h4>
                <p>${event.type.description}</p>
            </div>
            <div class="col-md-4">
                <h4><spring:message code="event.start"/></h4>
                <p>${event.startAddress}</p>
            </div>
            <div class="col-md-4">
                <h4><spring:message code="event.end"/></h4>
                <p>${event.endAddress}</p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <h4><spring:message code="car.registration"/></h4>
                <p>${event.car.registrationNumber}</p>
            </div>
            <div class="col-md-4">
                <h4><spring:message code="event.show.type"/></h4>
                <p>${event.description.description}</p>
            </div>
            <div class="col-md-4">
                <h4><spring:message code="date.start"/></h4>
                <p>${event.startDate}</p>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <h4><spring:message code="date.end"/></h4>
                <p>${event.endDate}</p>
            </div>
        </div>

        <div class="row">
            <table class="table">
                <caption><spring:message code="event.specific.velues.desc"/></caption>
                <tr>
                    <td><spring:message code="key"/></td>
                    <td><spring:message code="value"/></td>
                </tr>
                <c:forEach items="${event.specificValuesMap}" var="specyficMap">
                    <tr>
                        <td>${specyficMap.key}</td>
                        <td>${specyficMap.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <div class="row">
            <div class="col-md-4">
                <a href="${deleteEvent}" class="btn btn-danger btn-block"><spring:message code="delete"/></a>
            </div>
            <div class="col-md-4">
                <a href="${downloadEvent}" class="btn btn-success btn-block"><spring:message code="download"/></a>
            </div>
        </div>
    </div>

</div>

<script src="${mapJs}"></script>

<script>
    showPopup(${event.startPointLatitud}, ${event.startPointLongitud}, "${event.startAddress}",0);
    showPopup(${event.endPointLatitud}, ${event.endPointLongitud}, "${event.endAddress}",1);
</script>
</body>
</html>