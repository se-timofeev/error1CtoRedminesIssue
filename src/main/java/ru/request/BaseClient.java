package ru.request;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import ru.model.redmine.IssueDto;

import java.util.HashMap;
import java.util.Map;

public class BaseClient {
    protected final RestTemplate rest;
    protected final String redmineApiKey;

    public BaseClient() {
        String redmineApiKey = "5459e2250590eaf4c4eb188ce13781676011f860";
        RestTemplate rest = new RestTemplate();

        this.rest = rest;
        this.redmineApiKey = redmineApiKey;
    }


    protected  void createIssue (String path, Map<String, Object> issue){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("X-Redmine-API-Key",redmineApiKey);
        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("issue", issue);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, httpHeaders);
        ResponseEntity<String> responseEntity = rest.exchange(path, HttpMethod.POST, requestEntity, String.class);


    }

}
