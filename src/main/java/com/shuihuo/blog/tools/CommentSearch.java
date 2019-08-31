package com.shuihuo.blog.tools;

import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

public class CommentSearch {
    Integer articleId;
    Integer page;

    public Integer getArticleId() {
        return articleId;
    }

    public Integer getPage() {
        return page;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
