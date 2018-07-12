var place = null;

var var_map;

var directionsService = new google.maps.DirectionsService();
var directionsDisplay = new google.maps.DirectionsRenderer();
var var_location = new google.maps.LatLng(40.725118, -73.997699);

var var_mapoptions = {
    center: var_location,
    zoom: 14
};

var var_marker;

$.get("/session/all", function (data) {
    if (data != null) {
        $(".show-fullname").html(data.name + " " + data.surname);
    }
});

$("#formplacecreatetitle").change(function () {
    validateSubmit();
});

// Regular map
function init() {
    var_map = new google.maps.Map(document.getElementById("map-container"),
        var_mapoptions);
    directionsDisplay.setMap(var_map);

    var autocomplete = new google.maps.places.Autocomplete(document.getElementById("formplacecreateplace"));

    google.maps.event.addListener(autocomplete, 'place_changed', function () {
        $("#formplacecreatesubmit").attr("disabled", autocomplete.getPlace() == null);
        if (autocomplete.getPlace() != null) {
            place = autocomplete.getPlace();
            var_marker = new google.maps.Marker({
                position: place.geometry.location,
                map: var_map,
                title: place.formatted_address
            });
            var_map.setCenter(place.geometry.location);
        }
    });
}

// Initialize maps
google.maps.event.addDomListener(window, 'load', function () {
    init();
    getPlaces();
});

$("#formplacecreatesubmit").click(function () {
    if (place != null) {
        $.post("/place/create", {
            latitude: place.geometry.location.lat(),
            longitude: place.geometry.location.lng(),
            title: place.formatted_address
        }, function (data) {
            $("#modalPlaceCreate").modal("hide");
            getPlaces();
            drawPlaceQoutes();
        });
    }
});

function getPlaces() {
    var placeservice = new google.maps.places.PlacesService(var_map);
    $.get("/place/bysession", function (data) {
        var output = "";
        if (data != null && data.length > 0) {
            output += "<table class='table'>" +
                "<thead class='thead-dark'>" +
                "<th>Ort</th>" +
                "</thead>" +
                "<tbody>";
            for (i in data) {
                var route = data[i];
                output += "<tr>"
                    + "<td><a href='#'>" + route.title + "</a></td>"
                    + "</tr>";
            }
            output += "</tbody></table>";
        }
        else {
            output = "<h4 class='text-center'>Es sind noch keine Orte vorhanden</h4>";
        }
        $("#tableplacecard").html(output);
    });
}

function drawPlaceQoutes() {
    $.get("/place/bysession", function (data) {
        if (data != null && data.length > 0) {
            var starts = [];
            var startlabels = [];
            for (i in data) {
                var place = data[i];
                var tempstart = -1;
                var country = place.title.substr(place.title.lastIndexOf(",") + 2).trim();
                for (j in starts) {
                    if (startlabels[j] == country) {
                        tempstart = j;
                    }
                }
                if (tempstart != -1) {
                    starts[tempstart]++;
                }
                else {
                    starts.push(1);
                    startlabels.push(country);
                }
            }
            $("#startChartCard").html("<canvas id='startChart'></canvas>");
            //pie
            var ctxStart = document.getElementById("startChart").getContext('2d');
            var startChart = new Chart(ctxStart, {
                type: 'pie',
                data: {
                    labels: startlabels,
                    datasets: [
                        {
                            data: starts,
                            backgroundColor: ["#F7464A", "#46BFBD", "#FDB45C", "#949FB1", "#4D5360"],
                            hoverBackgroundColor: ["#FF5A5E", "#5AD3D1", "#FFC870", "#A8B3C5", "#616774"]
                        }
                    ]
                },
                options: {
                    responsive: true
                }
            });
        }
        else {
            $("#startChartCard").html("<h4 class='text-center'>Noch keine Orte eingetragen.</h4>");
        }
    });
}

drawPlaceQoutes();