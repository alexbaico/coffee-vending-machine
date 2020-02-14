package bayer.test.coffeevendingmachine.service;

import bayer.test.coffeevendingmachine.dto.OrderDTO;
import bayer.test.coffeevendingmachine.model.CoffeeOptionsEnum;
import bayer.test.coffeevendingmachine.model.CoffeeTypesEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CoffeeService {

    public Double calculatePrice(CoffeeTypesEnum coffeeType, List<CoffeeOptionsEnum> options){
        log.info("CoffeeService.calculatePrice - options [{}]", options);

        return coffeeType.getPrice() + options.stream().mapToDouble(CoffeeOptionsEnum::getExtraPrice).sum();
    }

    public void orderCoffee(OrderDTO orderDTO) {
        log.info("CoffeeService.orderCoffee - orderDTO [{}]", orderDTO);

        calculatePrice(orderDTO.getCoffeeType(),orderDTO.getOptions());

        log.info("Thanks! Enjoy your coffee");

        //Save orders here for audit?
    }
}
