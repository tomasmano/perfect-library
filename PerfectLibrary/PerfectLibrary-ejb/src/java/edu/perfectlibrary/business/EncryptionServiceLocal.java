/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.perfectlibrary.business;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Local;

/**
 *
 * @author Užívateľ
 */
@Local
public interface EncryptionServiceLocal {

    String encode(String password, String saltKey) throws NoSuchAlgorithmException, IOException;

    String generateSalt() throws UnsupportedEncodingException;
}
