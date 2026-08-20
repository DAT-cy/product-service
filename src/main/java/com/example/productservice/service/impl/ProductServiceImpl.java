package com.example.productservice.service.impl;

import com.example.productservice.dto.ProductDto;
import com.example.productservice.dto.request.CreateProductReq;
import com.example.productservice.dto.request.ProductFilter;
import com.example.productservice.entity.Category;
import com.example.productservice.entity.Product;
import com.example.productservice.exception.ApplicationException;
import com.example.productservice.mapper.ProductMapper;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    @Override
    public Product create(CreateProductReq createProductReq){
        Optional<Category> existCategory = categoryRepository.findById(createProductReq.getCategoryId());
        if(existCategory.isEmpty()){
            throw new ApplicationException("category not found");
        }
        Product createProduct = productMapper.mapProduct(createProductReq);
        productRepository.save(createProduct);
        return productRepository.save(createProduct);
    }

    @Override
    public List<ProductDto> search(ProductFilter productFilter) {

        List<Product> productList = productRepository.findAllById(productFilter.getIds());

        return productList.stream()
                .map(productMapper::mapDto)
                .toList();
    }


}
