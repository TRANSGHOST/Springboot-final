package com.shuihuo.blog.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(value ="blogComment" )
public class Comment {
    private String username;
    private Integer articleId;
    private String context;
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContext() {
        return context;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public String getUsername() {
        return username;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
