package bayer.test.coffeevendingmachine.dto;

import bayer.test.coffeevendingmachine.model.CoffeeOptionsEnum;
import bayer.test.coffeevendingmachine.model.CoffeeTypesEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull
    private CoffeeTypesEnum coffeeType;
    private List<CoffeeOptionsEnum> options;
}
