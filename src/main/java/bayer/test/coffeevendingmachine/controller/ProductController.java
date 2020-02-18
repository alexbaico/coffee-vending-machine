package bayer.test.coffeevendingmachine.controller;

import bayer.test.coffeevendingmachine.dto.ProductDTO;
import bayer.test.coffeevendingmachine.model.Inventory;
import bayer.test.coffeevendingmachine.model.Product;
import bayer.test.coffeevendingmachine.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;

    @GetMapping("/products")
    public ResponseEntity getAllProducts(){
        log.info("ProductController.getAllProducts");

        //TODO Send this to a @ProductService method
        Iterable<Product> all = productRepository.findAll();
        List<ProductDTO> result = new ArrayList<>();
        all.forEach(product -> result.add(ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getInventory().getStock())
                .type(product.getType()).build()));
        return ResponseEntity.ok(result);
    }

    @PostMapping("/products")
    public ResponseEntity addProduct(@RequestBody @Valid ProductDTO productDTO){
        log.info("ProductController.addProduct - productDTO [{}]", productDTO);

        //TODO Send this to a @ProductService method and do the corresponding validations of uniqueness by name-type-etc
        Product product = new Product();
        product.setDescription(productDTO.getDescription());
        product.setName(productDTO.getName());
        product.setType(productDTO.getType());
        product.setPrice(productDTO.getPrice());
        product.setInventory(new Inventory());
        product.getInventory().setStock(productDTO.getStock());
        product.getInventory().setProduct(product);

        productRepository.save(product);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity removeProduct(@PathVariable("productId")Long productId){
        log.info("ProductController.removeProduct - productId [{}]", productId);

        productRepository.deleteById(productId);

        return ResponseEntity.ok().build();
    }

}
