package com.springboot.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class S07PApplicationTests {

    @Test
    void contextLoads() {
    }

}

// 블랙 박스 테스트 / 화이트 박스 테스트
// - 블랙 박스 테스트
// -- 소프트웨어 내부 구조나 작동 원리를 모르는 상태에서 동작을 검사하는 방식
// -- 다양한 값을 입력하여 올바른 출력이 나오는지 테스트
// -- 사용자 관점의 테스트 방법

// - 화이트 박스 테스트
// -- 소프트웨어 내부 구조와 동작을 검사하는 테스트 방식
// -- 소프트웨어 내부 소스 코드를 테스트하는 방법
// -- 개발자 관점의 테스트 방법

// JaCoCo
// - ./gradlew clean test 실행
// - build/reports/jacoco/test/html/index.html

// 커버리지
// - 해당 코드가 테스트 되는 동안 지나갔는지 확인하는 용도
// - 조건문의 경우 true / false의 경우를 다 테스트 해야함

// TDD
// - 1. 실패 테스트 작성
// - 2. 테스트를 통과하는 코드 작성
// - 3. 리팩터링: 중복 코드를 제거하거나 일반화하는 리팩터링 수행

// SonarQube라는 솔루션과 JaCoCo를 같이 활용함
// - 나중에 기회가 된다면!!