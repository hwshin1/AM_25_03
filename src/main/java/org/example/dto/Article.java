package org.example.dto;

public class Article extends Dto {
    private String updateTime;
    private String title;
    private String content;

    private int memberId;

    public Article(int id, String regDate, String updateTime, int memberId, String title, String content) {
        this.id = id;
        this.regDate = regDate;
        this.updateTime = updateTime;
        this.memberId = memberId;
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getRegDate() {
        return regDate;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
