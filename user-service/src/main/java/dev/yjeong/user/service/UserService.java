package dev.yjeong.user.service;

import dev.yjeong.user.domain.User;
import dev.yjeong.user.domain.UserRepository;
import dev.yjeong.user.dto.request.SignInRequest;
import dev.yjeong.user.dto.response.SignInResponse;
import dev.yjeong.user.dto.request.SignUpRequest;
import dev.yjeong.user.dto.response.SignUpResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponse signUpUser(SignUpRequest signUpRequest) {
        if (existsUserByEmail(signUpRequest.getEmail())) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }
        String encryptedPassword = signUpRequest.getPassword(); // TODO: 비밀번호 암호화
        User user = userRepository.save(signUpRequest.toEntity(encryptedPassword));
        return SignUpResponse.of(user);
    }

    public SignInResponse signInUser(SignInRequest signInRequest) {
        if (!existsUserByEmail(signInRequest.getEmail())) {
            throw new IllegalArgumentException("존재하지 않은 이메일입니다.");
        }
        String encryptedPassword = signInRequest.getPassword(); // TODO: 비밀번호 암호화
        User user = userRepository.findByEmail(signInRequest.getEmail());
        validatePassword(user.getPassword(), encryptedPassword);
        return SignInResponse.of(user);
    }

    private boolean existsUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private void validatePassword(String setPassword, String encryptedPassword) {
        if (!setPassword.equals(encryptedPassword)) {
            throw new IllegalArgumentException("비밀번호가 옳지 않습니다.");
        }
    }

}
