package com.shuihuo.blog.controller;

import com.shuihuo.blog.dao.ArticleDao;
import com.shuihuo.blog.dao.CommentDao;
import com.shuihuo.blog.entity.Article;
import com.shuihuo.blog.entity.Comment;
import com.shuihuo.blog.entity.User;
import com.shuihuo.blog.result.Result;
import com.shuihuo.blog.result.ResultFactory;
import com.shuihuo.blog.tools.CommentSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class CommentController {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ResultFactory resultFactory;

    @PostMapping(value = "/api/comments")
    public Result searchComment(@Valid @RequestBody CommentSearch commentSearch) {
        //System.out.println(commentSearch.getPage());
        ArrayList<Comment> returner = commentDao.findCommentByArticleId(commentSearch.getArticleId());
       // System.out.println(returner.size());
        if(returner.size()>=5){
            ArrayList<Comment> commentReturner=new ArrayList<Comment>();
            if(returner.size()>commentSearch.getPage()*5){
                for(int i=0;i<5;i++){
                    commentReturner.add(returner.get(5*(commentSearch.getPage()-1)+i));
                }
            }else{
                for(int i=5*(commentSearch.getPage()-1);i<returner.size();i++){
                    commentReturner.add(returner.get(i));
                }
            }
            //System.out.println(commentReturner);
            return resultFactory.buildSuccessResult(" ",returner.size(),commentReturner);
        }else{
            return resultFactory.buildSuccessResult(" ",returner.size(),returner);
        }
    }

    @PostMapping(value = "/api/savecomments")
    public Result saveComment(@Valid @RequestBody Comment comment){
        //System.out.println(comment.getContext());
        int ID=comment.getArticleId();
        commentDao.save(comment);
        Article article= articleDao.findArticleByArticleId(ID);
        article.setCommentNumber(commentDao.findCommentByArticleId(ID).size());
        articleDao.save(article);
        return resultFactory.buildSuccessResult();
    }
}
