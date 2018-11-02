/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author baeldung at https://www.baeldung.com/sha-256-hashing-java
 * Modifications by:
 * @author Kaveen Kumarasinghe
 */
public class CryptographyUtils {
    
    public static String hash(String toHash) {
        MessageDigest digest;
        try {
        digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Failed to find the SHA-256 algorithm");
            e.printStackTrace();
            return "error";
        }
        return bytesToHex(digest.digest(
        toHash.getBytes(StandardCharsets.UTF_8)));
    }
    
    private static String bytesToHex(byte[] hash) {
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < hash.length; i++) {
    String hex = Integer.toHexString(0xff & hash[i]);
    if(hex.length() == 1) hexString.append('0');
        hexString.append(hex);
    }
    return hexString.toString();
}
    
    
}
