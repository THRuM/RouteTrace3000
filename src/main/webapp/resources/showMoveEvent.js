function showRoute(wayPoints) {
    var polyline = L.polyline(wayPoints, {color: 'red'}).addTo(mymap);
    // zoom the map to the polyline
    mymap.fitBounds(polyline.getBounds());
}