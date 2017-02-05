package cn.upshi.urlredirect.util;

import org.springframework.security.crypto.codec.Base64;

/**
 * CloudPrint cn.edu.zju.cst.cloudprint.util
 * 描述：
 * 时间：2016-12-16 10:48.
 */

public class Base64Util {

    public static String encode(String str) {
        return new String(Base64.encode(str.getBytes()));
    }

    public static String decode(String str) {
        return new String(Base64.decode(str.getBytes()));
    }

}
