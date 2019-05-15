package cn.edu.neu.School_Jobs.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryptor {

    // 默认盐
    private final static String DEFAULT_SALT = "sLoiEIbn3EWP93nw";

    /**
     * 不可逆加密
     *
     * @param input 输入字符串
     * @param salt  盐（若null则使用默认盐）
     * @return
     */
    public static String encrypt(String input, String salt) {
        try {
            if (salt == null) {
                salt = DEFAULT_SALT;
            }
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            // 混合盐
            messageDigest.update((input + salt + DEFAULT_SALT).getBytes());
            return new BigInteger(messageDigest.digest()).toString(32);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
