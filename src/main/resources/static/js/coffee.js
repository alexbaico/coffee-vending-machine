COFFEE = (function () {
    "use strict";

    function init(params) {

        recalculateTotalPrice();

        $('.extras-checkbox, .coffeeType').on('click', function () {
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

            var data = $order.serializeObject();
            var extraOptions = [];
            $('.extras-checkbox:checked').each(function (index, option) {
                extraOptions.push(option.value);
            });
            data.options = extraOptions; //Fix ya que los checkbox no se deserializan bien

            var usersConfirm = confirm("¿Desea confirmar su orden?");
                    if (usersConfirm) {
                        $.ajax({
                            url: "/order",
                            method: "POST",
                            contentType: "application/json",
                            data: JSON.stringify(data)
                        })
                            .done(function (response) {
                                alert(response);
                                reset();
                                recalculateTotalPrice();
                            })
                            .fail(function (jqXHR) {
                                alert("Oops! Algo anduvo mal...");
                                if (jqXHR.responseJSON && jqXHR.responseJSON.errors) {
                                    $order.validate().showErrors(jqXHR.responseJSON.errors);
                                }
                            });
                    }
                });
    }

    function reset(){
        $('.coffeeType').prop('checked',false);
        $('.extras-checkbox').prop('checked',false);
        $('#input-money').val("");
    }

    function recalculateTotalPrice() {
        var selectedExtraOptions = $('.extras-checkbox:checked');
        var totalPrice = 0;

        if(selectedExtraOptions.length !== 0){
            for(var i = 0; i < selectedExtraOptions.length; i++){
                totalPrice = totalPrice + Number(selectedExtraOptions[i].getAttribute("price"));
            }
        }

        var selectedCoffee = $("input[name='coffeeType']:checked");

        if(selectedCoffee.length > 0){
            totalPrice = totalPrice + Number(selectedCoffee[0].getAttribute('price'));
        }

        $('#precio-total').html(totalPrice);

    }

    return {
        init: init
    }
}());