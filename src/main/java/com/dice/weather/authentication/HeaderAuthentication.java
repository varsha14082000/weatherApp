package com.dice.weather.authentication;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HeaderAuthentication {

    private boolean isValidClient(String clientId,String clientSecret) {
        String validClientId = "1234";
        String validClientSecret = "wgxwbuxwbxijaxniqwunxiqh";
        return clientId != null && clientSecret != null &&
                clientId.equals(validClientId) && clientSecret.equals(validClientSecret);
    }

    public void authenticateClient(HttpServletRequest request) {
        String clientId = request.getHeader("X-Client-ID");
        String clientSecret = request.getHeader("X-Client-Secret");
        if (!isValidClient(clientId, clientSecret)) {
            throw new SecurityException("Invalid client credentials");
        }
    }


}
