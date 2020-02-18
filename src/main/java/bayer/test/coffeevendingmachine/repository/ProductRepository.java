package bayer.test.coffeevendingmachine.repository;

import bayer.test.coffeevendingmachine.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
