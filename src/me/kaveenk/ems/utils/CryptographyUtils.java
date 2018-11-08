/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import static me.kaveenk.ems.main.EMSMain.logger;

/**
 *
 * @author baeldung at https://www.baeldung.com/sha-256-hashing-java
 * Modifications by:
 * @author Kaveen Kumarasinghe
 */
public class CryptographyUtils {
    
    
    /**
     * Generate a SHA-256 byte array for a given String.
     * @param toHash String value to hash
     * @return byteArray in String format
     */
    public static String hash(String toHash) {
        MessageDigest digest;
        try {
        digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Failed to find the SHA-256 algorithm");
            logger.error("There was an error in loading the SHA-256 algorithm. Because of security risk, login has now been disabled.", e);
            return "error";
        }
        return bytesToHex(digest.digest(
        toHash.getBytes(StandardCharsets.UTF_8)));
    }
    
    /**
     * Converts a byte array to hexadecimal format used as the final check for a password.
     * @param hash the byte array to convert to Hexadecimal
     * @return String of the hexadecimal value of the bytearray.
     */
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
