var routefound = false;
var start = null;
var end = null;

var var_map;

var directionsService = new google.maps.DirectionsService();
var directionsDisplay = new google.maps.DirectionsRenderer();
var var_location = new google.maps.LatLng(40.725118, -73.997699);

var var_mapoptions = {
    center: var_location,
    zoom: 14
};

$.get("/session/all", function (data) {
    if (data != null) {
        $(".show-fullname").html(data.name + " " + data.surname);
    }
});

$("#formroutecreatetitle").change(function () {
    validateSubmit();
});

// Regular map
function init() {

    var_map = new google.maps.Map(document.getElementById("map-container"),
        var_mapoptions);
    directionsDisplay.setMap(var_map);

    var autocompletestart = new google.maps.places.Autocomplete(document.getElementById("formroutecreatestart"));
    var autocompleteend = new google.maps.places.Autocomplete(document.getElementById("formroutecreateend"));

    google.maps.event.addListener(autocompletestart, 'place_changed', function () {
        if (autocompletestart.getPlace() != null && autocompleteend.getPlace() != null) {
            start = autocompletestart.getPlace();
            end = autocompleteend.getPlace();
            displayRoute(directionsService, directionsDisplay);
        }
    });
    google.maps.event.addListener(autocompleteend, 'place_changed', function () {
        if (autocompletestart.getPlace() != null && autocompleteend.getPlace() != null) {
            start = autocompletestart.getPlace();
            end = autocompleteend.getPlace();
            displayRoute(directionsService, directionsDisplay);
        }
    });
}

// Initialize maps
google.maps.event.addDomListener(window, 'load', function () {
    init();
    getRoutes();
});

function displayRoute(directionsService, directionsDisplay) {
    if (start != null && end != null) {
        var request = {
            origin: start.geometry.location,
            destination: end.geometry.location,
            travelMode: 'DRIVING'
        };
        directionsService.route(request, function (result, status) {
            if (status == 'OK') {
                $("#label-route-length").html(result.routes[0].legs[0].distance.text);
                $("#label-route-time").html(result.routes[0].legs[0].duration.text);
                directionsDisplay.setDirections(result);
                $("#label-route-error").html("");
                routefound = true;
            }
            else {
                $("#label-route-error").html("Keine Route gefunden");
                $("#label-route-length").html("");
                $("#label-route-time").html("");
                routefound = false;
            }
            validateSubmit();
        });
    }
}

function validateSubmit() {
    $("#formroutecreatesubmit").attr("disabled",
        !($("#formroutecreatetitle").val().length > 0 && routefound));
}

$("#formroutecreatesubmit").click(function () {
    if (start != null && end != null) {
        $.post("/route/create", {
            startlat: start.geometry.location.lat(),
            startlong: start.geometry.location.lng(),
            starttitle: start.formatted_address,
            endlat: end.geometry.location.lat(),
            endlong: end.geometry.location.lng(),
            endtitle: end.formatted_address,
            title: $("#formroutecreatetitle").val()
        }, function (data) {
            $("#modalRouteCreate").modal("hide");
            getRoutes();
            drawRouteQoutes();
        });
    }
});

function getRoutes() {
    $.get("/route/bysession", function (data) {
        var output = "";
        if (data != null && data.length > 0) {
            output += "<table class='table'>" +
                "<thead class='thead-dark'>" +
                "<th>Titel</th>" +
                "<th>Start</th>" +
                "<th>Ende</th>" +
                "<th>Distanz</th>" +
                "<th>Zeit</th>" +
                "</thead>" +
                "<tbody>";
            for (i in data) {
                var route = data[i];
                output += "<tr>"
                    + "<td>" + route.title + "</td>"
                    + "<td>" + route.start.title + "</td>"
                    + "<td>" + route.end.title + "</td>"
                    + "<td id='tableroutebodydistance" + i + "'>[...]</td>"
                    + "<td id='tableroutebodyduration" + i + "'>[...]</td>"
                    + "</tr>";
            }
            output += "</tbody></table>";
            for (i in data) {
                var route = data[i];
                displayRouteOnElements(route, i);
            }
        }
        else {
            output = "<h4 class='text-center'>Es sind noch keine Fahrten vorhanden</h4>";
        }
        $("#tableroutecard").html(output);
    });
}

function displayRouteOnElements(route, i) {
    var request = {
        origin: new google.maps.LatLng(route.start.latitude, route.start.longitude),
        destination: new google.maps.LatLng(route.end.latitude, route.end.longitude),
        travelMode: 'DRIVING'
    };
    directionsService.route(request, function (result, status) {
        $("#tableroutebodydistance" + i).html(result.routes[0].legs[0].distance.text);
        $("#tableroutebodyduration" + i).html(result.routes[0].legs[0].duration.text);
    });
}

function drawRouteQoutes() {
    $.get("/route/bysession", function (data) {
        var output = "";
        if (data != null && data.length > 0) {
            var starts = [];
            var ends = [];
            var startlabels = [];
            var endlabels = [];
            for (i in data) {
                var route = data[i];
                var tempstart = -1;
                for (j in starts) {
                    if (startlabels[j] == route.start.title) {
                        tempstart = j;
                    }
                }
                if (tempstart != -1) {
                    starts[tempstart]++;
                }
                else {
                    starts.push(1);
                    startlabels.push(route.start.title);
                }
                var tempend = -1;
                for (j in ends) {
                    if (endlabels[j] == route.end.title) {
                        tempend = j;
                    }
                }
                if (tempend != -1) {
                    ends[tempend]++;
                }
                else {
                    ends.push(1);
                    endlabels.push(route.end.title);
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
            $("#endChartCard").html("<canvas id='endChart'></canvas>");
            //pie
            var ctxEnd = document.getElementById("endChart").getContext('2d');
            var endChart = new Chart(ctxEnd, {
                type: 'pie',
                data: {
                    labels: endlabels,
                    datasets: [
                        {
                            data: ends,
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
            $("#startChartCard").html("<h4 class='text-center'>Noch keine Fahrten eingetragen.</h4>");
            $("#endChartCard").html("<h4 class='text-center'>Noch keine Fahrten eingetragen.</h4>");
        }
    });
}

drawRouteQoutes();