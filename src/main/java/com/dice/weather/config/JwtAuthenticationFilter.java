package com.dice.weather.config;

import com.dice.weather.service.CustomerUserDetailsService;
import com.dice.weather.utils.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Component
    public class JwtAuthenticationFilter extends OncePerRequestFilter {

        @Autowired
        private JwtUtil jwtUtil;

        @Autowired
        private CustomerUserDetailsService customerUserDetailsService;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            String requestTokenHeader = request.getHeader("Authorization");
            String jwtToken = null;

            if (!StringUtils.isEmpty(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
                jwtToken = requestTokenHeader.substring(7);

                try {
                    String userName = jwtUtil.getUserNameFromToken(jwtToken);
                    UserDetails userDetails = customerUserDetailsService.loadUserByUsername(userName);

                    if (SecurityContextHolder.getContext().getAuthentication() == null) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }

                    filterChain.doFilter(request, response);
                } catch (ExpiredJwtException e) {
                    sendErrorResponse(response, "Token expired", HttpStatus.UNAUTHORIZED);
                } catch (SignatureException | UsernameNotFoundException e) {
                    sendErrorResponse(response, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
                }
            } else {
                filterChain.doFilter(request, response);
            }
        }

        private void sendErrorResponse(HttpServletResponse response, String message, HttpStatus status) throws IOException {
            response.setContentType("application/json");
            response.setStatus(status.value());

            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("message", message);

            objectMapper.writeValue(response.getWriter(), responseBody);
        }

}


