package com.jojoldu.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
// @Controller, @ControllerAdvice 등은 사용 가능
// @Service, @Component, @Repository 등은 사용 불가능
public class HelloControllerTest {

    @Autowired // 빈을 주입 받는다.
    private MockMvc mvc;
    // 웹 API 테스트를할 떄 사용한다.
    // 스프링 MVC 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트를 할 수 있다.

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
                                            // 체이닝이 지원되어 아래와 같이 여러 검증 가능
                .andExpect(status().isOk()) // mvc.perform의 결과를 검증
                                            // HTTP Header의 Status를 검증
                                            // 200, 404, 500 등의 상태를 검증
                                            // isOk = 200 코드 검증
                .andExpect(content().string(hello)); // mvc.perform의 결과를 검증
                                                    // 응답 본문의 내용을 검증
                                                    // Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount",String.valueOf(amount)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name", is(name)))
                        .andExpect(jsonPath("$.amount", is(amount)));
    }
}
