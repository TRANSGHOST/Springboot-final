package com.shuihuo.blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(value="blogArticle")
public class Article {
    static public int ArticleID=0;
    @Id
    private Integer articleId;
    private String title;
    private String authorName;
    private String context;
    private String label;
    private String date;
    private Integer commentNumber;

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContext() {
        return context;
    }

    public String getLabel() {
        return label;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
