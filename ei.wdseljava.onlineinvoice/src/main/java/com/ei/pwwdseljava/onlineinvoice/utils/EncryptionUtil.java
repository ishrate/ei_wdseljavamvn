package com.ei.pwwdseljava.onlineinvoice.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtil {
    private static final String ALGORITHM = "AES";

    public static String encrypt(String value, String secret) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encrypted = cipher.doFinal(value.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }

    public static String decrypt(String encryptedValue, String secret) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secret.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decoded = Base64.getDecoder().decode(encryptedValue);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}
