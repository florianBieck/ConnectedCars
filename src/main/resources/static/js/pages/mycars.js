$.get("/session/all", function (data) {
    if (data != null) {
        $(".show-fullname").html(data.name + " " + data.surname);
    }
});

$("#formcarcreatesubmit").click(function () {
    var identifier = $("#formcarcreateid").val();
    var title = $("#formcarcreatetitle").val();
    $.post("/car/create?identifier=" + identifier + "&title=" + title, function (data) {

    }).always(function () {
        getCars();
    });
});

getCars();

function getCars() {
    $.get("/car/bysession", function (data) {
        var output = "";
        if (data != null && data.length > 0) {
            output += "<table class='table'>" +
                "<thead class='thead-dark'>" +
                "<th>Titel</th>" +
                "<th>Modell</th>" +
                "<th>Leistung</th>" +
                "<th>Kilometerstand</th>" +
                "</thead>" +
                "<tbody>";
            for (i in data) {
                var car = data[i];
                output += "<tr>";
                output += "<td>" + car.title + "</td>";
                output += "<td>" + car.model.title + "</td>";
                output += "<td>" + car.model.motor.kilowatt + "kw</td>";
                output += "<td id='mileage" + car.id + "'>" + Math.round(car.mileage * 100) / 100 + "km</td>";
            }
            output += "</tbody></table>";
        }
        else {
            output = "<h4 class='text-center'>Es sind noch keine Autos vorhanden</h4>";
        }
        $("#tablecarscard").html(output);
    });
}

window.setInterval(function () {
    $.get("/car/bysession", function (data) {
        for (i in data) {
            var car = data[i];
            $(("#mileage" + car.id)).html(Math.round(car.mileage * 100) / 100 + "km");
        }
    });
}, 500);

$.get("/carqoute/bysession", function (data) {
    console.log(data);
    var output = "";
    if (data != null && data.length > 0) {
        var values = [];
        var labels = [];
        for (i in data) {
            var carqoute = data[i];
            values.push(carqoute.percentage);
            labels.push(carqoute.car.title);
        }
        $("#usageChartCard").html("<canvas id='usageChart'></canvas>");
        //pie
        var ctxStart = document.getElementById("usageChart").getContext('2d');
        var startChart = new Chart(ctxStart, {
            type: 'pie',
            data: {
                labels: labels,
                datasets: [
                    {
                        data: values,
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
        $("#usageChartCard").html("<h4 class='text-center'>Noch keine Fahrten eingetragen.</h4>");
    }
});

