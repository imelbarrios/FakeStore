package com.store.fake.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.fake.utils.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider  jwtTokenProvider= new JwtTokenProvider();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = resolveToken(request);
        Map<String, Object> tokenDetail = null;

        if (token != null) {

            tokenDetail = jwtTokenProvider.validateToken(request.getHeader(CommonConstants.HEADER).replace(CommonConstants.HEADER_PREFIX, ""));
            String val = (String) tokenDetail.get("Error");
            if(val != null){
                generateResponseToSend(tokenDetail, response);
                return;
            }else {
                setUpSpringAuthentication(tokenDetail);
            }

        }

        filterChain.doFilter(request, response);
    }

    private void setUpSpringAuthentication(Map<String, Object> tokenDetail) {


        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                tokenDetail.get("Subject"),
                null,
                null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }



    private GeneralResponse generateError(Map<String,Object> tokenDetail) {
        GeneralResponse errorToken = new GeneralResponse();
        errorToken.setStatus((Integer) tokenDetail.get("Code"));
        errorToken.setMessage((String) tokenDetail.get("Error"));

        return errorToken;
    }

    private byte[] restResponseBytes(GeneralResponse eErrorResponse) throws IOException {
        String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
        return serialized.getBytes();
    }

    private void generateResponseToSend(Map<String, Object> tokenDetail, HttpServletResponse response) {
        try {
            byte[] responseToSend = restResponseBytes(generateError(tokenDetail));
            response.setHeader("Content-Type", "application/json");
            response.setStatus(401);
            response.getOutputStream().write(responseToSend);
        }catch (Exception e) {
            logger.error("Error en JWT Authorization Filter");
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(CommonConstants.HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(CommonConstants.HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

}

