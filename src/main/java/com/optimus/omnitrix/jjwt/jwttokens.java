package com.optimus.omnitrix.jjwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class jwttokens {

    private static final String security_key="d69jbNSko6rMhqImob9TEnzMKw58tLjy";// key to create token

    private   final SecretKey jwtkey= Keys.hmacShaKeyFor(security_key.getBytes());

    public  String GenerateToken(UserDetails  userDetails){
        return Jwts.builder()
                .subject(userDetails.getUsername()) /// to add username in token
                .issuedAt(new Date())
                .expiration(new Date( System.currentTimeMillis()+1000 * 60 * 60))
                .signWith(jwtkey, Jwts.SIG.HS256 ) //key added
                .compact();

    }
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith(jwtkey) // SECRET_KEY must be SecretKey, not String
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

}
