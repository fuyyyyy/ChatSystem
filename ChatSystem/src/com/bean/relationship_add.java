package com.bean;

public class relationship_add {
    private Integer userId;
    private String userName;
    private String password;
    private int relationship;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public relationship_add() {
    }

    public relationship_add(Integer userId, String userName, String password, int relationship) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.relationship = relationship;
    }
}
