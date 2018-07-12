$.get("/session/all", function (data) {
    if (data != null) {
        $(".show-fullname").html(data.name + " " + data.surname);
    }
});

$.get("/car/bysession", function (data) {
    if (data != null && data.length > 0) {
        var output = "";
        for (i in data) {
            var car = data[i];
            output += "<option value='" + car.id + "'>" + car.title + "</option>";
        }
        $(".select-car").html(output);
    }
    else {
        $(".select-car").hide();
    }
});

window.setInterval(function () {
    $.get("/car/byid?id=" + $(".select-car").val(), function (data) {
        var signalstrength = data.signalstrength == 0 ? "empty" : data.signalstrength;
        $(".car-signalstrength").removeClass().addClass("car-signalstrength fa fa-2x fa-thermometer-" + signalstrength);
    });
}, 500);

var lat = 0;
var long = 0;
var locate = true;
setLocate(true);

function setLocate(val) {
    if (val) {
        $("#btnlocate").addClass("btn-primary").removeClass("btn-outline-primary");
    }
    else {
        $("#btnlocate").removeClass("btn-primary").addClass("btn-outline-primary");
    }
    locate = val;
}

window.setInterval(function () {
    var max = $(".indeterminate").parent().width();
    var width = $(".indeterminate").width();
    width = width >= max ? 0 : width + 3;
    $(".indeterminate").width(width);
}, 10);

$("#btnlocate").click(function () {
    setLocate(!locate);
});

// Regular map
function regular_map() {
    var var_location = new google.maps.LatLng(lat, long);

    var var_mapoptions = {
        center: var_location,
        zoom: 14
    };

    var var_map = new google.maps.Map(document.getElementById("map-container"),
        var_mapoptions);

    var var_marker = new google.maps.Marker({
        position: var_location,
        map: var_map,
        title: "Car"
    });

    window.setInterval(function () {
        $.get("/car/byid?id=" + $("#carfocus").val(), function (car) {
            if (car != null) {
                if (locate) {
                    var_location = new google.maps.LatLng(car.latitude, car.longitude);
                    var_marker.setPosition(var_location);
                    var_marker.setTitle(car.title);
                    var_map.setCenter(var_location);
                    $(".label-car-focused-speed").html((Math.round(car.speed * 100) / 100) + " km/h");
                    $(".label-car-focused-speed").show();
                    $("#carconnection").html(car.title);
                    $("#carconnectionparent").show();
                    $(".car-signalstrength").parent().show();
                }
                else {
                    var_marker.setTitle(car.title + " (Eventuell nicht aktuell)");
                    $("#carconnectionparent").hide();
                    $(".label-car-focused-speed").hide();
                    $(".car-signalstrength").parent().hide();
                }
            }
        });
    }, 500);
}

// Initialize maps
google.maps.event.addDomListener(window, 'load', regular_map);