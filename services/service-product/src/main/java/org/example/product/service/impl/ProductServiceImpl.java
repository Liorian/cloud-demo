package org.example.product.service.impl;

import org.example.product.bean.Product;
import org.example.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setProductName("华为手机");
        product.setPrice(new BigDecimal(2999));
        product.setNum(2);
        return product;
    }
}
