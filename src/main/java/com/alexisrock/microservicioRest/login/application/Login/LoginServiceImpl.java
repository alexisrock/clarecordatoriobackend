package com.alexisrock.microservicioRest.login.application.Login;


import com.alexisrock.microservicioRest.Common.enums.ParamConfig;
import com.alexisrock.microservicioRest.login.domain.entities.Users;
import com.alexisrock.microservicioRest.login.domain.interfaces.ConfigurationRepository;
import com.alexisrock.microservicioRest.login.domain.interfaces.LoginRepository;
import com.alexisrock.microservicioRest.Common.exceptions.ApiException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.springframework.stereotype.Service;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRepository repository;
    @Autowired
    private ConfigurationRepository configurationRepository;

    private String keyEncrypted; // Simula el valor desde tu repositorio
    private String ivEncrypted;

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse response;
        try {
            validationRequest(request);
            Users user;
            user = repository.getUserByEmail(request.getEmail());
            validationEmail(user);
            String pass = decodeBase64(request.getPassword());
            if (!validatePassword(pass, user.getPassword())) {
                throw new ApiException("Contraseña incorrecta");
            }
            String token = generateToken(user.getEmail());
            response = new LoginResponse(token);
            return response;
        } catch (Exception e) {
            throw e;
        }
    }


    public static String decodeBase64(String base64String) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            return new String(decodedBytes, StandardCharsets.UTF_8); // Or another charset if needed
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Base64 string: " + base64String, e);
        }
    }

    void validationRequest(LoginRequest request) {
        if (request == null)
            throw new ApiException("request invalido");

    }

    void validationEmail(Users users) {
        if (users == null)
            throw new ApiException("correo invalido");
    }

    public boolean validatePassword(String password, String encryptedPassword) {
        String keyEncrypted = configurationRepository.getValuesConfigurationsById(ParamConfig.KeyEncrypted.toString()).getValue();
        String iVEncrypted = configurationRepository.getValuesConfigurationsById(ParamConfig.IVEncrypted.toString()).getValue();

        try {
            byte[] key = keyEncrypted.getBytes(StandardCharsets.UTF_8);
            byte[] iv = iVEncrypted.getBytes(StandardCharsets.UTF_8);

            // Ensure key and IV are the correct length for Triple DES (24 bytes for key, 8 for IV)
            if (key.length != 24 || iv.length != 8) {
                System.err.println("Warning: Key or IV length is incorrect for Triple DES.");
                return false; // Or throw an exception if this is critical
            }

            KeySpec keySpec = new DESedeKeySpec(key);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            javax.crypto.SecretKey secretKey = keyFactory.generateSecret(keySpec);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

            byte[] encryptedPasswordBytes = Base64.getDecoder().decode(encryptedPassword);
            byte[] decryptedPasswordBytes = cipher.doFinal(encryptedPasswordBytes);
            String decryptedPasswordStr = new String(decryptedPasswordBytes, StandardCharsets.UTF_8).trim(); // Trim to remove potential padding

            return decryptedPasswordStr.equals(password);

        } catch (Exception e) {
            throw new ApiException("Error during password validation: " + e.getMessage());
        }

    }

    public String generateToken(String userName) {
        try {
            String secretKey = configurationRepository.getValuesConfigurationsById(ParamConfig.JwtSecretKey.toString()).getValue();
            String issuer = configurationRepository.getValuesConfigurationsById(ParamConfig.JwtIssuerToken.toString()).getValue();
            String audience = configurationRepository.getValuesConfigurationsById(ParamConfig.JwtAudienceToken.toString()).getValue();
            String expireMinutesStr = configurationRepository.getValuesConfigurationsById(ParamConfig.JwtExpireTime.toString()).getValue();

            int expireMinutes = Integer.parseInt(expireMinutesStr);
            Date now = new Date();
            Date expiryDate = new Date(now.getTime() + expireMinutes * 60 * 1000L);

            SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

            String jwt = Jwts.builder()
                    .setSubject(userName)
                    .setIssuer(issuer)
                    .setAudience(audience)
                    .setIssuedAt(now)
                    .setNotBefore(now)
                    .setExpiration(expiryDate)
                    .claim("unique_name", userName) // equivale a ClaimTypes.Name
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();

            return jwt;
        } catch (Exception e) {
            throw new RuntimeException("Error al generar el token", e);
        }
    }

    @Override
    public String searchEmailUsers(String email){
        return repository.getUserByEmail(email).getEmail();
    }
}
