package com.dice.weather;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WeatherAppApplication {
	@Bean
	public HttpClient httpClient() {
		return HttpClients.createDefault();
	}

	public static void main(String[] args) {
		SpringApplication.run(WeatherAppApplication.class, args);
	}

}
