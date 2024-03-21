package com.dice.weather;

import com.dice.weather.response.VisualCrossingForeCastSummaryResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class VisualCrossingClient{
    @Autowired
    private final HttpClient httpClient;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Value("${prod.service.host}")
    private String serviceUrl;
    @Value("${prod.visualCrossing.key}")
    private String API_KEY;



    public String getServiceUrl() {
        return this.serviceUrl;
    }

    @Autowired
    public VisualCrossingClient(HttpClient httpClient){
        this.httpClient = httpClient;
    }

    public VisualCrossingForeCastSummaryResponse getWeatherForeCast(String city) throws IOException {
        String apiUrl = getServiceUrl() + city;
        HttpGet request = new HttpGet(apiUrl + "?unitGroup=metric&key=" + API_KEY);
        return executeRequest(request);
    }

    private VisualCrossingForeCastSummaryResponse executeRequest(HttpGet request) throws IOException {
        VisualCrossingForeCastSummaryResponse responseMap = null;
        try {
            responseMap = httpClient.execute(request, httpResponse -> {
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    return objectMapper.readValue(httpResponse.getEntity().getContent(), VisualCrossingForeCastSummaryResponse.class);
                }
                // Handle other status codes or error cases here
                return null;
            });
        } finally {
            request.releaseConnection();
        }
        return responseMap;
    }
}

