package com.example.demo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

public class PasswordUtils {
    
    /**
     * Encripta la contraseña usando SHA + Base64
     * (mismo método que pfb.encriptar_clave())
     */
    public static String encriptarClave(String clave) {
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("SHA");
        } catch(NoSuchAlgorithmException e) {
            throw new IllegalStateException(e.getMessage());
        }

        try {
            md.update(clave.getBytes("UTF-8"));
        } catch(java.io.UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }

        byte raw[] = md.digest();
        Base64.Encoder encoder = Base64.getEncoder();
        String clave_encriptada = encoder.encodeToString(raw);
        
        return clave_encriptada;
    }
    
    /**
     * Genera contraseña aleatoria
     * (equivalente a PasswordGenerator del código original)
     */
    public static String generarClaveAleatoria(int longitud) {
        String caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder clave = new StringBuilder();
        Random random = new Random();
        
        for (int i = 0; i < longitud; i++) {
            clave.append(caracteres.charAt(random.nextInt(caracteres.length())));
        }
        
        return clave.toString();
    }
}
