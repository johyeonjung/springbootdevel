package me.johyeonjung.springbootdeveloper.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.SerializationUtils;

import java.util.Base64;

public class CookieUtil {

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }


    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie[] cookies= request.getCookies();
        if (cookies == null) {
            return;
        }

        for(Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                cookie.setValue("");
                cookie.setPath("/");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
    }

    public static String serialize(Object obj) {
        return Base64.getUrlEncoder()
                .encodeToString(SerializationUtils.serialize(obj));
    }


    public static <T> T deserialize(Cookie cookie, Class<T> cls) {
        return cls.cast(
                SerializationUtils.deserialize(Base64.getUrlDecoder().decode(cookie.getValue()))
        );
    }
}

//addCookie :요청값(이름, 값, 만료기간)을 바탕으로 HTTP응답에 쿠키를 추가합니다
// deleteCookie : 쿠키이름을 입력받아 쿠키를 삭제합니다. 실제로 삭제하는 방법은 없으므로 파라미터로
// 넘어온 키의 쿠키를 빈 값으로 바꾸고 만료 시간을 0으로 설정해 쿠키가 재생성 되자마자 만료 처리합니다.

//serialize : 객체를 직렬화해 쿠키의 값으로 들어갈 값으로 변환합니다.

//deserialize :쿠키를 역직렬화 객체로 변환합니다.


