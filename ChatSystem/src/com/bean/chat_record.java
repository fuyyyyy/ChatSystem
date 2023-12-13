package com.bean;

public class chat_record {
    private Integer recordId;
    private Integer relationshipId;
    private String recordContent;

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Integer relationshipId) {
        this.relationshipId = relationshipId;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }

    public chat_record() {
    }

    public chat_record(Integer recordId, Integer relationshipId, String recordContent) {
        this.recordId = recordId;
        this.relationshipId = relationshipId;
        this.recordContent = recordContent;
    }

}
