/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import javax.ejb.Stateless;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.apache.commons.codec.binary.Base64;
    
/**
 *
 * @author Užívateľ
 */
@Stateless
public class EncryptionService implements EncryptionServiceLocal {

    private static final int ITERATION_COUNT = 658;
    private static final String ALGORITHM_NAME = "SHA-256";
    private static final String CHARSET = "UTF-8";

    @Override
    public String generateSalt() throws UnsupportedEncodingException {
        Random r = new SecureRandom();
//        SecureRandom secureRandom=SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[20];
        r.nextBytes(salt);
        return new BigInteger(130, r).toString(32);
    }

    @Override
    public synchronized String encode(String password, String saltKey)
            throws NoSuchAlgorithmException, IOException {
        // Computation time is equal to the time needed by a legitimate user
        if (password == null || saltKey == null) {
            password = "";
            saltKey = "";
        }
        String encodedPassword = null;
        byte[] salt = base64ToByte(saltKey);
//        System.out.println(">>>>gonna print sun.misc.BASE64....");
//        printArray(salt);
//        Base64 apacheBase64 = new Base64(80);
//        salt = apacheBase64.decodeBase64(saltKey.getBytes());
//        System.out.println(">>>>gonna print org.apache.commons.codec.binary.Base64....");
//        printArray(salt);

        MessageDigest digest = MessageDigest.getInstance(ALGORITHM_NAME);
        digest.reset();
        digest.update(salt);

        byte[] btPass = digest.digest(password.getBytes(CHARSET));
        for (int i = 0; i < ITERATION_COUNT; i++) {
            digest.reset();
            btPass = digest.digest(btPass);
        }

        encodedPassword = byteToBase64(btPass);
//        System.out.println(">>> encodedPassword with sun.misc.BASE64= " + encodedPassword);
//        encodedPassword = Base64.encodeBase64String(btPass);
//        System.out.println(">>> encodedPassword with org.apache.commons.codec.binary.Base64= " + encodedPassword);

        return encodedPassword;
    }

    private byte[] base64ToByte(String str) throws IOException {

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] returnbyteArray = decoder.decodeBuffer(str);
        return returnbyteArray;
    }

    private String byteToBase64(byte[] bt) {
        BASE64Encoder endecoder = new BASE64Encoder();
        String returnString = endecoder.encode(bt);

        return returnString;
    }

    private void printArray(byte[] arr) {
        System.out.println(">>>>> printing: " + arr+" size: "+arr.length);
        for (int i = 0; i < arr.length; i++) {
            byte b = arr[i];
            System.out.println(b);

        }
    }
}
