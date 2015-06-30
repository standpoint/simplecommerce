/**
 * Copyright (c) 2015. SimpleCommerce.pp.ua
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package ua.pp.simplecommerce.service.security;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * Created by Vladimir Kamenskiy on 28.06.2015.
 */
public class AuthenticationServiceImpl implements AuthenticationService {

    private final String PASSWORD_ALGORITHM = "PBKDF2WithHmacSHA1";
    private final String SALT_ALGORITHM = "SHA1PRNG";
    private final int DERIVED_KEY_LENGTH = 160;
    private final int ITERATIONS = 20000;
    private final int SALT_SIZE = 8;
    /**
     * Provides authentication process.
     *
     * @param attemptedPassword the password was obtained from user during attempt of the authenticate
     * @param encryptedPassword the encrypted password was stored during last successful registration or password change
     * @param salt              the salt that was stored with the encrypted password
     * @return true - if authentication was successful and false - if not.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    @Override
    public boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }

    /**
     * Encrypt the password that was obtained from user
     *
     * @param password non-encrypted user password
     * @param salt     the salt generated for the password
     * @return array of the bytes that expressed an encrypted password
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    @Override
    public byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, DERIVED_KEY_LENGTH);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(PASSWORD_ALGORITHM);
        return secretKeyFactory.generateSecret(keySpec).getEncoded();
    }

    /**
     * Generate the salt for password encryption in the future.
     *
     * @return array of the bytes - the generated salt
     * @throws NoSuchAlgorithmException
     */
    @Override
    public byte[] generateSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance(SALT_ALGORITHM);
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);
        return salt;
    }
}
