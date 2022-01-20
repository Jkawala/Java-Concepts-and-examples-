package NeworkTraffic;

/**
 *
 * @author joswel bautista, James Kawala, Lloyd Portes
 */
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

public class EcryptionMessages {

    private static SecretKeySpec secretKey;
    private static final String algorithm = "AES";
    private static byte[] key;

    public void secreteKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String decryptionator(String strToDecrypt, String secret) {
        try {
            secreteKey(secret);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error decrypting: " + e.toString());
        }
        return null;
    }

    public String encrptionator(String strToEncrypt, String secret) {
        try {
            secreteKey(secret);
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error encrypting: " + e.toString());
        }
        return null;
    }

}
