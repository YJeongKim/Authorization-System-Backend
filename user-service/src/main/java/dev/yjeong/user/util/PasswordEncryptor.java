package dev.yjeong.user.util;

import dev.yjeong.user.exception.ExceptionType;
import dev.yjeong.user.exception.InternalServerException;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class PasswordEncryptor {

    private static final int SALT_SIZE = 16;

    private static final String HEX = "%02x";
    private static final String SHA = "SHA-256";

    public String createSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        secureRandom.nextBytes(salt);
        return convertByteToString(salt);
    }

    public String hashPasswordWithSalt(String password, String salt) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA);
            messageDigest.update((password + salt).getBytes());
            byte[] encryptedPassword = messageDigest.digest();
            return convertByteToString(encryptedPassword);
        } catch (NoSuchAlgorithmException exception) {
            throw new InternalServerException(ExceptionType.NOT_EXIST_ENCRYPTION_ALGORITHM);
        }
    }

    private String convertByteToString(byte[] data) {
        StringBuilder stringBuilder = new StringBuilder();

        for (byte d : data) {
            stringBuilder.append(String.format(HEX, d));
        }
        return stringBuilder.toString();
    }

}
