package com.spring.book.webservice.web;

import com.spring.book.webservice.config.auth.LoginUser;
import com.spring.book.webservice.config.auth.dto.SessionUser;
import com.spring.book.webservice.service.posts.PostsService;
import com.spring.book.webservice.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController { // 머스테치 스타터 덕분에 앞의 경로와 뒤의 파일 확장자는 자동으로 지정된다.

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { // Model에 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
        // 게시판 기능
        model.addAttribute("posts", postsService.findAllDesc()); // findAllDesc로 가져온 결과를 posts로 index.mustache에 전달

        // 로그인 기능
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        // 머시테치 코드에 의해 userName에 값이 없으면 로그인 버튼이 보인다.
        if(user!=null) model.addAttribute("IsuserName", user.getName());


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
