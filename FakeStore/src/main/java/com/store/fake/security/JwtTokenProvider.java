package com.store.fake.security;

import com.store.fake.utils.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenProvider {

    private final JwtProperties jwtProperties = new JwtProperties();
    public static final int REFRESH_TOKE_EXPIRATION = 12 * 60 * 60 * 1000; // 12 hours
    public static final int ACCESS_TOKEN_EXPIRATION = 60 * 60 * 1000; // 1 hour

    public Key getKey(){
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_16));
    }

    public String createToken(int id, String userName, String name) {


        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        return Jwts.builder()
                .setAudience(userName)
                .setId(String.valueOf(id))
                .setIssuedAt(now)
                .setIssuer(name)
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKE_EXPIRATION))
                .signWith(getKey())
                .compact();

    }

    public  String refreshToken(int id, String userName, String name){

        return Jwts.builder()
                .setAudience(userName)
                .setId(String.valueOf(id))
                .setIssuer(name)
                .setExpiration(new Date(System.currentTimeMillis() +  ACCESS_TOKEN_EXPIRATION))
                .signWith(getKey())
                .compact();
    }

    public Map<String, Object> validateToken(String token) {
        Map<String, Object> tokenDetail  = new HashMap<>();

        try {

            Claims claims = Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();

            tokenDetail.put("ID", claims.getId());
            tokenDetail.put("Name", claims.getAudience());
            tokenDetail.put("Subject", claims.getSubject());
            tokenDetail.put("Issuer", claims.getIssuer());
            tokenDetail.put("Expiration", claims.getExpiration());
            tokenDetail.put("Message", null);

        } catch (JwtException | IllegalArgumentException e) {
            tokenDetail.put("Error", CommonConstants.EXPIRED_TOKEN );
            tokenDetail.put("Code", CommonConstants.HTTP_CODE_UNAUTHORIZED);
        }	catch(Exception ex){
            tokenDetail.put("Error", "Token de acceso no valido");
            tokenDetail.put("Code", CommonConstants.HTTP_CODE_UNAUTHORIZED);
        }
        return tokenDetail;
    }


    public String getResponseBody(HttpServletRequest request) {
        ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
        return new String(requestWrapper.getContentAsByteArray());
    }

    /**
     * @return the claims
     */
    public   final  String getClaims(HttpServletRequest request,String name) {
        String jwtToken = request.getHeader(CommonConstants.HEADER).replace(CommonConstants.HEADER_PREFIX, "");
        return validateToken(jwtToken).get(name).toString();
    }
}
