package ru.model.redmine;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
public class Issue {
    private String project;
    private String tracker;
    private String status;
    private String priority;
    private String user;
    private LocalDateTime time;
    private String description;
    private String subject;
    private String category;
    private String ParentIssueId;
    private ArrayList<String> watchers;

}
