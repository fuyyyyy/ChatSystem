package com.bean;

public class group_information {

    private Integer groupId;
    private String groupName;
    private Integer createUserId;
    private String groupCreateTime;

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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getGroupCreateTime() {
        return groupCreateTime;
    }

    public void setGroupCreateTime(String groupCreateTime) {
        this.groupCreateTime = groupCreateTime;
    }

    public group_information() {
    }

    public group_information(Integer groupId, String groupName, Integer createUserId, String groupCreateTime) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.createUserId = createUserId;
        this.groupCreateTime = groupCreateTime;
    }

}
