package com.ei.pwwdseljava.onlineinvoice.utils;

public class EncryptPassword {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java EncryptPassword <password> <secretKey>");
            System.exit(1);
        }

        String password = args[0];
        String secretKey = args[1];

        try {
            String encrypted = EncryptionUtil.encrypt(password, secretKey);
            System.out.println("Encrypted password: " + encrypted);
        } catch (Exception e) {
            System.err.println("Encryption failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
