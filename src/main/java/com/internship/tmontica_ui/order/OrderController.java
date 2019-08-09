package com.internship.tmontica_ui.order;

import com.internship.tmontica_ui.RestTemplateResponseErrorHandler;
import com.internship.tmontica_ui.order.model.request.OrderReq;
import com.internship.tmontica_ui.order.model.response.OrderListByUserIdResp;
import com.internship.tmontica_ui.order.model.response.OrderResp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final RestTemplate restTemplate;

    private String tmonticaOrderApi;

    public OrderController(RestTemplateBuilder restTemplateBuilder, @Value("${api.url}")String apiUrl) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
        this.tmonticaOrderApi = apiUrl + "/api/orders/";
    }

    @PostMapping
    public ResponseEntity<Map<String, Integer>> addOrder(@RequestBody OrderReq orderReq, @RequestHeader(value="authorization") String token,
                                                         @RequestHeader(value = "User-Agent")String userAgent){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        httpHeaders.set("User-Agent",userAgent);

        HttpEntity entity = new HttpEntity(orderReq, httpHeaders);

        return restTemplate.exchange(tmonticaOrderApi, HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, Integer>>() {});
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrder(@RequestHeader(value="authorization") String token, @PathVariable("orderId") int orderId){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(tmonticaOrderApi+orderId, HttpMethod.DELETE, entity, String.class);

    }

    @GetMapping("/{orderId:\\d+}")
    public ResponseEntity<OrderResp> getOrderByOrderId(@RequestHeader(value="authorization") String token, @PathVariable("orderId")int orderId) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(tmonticaOrderApi+orderId, HttpMethod.GET, entity, OrderResp.class);

    }

    @GetMapping
    public ResponseEntity<OrderListByUserIdResp> getOrderList(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                              @RequestParam(value = "size", required = false, defaultValue = "10") int size,
                                                              @RequestHeader(value="authorization") String token){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(tmonticaOrderApi + "?page={page}&size={size}", HttpMethod.GET, entity, OrderListByUserIdResp.class ,page, size);

    }

}
