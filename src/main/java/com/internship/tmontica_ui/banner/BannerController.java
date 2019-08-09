package com.internship.tmontica_ui.banner;

import com.internship.tmontica_ui.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/banners")
public class BannerController {

    private String tmonticaBannerApi;

    private final RestTemplate restTemplate;

    public BannerController(RestTemplateBuilder restTemplateBuilder, @Value("${api.url}")String apiUrl) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
        this.tmonticaBannerApi = apiUrl + "/api/banners/";
    }

    @GetMapping("/{usePageEng}")
    public ResponseEntity<List<Banner>> getBannerByUsePage(@PathVariable String usePageEng){

        return restTemplate.exchange(tmonticaBannerApi + usePageEng, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Banner>>() {});

    }
}
