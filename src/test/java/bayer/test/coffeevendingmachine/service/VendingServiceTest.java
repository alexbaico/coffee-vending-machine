package bayer.test.coffeevendingmachine.service;

import bayer.test.coffeevendingmachine.dto.OrderDTO;
import bayer.test.coffeevendingmachine.exception.NotEnoughMoneyException;
import bayer.test.coffeevendingmachine.model.CoffeeOptionsEnum;
import bayer.test.coffeevendingmachine.model.CoffeeTypesEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class VendingServiceTest {

    @InjectMocks
    private VendingService target;

    @Test(expected = NotEnoughMoneyException.class)
    public void orderCoffeeTestPaymentNotEnough(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCoffeeType(CoffeeTypesEnum.BLACK);
        orderDTO.setOptions(Arrays.asList(CoffeeOptionsEnum.CACAO,CoffeeOptionsEnum.CHOCOLATE, CoffeeOptionsEnum.CINNAMON));

        double price = orderDTO.getCoffeeType().getPrice() +  orderDTO.getOptions().stream().mapToDouble(CoffeeOptionsEnum::getExtraPrice).sum();
        orderDTO.setMoney(Double.valueOf(price - 5).toString());

        target.orderCoffee(orderDTO);

        Assert.fail("NotEnoughMoneyException expected but hasn't been thrown");
    }

    @Test
    public void orderCoffeeTestPaymentExact(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCoffeeType(CoffeeTypesEnum.CAPPUCCINO);
        orderDTO.setOptions(Arrays.asList(CoffeeOptionsEnum.CACAO,CoffeeOptionsEnum.MILK, CoffeeOptionsEnum.RON));

        double price = orderDTO.getCoffeeType().getPrice() +  orderDTO.getOptions().stream().mapToDouble(CoffeeOptionsEnum::getExtraPrice).sum();
        orderDTO.setMoney(Double.valueOf(price).toString());

        Double change = target.orderCoffee(orderDTO);

        Assert.assertEquals("Change is 0", "0.0", change.toString());
    }

    @Test
    public void orderCoffeeTestPaymentExceeded(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCoffeeType(CoffeeTypesEnum.IRISH);
        orderDTO.setOptions(Arrays.asList(CoffeeOptionsEnum.MILK,CoffeeOptionsEnum.CHOCOLATE));

        double price = orderDTO.getCoffeeType().getPrice() +  orderDTO.getOptions().stream().mapToDouble(CoffeeOptionsEnum::getExtraPrice).sum();
        orderDTO.setMoney(Double.valueOf(price + 5.5D).toString());

        Double change = target.orderCoffee(orderDTO);

        Assert.assertEquals("Change is 5.5", "5.5", change.toString());
    }
}
