package com.springboot.jpa;

import org.junit.jupiter.api.*;

public class TestLifeCycle {

    @BeforeAll
    // - 테스트를 시작하기 전에 호출되는 메서드를 정의
    static void beforeAll() {
        System.out.println("TestLifeCycle.beforeAll");
        System.out.println();
    }

    @AfterAll
    // - 테스트를 종료하면서 호출되는 메서드를 정의
    static void afterAll() {
        System.out.println("TestLifeCycle.afterAll");
        System.out.println();
    }

    // Before / After All은 무조건 static이여야함

    @BeforeEach
    // - 각 테스트가 시작되기 전에 호출되는 메서드를 정의
    void beforeEach() {
        System.out.println("TestLifeCycle.beforeEach");
        System.out.println();
    }

    @AfterEach
    // - 각 테스트가 종료된 후에 호출되는 메서드를 정의
    void afterEach() {
        System.out.println("TestLifeCycle.afterEach");
        System.out.println();
    }

    @Test
    void test1() {
        System.out.println("TestLifeCycle.test1");
        System.out.println();
    }

    @Test
    @DisplayName("Test Case 2!!")
    void test2() {
        System.out.println("TestLifeCycle.test2");
        System.out.println();
    }

    @Test
    @Disabled
    // - 해당 어노테이션이 붙은 테스트는 진행하지 않음
    void test3() {
        System.out.println("TestLifeCycle.test3");
        System.out.println();
    }
}
