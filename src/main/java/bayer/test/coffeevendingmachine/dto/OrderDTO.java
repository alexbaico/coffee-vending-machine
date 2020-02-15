package bayer.test.coffeevendingmachine.dto;

import bayer.test.coffeevendingmachine.model.CoffeeOptionsEnum;
import bayer.test.coffeevendingmachine.model.CoffeeTypesEnum;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull(message = "Debe elegir al menos un tipo de café")
    private CoffeeTypesEnum coffeeType;

    private List<CoffeeOptionsEnum> options;

    @Pattern(regexp = "^[0-9]+(\\.[0-9]*)?$", message="Ingrese dinero con números y decimales separados por \".\"")
    private String money;
}
