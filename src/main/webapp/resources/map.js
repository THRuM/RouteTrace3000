var mymap = L.map('mapid').setView([50.68391, 17.87413], 14);
var popups = [];

L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
    maxZoom: 18,
    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
    '<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
    'Imagery © <a href="http://mapbox.com">Mapbox</a>',
    id: 'mapbox.streets'
}).addTo(mymap);


function showPopup(lat, long, desc, number) {
    var lati = lat;
    var longi = long;

    var popupLocation1 = new L.LatLng(lati, longi);
    var popupContent1 = desc;

    var popup1 = new L.Popup();
    popup1.setLatLng(popupLocation1);
    popup1.setContent(popupContent1);

    var m1 = L.marker([lati, longi]).addTo(mymap);

    m1.bindPopup(popup1);

    m1.openPopup();

    // popups.push(m1);
    popups[number] = m1;

    mymap.setView(m1.getLatLng(), 14);
}

function closePopup(number) {
    if (typeof popups[number] !== 'undefined') {
        popups[number].remove();
    }
}

function closeAllPopups() {
    for (var i = 0, sp; sp = popups[i]; i++) {
        sp.remove();
    }
}