package me.johyeonjung.springbootdeveloper.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt")
public class JwtProperties {
    //자바 클래스에 프로피티값을 가져와서 사용하는 애너테이션

    private String issuer;
    private String secretKey;

}
