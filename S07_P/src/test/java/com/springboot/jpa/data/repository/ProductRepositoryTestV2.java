package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
public class ProductRepositoryTestV2 {

    @Autowired
    ProductRepository productRepository;

    @Test
    void basicCRUDTest() {
        // - Create
        // given
        Product givenProduct = Product.builder()
                .name("노트")
                .price(1000)
                .stock(500)
                .build();
        log.info("givenProduct.getNumber()={}", givenProduct.getNumber());

        // when
        Product savedProduct = productRepository.save(givenProduct);
        log.info("givenProduct.getNumber()={}", givenProduct.getNumber());
        log.info("savedProduct.getNumber()={}", savedProduct.getNumber());
        // - 놀라운 사실: givenProduct와 savedProduct는 동일한 객체를 가리키고 있다

        // then
        assertThat(savedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(savedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(savedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(savedProduct.getStock()).isEqualTo(givenProduct.getStock());

        // - Read
        // when
        Product selectedProduct = productRepository.findById(savedProduct.getNumber()).orElseThrow(RuntimeException::new);

        // then
        assertThat(selectedProduct.getNumber()).isEqualTo(givenProduct.getNumber());
        assertThat(selectedProduct.getName()).isEqualTo(givenProduct.getName());
        assertThat(selectedProduct.getPrice()).isEqualTo(givenProduct.getPrice());
        assertThat(selectedProduct.getStock()).isEqualTo(givenProduct.getStock());

        // - Update
        // when
        Product foundProduct = productRepository.findById(selectedProduct.getNumber()).orElseThrow(RuntimeException::new);
        foundProduct.setName("장난감");
        Product updatedProduct = productRepository.save(foundProduct);

        // then
        assertThat(updatedProduct.getName()).isEqualTo("장난감");

        // - Delete
        // when
        productRepository.delete(updatedProduct);

        // then
        assertThat( productRepository.findById(updatedProduct.getNumber()).isPresent()).isFalse();
    }
}
