package com.bean;

public class groupRelationship_information {

    private Integer userId;
    private Integer groupId;
    private String groupName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public groupRelationship_information() {
    }

    public groupRelationship_information(Integer userId, Integer groupId, String groupName) {
        this.userId = userId;
        this.groupId = groupId;
        this.groupName = groupName;
    }

}
