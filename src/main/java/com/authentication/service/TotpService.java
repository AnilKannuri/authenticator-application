package com.authentication.service;

public interface TotpService {
    String generateSecret();
    boolean verifyCode(String secret, String code);
}
