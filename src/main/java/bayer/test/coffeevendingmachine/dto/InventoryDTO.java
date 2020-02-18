package bayer.test.coffeevendingmachine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {

    private Long id;

    private Long productId;

    private String productName;

    private String productType;

    private Integer stock;
}
