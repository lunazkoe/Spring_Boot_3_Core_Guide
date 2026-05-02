package com.springboot.actuator.config.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> content = new HashMap<>();
        content.put("code-info", "InfoContributor 구현체에서 정의한 정보입니다.");
        builder.withDetail("custom-info-contributor", content);
        // - builder 객체는 액추에이터 패키지의 Info 클래스 안에 정의되어있는 클래스로서 Info 엔드 포인트에서 보여줄 내용을 담는 역할을 수행함
        // - 기존 application.properties에서 정의했던 속성값도 포함되서 보여줌
    }
}
