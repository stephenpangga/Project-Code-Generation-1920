package io.swagger.service;

import java.util.Random;

public class TokenGenerator {
    public String generate(int length) {
        Random random = new Random();
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String token = "";
        for (int i = 0; i < length; i++) {
            token += characters.charAt(random.nextInt(characters.length()));
        }
        return token;
    }
}
