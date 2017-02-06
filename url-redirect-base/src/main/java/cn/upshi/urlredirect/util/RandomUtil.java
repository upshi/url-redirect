package cn.upshi.urlredirect.util;

import java.util.Random;

/**
 * url-redirect cn.upshi.urlredirect.util
 * 描述：
 * 时间：2017-2-6 20:54.
 */

public class RandomUtil {

    private static Random random = new Random();

    /**
     * 随机整型数字
     * @param min 最小值(包含）
     * @param max 最大值(包含)
     * @return 指定范围内的随机值
     */
    public static int digit(int min, int max) {
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 随机字符型数字
     * @return 0-9随机字符
     */
    public static char digit() {
        return (char) digit(48, 57);
    }

    /**
     * 随机小写字母
     * @return a-z随机字母
     */
    public static char lowerChar() {
        return (char) digit(97, 122);
    }

    /**
     * 随机大写字母
     * @return A-Z随机字母
     */
    public static char upperChar() {
        return (char) digit(65, 90);
    }

    /**
     * 随机大小写字母
     * @return a-zA-Z随机字母
     */
    public static char lowerUpperChar() {
        if(random.nextBoolean()) {
            return lowerChar();
        } else {
            return upperChar();
        }
    }

    /**
     * 指定长度的随机小写字符串
     * @param length 长度
     * @return 指定长度的随机小写字符串
     */
    public static String lowerString(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            sb.append(lowerChar());
        }
        return sb.toString();
    }

    /**
     * 指定长度范围的随机小写字符串
     * @param minLength 最小长度(包含)
     * @param maxLength 最大长度(包含)
     * @return 指定长度范围的随机小写字符串
     */
    public static String lowerString(int minLength, int maxLength) {
        int length = digit(minLength, maxLength);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            sb.append(lowerChar());
        }
        return sb.toString();
    }

    /**
     * 指定长度的随机大写字符串
     * @param length 长度
     * @return 指定长度的随机大写字符串
     */
    public static String upperString(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            sb.append(upperChar());
        }
        return sb.toString();
    }

    /**
     * 指定长度范围的随机大写字符串
     * @param minLength 最小长度(包含)
     * @param maxLength 最大长度(包含)
     * @return 指定长度范围的随机大写字符串
     */
    public static String upperString(int minLength, int maxLength) {
        int length = digit(minLength, maxLength);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            sb.append(upperChar());
        }
        return sb.toString();
    }

    /**
     * 指定长度的随机大小写字符串
     * @param length 长度
     * @return 指定长度的随机大小写字符串
     */
    public static String lowerUpperString(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            sb.append(lowerUpperChar());
        }
        return sb.toString();
    }

    /**
     * 指定长度范围的随机大小写字符串
     * @param minLength 最小长度(包含)
     * @param maxLength 最大长度(包含)
     * @return 指定长度范围的随机大小写字符串
     */
    public static String lowerUpperString(int minLength, int maxLength) {
        int length = digit(minLength, maxLength);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            sb.append(lowerUpperChar());
        }
        return sb.toString();
    }

    /**
     * 指定长度的数字小写字母字符串
     * @param length 长度
     * @return 指定长度的数字小写字母字符串
     */
    public static String digitLowerString(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            if(random.nextBoolean()) {
                sb.append(digit());
            } else {
                sb.append(lowerChar());
            }
        }
        return sb.toString();
    }

    /**
     * 指定长度范围的数字小写字母字符串
     * @param minLength 最小长度(包含)
     * @param maxLength 最大长度(包含)
     * @return 指定长度范围的数字小写字母字符串
     */
    public static String digitLowerString(int minLength, int maxLength) {
        int length = digit(minLength, maxLength);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            if(random.nextBoolean()) {
                sb.append(digit());
            } else {
                sb.append(lowerChar());
            }
        }
        return sb.toString();
    }

    /**
     * 指定长度的数字大写字母字符串
     * @param length 长度
     * @return 指定长度的数字大写字母字符串
     */
    public static String digitUpperString(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            if(random.nextBoolean()) {
                sb.append(digit());
            } else {
                sb.append(upperChar());
            }
        }
        return sb.toString();
    }

    /**
     * 指定长度范围的数字大写字母字符串
     * @param minLength 最小长度(包含)
     * @param maxLength 最大长度(包含)
     * @return 指定长度范围的数字大写字母字符串
     */
    public static String digitUpperString(int minLength, int maxLength) {
        int length = digit(minLength, maxLength);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            if(random.nextBoolean()) {
                sb.append(digit());
            } else {
                sb.append(upperChar());
            }
        }
        return sb.toString();
    }

    /**
     * 指定长度的数字字母字符串
     * @param length 长度
     * @return 指定长度的数字字母字符串
     */
    public static String digitCharString(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            if(random.nextBoolean()) {
                sb.append(digit());
            } else {
                sb.append(lowerUpperChar());
            }
        }
        return sb.toString();
    }

    /**
     * 指定长度的数字字母字符串
     * @param minLength 最小长度(包含)
     * @param maxLength 最大长度(包含)
     * @return 指定长度的数字字母字符串
     */
    public static String digitCharString(int minLength, int maxLength) {
        int length = digit(minLength, maxLength);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<length; i++) {
            if(random.nextBoolean()) {
                sb.append(digit());
            } else {
                sb.append(lowerUpperChar());
            }
        }
        return sb.toString();
    }
}
