package com.shuihuo.blog.controller;

import com.shuihuo.blog.dao.ArticleDao;
import com.shuihuo.blog.entity.Article;
import com.shuihuo.blog.result.Result;
import com.shuihuo.blog.result.ResultFactory;
import com.shuihuo.blog.tools.HomeTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class HomeController {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ResultFactory resultFactory;
    @RequestMapping("/api/home")
    public Result homeCategory(@Valid @RequestBody HomeTag homeTag){
        //System.out.println(homeTag.getTagId());
        if(homeTag.getTagId()==1){
            ArrayList<Article> articles= (ArrayList<Article>) articleDao.findArticleByLabel("技术剖析");
            if(articles.size()>=8){
                return resultFactory.buildSuccessResult(articles.subList(0,8));
            }else{
               // System.out.println(articles);
                return resultFactory.buildSuccessResult(articles);
            }

        } else if (homeTag.getTagId() == 2) {
            ArrayList<Article> articles= (ArrayList<Article>) articleDao.findArticleByLabel("业界新闻");
            if(articles.size()>=8){
                return resultFactory.buildSuccessResult(articles.subList(0,8));
            }else{
                //System.out.println(articles);
                return resultFactory.buildSuccessResult(articles);
            }
        }else if (homeTag.getTagId() == 3) {
            ArrayList<Article> articles= (ArrayList<Article>) articleDao.findArticleByLabel("经验随笔");
            if(articles.size()>=8){
                return resultFactory.buildSuccessResult(articles.subList(0,8));
            }else{
                //System.out.println(articles);
                return resultFactory.buildSuccessResult(articles);
            }
        }else if (homeTag.getTagId() == 4) {
            ArrayList<Article> articles= (ArrayList<Article>) articleDao.findArticleByLabel("软件工程");
            if(articles.size()>=8){
                return resultFactory.buildSuccessResult(articles.subList(0,8));
            }else{
                //System.out.println(articles);
                return resultFactory.buildSuccessResult(articles);
            }
        }else if (homeTag.getTagId() == 5) {
            ArrayList<Article> articles= (ArrayList<Article>) articleDao.findArticleByLabel("工具推荐");
            if(articles.size()>=8){
                return resultFactory.buildSuccessResult(articles.subList(0,8));
            }else{
                //System.out.println(articles);
                return resultFactory.buildSuccessResult(articles);
            }
        }else if (homeTag.getTagId() == 6) {
            List<Article> articles=articleDao.findAll(new Sort(Sort.Direction.DESC,"commentNumber"));
            if(articles.size()>=8){
                return resultFactory.buildSuccessResult(articles.subList(0,8));
            }else{
                /*for(Article a:articles){
                    System.out.println(a.getArticleId());
                }*/
                return resultFactory.buildSuccessResult(articles);
            }
        }else if (homeTag.getTagId() == 7) {
            List<Article> articles=articleDao.findAll(new Sort(Sort.Direction.DESC,"articleId"));
            if(articles.size()>=8){
                return resultFactory.buildSuccessResult(articles.subList(0,8));
            }else{
                //System.out.println(articles.get(0).getTitle());
                return resultFactory.buildSuccessResult(articles);
            }
        }
        return resultFactory.buildFailResult("错误操作");
    }
}
