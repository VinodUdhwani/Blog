package com.blog.Blog.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenHelper {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;  // 5 hours validity

    // Secret key for signing JWTs
    private final byte[] secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

    // Retrieve username from JWT token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Retrieve expiration date from JWT token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Retrieve any claim from the token using a claims resolver function
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getALLClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    // Retrieve all claims from the JWT token using the secret key
    public Claims getALLClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)  // Parse the JWT
                .getBody();  // Get the body (claims)
    }

    // Check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());  // Token is expired if expiration date is before current date
    }

    // Generate a token from user details (typically from Spring Security UserDetails)
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    // Create a JWT with the given claims and subject (username)
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)  // Add custom claims
                .setSubject(subject)  // Set the subject (usually the username)
                .setIssuedAt(new Date())  // Set the issue date
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))  // Set expiration date
                .signWith(SignatureAlgorithm.HS512,secretKey)  // Sign with the HS512 algorithm and the secret key
                .compact();  // Build the JWT and return it as a string
    }

    // Validate the token with the username from the UserDetails
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        // Check if the username matches and the token is not expired
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
