package bayer.test.coffeevendingmachine.controller;

import bayer.test.coffeevendingmachine.dto.OrderDTO;
import bayer.test.coffeevendingmachine.model.CoffeeOptionsEnum;
import bayer.test.coffeevendingmachine.model.CoffeeTypesEnum;
import bayer.test.coffeevendingmachine.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
@Slf4j
@RequiredArgsConstructor
public class VendingController {

    private final CoffeeService coffeeService;

    @GetMapping("/")
    public String home(Model model){
        log.info("VendingController.home");

        model.addAttribute("extraOptions", CoffeeOptionsEnum.values());
        model.addAttribute("coffeTypes", CoffeeTypesEnum.values());
        model.addAttribute("coffeeOrder", new OrderDTO());

        return "home";
    }

    @PostMapping("/order")
    public ResponseEntity orderCoffee(@Valid @RequestBody OrderDTO orderDTO){
        log.info("VendingController.orderCoffee - OrderDTO [{}]", orderDTO);

        coffeeService.orderCoffee(orderDTO);

        return ResponseEntity.ok().build();
    }
}
