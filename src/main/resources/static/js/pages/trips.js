var routefound = false;
var start = null;
var end = null;

var carchooser_intervall = null;

function trackSignalStrengthInCarChooserModal() {
    carchooser_intervall = window.setInterval(function () {
        $.get("/car/byid?id=" + $("#formcarchooserselect").val(), function (data) {
            var signalstrength = data.signalstrength == 0 ? "empty" : data.signalstrength;
            $("#formcarchoosersignalstrength").removeClass().addClass("btn btn-primary fa fa-2x fa-thermometer-" + signalstrength);
            $("#formcarchoosersubmit").attr("disabled", signalstrength == "empty");
            if (signalstrength == "empty") {
                $("#formcarchoosersignalstrength").removeClass("btn-primary").addClass("btn-danger");
            }
        });
    }, 500);
}

$("#modalCarChooser").on("hidden.bs.modal", function () {
    clearInterval(carchooser_intervall);
});
$("#modalCarChooser").on("show.bs.modal", function () {
    trackSignalStrengthInCarChooserModal();
});

window.setInterval(function () {
    var max = $(".indeterminate").parent().width();
    var width = $(".indeterminate").width();
    width = width >= max ? 0 : width + 3;
    $(".indeterminate").width(width);
}, 10);

$("#modalTripCreate").on("show.bs.modal", function () {
    $.get("/route/bysession", function (routes) {
        $("#formtripcreateroute").html();
        var output = "";
        for (i in routes) {
            var route = routes[i];
            output += "<option value='" + route.id + "'>" + route.title + "</option>";
        }
        $("#formtripcreateroute").html(output);
    });
    $.get("/car/bysession", function (cars) {
        $("#formtripcreateroute").html();
        var output = "";
        for (i in cars) {
            var car = cars[i];
            output += "<option value='" + car.id + "'>" + car.title + "</option>";
        }
        $("#formtripcreatecar").html(output);
    });
});

$("#carconnectionparent").hide();
$("#formcarchoosersubmit").click(function () {
    $("#carconnectionparent").show();
    $("#carconnection").html($("#formcarchooserselect :selected").html());
    window.setTimeout(function () {
        $("#carconnectionparent").hide();
    }, (Math.floor(Math.random() * 4) + 1) * 1000);
    $("#modalCarChooser").modal("hide");
});

$.get("/session/all", function (data) {
    if (data != null) {
        $(".show-fullname").html(data.name + " " + data.surname);
    }
});

$("#formtripcreatesubmit").click(function () {
    $.post("/trip/create?timestamp=" + (new Date($("#formtripcreatetimestamp").val())).toISOString(), {
        idroute: $("#formtripcreateroute").val(),
        idcar: $("#formtripcreatecar").val(),
        duration: 30
    }, function (data) {
        $("#modalTripCreate").modal("hide");
        getTrips();
        //drawRouteQoutes();
    });
});

function getTrips() {
    $.get("/trip/bysession", function (trips) {
        var output = "";
        if (trips != null && trips.length > 0) {
            output += "<table class='table'>" +
                "<thead class='thead-dark'>" +
                "<th>Route</th>" +
                "<th>Car</th>" +
                "<th>Startzeit</th>" +
                "<th>Fahrtzeit</th>" +
                "</thead>" +
                "<tbody>";
            for (i in trips) {
                var trip = trips[i];
                output += "<tr>"
                    + "<td>" + trip.myRoute.title + "</td>"
                    + "<td>" + trip.car.title + "</td>"
                    + "<td>" + new Date(trip.timestamp).toLocaleString() + "</td>"
                    + "<td>" + trip.duration + "min</td>"
                    + "</tr>";
            }
            output += "</tbody></table>";
        }
        else {
            output = "<h4 class='text-center'>Es sind noch keine Fahrten vorhanden</h4>";
        }
        $("#tabletripcard").html(output);
    });
}

getTrips();

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