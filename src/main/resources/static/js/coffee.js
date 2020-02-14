COFFEE = (function () {
    "use strict";

    function init(params) {
        $('.extras-checkbox, .coffee-radio').on('click', function () {
            recalculateTotalPrice();
        });

        var $order = $('#order-form');
        $order.validate({});

        $('#order-form-submit').on('click', function (event) {
            if(!$order.valid()){
                return false;
            }
            event.preventDefault();
            event.stopPropagation();
            var usersConfirm = confirm("¿Desea confirmar su orden?");
                    if (usersConfirm) {
                        $.ajax({
                            url: "/order",
                            method: "POST",
                            contentType: "application/json",
                            data: $order.serializeJSON()
                        })
                            .done(function () {
                                /*alert("");*/
                                window.location.reload();
                            })
                            .fail(function (jqXHR) {
                                if (jqXHR.responseJSON && jqXHR.responseJSON.errors) {
                                    var errors = jqXHR.responseJSON.errors;
                                    $order.validate().showErrors(errors);
                                }
                            })
                            .always(function () {

                            });
                    }
                });
    }

    function recalculateTotalPrice() {
        var selectedExtraOptions = $('.extras-checkbox:checked');
        var totalPrice = 0;

        if(selectedExtraOptions.length !== 0){
            for(var i = 0; i < selectedExtraOptions.length; i++){
                totalPrice = totalPrice + Number(selectedExtraOptions[i].getAttribute("price"));
            }
        }

        var selectedCoffee = $("input[name='coffee-radio']:checked");

        if(selectedCoffee.length > 0){
            totalPrice = totalPrice + Number(selectedCoffee[0].getAttribute('price'));
        }

        $('#precio-total').html(totalPrice);

    }

    return {
        init: init
    }
}());