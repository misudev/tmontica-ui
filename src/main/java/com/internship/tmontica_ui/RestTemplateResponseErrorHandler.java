package com.internship.tmontica_ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.internship.tmontica_ui.exception.ApiClientException;
import com.internship.tmontica_ui.exception.ApiServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;


import java.io.IOException;
import java.util.Map;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {

        return (
                httpResponse.getStatusCode().series() == CLIENT_ERROR
                        || httpResponse.getStatusCode().series() == SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        log.info("status code : {}", httpResponse.getStatusCode());
        if (httpResponse.getStatusCode()
                .series() == SERVER_ERROR) {
            // handle SERVER_ERROR
            throw new ApiServerException();
        } else if (httpResponse.getStatusCode()
                .series() == CLIENT_ERROR) {
            // handle CLIENT_ERROR
            ObjectMapper mapper = new ObjectMapper();
            Map jsonMap = mapper.readValue(httpResponse.getBody(), Map.class);
            throw new ApiClientException(httpResponse.getStatusCode(), jsonMap);

        }
    }
}
