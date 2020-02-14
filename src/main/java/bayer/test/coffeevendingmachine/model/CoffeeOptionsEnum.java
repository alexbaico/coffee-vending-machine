package bayer.test.coffeevendingmachine.model;

import lombok.Getter;

@Getter
public enum CoffeeOptionsEnum {
    MILK("Leche", 10D), CACAO("Cacao", 3D), CHOCOLATE("Chocolate",5.5D), RON("Ron", 15D), CINNAMON("Canela", 3D);

    private String description;
    private Double extraPrice;

    CoffeeOptionsEnum(String description, Double extraPrice){
        this.description = description;
        this.extraPrice = extraPrice;
    }
}
