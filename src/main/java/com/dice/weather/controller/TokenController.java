package com.dice.weather.controller;

import com.dice.weather.request.UserRegistrationRequest;
import com.dice.weather.service.CustomerUserDetailsService;
import com.dice.weather.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TokenController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private  CustomerUserDetailsService customerUserDetailsService;

    @PostMapping("/token")
    public ResponseEntity<String> generateToken(@RequestBody UserRegistrationRequest registrationRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registrationRequest.getUsername(), registrationRequest.getPassword()));

        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad Credentials");
        }

        UserDetails userDetails = this.customerUserDetailsService.loadUserByUsername(registrationRequest.getUsername());

        String token = this.jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }


}
