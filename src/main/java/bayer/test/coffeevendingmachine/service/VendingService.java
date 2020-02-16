package bayer.test.coffeevendingmachine.service;

import bayer.test.coffeevendingmachine.dto.OrderDTO;
import bayer.test.coffeevendingmachine.exception.NotEnoughMoneyException;
import bayer.test.coffeevendingmachine.model.CoffeeOptionsEnum;
import bayer.test.coffeevendingmachine.model.CoffeeTypesEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VendingService {

    /**
     * Calculates the total price of the coffe with the extra options selected
     * @param coffeeType
     * @param options
     * @return the total price of the coffee
     */
    private Double calculatePrice(CoffeeTypesEnum coffeeType, List<CoffeeOptionsEnum> options){
        log.info("VendingService.calculatePrice - options [{}]", options);

        return coffeeType.getPrice() + (CollectionUtils.isEmpty(options) ? 0 : options.stream().mapToDouble(CoffeeOptionsEnum::getExtraPrice).sum());
    }

    /**
     * Orders the coffee, calculating the total price and checking if payed money is enough
     *
     * @param orderDTO
     * @return the change (difference between payed money and total price)
     */
    public Double orderCoffee(OrderDTO orderDTO) {
        log.info("VendingService.orderCoffee - orderDTO [{}]", orderDTO);

        Double totalPrice = calculatePrice(orderDTO.getCoffeeType(), orderDTO.getOptions());
        Double moneyPayed = Double.valueOf(orderDTO.getMoney());
        if(moneyPayed < totalPrice){
            throw new NotEnoughMoneyException("Dinero insuficiente para pagar el café");
        }

        Double change = moneyPayed - totalPrice;

        //Save orders here for audit?

        log.info("Coffee delivered correctly");

        return change;
    }
}
