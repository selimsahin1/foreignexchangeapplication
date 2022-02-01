package com.selimsahin.foreignexchangeapplication.RestAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private HttpStatus status;

    private String server;
    private String accessKey;

    @Value("${api.baseUrl}")
    public void setServer(String server) {
        this.server = server;
    }

    @Value("${api.accessKey}")
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public HttpHeaders httpHeaders() {
        this.restTemplate = new RestTemplate();
        this.headers = new HttpHeaders();
        return headers;
    }

    public String getAPIRequest(String uri) {
        HttpHeaders httpHeaders = httpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<String>("", httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                server + "?access_key=" + accessKey + uri,
                HttpMethod.GET,
                requestEntity,
                String.class);
        this.setStatus(responseEntity.getStatusCode());
        System.out.println("Rest Client" + responseEntity.getBody());
        return responseEntity.getBody();
    }
}
