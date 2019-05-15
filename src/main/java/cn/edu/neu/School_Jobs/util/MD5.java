package cn.edu.neu.School_Jobs.util;

import java.security.MessageDigest;

public class MD5 {

    //十六进制下数字到字符的映射数组
    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 把inputString加密
     */
    public static String createPassword(String inputString) {
        return encodeByMD5(inputString);
    }

    /**
     * Creation  Date:2008-3-14
     * TODO    验证输入的密码是否正确
     *
     * @param password    加密后的源密码
     * @param inputString 输入字符串
     * @return boolean true为真，false为假
     * @Author tianw
     */
    public static boolean authenticatePassword(String password,
                                               String inputString) {
        if (password.equals(encodeByMD5(inputString))) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 对字符串进行MD5加密
     */
    public static String encodeByMD5(String originString) {
        if (originString != null) {
            try {
                // 创建具有指定算法名称的信息摘要
                MessageDigest md = MessageDigest.getInstance("MD5");
                // 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
                byte[] results = md.digest(originString.getBytes());
                // 将得到的字节数组变成字符串返回
                String resultString = byteArrayToHexString(results);
                return resultString.toUpperCase();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Creation  Date:2008-3-14
     * TODO    转换字节数组为十六进制字符串
     *
     * @param b 字节数组
     * @return String 十六进制字符串
     * @Author tianw
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {

            String ss = byteToHexString(b[i]);
            resultSb.append(ss);
        }

        return resultSb.toString();
    }


    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static void main(String[] args) {
        System.out.println(MD5.createPassword("123456"));//8994E0A54CF3B38CC08F251780375F19
    }
}