package com.spring.book.webservice.config.auth.dto;

import com.spring.book.webservice.domain.user.Role;
import com.spring.book.webservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes,
                           String nameAttributekey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributekey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    // of -> 사용자 정보가 Map이기 때문에 값 하나하나를 변환해야한다.
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributekey(userNameAttributeName)
                .build();
    }

    // 가입할 때 기본 권한을 GUEST로 준다.
    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST)
                .build();
    }
}
