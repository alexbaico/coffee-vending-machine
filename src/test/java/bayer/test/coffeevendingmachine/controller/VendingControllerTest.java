package bayer.test.coffeevendingmachine.controller;

import bayer.test.coffeevendingmachine.service.VendingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class VendingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private VendingService vendingService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void orderWithoutCoffeeTypeSelected() throws Exception {
        this.mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"money\": \"20\",\"options\":[\"MILK\"]}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void orderWithoutMoneyInputted() throws Exception {
        this.mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"coffeeType\": \"IRISH\",\"options\":[\"CHOCOLATE\"]}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void orderOK() throws Exception {
        this.mockMvc.perform(post("/order")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"coffeeType\": \"BLACK\",\"money\": \"30\",\"options\":[\"CACAO\", \"RON\"]}"))
                .andExpect(status().isOk());

        Mockito.verify(vendingService, Mockito.times(1)).orderCoffee(ArgumentMatchers.any());

    }

}
