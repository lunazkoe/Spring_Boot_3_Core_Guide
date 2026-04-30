package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        return "Hello World";
    }

    @GetMapping(value = "/name")
    public String getName() {
        return "lunazkoe";
    }

    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    @Operation(
            summary = "GET 메서드 예제",
            description = "@RequestParam을 활용한 GET 메서드"
    )
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @Parameter(name = "name", description = "이름", required = false) @RequestParam(defaultValue = "lunazkoe") String name,
            @RequestParam(value = "email") String e,
            @RequestParam(required = false) String organization
    ) {
        log.info("info/ name={}, email={}, organization={}", name, e, organization);
        log.debug("debug/ name={}, email={}, organization={}", name, e, organization);
        return name + " " + e + " " + organization;
        // defaultValue: 자동으로 required = false가 적용됨
        // - 값이 null인 경우뿐 아니라 ""(빈 문자열)인 경우(?name)에도 동작
    }

    // 쿼리 스트링에 어떤 값이 들어올지 모르는 경우 주로 사용
    // - 회원 가입 시 선택 필드
    @GetMapping(value = "/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.forEach((key, value) -> sb.append(key).append(" : ").append(value).append("\n"));
        return sb.toString();
    }

    // 필드명으로 쿼리 스트링의 키와 매핑
    // - @ModelAttribute
    // - 매핑이 안되는 값이 있으면 기본값이 들어감
    @GetMapping(value = "request3")
    public String getRequestParam3(@ModelAttribute MemberDto memberDto) {
        return memberDto.toString();
    }
}
