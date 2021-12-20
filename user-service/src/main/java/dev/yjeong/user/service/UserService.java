package dev.yjeong.user.service;

import dev.yjeong.user.domain.Salt;
import dev.yjeong.user.domain.SaltRepository;
import dev.yjeong.user.domain.User;
import dev.yjeong.user.domain.UserRepository;
import dev.yjeong.user.dto.request.SignInRequest;
import dev.yjeong.user.dto.request.SignUpRequest;
import dev.yjeong.user.dto.response.SignInResponse;
import dev.yjeong.user.dto.response.SignUpResponse;
import dev.yjeong.user.exception.BadRequestException;
import dev.yjeong.user.exception.ExceptionType;
import dev.yjeong.user.util.PasswordEncryptor;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final SaltRepository saltRepository;

    private final PasswordEncryptor passwordEncryptor;

    public SignUpResponse signUpUser(SignUpRequest signUpRequest) {
        if (existsUserByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException(ExceptionType.DUPLICATE_EMAIL);
        }
        Salt salt = saltRepository.save(Salt.toEntity(passwordEncryptor.createSalt()));
        String encryptedPassword = passwordEncryptor.hashPasswordWithSalt(signUpRequest.getPassword(), salt.getValue());
        User user = userRepository.save(signUpRequest.toEntity(encryptedPassword, salt));
        return SignUpResponse.of(user);
    }

    public SignInResponse signInUser(SignInRequest signInRequest) {
        if (!existsUserByEmail(signInRequest.getEmail())) {
            throw new BadRequestException(ExceptionType.NOT_EXIST_EMAIL);
        }
        User user = userRepository.findByEmail(signInRequest.getEmail());
        String salt = user.getSalt().getValue();
        String encryptedPassword = passwordEncryptor.hashPasswordWithSalt(signInRequest.getPassword(), salt);
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
