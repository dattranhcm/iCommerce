package com.technicaltest.icommerce_gateway.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

public class UserActivity implements Serializable {
    private String userUUID;
    private String activityName;
    private String actionContent;

    public UserActivity() {}

    public UserActivity(String userUUID, String activityName, String actionContent) {
        this.userUUID = userUUID;
        this.activityName = activityName;
        this.actionContent = actionContent;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setActionContent(String actionContent) {
        this.actionContent = actionContent;
    }

    public String getUserUUID() {
        return userUUID;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getActionContent() {
        return actionContent;
    }
}
