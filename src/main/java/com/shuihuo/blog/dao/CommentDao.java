package com.shuihuo.blog.dao;

import com.shuihuo.blog.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface CommentDao extends MongoRepository<Comment,Integer>{
    ArrayList<Comment> findCommentByArticleId(Integer articleId);
}
