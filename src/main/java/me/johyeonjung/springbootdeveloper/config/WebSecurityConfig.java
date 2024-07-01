package me.johyeonjung.springbootdeveloper.config;
//
//import lombok.RequiredArgsConstructor;
//import me.johyeonjung.springbootdeveloper.service.UserDetailService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@RequiredArgsConstructor
//@Configuration
//public class WebSecurityConfig {
//
//    private final UserDetailService userService;
//
//    //스프링 시큐리티의 모든 기능을 사용하지 않게 하는 코드
//    // 인증, 인가 서비스를 모든 곳에 적용하지는 않음
//    //일반저긍로 정적 리소스에 설정함
//    //정적 리소스만 스프링 시큐리티 사용을 비활성화하는데 static하위 경로에 있는 리소스와 h2의 데이터를
//    //확인하는데 사용하는 h2console 하위 url,을 대상으로 ignoring 메서드를 사요함
//    @Bean
//    public WebSecurityCustomizer configure() {
//        return (web) -> web.ignoring()
//                .requestMatchers(toH2Console())
//                .requestMatchers("/static/**");
//    }
//
//
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        return http
//                .authorizeRequests()    //인증, 인가 설정
//                //permitAll 누구나 접근이 가능하게 설정
//                .requestMatchers("/login","/signup","/user").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()    // 폼 기반 로긍니 설정
//                .loginPage("/login")
//                .defaultSuccessUrl("/articles")
//                .and()
//                .logout()   //로그아웃 설정
//                .logoutSuccessUrl("/login")
//                .invalidateHttpSession(true)
//                .and()
//                .csrf().disable()   //csrf 비활성화
////                .build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(userService)
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//
//    }
//
//    @Bean
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//}
