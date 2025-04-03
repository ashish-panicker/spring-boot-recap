package in.stackroute.controllers;

import in.stackroute.domain.Product;
import in.stackroute.dto.ProductDto;
import in.stackroute.dto.ResponseDto;
import in.stackroute.exceptions.ProductNotFoundException;
import in.stackroute.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        var response = getResponseDto("Product created with id: " + res.getProductId(),
                HttpStatus.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //    GET /products/pid1
    @GetMapping("/{pid}")
    public ResponseEntity<ResponseDto> getProductById(@PathVariable String pid) {
        Product product = productService
                            .findById(pid)
                            .orElseThrow(() -> new ProductNotFoundException("Product with id " + pid
                                    + " not found."));
        var response = getResponseDto(product, HttpStatus.OK);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDto> handleValidationErrors(ProductNotFoundException e) {
        var response = getResponseDto(e.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<Object, Object> errors = new HashMap<>();
        e.getFieldErrors()
                .forEach(fe -> errors.put(fe.getField(), fe.getDefaultMessage()));
        var response = getResponseDto(errors, HttpStatus.BAD_REQUEST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    private static ResponseDto getResponseDto(Object message, HttpStatus status) {
        var response = ResponseDto.builder()
                .message(message)
                .status(status)
                .build();
        return response;
    }
}
