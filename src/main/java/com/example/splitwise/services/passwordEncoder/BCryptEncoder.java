package com.example.splitwise.services.passwordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptEncoder implements PasswordEncoder{

    private BCryptPasswordEncoder springBcryptEncoder = new BCryptPasswordEncoder();

    @Override
    public boolean matches(String realPwd, String hashedPwd) {
        return springBcryptEncoder.matches(realPwd,hashedPwd);
    }

    @Override
    public String getEncodedPassword(String realPwd) {
        return springBcryptEncoder.encode(realPwd);
    }
}
