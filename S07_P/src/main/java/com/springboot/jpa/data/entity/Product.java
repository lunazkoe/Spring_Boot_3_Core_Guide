package com.springboot.jpa.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "product")
@Getter @Setter
@NoArgsConstructor
// - 매개변수가 없는 생성자를 자동 생성
@AllArgsConstructor
// - 모든 필드를 매개변수로 갖는 생성자를 자동 생성
//@RequiredArgsConstructor
// - 필드 중 final이나 @NotNull이 설정된 변수를 매개변수로 갖는 생성자를 자동 생성
// @ToString(exclude = "name")
// - 특정 필드를 toString 결과에서 제외함 (비밀번호나 순환 참조가 발생하는 필드에 주로 사용)
// @EqualsAndHashCode(callSuper = true)
// - equals(): 객체 내부의 '데이터 내용'이 같은지(동등성) 비교하는 메서드 생성
// - hashCode(): 객체의 '데이터 내용'을 기반으로 생성된 정수값을 반환 (동등한 객체는 같은 해시코드를 가져야 함)
// - callSuper = true: 부모 클래스의 필드들까지 비교 대상에 포함함
// - 참고:
//      - equals를 오버라이딩하면 hashCode도 반드시 같이 오버라이딩해야 함!
//      - 이유: HashMap 등에서 "내용이 같은 객체"를 "같은 위치"에서 찾기 위함임.
//      - (주의) 롬복이 생성한 hashCode는 메모리 주소가 아니라 '필드 값'을 조합한 결과물임.
// @Data
// - Getter / Setter / RequiredArgsConstructor / ToString / EqualsAndHashCode

// 실무에서는 JPA 엔티티에 @EqualsAndHashCode나 @Data를 무심코 사용하면 위험할 수 있습니다.
// 순환 참조: 양방향 연관관계가 있을 때 서로의 toString이나 equals를 무한 호출하다가 StackOverflowError가 날 수 있어요.
// 그래서 보통 엔티티에서는 @EqualsAndHashCode 대신 PK(@Id) 값만 사용하도록 직접 오버라이딩하거나, 필요한 필드만 지정해서 사용하는 것을 추천합니다.
public class Product {

    @Id
    // - 모든 엔티티에 반드시 필요함
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // - GenerationType.IDENTITY: 기본값 생성을 데이터베이스에 위임하는 방식
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    // @Transient: 엔티티 클래스에 선언돼 있는 필드지만 데이터베이스에서는 필요 없을 경우 사용

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
