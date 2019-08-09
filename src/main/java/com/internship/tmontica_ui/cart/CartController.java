package com.internship.tmontica_ui.cart;

import com.internship.tmontica_ui.RestTemplateResponseErrorHandler;
import com.internship.tmontica_ui.cart.model.request.CartReq;
import com.internship.tmontica_ui.cart.model.request.CartUpdateReq;
import com.internship.tmontica_ui.cart.model.response.CartIdResp;
import com.internship.tmontica_ui.cart.model.response.CartResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private String tmonticaCartApi;

    private final RestTemplate restTemplate;

    public CartController(RestTemplateBuilder restTemplateBuilder, @Value("${api.url}")String apiUrl) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
        this.tmonticaCartApi = apiUrl + "/api/carts/";
    }

    @PostMapping
    public ResponseEntity<List<CartIdResp>> addCart(@RequestHeader(value="authorization") String token,
                                                    @RequestBody List<CartReq> cartReqs){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(cartReqs, httpHeaders);

        return restTemplate.exchange(tmonticaCartApi, HttpMethod.POST, entity,
                                     new ParameterizedTypeReference<List<CartIdResp>>() {});
    }

    @GetMapping
    public ResponseEntity<CartResp> getCartMenu(@RequestHeader(value="authorization") String token){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(tmonticaCartApi, HttpMethod.GET, entity, CartResp.class);
    }


    @PutMapping("/{id}")
    public ResponseEntity updateCartMenuQuantity(@RequestHeader(value="authorization") String token,
                                                 @PathVariable("id") int id,
                                                 @RequestBody CartUpdateReq cartUpdateReq){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(cartUpdateReq, httpHeaders);

        return restTemplate.exchange(tmonticaCartApi + id , HttpMethod.PUT, entity, String.class);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCartMenu(@RequestHeader(value="authorization")String token,
                                         @PathVariable("id")int id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(tmonticaCartApi + id , HttpMethod.DELETE, entity, String.class);
    }
}
