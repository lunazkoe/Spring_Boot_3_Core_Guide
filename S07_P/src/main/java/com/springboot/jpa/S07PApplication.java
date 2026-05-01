package com.springboot.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S07PApplication {

    public static void main(String[] args) {
        SpringApplication.run(S07PApplication.class, args);
    }

}

// 통합 테스트: @SpringBootTest
// - 테스트 비용이 큼

// 단위 테스트: 프로젝트에 필요한 모든 기능에 대한 테스트를 **각각** 진행하는 것을 의미
// - 테스트 비용이 통합 테스트에 비해서 작음
// - FIRST원칙
//      - Fast: 테스트 코드의 실행은 빠르게 진행되어야 함
//      - Independent: 독립적인 테스트가 가능해야 함
//      - Repeatable: 테스트는 매번 같은 결과를 만들어야 함
//      - Self-Validating: 테스트는 그 자체로 실행하여 결과를 확인할 수 있어야 함
//      - Timely: 단위 테스트는 비즈니스 코드가 완성되기 전에 구성하고 테스트가 가능해야 함
//              - 코드가 완성되기 전부터 테스트가 따라와야 한다는 TDD의 원칙을 담고 있음
//              - TDD를 하고 있지 않으면 Optional하게 냅두어도 됨

// 테스트 코드 작성 방법
// - Given - When - Then 패턴
// - given
//      - 테스트를 수행하기 전에 테스트에 필요한 환경을 설정하는 단계
//      - 테스트에 필요한 변수를 정의하거나 Mock 객체를 통해 특정 상황에 대한 통제를 정의
//  - when
//      - 테스트의 목적을 보여주는 단계
//      - 실제 테스트 코드가 포함되며, 테스트를 통한 결과값을 가져오게 됨
//  - then
//      - 테스트의 결과를 검증하는 단계
//      - 일반적으로 when 단계에서 나온 결괏값을 검증하는 작업을 수행
//      - 결과값이 아니여도 이 테스트를 통해 나온 결과에서 검증해야하는 부분이 있다면 이 단게에 포함
// - 참고: 간단한 테스트로 여겨지는 단위 테스트에서는 잘 사용하지 않음