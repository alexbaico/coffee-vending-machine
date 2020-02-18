package bayer.test.coffeevendingmachine.controller;

import bayer.test.coffeevendingmachine.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/inventories")
    public ResponseEntity getAllInventories(){
        log.info("InventoryController.getAllInventories");
        return ResponseEntity.ok(inventoryService.findAll());
    }

    @PutMapping("/inventories/{inventoryId}")
    public ResponseEntity addStock(@PathVariable("inventoryId")Long inventoryId, @RequestParam(value = "stock", required = false)Integer stock){
        log.info("InventoryController.addStock - inventoryId [{}] stock [{}]", inventoryId, stock);

        inventoryService.addStock(inventoryId, stock);

        return ResponseEntity.ok().build();
    }

}
