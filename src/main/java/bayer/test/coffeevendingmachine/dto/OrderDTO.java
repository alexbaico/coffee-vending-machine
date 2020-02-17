package bayer.test.coffeevendingmachine.dto;

import bayer.test.coffeevendingmachine.model.CoffeeOptionsEnum;
import bayer.test.coffeevendingmachine.model.CoffeeTypesEnum;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull(message = "Debe elegir al menos un tipo de café")
    private CoffeeTypesEnum coffeeType;

    private List<CoffeeOptionsEnum> options;

    @Min(value = 1, message = "Ingrese el dinero con el que va a pagar")
    @NotNull(message = "Ingrese el dinero con el que va a pagar")
    private Double money;
}
