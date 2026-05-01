package com.springboot.jpa.service.impl;

import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.data.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@Slf4j
class ProductServiceTestV1 {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    // - @MockitoBean와 차이점
    //      - @MockitoBean은 가짜 객체를 생성해서 주입까지 해줌
    //      - 말 그대로 가짜 빈을 만듦
    //      - 컨트롤러 테스트에서 사용했던 이유는 스프링 컨테이너를 띄우기 때문에 빈으로 등록하면 Controller에 의존성 주입이 일어남
    //      - 여기(단위 테스트)에서는 스프링 컨테이너를 띄우지 않기 때문에 가짜 객체를 생성하는 것
    private ProductServiceImpl productService;

    @BeforeEach
    void setUpTest() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void getProductTest() {
        Product givenProduct = new Product(123L, "펜", 1000, 1234, LocalDateTime.now(), LocalDateTime.now());

        given(productRepository.findById(123L))
                .willReturn(Optional.of(givenProduct));
//        Mockito.when(productRepository.findById(123L))
//                .thenReturn(Optional.of(givenProduct));

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
        // - any(Product.class)
        //      - 어떤 Product 클래스가 인자로 들어와도
        // - will()
        //      - 수행할 동작
        // - returnsFirstArg()
        //      - 메서드에 전달된 첫번째 인자를 결과로 반환해라
        // - 즉, 메서드의 동작만 확인하고 싶은 경우

//        Mockito.when(productRepository.save(any(Product.class)))
//                .then(returnsFirstArg());

        ProductResponseDto productResponseDto = productService.saveProduct(new ProductDto("펜", 1000, 1234));

        assertThat(productResponseDto.getName()).isEqualTo("펜");
        assertThat(productResponseDto.getPrice()).isEqualTo(1000);
        assertThat(productResponseDto.getStock()).isEqualTo(1234);

        log.info("productResponseDto={}", productResponseDto);
        // - number가 null인 것을 확인할 수 있음
    }
}