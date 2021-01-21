package com.spring.book.webservice.web;

import com.spring.book.webservice.service.posts.PostsService;
import com.spring.book.webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController { // 머스테치 스타터 덕분에 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { // Model에 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        model.addAttribute("posts", postsService.findAllDesc()); // findAllDesc로 가져온 결과를 posts로 index.mustache에 전달
        return "index"; // src/main/resources/templates/index.mustache 로 전환되어 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
