package com.example.productservice.service;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.request.CreateProductReq;
import com.example.productservice.dto.request.ProductFilter;
import com.example.productservice.entity.Product;

import java.util.List;

public interface ProductService {
    Product create(CreateProductReq createProductReq);

    List<ProductDto> search(ProductFilter productFilter);
}
