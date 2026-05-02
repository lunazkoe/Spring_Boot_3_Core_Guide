package com.springboot.actuator.config.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "note")
// - 액추에이터 엔드 포인트로 등록됨
// - id로 경로를 지정할 수 있음
// - @JmxEndPoint / @WebEndPoint를 사용해서 노출 범위를 지정할 수 있음
// - enableByDefault라는 속성으로 현재 생성한 엔드포인트의 기본 활성화 여부도 설정 가능함 (기본값: true)
public class NoteEndpoint {

    private Map<String, Object> noteContent = new HashMap<>();

    @ReadOperation
    // - GET 요청에 반응하는 메서드
    public Map<String, Object> getNote() {
        return noteContent;
    }

    @WriteOperation
    // - POST 호출에 반응
    // - 받고자하는 값을 Request Body에 key, value로 넣으면 해당 값을 받아서 처리함
    public Map<String, Object> writeNote(String key, Object value) {
        noteContent.put(key, value);
        return noteContent;
    }

    @DeleteOperation
    // - DELETE 호출에 반응
    // - 받고자하는 값을 Request Body에 key, value로 넣으면 해당 값을 받아서 처리함
    public Map<String, Object> deleteNote(String key) {
        noteContent.remove(key);
        return noteContent;
    }
}
