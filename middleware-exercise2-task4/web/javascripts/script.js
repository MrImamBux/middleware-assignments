$(document).ready(function (){
    $("#submit").on("click", function(){

        var currency 	= $("#currency").val();		//fetch currency selected

        if (currency === "dollar") {
            currency = "Dollar";
        } else if (currency === "euro") {
            currency = "Euro";
        } else {
            currency = "N/A";
        }

        $.ajax({
            url: "calculateTax",
            type: "POST",
            data: {
                amount: $("#amount").val(),
                tax: $("#tax").val()
            },
            success: function(data) {
                $("#result").empty();
                $("#result").append("The total amount is " + data + " " + currency);
            },
            error: function(data, status, er) {
                $("#result").empty();
                $("#result").append("Please enter a valid number");
            }
        });

    });

    $("form").on("reset", function() {
        $("#result").empty();
    });

    updateWebCounter();

});

function updateWebCounter() {
    $.ajax({
        url: "updateCounter",
        success: function (data) {
            $("#count").empty();
            $("#count").append(data);
        }
    });
    setTimeout(updateWebCounter, 10);
}

