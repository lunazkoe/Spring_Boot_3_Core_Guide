package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.repository.ProductRepository;
import com.springboot.jpa.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({SpringExtension.class})
// - 스프링의 기능을 사용한다는 의미
// - JUnit 5의 테스트에서 스프링 테스트 컨텍스트를 사용하도록 설정
//      - 이게 없으면 아래 @MockitoBean / @Autowired가 실행될 수 없음 (스프링이 지원해주는 기능이므로)
//      - 그래도 필요한 것만 띄우기 때문에 @SpringBootTest보다는 더 빠름
@Import({ProductServiceImpl.class})
// - ProductServiceImpl을 스프링 빈으로 등록
// - @configuration이 붙어있으면 하위 빈을 추가적으로 빈으로 등록
// - 일반 Component는 그 자체를 빈으로 등록
class ProductServiceTestV2 {

    @MockitoBean
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    void getProductTest() {
        Product givenProduct = new Product(123L, "펜", 1000, 1234, LocalDateTime.now(), LocalDateTime.now());

        given(productRepository.findById(123L))
                .willReturn(Optional.of(givenProduct));

        ProductResponseDto productResponseDto = productService.getProduct(123L);

        assertThat(productResponseDto.getNumber()).isEqualTo(123L);
        assertThat(productResponseDto.getName()).isEqualTo("펜");
        assertThat(productResponseDto.getPrice()).isEqualTo(1000);
        assertThat(productResponseDto.getStock()).isEqualTo(1234);

        verify(productRepository).findById(123L);
    }

    @Test
    void saveProductTest() {

        given(productRepository.save(any(Product.class)))
                .will(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(new ProductDto("펜", 1000, 1234));

        assertThat(productResponseDto.getName()).isEqualTo("펜");
        assertThat(productResponseDto.getPrice()).isEqualTo(1000);
        assertThat(productResponseDto.getStock()).isEqualTo(1234);
    }

    @Test
    void changeProductNameTest() throws Exception {
        Product givenProduct = new Product(123L, "펜", 1000, 1234, LocalDateTime.now(), LocalDateTime.now());

        given(productRepository.findById(123L))
                .willReturn(Optional.of(givenProduct));

        given(productRepository.save(any(Product.class)))
                .will(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.changeProductName(123L, "노트");

        assertThat(productResponseDto.getName()).isEqualTo("노트");
        assertThat(productResponseDto.getPrice()).isEqualTo(1000);
        assertThat(productResponseDto.getStock()).isEqualTo(1234);
    }
}