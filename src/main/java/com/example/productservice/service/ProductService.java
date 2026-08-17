package com.example.productservice.service;

import com.example.productservice.dto.request.CreateProductReq;
import com.example.productservice.entity.Product;

public interface ProductService {
    Product create(CreateProductReq createProductReq);
}
