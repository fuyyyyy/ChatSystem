package com.bean;

public class send_information {
    private String recordContent;
    private int sendId;
    private String sendName;
    private String sendTime;


    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public int getSendId() {
        return sendId;
    }

    public void setSendId(int sendId) {
        this.sendId = sendId;
    }

    public String getSendName() {
        return sendName;
    }

    public void setSendName(String sendName) {
        this.sendName = sendName;
    }

    public send_information() {
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public send_information(String recordContent, int sendId, String sendName, String sendTime) {
        this.recordContent = recordContent;
        this.sendId = sendId;
        this.sendName = sendName;
        this.sendTime = sendTime;
    }

}