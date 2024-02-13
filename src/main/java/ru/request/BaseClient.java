package ru.request;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class BaseClient {
    protected final RestTemplate rest;
    protected final String redmineApiKey;

    public BaseClient(RestTemplate rest, String redmineApiKey) {
        this.rest = rest;
        this.redmineApiKey = redmineApiKey;
    }
    protected  <T>ResponseEntity<Object> post (String path, T body){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Redmine-API-Key",redmineApiKey);
        return post(path,body);
    }
}
