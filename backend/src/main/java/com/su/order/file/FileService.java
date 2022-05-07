package com.su.order.file;

import com.su.order.product.dto.ProductDto;

import java.io.IOException;

public interface FileService {
    String saveProduct(ProductDto.Create dto) throws IOException;
}
