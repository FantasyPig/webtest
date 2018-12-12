package com.alex.webtest.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    public static String inputPassToFormPass(String inputPass){
        String str = "" + salt.charAt(0) + salt.charAt(2) +
                inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt){
        String str = salt + formPass;
        return md5(str);
    }

    public static String inputPassToDbPass(String inputPass, String dbSalt){
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass, dbSalt);
        return dbPass;
    }

}
