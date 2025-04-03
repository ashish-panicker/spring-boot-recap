package in.stackroute.controllers;

import in.stackroute.domain.Product;
import in.stackroute.dto.ProductDto;
import in.stackroute.dto.ResponseDto;
import in.stackroute.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<ResponseDto> createProduct(@Valid @RequestBody ProductDto dto) {
//        Product product = new Product(dto.getProductName(), dto.getPrice(),  dto.getStock());
//        To be used if BindingResult parameter is supplied to the controller methods
//        if(result.hasErrors()){
//            Map<Object, Object> errors = new HashMap<>();
//            result.getFieldErrors()
//                    .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
//            var response = ResponseDto.builder()
//                    .message(errors)
//                    .status(HttpStatus.BAD_REQUEST)
//                    .build();
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        }
        var product = Product.builder()
                .productName(dto.getProductName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .build();
        var res = productService.save(product);
        var response = ResponseDto.builder()
                .message("Product created with id: " + res.getProductId())
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationErrors(MethodArgumentNotValidException e){
        Map<Object, Object> errors = new HashMap<>();
        e.getFieldErrors()
                .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
        var response = ResponseDto.builder()
                .message(errors)
                .status(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
