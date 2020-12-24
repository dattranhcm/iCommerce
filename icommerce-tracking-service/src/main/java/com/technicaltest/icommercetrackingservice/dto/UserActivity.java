package com.technicaltest.icommercetrackingservice.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user-activity")
public class UserActivity {
    @Id
    private String id;
    private String userName;
    private String activityName;
    private String actionContent;

    public UserActivity() {}

    public UserActivity(String userName, String activityName, String actionContent) {
        this.userName = userName;
        this.activityName = activityName;
        this.actionContent = actionContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActionContent() {
        return actionContent;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
