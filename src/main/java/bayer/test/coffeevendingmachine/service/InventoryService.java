package bayer.test.coffeevendingmachine.service;

import bayer.test.coffeevendingmachine.dto.InventoryDTO;
import bayer.test.coffeevendingmachine.model.Inventory;
import bayer.test.coffeevendingmachine.model.Product;
import bayer.test.coffeevendingmachine.model.ProductType;
import bayer.test.coffeevendingmachine.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<Inventory> findAllByProductType(ProductType productType){
        log.info("InventoryService.findAllByProductType - Finding Inventories with stock and of productType [{}]", productType);

        return inventoryRepository.findByProductType(productType);
    }

    /**
     * Reduces the inventories stock by 1 unit of all the products received by parameter
     *
     * @param products
     */
    public void reduceInventory(Iterable<Product> products) {
        log.info("InventoryService.reduceInventory - Reducing Inventories stock of products [{}]", products);

        List<Inventory> inventories = inventoryRepository.findByProductIn(StreamSupport.stream(products.spliterator(), false).collect(Collectors.toList()));

        inventories.forEach(inventory -> inventory.setStock(inventory.getStock()-1));

        inventoryRepository.saveAll(inventories);
    }

    public List<InventoryDTO> findAll() {
        log.info("InventoryService.findAll");

        return StreamSupport.stream(inventoryRepository.findAll().spliterator(),false).map(inventory -> InventoryDTO.builder()
                .id(inventory.getId())
                .productId(inventory.getProduct().getId())
                .productName(inventory.getProduct().getName())
                .productType(inventory.getProduct().getType().name())
                .stock(inventory.getStock())
                .build()
                ).collect(Collectors.toList());
    }

    /**
     * Adds stock to the inventory with the id received by parameter
     * if stock parameter is null, then adds 1 unit to the stock
     *
     * @param inventoryId
     * @param stock
     */
    public void addStock(Long inventoryId, Integer stock) {
        log.info("InventoryService.addStock - inventoryId [{}] stock [{}]", inventoryId, stock);

        Optional<Inventory> inventoryOptional = inventoryRepository.findById(inventoryId);

        if(!inventoryOptional.isPresent()){
            log.error("Trying to update stock of inventory id [{}] not present in database", inventoryId);
            throw new EntityNotFoundException(String.format("Not found inventory with id [%s]", inventoryId));
        }

        Inventory inventory = inventoryOptional.get();

        if(stock != null) {
            inventory.addStock(stock);
        } else {
            inventory.addStock(1);
        }
        inventoryRepository.save(inventory);
    }
}
