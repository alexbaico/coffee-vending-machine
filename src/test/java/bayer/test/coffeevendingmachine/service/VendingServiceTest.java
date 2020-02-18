package bayer.test.coffeevendingmachine.service;

import bayer.test.coffeevendingmachine.dto.OrderDTO;
import bayer.test.coffeevendingmachine.exception.NotEnoughMoneyException;
import bayer.test.coffeevendingmachine.model.Product;
import bayer.test.coffeevendingmachine.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class VendingServiceTest {

    @InjectMocks
    private VendingService target;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private InventoryService inventoryService;

    @Test(expected = NotEnoughMoneyException.class)
    public void orderCoffeeTestPaymentNotEnough(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCoffeeTypeId(1L);
        orderDTO.setOptionsIds(Arrays.asList(2L,3L));

        Product productMock1 = new Product();
        productMock1.setPrice(5D);

        Product productMock2 = new Product();
        productMock2.setPrice(6D);

        Product productMock3 = new Product();
        productMock3.setPrice(8D);

        Mockito.when(productRepository.findAllById(ArgumentMatchers.any())).thenReturn(Arrays.asList(productMock1, productMock2, productMock3));

        orderDTO.setMoney(15D);

        target.orderCoffee(orderDTO);

        Assert.fail("NotEnoughMoneyException expected but hasn't been thrown");
    }

    @Test
    public void orderCoffeeTestPaymentExact(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCoffeeTypeId(1L);
        orderDTO.setOptionsIds(Arrays.asList(2L,3L));

        Product productMock1 = new Product();
        productMock1.setPrice(3D);

        Product productMock2 = new Product();
        productMock2.setPrice(6D);

        Product productMock3 = new Product();
        productMock3.setPrice(6D);

        Mockito.when(productRepository.findAllById(ArgumentMatchers.any())).thenReturn(Arrays.asList(productMock1, productMock2, productMock3));

        orderDTO.setMoney(15D);

        Double change = target.orderCoffee(orderDTO);

        Assert.assertEquals("Change is 0", "0.0", change.toString());
    }

    @Test
    public void orderCoffeeTestPaymentExceeded(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCoffeeTypeId(1L);
        orderDTO.setOptionsIds(Arrays.asList(2L,3L));

        Product productMock1 = new Product();
        productMock1.setPrice(2D);

        Product productMock2 = new Product();
        productMock2.setPrice(8D);

        Product productMock3 = new Product();
        productMock3.setPrice(10D);

        Mockito.when(productRepository.findAllById(ArgumentMatchers.any())).thenReturn(Arrays.asList(productMock1, productMock2, productMock3));

        orderDTO.setMoney(25.5D);

        Double change = target.orderCoffee(orderDTO);

        Assert.assertEquals("Change is 5.5", "5.5", change.toString());
    }
}
