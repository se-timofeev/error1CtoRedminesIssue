package ru.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.model.redmine.IssueDto;

import java.util.HashMap;
import java.util.Map;

@Service
public class Redmine extends BaseClient{

    @Autowired
    public Redmine() {
        super();
    }

    public void  addIssue (IssueDto issueDto) throws JsonProcessingException {
        String path="http://192.168.0.42/issues.json";

        Map<String, Object> issue = new HashMap<>();
        issue.put("project_id", issueDto.getProject_id());
        issue.put("tracker_id", issueDto.getTracker_id());
        issue.put("subject", issueDto.getSubject());
        issue.put("description", issueDto.getDescription());
        issue.put("priority_id",issueDto.getPriority_id());
        issue.put("parent_issue_id", issueDto.getParent_issue_id());


        createIssue(path, issue);
    }
}
