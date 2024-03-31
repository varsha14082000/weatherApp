package com.dice.weather.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class HeaderAuthentication {

    @Value("${clientId}")
    private String validClientId;

    @Value("${secret}")
    private String validClientSecret;


    private boolean isValidClient(String clientId,String clientSecret) {
        return clientId != null && clientSecret != null &&
                clientId.equals(validClientId) && clientSecret.equals(validClientSecret);
    }

    public boolean authenticateClient(HttpServletRequest request) {
        boolean isValid = true;

        String clientId = request.getHeader("X-Client-ID");
        String clientSecret = request.getHeader("X-Client-Secret");
        if (!isValidClient(clientId, clientSecret)) {
            isValid=false;
        }

        return isValid;
    }


}
