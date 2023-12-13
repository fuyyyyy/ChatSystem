package com.bean;

public class user_relationship {

    private Integer relationshipId;
    private Integer userId1;
    private Integer userId2;
    private String createTime;

    public Integer getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Integer relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Integer getUserId1() {
        return userId1;
    }

    public void setUserId1(Integer userId1) {
        this.userId1 = userId1;
    }

    public Integer getUserId2() {
        return userId2;
    }

    public void setUserId2(Integer userId2) {
        this.userId2 = userId2;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public user_relationship() {
    }

    public user_relationship(Integer relationshipId, Integer userId1, Integer userId2, String createTime) {
        this.relationshipId = relationshipId;
        this.userId1 = userId1;
        this.userId2 = userId2;
        this.createTime = createTime;
    }

}
