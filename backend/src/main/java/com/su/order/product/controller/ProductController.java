package com.su.order.product.controller;

import com.su.order.file.FileService;
import com.su.order.product.dto.ProductDto;
import com.su.order.product.dto.ProductDtoMapper;
import com.su.order.product.entity.Product;
import com.su.order.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.stream.Collectors;

import static com.su.order.product.dto.ProductDtoMapper.asProductModel;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final FileService fileService;

    /* 메인 페이지 */
    @GetMapping("/products")
    public String productsPage(Model model) {
        var products = productService.findAllProducts();
        var resultDto = products.stream()
                .map(ProductDtoMapper::asProductResult)
                .collect(Collectors.toList());

        model.addAttribute("products", resultDto);
        return "product/product-list";
    }
    
    /* 상품 생성 페이지 */
    @GetMapping("/products/product")
    public String createProductPage(Model model){
        model.addAttribute("product", new ProductDto.Create());
        return "product/product-create";
    }

    /* 상품 생성 */
    @PostMapping("/products/product")
    public String createProduct(ProductDto.Create dto) throws IOException {
        Product product = asProductModel(dto);

        if(!dto.getImageMultipart().isEmpty()){
            var savedFileName = fileService.saveProduct(dto);
            product.setImagePath(savedFileName);
        }

        productService.createProduct(product);
        return "redirect:/products";
    }

    /* 상품 삭제 : 제약조건 - 주문목록에 있으면 삭제 불가 */
    @DeleteMapping("products/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/products";
    }

}
