package ru.request;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.model.redmine.IssueDto;

public class Redmine extends BaseClient{

    public Redmine(RestTemplate rest, String redmineApiKey) {
        super(rest, redmineApiKey);
    }

    public ResponseEntity <Object> addIssue (IssueDto issueDto){
        return post("",issueDto);
    }
}
