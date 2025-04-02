package in.stackroute.service;

import in.stackroute.domain.Product;
import in.stackroute.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Product update(Product p) {
        return productRepository.save(p);
    }

    @Override
    public Optional<Product> findIfProductIsInStock(String id, int quantity) {
        return productRepository.findProductInStock(id, quantity);
    }
}
