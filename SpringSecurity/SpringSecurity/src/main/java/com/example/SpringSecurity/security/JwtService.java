package com.example.SpringSecurity.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final String secreteKey = "nTcHbTBzfqSkbedG/pIHMVNYvi3M/4PCW69iVdXWaHA=";

    public String extractUsername(String token)
    {
        return extractClaims(token,Claims::getSubject);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T>T extractClaims(String token, Function<Claims,T>claimResolver)
    {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    // Generate Active Token
    public String generateToken( UserDetails userDetails)
    {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("tokenType", "active");
        Set<SimpleGrantedAuthority> collect = userDetails.getAuthorities().stream().map(grantedAuthority ->
                new SimpleGrantedAuthority(grantedAuthority.getAuthority())).collect(Collectors.toSet());
        extraClaims.put("roles", collect);
        return
                Jwts
                        .builder()
                        .setClaims(extraClaims)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 1000*60*2))
                        .signWith(getSignInKey())
                        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                        .compact();
    }

    // Generate Refresh Token
    public String generateRefreshToken( UserDetails userDetails)
    {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("tokenType", "refresh");
        return
                Jwts
                        .builder()
                        .setClaims(extraClaims)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 1000*60*10))
                        .signWith(getSignInKey())
                        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                        .compact();
    }

    public boolean validateToken(String token,UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpireDate(token).before(new Date());
    }

    public Date extractExpireDate(String token)
    {
        return extractClaims(token,Claims::getExpiration);
    }

    public Key getSignInKey()
    {
        byte[]keyBytes= Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractTokenType(String token){
        return extractClaims(token, c-> c.get("tokenType")).toString();
    }
}
