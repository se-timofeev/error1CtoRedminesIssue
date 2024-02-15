package ru.model.redmine;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@EqualsAndHashCode
public class IssueDto {
    private String project_id;
    private String tracker_id;
    private Integer priority_id;
    // private String user;
    private String description;
    private String subject;
    //private String category_id;
    private Integer parent_issue_id;
    //private ArrayList <String> watcher_user_ids;

    public static IssueDto fromIssue(Issue issue) {
        IssueDto issueDto = new IssueDto();
        issueDto.project_id = issue.getProject();
        if (issue.getSubject().isEmpty()) {
            issueDto.subject = "Ошибка без категории";
        } else {
            issueDto.subject = issue.getSubject();

        }
        issueDto.subject = "Пользователь: "+issue.getUser()+"; "+issueDto.subject;
        issueDto.description = "Пользователь:" + issue.getUser() + "/r/n/" +
                "Описание ошибки: " +
                "/r/n/" +
                issue.getDescription();

        issueDto.priority_id = Integer.valueOf(issue.getPriority());
        //issueDto.category_id = issue.getCategory();
        issueDto.tracker_id = issue.getTracker();
        issueDto.parent_issue_id = Integer.valueOf(issue.getParentIssueId());
        // issueDto.watcher_user_ids=issue.getWatchers();
        return issueDto;
    }
}
