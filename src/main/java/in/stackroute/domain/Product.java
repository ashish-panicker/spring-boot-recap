package in.stackroute.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_details") // Create the table with the name product_details
@NamedQueries({
    @NamedQuery(
            query = "SELECT p FROM Product p WHERE p.productId = :id and p.stock >= :q",
            name = "Product.findIfProductIsInStock")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id // Every entity class must have an @Id attribute
    @GeneratedValue(strategy = GenerationType.UUID) // Automatically generate the UUID value for this field
    @Column(name = "p_id") // Name of the column in the table
    private String productId;

    @Column(name = "p_name", nullable = false)// Cannot have null values
    private String productName;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;
}
