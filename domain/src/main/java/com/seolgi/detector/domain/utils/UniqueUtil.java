package com.seolgi.detector.domain.utils;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.util.Random;
import java.util.UUID;

@Slf4j
public class UniqueUtil {

    private static String SALT = "seolgi_ga_user_id_123_!@#";

    public static String generate(String id) {
        return getEncryptMD5(id + SALT);
    }

    public static String generate() {
        return UUID.randomUUID().toString();
    }

    public static String generateURI(int digit) {
        String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String result = "";
        Random random = new Random();

        for (int i = 0; i < digit; i++) {
            result+= alphabet.charAt(random.nextInt(26));
        }

        return result;
    }

    private static String getEncryptMD5(String gaUserId) {
        String gaUserIdMd5 = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(gaUserId.getBytes());
            byte byteData[] = messageDigest.digest();

            StringBuffer gaUserIdMd5Temp = new StringBuffer();

            for (byte byteTmp : byteData) {
                gaUserIdMd5Temp.append(Integer.toString((byteTmp & 0xff) + 0x100, 16).substring(1));
            }
            gaUserIdMd5 = gaUserIdMd5Temp.toString();
        } catch (Exception e) {
            gaUserIdMd5 = null;
            log.error("GoogleAnalyticsUtil.getEncryptMD5 Fail: ", e);
        }
        return gaUserIdMd5;
    }
}