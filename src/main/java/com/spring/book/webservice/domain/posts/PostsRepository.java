package com.spring.book.webservice.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Posts 클래스로 Database를 접근하게 해줄 jparepository(인터페이스로 생성)
// 기본적인 CRUD 메소드가 자동으로 생성된다.
// Entity 클래스와 Entity Repository는 함께 위치해야 한다.
public interface PostsRepository extends JpaRepository<Posts, Long> { // <Entity 클래스, PK 타입>
}
