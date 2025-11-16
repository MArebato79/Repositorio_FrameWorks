package com.example.ProyectoReservas.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final SecretKey secretKey;

    public JwtService(@Value("${jwt.secret-key}") String secretKeyString) {
        // Verificación de existencia para debugging
        if (secretKeyString == null || secretKeyString.isEmpty()) {
            throw new IllegalStateException("FATAL: La clave secreta JWT no se cargó correctamente desde application.properties.");
        }

        // Decodificación de la clave BASE64
        byte[] keyBytes = Decoders.BASE64.decode(secretKeyString);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    // Genera un token JWT para un usuario autenticado
    public String generateToken(Authentication authentication) {
        // Extraer los roles del usuario y convertirlos a String
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Construir el token JWT
        return Jwts.builder()
                .subject(authentication.getName())  // Email del usuario
                .issuer("gestion-centro-api")       // Quién emite el token
                .issuedAt(new Date())               // Cuándo se creó
                .expiration(new Date(
                        System.currentTimeMillis() + 86400000  // Expira en 24h
                ))
                .claim("roles", roles)            // Roles del usuario
                .signWith(secretKey)                // Firmar con clave secreta
                .compact();                         // Generar String del token
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
