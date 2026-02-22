package com.teste.spring.view.controller;

import com.teste.spring.models.Product;
import com.teste.spring.service.ProductService;
import com.teste.spring.shared.ProductDTO;
import com.teste.spring.view.model.ProductRequest;
import com.teste.spring.view.model.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> selectAllProducts(){
        List<ProductDTO> products = productService.selectAllProducts();

        ModelMapper mapper = new ModelMapper();

        List<ProductResponse> response = products.stream()
                .map(productDto -> mapper.map(productDto, ProductResponse.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProductResponse>> selectProductById(@PathVariable Integer id){
        try{
            Optional<ProductDTO> dto = productService.getProductById(id);

            ProductResponse product = new ModelMapper().map(dto.get(), ProductResponse.class);

            return new ResponseEntity<>(Optional.of(product), HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productReq){
        ModelMapper mapper = new ModelMapper();

        ProductDTO productDto = mapper.map(productReq, ProductDTO.class);

        productDto = productService.addProduct(productDto);

        return new ResponseEntity<>(mapper.map(productDto, ProductResponse.class), HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productReq, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();

        ProductDTO productDto = mapper.map(productReq, ProductDTO.class);

        productDto = productService.updateProduct(id, productDto);

        return new ResponseEntity<>(mapper.map(productDto, ProductResponse.class), HttpStatus.OK);
    }
}
