package config.jwt;
//https://github.com/shinsunyoung/springboot-developer


import io.jsonwebtoken.Jwts;

import me.johyeonjung.springbootdeveloper.SpringBootDeveloperApplication;
import me.johyeonjung.springbootdeveloper.config.jwt.JwtProperties;
import me.johyeonjung.springbootdeveloper.config.jwt.TokenProvider;
import me.johyeonjung.springbootdeveloper.domain.User;
import me.johyeonjung.springbootdeveloper.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.util.Date;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//generateToken()
//given : 토큰에 유저 정보를 추가하기 위한 테스트 유저를 만듦
//when : 토큰 제공자의 generateToken()메서드를 호출해 토큰을 만듦
//then : jjwt 라이브러리를 사용해 토큰을 복호화, 토큰을 만들 때 클레임으로 넣어둔 id값이 given절에서
//만든 유저의ID와 동일한지 확인함

@SpringBootTest(classes = SpringBootDeveloperApplication.class)
public class TokenProviderTest {
    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtProperties jwtProperties;

//generateToken() 검증 테스트
    @DisplayName("generateToken() : 유저 정보와 만료 기간을 전달해 토큰을 만들 수 있다")
    @Test
    void generateToken() {
        //given
        User testUser = userRepository.save(User.builder()
                .email("user@email.com")
                .password("test")
                .build());
        //when
        String token = tokenProvider.generateToken(testUser, Duration.ofDays(14));

        //then
        Long userId = Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);

        assertThat(userId).isEqualTo(testUser.getId());
    }
    //validToken() 검증 테스트
    @DisplayName("validToken() : 만료된 토큰인 때에 유효성 검증에 실패한다")
    @Test
    void validToken_invalidToken(){
            //given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(7).
                        toMillis()))
                .build()
                .createToken(jwtProperties);
        //when
        boolean result = tokenProvider.validToken(token);

        //then
        assertThat(result).isFalse();
    }
    @DisplayName("validToken(): 유효한 토큰인 때에 유효성 검증에 성공한다.")
    @Test
    void validToken_validToken(){
        String token = JwtFactory.withDefaultValues().createToken(jwtProperties);


        boolean result = tokenProvider.validToken(token);

        assertThat(result).isTrue();
    }

    @DisplayName("getAuthentication() : 토큰 기반으로 인증 정보를 가져올 수 있다")
    @Test
    void getAuthentication() {
        String userEmail = "user@email.com";
        String token = JwtFactory.builder()
                .subject(userEmail)
                .build()
                .createToken(jwtProperties);

        Authentication authentication = tokenProvider.getAuthentication(token);

        assertThat(((UserDetails) authentication.getPrincipal()).getUsername()).isEqualTo(userEmail);

    }


}
