package com.spring.book.webservice.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 get 메소드를 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자를 생성해준다.
public class HelloResponseDto { // 롬복이 잘 작동하는지 보기위한 메소드

    private final String name;
    private final int amount;
}
