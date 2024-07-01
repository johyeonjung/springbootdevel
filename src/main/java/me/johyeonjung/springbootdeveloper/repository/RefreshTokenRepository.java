package me.johyeonjung.springbootdeveloper.repository;

import me.johyeonjung.springbootdeveloper.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserId(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}


//토큰 필터 구현하기
//security context : 인증 객체가 저장되는 보관소
//시큐리티 컨텍스트 객체를 저장하는 객체가 시큐리티 컨텍스트 홀더
