package com.example.splitwise.services.passwordEncoder;

public interface PasswordEncoder {

    boolean matches(String realPwd,String hashedPwd);

    String getEncodedPassword(String realPwd);
}
