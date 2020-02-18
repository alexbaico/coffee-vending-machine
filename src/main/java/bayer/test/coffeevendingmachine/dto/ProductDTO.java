package bayer.test.coffeevendingmachine.dto;

import bayer.test.coffeevendingmachine.model.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @Null
    private Long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Min(1)
    private Double price;

    @NotNull
    private ProductType type;

    @NotNull
    private Integer stock;
}
