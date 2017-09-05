package com.example.reservationclient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;

@SpringBootApplication
public class ReservationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationClientApplication.class, args);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Reservation {
    private Long id;
    private String reservationName;
}

@Configuration
class ReservationClientConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ReservationClient client(RestTemplate restTemplate) {
        return new ReservationClient(restTemplate);
    }
}

class ReservationClient {

    private final RestTemplate restTemplate;

    public ReservationClient(RestTemplate rt) {
        this.restTemplate = rt;
    }

    public Collection<Reservation> read() {

        ParameterizedTypeReference<Collection<Reservation>> ptr =
                new ParameterizedTypeReference<Collection<Reservation>>() {
                };

        return this
                .restTemplate
                .exchange("http://localhost:8081/reservations", HttpMethod.GET, null, ptr)
                .getBody();
    }
}