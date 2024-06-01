package com.example.station_level_management_back;

import org.mindrot.jbcrypt.BCrypt;

/**
 * 密码工具类，用于处理密码的哈希和验证
 */
public class PasswordUtils {

    /**
     * 使用bcrypt算法对密码进行哈希处理
     *
     * @param password 要哈希的明文密码
     * @return 哈希后的密码字符串
     */
    public static String hashPassword(String password) {

        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 验证明文密码是否与哈希密码匹配
     *
     * @param password 明文密码
     * @param hashed 哈希后的密码
     * @return 如果密码匹配则返回true，否则返回false
     */
    public static boolean checkPassword(String password, String hashed) {

        return BCrypt.checkpw(password, hashed);
    }
}
