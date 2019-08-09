package com.internship.tmontica_ui.menu;

import com.internship.tmontica_ui.menu.Response.MenuCategoryResp;
import com.internship.tmontica_ui.menu.Response.MenuDetailResp;
import com.internship.tmontica_ui.menu.Response.MenuMainResp;
import com.internship.tmontica_ui.RestTemplateResponseErrorHandler;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/api/menus")
public class MenuController {

    private final RestTemplate restTemplate;

    private String tmonticaMenuApi;

    public MenuController(RestTemplateBuilder restTemplateBuilder, @Value("${api.url}")String apiUrl) {
        this.restTemplate = restTemplateBuilder
                            .setConnectTimeout(Duration.ofSeconds(10))
                            .setReadTimeout(Duration.ofSeconds(10))
                            .errorHandler(new RestTemplateResponseErrorHandler())
                            .build();

        this.tmonticaMenuApi = apiUrl + "/api/menus/";
    }

    @GetMapping
    public ResponseEntity<List<MenuMainResp>> getAllMenus(){
        log.info("url : {}",tmonticaMenuApi);
        return restTemplate.exchange(tmonticaMenuApi, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<MenuMainResp>>() {});

    }

    @GetMapping("/{menuId:\\d+}")
    public ResponseEntity getMenuDetail(@PathVariable("menuId")int menuId){

        return restTemplate.getForEntity(tmonticaMenuApi + menuId, MenuDetailResp.class);

    }

    @GetMapping("/{category:[a-z-]+}")
    public ResponseEntity getMenusByCategory(@PathVariable("category")String category){

        return restTemplate.getForEntity(tmonticaMenuApi + category, MenuCategoryResp.class);


    }

}
