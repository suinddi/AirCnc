package com.aircnc.utility;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512 {
    // 유틸리티성 클래스
    // - 객체화가 되면 안된다.
    //   - 추상화 , 생성자의 접근제한자는 private 으로 둔다.
    // - 모든변수, 상수, 메서드는 정적이어야 한다.

    private Sha512 () {}  // 객체화를 막음

    public static String hash(String input) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
            messageDigest.reset();
            messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
            return String.format("%0128x", new BigInteger(1, messageDigest.digest()));
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
           return null;
        }
    }
}
