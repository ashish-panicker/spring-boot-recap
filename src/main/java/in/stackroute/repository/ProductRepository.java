package in.stackroute.repository;

import in.stackroute.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    Optional<Product> findByProductIdAndStockGreaterThan(String id, int stock);

    @Query(name = "Product.findIfProductIsInStock")
    Optional<Product> findProductInStock(@Param("id") String id, @Param("q") int quantity);
}
