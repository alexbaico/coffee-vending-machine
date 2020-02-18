package bayer.test.coffeevendingmachine.controller;

import bayer.test.coffeevendingmachine.dto.BaseResponseDTO;
import bayer.test.coffeevendingmachine.dto.OrderDTO;
import bayer.test.coffeevendingmachine.exception.NotEnoughMoneyException;
import bayer.test.coffeevendingmachine.model.Inventory;
import bayer.test.coffeevendingmachine.model.Product;
import bayer.test.coffeevendingmachine.model.ProductType;
import bayer.test.coffeevendingmachine.service.InventoryService;
import bayer.test.coffeevendingmachine.service.VendingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class VendingController {

    private final VendingService vendingService;
    private final InventoryService inventoryService;

    @GetMapping("/")
    public String home(Model model){
        log.info("VendingController.home");

        List<Product> coffeeTypes = inventoryService.findAllByProductType(ProductType.COFFEE).stream().map(Inventory::getProduct).collect(Collectors.toList());
        List<Product> extrasTypes = inventoryService.findAllByProductType(ProductType.EXTRA_OPTION).stream().map(Inventory::getProduct).collect(Collectors.toList());

        model.addAttribute("extraOptions", extrasTypes);
        model.addAttribute("outOfCoffee", coffeeTypes.stream().allMatch(product -> product.getInventory().getStock() == 0));
        model.addAttribute("coffeeTypes", coffeeTypes);
        model.addAttribute("coffeeOrder", new OrderDTO());

        return "home";
    }

    @PostMapping("/order")
    public ResponseEntity orderCoffee(@Valid @RequestBody OrderDTO orderDTO){
        log.info("VendingController.orderCoffee - OrderDTO [{}]", orderDTO);

        try {
            Double change = vendingService.orderCoffee(orderDTO);
            return ResponseEntity.ok("Muchas Gracias! Disfrute su café. Su vuelto: $ " +  change);
        } catch (NotEnoughMoneyException e){
            log.warn("Tried to get paid with less money than the total price");
            BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
            baseResponseDTO.addError("money", e.getMessage());
            return ResponseEntity.badRequest().body(baseResponseDTO);
        }
    }
}
