package com.store.fake.security;

public class JwtProperties {
    private String SecretKey = "1aT8QDMDZS2PxcJKklUKW8JBN11";

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }
}
