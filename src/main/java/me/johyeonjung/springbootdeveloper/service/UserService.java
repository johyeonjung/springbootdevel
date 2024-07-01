package me.johyeonjung.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import me.johyeonjung.springbootdeveloper.domain.User;
import me.johyeonjung.springbootdeveloper.dto.AddUserRequest;
import me.johyeonjung.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;



    public Long save(AddUserRequest dto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return userRepository.save(User.builder()
                .email(dto.getEmail())
                //패스워드 암호회
                //패스워드를 저장할때 시큐리티를 설정하며 패스워드 인코딩용으로 등록학
                //빈을 사용해서 암호화 후 저장함.
                .password(encoder.encode(dto.getPassword()))
                        .build()).getId();

    }

    public User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new IllegalArgumentException("Unexpected user"));
    }
}
