package bayer.test.coffeevendingmachine.repository;

import bayer.test.coffeevendingmachine.model.Inventory;
import bayer.test.coffeevendingmachine.model.Product;
import bayer.test.coffeevendingmachine.model.ProductType;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory, Long> {

    List<Inventory> findByProductType(ProductType type);

    List<Inventory> findByProductIn(List<Product> product);
}
