package bayer.test.coffeevendingmachine.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDTO {

    @NotNull(message = "Debe elegir al menos un tipo de café")
    private Long coffeeTypeId;

    private List<Long> optionsIds;

    @Min(value = 1, message = "Ingrese el dinero con el que va a pagar")
    @NotNull(message = "Ingrese el dinero con el que va a pagar")
    private Double money;
}
