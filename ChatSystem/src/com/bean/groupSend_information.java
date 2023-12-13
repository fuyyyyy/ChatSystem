package com.bean;

public class groupSend_information {
    private Integer groupSendId;
    private String groupSendName;
    private Integer groupId;
    private String groupRecordContent;
    private String groupSendTime;

    public Integer getGroupSendId() {
        return groupSendId;
    }

    public void setGroupSendId(Integer groupSendId) {
        this.groupSendId = groupSendId;
    }

    public String getGroupSendName() {
        return groupSendName;
    }

    public void setGroupSendName(String groupSendName) {
        this.groupSendName = groupSendName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupRecordContent() {
        return groupRecordContent;
    }

    public void setGroupRecordContent(String groupRecordContent) {
        this.groupRecordContent = groupRecordContent;
    }

    public String getGroupSendTime() {
        return groupSendTime;
    }

    public void setGroupSendTime(String groupSendTime) {
        this.groupSendTime = groupSendTime;
    }

    public groupSend_information() {
    }

    public groupSend_information(Integer groupSendId, String groupSendName, Integer groupId, String groupRecordContent, String groupSendTime) {
        this.groupSendId = groupSendId;
        this.groupSendName = groupSendName;
        this.groupId = groupId;
        this.groupRecordContent = groupRecordContent;
        this.groupSendTime = groupSendTime;
    }
}
