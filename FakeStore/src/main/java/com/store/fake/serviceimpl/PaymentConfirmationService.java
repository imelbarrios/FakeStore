package com.store.fake.serviceimpl;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Service
public class PaymentConfirmationService {

    private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    public String generatePaymentConfirmation() {

        UUID uuid = UUID.randomUUID();


        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);

        // Combine UUID and safe random value
        String confirmationCode = uuid.toString() + "-" + base64Encoder.encodeToString(randomBytes);

        return confirmationCode;
    }
}
