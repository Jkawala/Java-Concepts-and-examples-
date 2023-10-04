package model;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Staff {
    String name;
    String password;
    int id;
    public ROLE role;

    Double payRate;
    public static int employeeid = 0;

    public Staff() {
    }

    public Staff(String name, String password) {
        this.name = name;
        this.password = password;
        String salt = null;
        try {
            salt = getSalt();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        String securePassword = get_SHA_1_SecurePassword(password, salt);
        System.out.println(securePassword);
        this.password = securePassword;
    }

    public static
    boolean checkPassword(String pass1){

        System.out.println("Checking "+pass1);
        boolean result = true;
        String sha1 = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(pass1.getBytes(StandardCharsets.UTF_8));
            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        String prefixHash = sha1.substring(0, 5).toUpperCase();
        String suffixHash = sha1.substring(5).toUpperCase();
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.pwnedpasswords.com/range/" + prefixHash;
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute();
             ResponseBody body = response.body()) {
            if (body != null) {
                String hashes = body.string();
                String[] lines = hashes.split("\\r?\\n");
                System.out.println("return " + lines.length);
                for (String line : lines) {
                    System.out.println("hashes found "+line);
                    if (line.startsWith(suffixHash)) {
                        System.out.println(
                                "password found, count: " + line.substring(line.indexOf(":") + 1));
                        result = false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String get_SHA_1_SecurePassword(String passwordToHash,
                                                  String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public static String get_SHA_256_SecurePassword(String passwordToHash,
                                                    String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public static String get_SHA_384_SecurePassword(String passwordToHash,
                                                    String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public static String get_SHA_512_SecurePassword(String passwordToHash,
                                                    String salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes());
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public static String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt.toString();
    }


    public static int getEploteeid() {
        return employeeid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }

    public void setRole(ROLE role) {
        this.role = role;
    }

    public ROLE getRole() {

        return role;
    }

    public void setPayRate(Double payRate) {
        this.payRate = payRate;
    }

    public Double getPayRate() {
        return payRate;
    }

    public static enum ROLE {ADMIN, LEAD, DEVELOPER}
}
