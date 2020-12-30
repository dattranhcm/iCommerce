package com.technicaltest.icommerce_gateway.dto;

import java.io.Serializable;

public class UserActivity implements Serializable {
    private String userName;
    private String activityName;
    private String actionContent;

    public UserActivity() {}

    public UserActivity(String userName, String activityName, String actionContent) {
        this.userName = userName;
        this.activityName = activityName;
        this.actionContent = actionContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActionContent() {
        return actionContent;
    }
}
