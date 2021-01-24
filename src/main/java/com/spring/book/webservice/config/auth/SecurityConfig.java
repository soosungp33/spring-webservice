package com.spring.book.webservice.config.auth;

import com.spring.book.webservice.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // spring security 설정들을 활성화 시켜준다.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable() // h2-console 화면을 사용하기 위해 해당 옵션들을 disable
                .and()
                    .authorizeRequests() // antMatchers 옵션을 사용하기 위한 선언(URL 별 권한 관리를 설정하는 옵션)
                    .antMatchers("/", "/css/**", "/images/**", //권한 관리 대상을 지정하는 옵션
                            "/js/**", "/h2-console/**").permitAll() // "/" 등 지정된 URL은 전체 열람 권한을 줌
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능
                    .anyRequest().authenticated() // 설정된 값들 이외 나머지 URL은 로그인한 사용자들에게만 허용용
               .and()
                    .logout()
                        .logoutSuccessUrl("/") // 로그아웃 설공시 /주소로 이동
                .and()
                    .oauth2Login() // 로그인 기능 설정의 진입점
                        .userInfoEndpoint() // 로그인 성공 이후 사용자 정보를 가져올 때 설정 담당
                            .userService(customOAuth2UserService); // 로그인 성공 시 후속 조치 진행(구현체 등록)
    }
}
