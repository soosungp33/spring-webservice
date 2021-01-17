package com.spring.book.webservice;

import com.spring.book.webservice.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class) // 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@WebMvcTest(controllers = HelloController.class) // web에 집중할 수 있는 어노테이션(Controller만 사용가능)
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈을 주입받는다.
    private MockMvc mvc; // 웹 API를 테스트할 때 사용한다.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // HTTP GET 요청
                .andExpect(status().isOk()) // Status를 검증(200이면 OK)
                .andExpect(content().string(hello)); // 리턴이 hello인지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                .param("name", name)
                .param("amount", String.valueOf(amount))
        ).andExpect(status().isOk())
         .andExpect((ResultMatcher) jsonPath("$.name", is(name)))
         .andExpect((ResultMatcher) jsonPath("$.amount", is(amount)));
    }
}
