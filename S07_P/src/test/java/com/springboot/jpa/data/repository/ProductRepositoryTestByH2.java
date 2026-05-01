package com.springboot.jpa.data.repository;

import com.springboot.jpa.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
//@ActiveProfiles("test")
// - 이거 사용하면 application-test로 돌릴 수 있음
@DataJpaTest
// - @Transactional이 포함되어 있어. 롤백을 수행함
// - H2를 임베디드로 설정해서 사용함 => 그래도 의존성은 반드시 필요함!!
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
// - ANY: H2 임베디드 모드
// - NONE: 애플리케이션이 실제로 사용하는 데이터베이스 사용
public class ProductRepositoryTestByH2 {

    @Autowired
    ProductRepository productRepository;

    @Test
    void saveTest() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        // when
        Product savedProduct = productRepository.save(product);

        // then
        assertThat(savedProduct.getName()).isEqualTo("펜");
        assertThat(savedProduct.getPrice()).isEqualTo(1000);
        assertThat(savedProduct.getStock()).isEqualTo(1000);
    }

    @Test
    void selectTest() {
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        Product savedProduct = productRepository.saveAndFlush(product);

        // when
        Product foundProduct = productRepository.findById(savedProduct.getNumber()).get();

        // then
        assertThat(foundProduct.getNumber()).isEqualTo(savedProduct.getNumber());
        assertThat(foundProduct.getName()).isEqualTo(savedProduct.getName());
        assertThat(foundProduct.getPrice()).isEqualTo(savedProduct.getPrice());
        assertThat(foundProduct.getStock()).isEqualTo(savedProduct.getStock());
    }

    @Test
    void saveWithRealDB() {
        // - 현재 @DataJpaTest에 @Transactional이 들어있어서 테스트 후 데이터를 롤백함
        // given
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(1000);

        // when
        Product savedProduct = productRepository.save(product);

        // then
        assertThat(savedProduct.getName()).isEqualTo("펜");
        assertThat(savedProduct.getPrice()).isEqualTo(1000);
        assertThat(savedProduct.getStock()).isEqualTo(1000);
    }
}
