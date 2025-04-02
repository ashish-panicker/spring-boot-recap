package in.stackroute.service;

import in.stackroute.domain.Product;

import java.util.Optional;

public interface ProductService {

    Product save(Product p);
    Product update(Product p);
    Optional<Product> findIfProductIsInStock(String id, int quantity);
}
