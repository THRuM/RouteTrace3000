<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 27.07.17
  Time: 13:51
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
<c:url value="/resources/addEvent.js" var="addMoveEvent"/>
<c:url value="/resources/lrm-graphhopper-1.2.0.js" var="routing"/>
<!DOCTYPE html>
<html>
<head>
    <title><spring:message code="event.add.moveEvent"/></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="${theme}">

    <link rel="stylesheet" href="https://unpkg.com/leaflet@1.1.0/dist/leaflet.css"
          integrity="sha512-wcw6ts8Anuw10Mzh9Ytw4pylW8+NAD4ch3lqm9lzAsTxg0GFeJgoAtxuCLREZSC5lUXdVyo/7yfsqFjQ4S+aKw=="
          crossorigin=""/>

    <link rel="stylesheet" href="http://www.liedman.net/leaflet-routing-machine/dist/leaflet-routing-machine.css"/>
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

        <h1><spring:message code="event.add.moveEvent"/> dla pojazdu ID: ${eventDTO.carId}</h1>

        <div class="row">
            <div class="col-md-4">
                <div id="From">
                    <label for="From_Input"><spring:message code="from"/> : </label>
                    <input id="From_Input" value="${eventDTO.startAddress != null ? eventDTO.startAddress : ""}">
                    <span id="Form_Input_Button" class="btn btn-success" style="padding-left: 15px"><spring:message
                            code="search"/></span>
                </div>
            </div>
            <div class="col-md-4">
                <div id="To">
                    <label for="To_Input"><spring:message code="to"/> : </label>
                    <input id="To_Input" value="${eventDTO.endAddress != null ? eventDTO.endAddress : ""}">
                    <span id="To_Input_Button" class="btn btn-success" style="padding-left: 15px"><spring:message
                            code="search"/></span>

                </div>
            </div>
            <div class="col-md-2">
                <button id="showRoute" class="btn btn-success btn-block"><spring:message code="show"/></button>
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <div id="From_Autocomplete" style="display: none">
                </div>
            </div>
            <div class="col-md-4">
                <div id="To_Autocomplete" style="display: none">
                </div>
            </div>
        </div>

        <div class="row" style="padding-bottom: 40px; padding-top: 40px">
            <div class="col-md-12" style="z-index: 0">
                <div id="mapid"></div>
            </div>
        </div>

        <div class="row">
            <%--Intellij nie łapie że nazwy widoków mogą być różne z jednej metody kontrolera--%>
            <form:form modelAttribute="eventDTO" id="eventForm" action="/addMoveEvent" method="post"
                       accept-charset="UTF-8">

                <form:errors path="*" element="div" cssClass="alert alert-danger"/>

                <div class="row" style="padding-bottom: 20px">
                    <div class="col-md-4">
                        <label for="startDate"><spring:message code="event.start"/></label>
                        <form:input path="startDate" id="startDate" placeholder="DD/MM/RR HH:MM"/>
                    </div>
                    <div class="col-md-4">
                        <label for="endDate"><spring:message code="event.end"/></label>
                        <form:input path="endDate" id="endDate" placeholder="DD/MM/RR HH:MM"/>
                    </div>
                    <div class="col-md-4">
                        <label for="description"><spring:message code="event.desctiption"/></label>
                        <form:select path="description" items="${eventDescriptions}" itemLabel="description"
                                     id="description"/>
                    </div>
                </div>

                <form:hidden path="carId"/>

                <form:hidden path="startAddress.number" id="startAddress.number"/>
                <form:hidden path="startAddress.street" id="startAddress.street"/>
                <form:hidden path="startAddress.city" id="startAddress.city"/>
                <form:hidden path="startAddress.country" id="startAddress.country"/>

                <form:hidden path="startPoint.latitud" id="startPoint.latitud"/>
                <form:hidden path="startPoint.longitud" id="startPoint.longitud"/>

                <form:hidden path="endAddress.number" id="endAddress.number"/>
                <form:hidden path="endAddress.street" id="endAddress.street"/>
                <form:hidden path="endAddress.city" id="endAddress.city"/>
                <form:hidden path="endAddress.country" id="endAddress.country"/>

                <form:hidden path="endPoint.latitud" id="endPoint.latitud"/>
                <form:hidden path="endPoint.longitud" id="endPoint.longitud"/>

                <form:hidden path="gpLink" id="gpLink"/>

                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <button class="btn btn-primary btn-block"><spring:message code="add"/></button>
                    </div>
                </div>
            </form:form>
        </div>

    </div>
</div>

<script src="http://www.liedman.net/leaflet-routing-machine/dist/leaflet-routing-machine.js"></script>
<script src="${routing}"></script>
<script src="${mapJs}"></script>
<script src="${addMoveEvent}"></script>
</body>
</html>
