package bayer.test.coffeevendingmachine.service;

import bayer.test.coffeevendingmachine.dto.OrderDTO;
import bayer.test.coffeevendingmachine.exception.NotEnoughMoneyException;
import bayer.test.coffeevendingmachine.model.Product;
import bayer.test.coffeevendingmachine.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class VendingService {

    private final InventoryService inventoryService;
    private final ProductRepository productRepository;

    /**
     *  Calculates the total price of the coffe with the extra options selected
     *
     * @param products
     * @return the total price of the coffee
     */
    private Double calculatePrice(Iterable<Product> products) {
        log.info("VendingService.calculatePrice - products [{}]", products);

        return StreamSupport.stream(products.spliterator(),false).mapToDouble(Product::getPrice).sum();
    }

    /**
     * Orders the coffee, calculating the total price and checking if payed money is enough
     * then reduces the inventory stock of the products in the order
     *
     * @param orderDTO
     * @return the change (difference between payed money and total price)
     */
    public Double orderCoffee(OrderDTO orderDTO) {
        log.info("VendingService.orderCoffee - orderDTO [{}]", orderDTO);

        List<Long> ids = Stream.concat(Stream.of(orderDTO.getCoffeeTypeId()), CollectionUtils.isEmpty(orderDTO.getOptionsIds()) ? Stream.empty() : orderDTO.getOptionsIds().stream()).collect(Collectors.toList());

        Iterable<Product> products = productRepository.findAllById(ids);

        Double totalPrice = calculatePrice(products);
        Double moneyPayed = orderDTO.getMoney();
        if(moneyPayed < totalPrice){
            throw new NotEnoughMoneyException("Dinero insuficiente para pagar el café");
        }

        Double change = moneyPayed - totalPrice;

        //Reduce the inventory of products in the order
        inventoryService.reduceInventory(products);

        //Save orders here for audit?

        log.info("Coffee delivered correctly");

        return change;
    }

}
