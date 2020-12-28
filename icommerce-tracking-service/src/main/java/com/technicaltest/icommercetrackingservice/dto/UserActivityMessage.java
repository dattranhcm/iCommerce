package com.technicaltest.icommercetrackingservice.dto;

import java.io.Serializable;

public class UserActivityMessage implements Serializable {
    private String userName;
    private String activityName;
    private String actionContent;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
}
