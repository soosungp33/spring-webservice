package com.spring.book.webservice.domain.posts;

import com.spring.book.webservice.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복 어노테이션(모든 필드의 Getter 메소드를 자동 생성)
@NoArgsConstructor // 롬복 어노테이션(기본 생성자 자동 추가)
@Entity // JPA의 어노테이션(필수)
public class Posts extends BaseTimeEntity { // 실제 DB의 테이블과 매칭될 클래스
    @Id // 해당 테이블의 PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙
    private Long id; // Long 타입 추천

    @Column(length = 500, nullable = false) // 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용(선언하지 않아도 모두 칼럼이 된다.)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 롬복 어노테이션(생성자와 동일한 기능이고 채워야 할 필드가 무엇인지 명확히 알 수 있다.)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
