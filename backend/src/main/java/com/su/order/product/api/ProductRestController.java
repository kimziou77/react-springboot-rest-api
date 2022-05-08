package com.su.order.product.api;

import com.su.order.product.dto.ProductDto;
import com.su.order.product.dto.ProductDtoMapper;
import com.su.order.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ProductRestController {

    private final ProductRepository productRepository;

    // 바우처 전체 조회
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto.Result>> productList(){
        var products = productRepository.findAll();
        var resultDto = products.stream()
                .map(ProductDtoMapper::asProductResult)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resultDto);
    }

}
