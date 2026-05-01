package com.springboot.jpa.controller;

import com.google.gson.Gson;
import com.springboot.jpa.data.dto.ProductDto;
import com.springboot.jpa.data.dto.ProductResponseDto;
import com.springboot.jpa.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 슬라이스 테스트
// - @WebMvcTest 어노테이션을 사용한 테스트를 슬라이스 테스트라고 부름
// - 단위 테스트 < 슬라이스 테스트 < 통합 테스트
// - 각 레이어별로 나누어 테스트를 진행한다는 의미
// - 컨트롤러는 웹과 맞닿아있어 외부 요인을 차단하고 하면 의미가 없기 때문에 주로 슬라이스 테스트를 사용함

@WebMvcTest(ProductController.class)
// - @WebMvcTest(테스트 대상 클래스.class)
//      - 대상 클래스만 로드해 테스트를 수행할 수 있음
//      - 추가하지 않으면 @Controller / @RestController / @ControllerAdvice 등의 컨트롤러 관련 빈 객체가 모두 로드됨
//      - @SpringBootTest보다 가볍게 테스트하기 위해서 사용함
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    // - 컨트롤러의 API를 테스트하기 위해 사용되는 객체
    // - 가상의 MVC 환경에서 모의 HTTP 서블릿을 요청하는 유틸리티 클래스
    // - 서블릿 컨테이너 구동 없이 사용 가능

    @MockitoBean
    // - 실제 빈 객체가 아닌 Mock 객체를 생성해서 주입
    // - 실제 객체가 아니기 때문에 실제 행위를 수행하지 않음
    //      - 때문에 given()을 통해서 메서드의 동작을 정의해야함
    ProductServiceImpl productService;

    @Test
    // - JUnit Jupiter에서는 이 어노테이션을 감지해서 테스트 계획을 포함시킴
    @DisplayName("MockMvc를 통한 Product 데이터 가져오기 테스트")
    // - 테스트 메서드의 이름이 복잡해서 가독성이 떨어질 경우 이 어노테이션을 통해 테스트에 대한 표현을 정의할 수 있음
    void getProductTest() throws Exception {
        // given
        given(productService.getProduct(123L))
                .willReturn(new ProductResponseDto(123L, "pen", 5000, 2000));

        String productId = "123";

        // when
        mockMvc.perform(get("/product?number="+productId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.number").exists())
                        .andExpect(jsonPath("$.name").exists())
                        .andExpect(jsonPath("$.price").exists())
                        .andExpect(jsonPath("$.stock").exists())
                        .andDo(print());
        // - GET / POST / PUT / DELETE 매핑되는 메서드를 제공
        // - MockMvcServletRequestBuilder 객체를 리턴

        // - andExpect
        //      - ResultMatcher를 활용
        //      - 결과값 검증을 수행할 수 있음

        // - jsonPath()
        //      - 응답으로 반환된 json에 $.필드명이 존재하는지 확인할 수 있음
        //      - .value()로 해당 필드의 값까지 검증할 수 있음

        // - andDo()
        //      - 요청과 응답의 전체 내용을 확인

        // then
        verify(productService).getProduct(123L);
        // - verify
        //      - 지정된 메소드가 실행되었는지 확인할 수 있음
    }

    @Test
    @DisplayName("Product 데이터 생성 테스트")
    void createProductTest() throws Exception {
        given(productService.saveProduct(new ProductDto("pen", 5000, 2000)))
                .willReturn(new ProductResponseDto(12315L, "pen", 5000, 2000));

        ProductDto productDto = ProductDto.builder()
                .name("pen")
                .price(5000)
                .stock(2000)
                .build();

        Gson gson = new Gson();
        String content = gson.toJson(productDto);

        mockMvc.perform(
                post("/product")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number").value(12315L))
                .andExpect(jsonPath("$.name").value("pen"))
                .andExpect(jsonPath("$.price").value(5000))
                .andExpect(jsonPath("$.stock").value(2000))
                .andDo(print());

        verify(productService).saveProduct(new ProductDto("pen", 5000, 2000));
    }
}