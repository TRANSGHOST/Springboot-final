package com.shuihuo.blog.controller;

import com.shuihuo.blog.dao.ArticleDao;
import com.shuihuo.blog.dao.CommentDao;
import com.shuihuo.blog.entity.Article;
import com.shuihuo.blog.result.Result;
import com.shuihuo.blog.result.ResultFactory;
import com.shuihuo.blog.tools.ArticleID;
import com.shuihuo.blog.tools.RemoveDuplicate;
import com.shuihuo.blog.tools.SearchKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@CrossOrigin
@RestController
public class ArticleController {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ResultFactory resultFactory;
    @Autowired
    private CommentDao commentDao;

    @RequestMapping(value = "/api/blog")
    public Result showBlog(@Valid @RequestBody ArticleID articleID){
        System.out.println(articleID.getArticleId());
        Article article=articleDao.findArticleByArticleId(articleID.getArticleId());
        if(article==null){
            return resultFactory.buildFailResult("未找到该文章");
        }else{
            System.out.println(article.getArticleId());
            System.out.println(article.getTitle());
            return resultFactory.buildSuccessResult(article);
        }
    }

    @PostMapping(value = "/api/savearticle")
    public Result saveArticle(@Valid @RequestBody Article requestArticle){
        //System.out.println(requestArticle.getLabel()+requestArticle.getAuthorName());
        requestArticle.setArticleId(Article.ArticleID++);
        Date now=new Date();
        SimpleDateFormat bartDateFormat = new SimpleDateFormat
                ("yyyy-mm-dd");
        requestArticle.setDate( bartDateFormat.format(now));
        switch(requestArticle.getLabel()){
            case "1":requestArticle.setLabel("技术解析");break;
            case "2":requestArticle.setLabel("业界新闻");break;
            case "3": requestArticle.setLabel("经验随笔");break;
            case "4": requestArticle.setLabel("软件工程");break;
            case "5": requestArticle.setLabel("工具推荐");break;
        }
        requestArticle.setCommentNumber(commentDao.findCommentByArticleId(requestArticle.getArticleId()).size());
        articleDao.save(requestArticle);
        return resultFactory.buildSuccessResult("保存成功");
    }



    @PostMapping(value = "/api/myarticle")
    public Result myArticle(@Valid @RequestBody String username){
        //System.out.println(username);
        ArrayList<Article> searchResult=new ArrayList<Article>();
        searchResult.addAll(articleDao.findArticleByAuthorName(username));
        if(searchResult.isEmpty()){
            return resultFactory.buildFailResult("未找到符合条件的文章");
        }else{
            return resultFactory.buildSuccessResult("找到的文章如下：",searchResult);
        }
    }


}
