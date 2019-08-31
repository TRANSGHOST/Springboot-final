package com.shuihuo.blog.dao;

import com.shuihuo.blog.entity.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

public interface ArticleDao extends MongoRepository<Article,Integer> {
    Article findArticleByArticleId(Integer articleId);
    ArrayList<Article> findArticleByTitleContains(String title);
    ArrayList<Article> findArticleByAuthorNameLike(String authorName);
    ArrayList<Article> findArticleByContextLike(String context);
    ArrayList<Article> findArticleByAuthorName(String authorName);
    ArrayList<Article> findArticleByLabel(String label);

}

