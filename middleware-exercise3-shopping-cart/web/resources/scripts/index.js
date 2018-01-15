$(document).ready(function() {

    var imagesLocation = "resources/images/";
    var content = $("section ul");
    var itemTemplate = $(content).children().attr("hidden", false).clone();
    $(content).empty();

    // load contents
    $.ajax({
        url: "loadItems",
        success: function(data){
            $.each(data, function(index, resp) {
                var itemToBeAdded = itemTemplate.clone();
                $(itemToBeAdded).find("img").attr("src", imagesLocation + resp.imageSource);
                $(itemToBeAdded).find(".price span").text(resp.price);
                $(itemToBeAdded).find("button").attr("value", resp.id);
                $(content).append(itemToBeAdded);
            });

            updateItemsQuantity();

            $(".add-to-cart").on("click", function(event) {
                event.preventDefault();
                var urlLocation = "addToCart";
                $.ajax({
                    url: urlLocation,
                    data: {"item":$(this).val()},
                    success: function() {
                        updateItemsQuantity();
                    },
                    error: function(xhr) {
                        console.log(xhr);
                    }
                });
            });
        },
        error: function(xhr){
            console.log(xhr);
        }
    });

});

function updateItemsQuantity() {
    $.ajax({
        url: "getItemsQuantity",
        success: function(resp) {
            $("#items-quantity").text(resp.quantity);
        }
    });
}

