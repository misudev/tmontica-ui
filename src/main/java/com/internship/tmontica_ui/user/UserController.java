package com.internship.tmontica_ui.user;

import com.internship.tmontica_ui.RestTemplateResponseErrorHandler;
import com.internship.tmontica_ui.user.Request.*;
import com.internship.tmontica_ui.user.Response.UserFindIdRespDTO;
import com.internship.tmontica_ui.user.Response.UserInfoRespDTO;
import com.internship.tmontica_ui.user.Response.UserSignInRespDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Duration;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private String tmonticaUserApi;

    private final RestTemplate restTemplate;

    public UserController(RestTemplateBuilder restTemplateBuilder, @Value("${api.url}")String apiUrl) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10))
                .setReadTimeout(Duration.ofSeconds(10))
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();

        this.tmonticaUserApi = apiUrl + "/api/users/";
    }

    @PostMapping("/signin")
    public ResponseEntity<UserSignInRespDTO> signIn(@RequestBody UserSignInReqDTO userSignInReqDTO){
        UserSignInRespDTO userSignInRespDTO =  restTemplate.postForObject(tmonticaUserApi+"signin",  userSignInReqDTO, UserSignInRespDTO.class);
        return new ResponseEntity<>(userSignInRespDTO, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserSignUpReqDTO userSignUpReqDTO) {
        return restTemplate.postForEntity(tmonticaUserApi+"signup", userSignUpReqDTO, String.class);
    }

    @GetMapping("/active")
    public ResponseEntity<String> activateUser(@RequestParam("id")String userId, @RequestParam("token")String token){
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(tmonticaUserApi + "active")
                .queryParam("id",userId)
                .queryParam("token",token).build();
        return restTemplate.getForEntity(builder.toUriString(), String.class);
    }

    @GetMapping("/duplicate/{userId}")
    public ResponseEntity<String> idDuplicateCheck(@PathVariable("userId") String id) {
        return restTemplate.getForEntity(tmonticaUserApi+"duplicate/"+id, String.class);
    }

    @PostMapping("/checkpw")
    public ResponseEntity<String> checkPassword(@RequestHeader(value="authorization") String token,
                                                @RequestBody UserCheckPasswordReqDTO userCheckPasswordReqDTO){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(userCheckPasswordReqDTO, httpHeaders);

        return restTemplate.exchange(tmonticaUserApi + "checkpw", HttpMethod.POST, entity, String.class);

    }

    @PutMapping
    public ResponseEntity<String> changePassword(@RequestHeader(value="authorization") String token,
                                                 @RequestBody UserChangePasswordReqDTO userChangePasswordReqDTO){

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(userChangePasswordReqDTO, httpHeaders);

        return restTemplate.exchange(tmonticaUserApi, HttpMethod.PUT, entity, String.class);

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> withDrawUser(@RequestHeader(value="authorization") String token,
                                               @PathVariable("userId") String id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("authorization", token);
        HttpEntity entity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(tmonticaUserApi + id, HttpMethod.DELETE, entity, String.class);

    }

    @GetMapping("/findid")
    public ResponseEntity<String> findUserId(@RequestParam("email") String email) {
        return restTemplate.getForEntity(tmonticaUserApi + "findid?email={email}", String.class, email);
    }

    @GetMapping("/findpw")
    public ResponseEntity<String> findUserPassword(@RequestParam("id") String id, @RequestParam("email") String email){
        return restTemplate.getForEntity(tmonticaUserApi + "findpw?id={id}&email={email}", String.class, id, email);
    }

    @PostMapping("/findid/confirm")
    public ResponseEntity<UserFindIdRespDTO> findIdConfirm(@RequestBody UserFindIdReqDTO userFindIdReqDTO){
        return restTemplate.postForEntity(tmonticaUserApi + "/findid/confirm", userFindIdReqDTO, UserFindIdRespDTO.class);

    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoRespDTO> getUerInfo(@PathVariable("userId") String id){
        return restTemplate.getForEntity(tmonticaUserApi + id, UserInfoRespDTO.class);
    }


}
