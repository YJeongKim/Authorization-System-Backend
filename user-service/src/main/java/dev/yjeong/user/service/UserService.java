package dev.yjeong.user.service;

import dev.yjeong.user.domain.User;
import dev.yjeong.user.domain.UserRepository;
import dev.yjeong.user.dto.request.SignInRequest;
import dev.yjeong.user.dto.request.SignUpRequest;
import dev.yjeong.user.dto.response.SignInResponse;
import dev.yjeong.user.dto.response.SignUpResponse;
import dev.yjeong.user.exception.BadRequestException;
import dev.yjeong.user.exception.ExceptionType;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponse signUpUser(SignUpRequest signUpRequest) {
        if (existsUserByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException(ExceptionType.DUPLICATE_EMAIL);
        }
        String encryptedPassword = signUpRequest.getPassword(); // TODO: 비밀번호 암호화
        User user = userRepository.save(signUpRequest.toEntity(encryptedPassword));
        return SignUpResponse.of(user);
    }

    public SignInResponse signInUser(SignInRequest signInRequest) {
        if (!existsUserByEmail(signInRequest.getEmail())) {
            throw new BadRequestException(ExceptionType.NOT_EXIST_EMAIL);
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
            throw new BadRequestException(ExceptionType.INCORRECT_PASSWORD);
        }
    }

}
