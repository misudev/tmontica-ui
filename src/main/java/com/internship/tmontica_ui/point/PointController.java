package com.internship.tmontica_ui.point;

import com.internship.tmontica_ui.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@RestController
@RequestMapping("/api/points")
public class PointController {

    private String tmonticaCartApi;

    private final RestTemplate restTemplate;

    public PointController(RestTemplateBuilder restTemplateBuilder, @Value("${api.url}")String apiUrl) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
        this.tmonticaCartApi = apiUrl + "/api/points/";
    }

    @GetMapping
    public ResponseEntity<Integer> getUserPoint(@RequestHeader(value="authorization") String token) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(tmonticaCartApi, HttpMethod.GET, entity, Integer.class);
    }
}
