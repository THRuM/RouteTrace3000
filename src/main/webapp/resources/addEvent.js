var places = [];
var choosen = [];
var tempDiv = "";
var lrm;

$(document).ready(function () {
        $("#Form_Input_Button").click(function () {
            var codeText = $("#From_Input").val();
            $.get("https://api.opencagedata.com/geocode/v1/json?q=" + codeText + "&pretty=1&language=pl&no_annotations=1&key=aeea6c44d4f14d9caa211250c8c06a24", {text: codeText}, function (data, status) {
                resetDiv('From_Autocomplete');
                tempDiv = "From_Autocomplete";
                places = [];
                data.results.forEach(appendDiv);

                var div = document.getElementById(tempDiv);
                div.style.position = "absolute";
                div.style.top = 0;
                div.style.left = 0;
                div.style.zIndex = "1"

            });
        });

        $("#To_Input_Button").click(function () {
            var codeText = $("#To_Input").val();
            $.get("https://api.opencagedata.com/geocode/v1/json?q=" + codeText + "&pretty=1&no_annotations=1&key=aeea6c44d4f14d9caa211250c8c06a24", {text: codeText}, function (data, status) {
                resetDiv('To_Autocomplete');
                tempDiv = "To_Autocomplete";
                places = [];
                data.results.forEach(appendDiv);

                var div = document.getElementById(tempDiv);
                div.style.position = "absolute";
                div.style.top = 0;
                div.style.left = 0;
                div.style.zIndex = "1"
            });
        });

        $("#showRoute").click(function () {
            var place1 = choosen[0];
            var place2 = choosen[1];

            if (typeof lrm !== 'undefined') {
                lrm.remove();
            }

            lrm = L.Routing.control({
                waypoints: [
                    L.latLng(place1.geometry.lat, place1.geometry.lng),
                    L.latLng(place2.geometry.lat, place2.geometry.lng)
                ],
                router: L.Routing.graphHopper('5c07aaf0-f8b6-4837-ad16-9bf6225dffe2'),
                fitSelectedRoutes: true,
                draggableWaypoints: false,
                addWaypoints: false
            }).addTo(mymap).on('routeselected', function (r) {
                fillGraspHopperLink(lrm.getRouter().gpUrl);
                closeAllPopups();
            });
        });

        $(document.body).on('click', '.singleAutoComplete', function (event) {
            var parentDiv = $(this).parent();
            var choosenPlaceIndex = event.currentTarget.children[0].innerHTML;
            var place = places[choosenPlaceIndex];
            var prefixes = [];

            if (parentDiv[0].id === "From_Autocomplete") {
                prefixes.push("startAddress");
                prefixes.push("startPoint");

                choosen[0] = place;

                closePopup(0);
                showPopup(place.geometry.lat, place.geometry.lng, place.formatted, 0);
            } else {
                prefixes.push("endAddress");
                prefixes.push("endPoint");

                choosen[1] = place;

                closePopup(1);
                showPopup(place.geometry.lat, place.geometry.lng, place.formatted, 1);
            }

            fillAddressPlaceByPrefix(prefixes[0], place);
            fillPointPlaceByPrefix(prefixes[1], place);

            resetDiv(parentDiv[0].id);
        });
    }
);

function appendDiv(item, index) {
    places.push(item);
    var div = document.getElementById(tempDiv);
    div.innerHTML += "<div class=\"singleAutoComplete panel panel-default\"><span class='number'>" + index + "</span>: <span class='address'>" + item.formatted + "</span>" +
        "<p>" + item.geometry.lat + "</p>" +
        "<p>" + item.geometry.lng + "</p></div>";
}

function resetDiv(divId) {
    var div = document.getElementById(divId);
    div.style.display = 'block';
    div.innerHTML = '';
}

function fillAddressPlaceByPrefix(prefix, place) {
    //Ustawianie wartośći miejsca z palce do pól  formularza
    var numberInput = document.getElementById(prefix + ".number");
    var streetInput = document.getElementById(prefix + ".street");
    var cityInput = document.getElementById(prefix + ".city");
    var countryInput = document.getElementById(prefix + ".country");

    numberInput.setAttribute("value", place.components.house_number);
    streetInput.setAttribute("value", place.components.road);
    cityInput.setAttribute("value", place.components.city);
    countryInput.setAttribute("value", place.components.country);
}

function fillPointPlaceByPrefix(prefix, place) {
    //Ustawianie punków dla startPoint/endPoint
    var latitudInput = document.getElementById(prefix + ".latitud");
    var longitudInput = document.getElementById(prefix + ".longitud");

    latitudInput.setAttribute("value", place.geometry.lat);
    longitudInput.setAttribute("value", place.geometry.lng);
}

function fillGraspHopperLink(link) {
    var gpLink = document.getElementById("gpLink");
    gpLink.setAttribute("value", link);
}