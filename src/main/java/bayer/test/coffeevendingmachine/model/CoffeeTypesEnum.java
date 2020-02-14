package bayer.test.coffeevendingmachine.model;

import lombok.Getter;

@Getter
public enum CoffeeTypesEnum {
    CAPPUCCINO("Cappuccino", 10D), ESPRESSO("Espresso", 3D), BLACK("Black",5.5D), IRISH("Irish ", 15D), MOCHA("Mocha", 3D);

    private String description;
    private Double price;

    CoffeeTypesEnum(String description, Double price){
        this.description = description;
        this.price = price;
    }
}
