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
    private String priority_id;
    private String user;
    private String description;
    private String subject;
    private String category_id;
    private String parent_issue_id;
    private ArrayList <String> watcher_user_ids;

    public static IssueDto fromIssue(Issue issue) {
        IssueDto issueDto = new IssueDto();

        issueDto.subject = issue.getSubject();
        issueDto.description = issue.getDescription();
        issueDto.priority_id = issue.getPriority();
        issueDto.category_id = issue.getCategory();
        issueDto.tracker_id= issue.getTracker();
        issueDto.parent_issue_id = issue.getParentIssueId();
        issueDto.watcher_user_ids=issue.getWatchers();
        return issueDto;
    }
}
