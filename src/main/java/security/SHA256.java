package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256{

    public static String encrypt(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String input = "1234";
        String encrypted = encrypt(input);
        System.out.println("Texto Original: " + input);
        System.out.println("Texto Cifrado: " + encrypted);
    }
}
