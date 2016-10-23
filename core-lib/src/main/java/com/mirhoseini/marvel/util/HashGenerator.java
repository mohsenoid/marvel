package com.mirhoseini.marvel.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class HashGenerator {

    public static String generate(long timestamp, String privateKey, String publicKey) {
        try {
            String concatResult = timestamp + privateKey + publicKey;
            return md5(concatResult);
        } catch (Exception e) {
            return null;
        }
    }

    public static String md5(String s) throws NoSuchAlgorithmException {
        // Create MD5 Hash
        MessageDigest digest = MessageDigest
                .getInstance("MD5");
        digest.update(s.getBytes());
        byte messageDigest[] = digest.digest();
        BigInteger bigInt = new BigInteger(1, messageDigest);
        String hashText = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32
        // chars.
        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }
        return hashText;
    }

}
