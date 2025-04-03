package in.stackroute.controllers;

import in.stackroute.domain.Product;
import in.stackroute.dto.ProductDto;
import in.stackroute.dto.ResponseDto;
import in.stackroute.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
//    var x = 10; Cannot be used to declare class level attributes.

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Create a new product
     * POST /products
     * {
     * "productName":"",
     * "price":"",
     * "stock":""
     * }
     */
    @PostMapping
    public ResponseEntity<ResponseDto> createProduct(@RequestBody ProductDto dto) {
//        Product product = new Product(dto.getProductName(), dto.getPrice(),  dto.getStock());
        var product = Product.builder()
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
        var result = productService.save(product);
        var response = ResponseDto.builder()
                .message("Product created with id: " +result.getProductId())
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
