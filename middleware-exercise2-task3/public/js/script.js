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
			url: "http://localhost:3000/calculateTax",
			type: "POST",
			data: {
				amount: $("#amount").val(),
				tax: $("#tax").val() 
			},
			success: function(tax){
				$("#result").empty();
				if(tax.status == 'Success') {
					$("#result").append("The total amount is " + tax.value + " " + currency);	
				} else {
					$("#result").append("Please enter a valid number");
				}
				
			}, 
			error: function(jqXHR, textStatus) {
				alert("some error occured please try again later");
			}
		});

	});

	$("form").on("reset", function() {
		$("#result").empty();
	});
});

