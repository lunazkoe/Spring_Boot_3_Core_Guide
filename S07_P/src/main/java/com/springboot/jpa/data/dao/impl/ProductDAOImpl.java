package com.springboot.jpa.data.dao.impl;

import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        Product selectedProduct = productRepository
                .findById(number).orElseThrow(() -> new EntityNotFoundException("해당 id를 가진 상품이 존재하지 않습니다." + number));
        return selectedProduct;
    }

    @Override
    public Product updateProduct(Long number, String name) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);
        // - findById로 데이터를 가져옴
        // - 영속성 컨텍스트에 추가

        Product updatedProduct = null;
        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product);
            // - save에 내부적으로 @Transactional이 붙어있음
            // - save 작업이 마치고 flush()를 수행
            // - 이때 영속성 컨텍스트에 있는 데이터의 변경이 감지되면 업데이트 쿼리를 실행함
        } else {
            throw new Exception("해당 id를 가진 상품이 존재하지 않습니다." + number);
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            productRepository.delete(product);
        } else {
            throw new Exception("해당 id를 가진 상품이 존재하지 않습니다." + number);
        }
    }
}
