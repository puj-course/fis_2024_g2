package org.musify.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
    @Value("${jwt.secret.key}")
    private String secretKey; //Ayuda a firmar el método (como permiso de autenticación)
    @Value("${jwt.time.expiration}")
    private String timeExpiration; //En milisegundos


    //Generar token de acceso
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+ Long.parseLong(timeExpiration)))
                .signWith(getSignatureKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //Validar el token de acceso
    public boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSignatureKey())
                    .build()
                    .parseClaimsJws(token);

            return true; // Si no lanza excepción, el token es válido
        } catch (Exception e) {
            // Si cae en una excepción, significa que el token no es válido
            return false;
        }
    }

    // Obtener el username del token
    public String getUsernameFromToken(String token) {
        return getClaim(token, Claims::getSubject); // Obtener el claim del token
    }

    // Obtener un solo claim
    public <T> T getClaim(String token, Function<Claims, T> claimsTFunction) {
        Claims claims = extractAllClaims(token); // Extraer todos los claims
        return claimsTFunction.apply(claims);
    }

    // Obtener todos los claims del token
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignatureKey()) // Establecer la clave de firma
                .build()
                .parseClaimsJws(token) // Parsear el token
                .getBody(); // Obtener el cuerpo de los claims
    }




    //Obtener firma del token
    public Key getSignatureKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

//PARA GENERAR LA CLAVE DE 256 BITS EN APPLICATION PROPERTIES
//    public static void main(String[] args) {
//        // Genera una clave de 256 bits
//        SecretKey secretKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
//
//        // Codifica la clave en formato Base64 para almacenarla en application.properties
//        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
//
//        System.out.println("Clave generada en Base64: " + encodedKey);
//    }

}
