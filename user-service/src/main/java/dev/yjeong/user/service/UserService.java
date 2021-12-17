package dev.yjeong.user.service;

import dev.yjeong.user.domain.User;
import dev.yjeong.user.domain.UserRepository;
import dev.yjeong.user.dto.SignUpRequest;
import dev.yjeong.user.dto.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponse createUser(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }
        String encryptedPassword = signUpRequest.getPassword(); // TODO: 비밀번호 암호화
        User user = userRepository.save(signUpRequest.toEntity(encryptedPassword));
        return SignUpResponse.of(user);
    }

}
