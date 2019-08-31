package com.shuihuo.blog.controller;

import com.shuihuo.blog.dao.ArticleDao;
import com.shuihuo.blog.dao.UserDao;
import com.shuihuo.blog.entity.Article;
import com.shuihuo.blog.entity.User;
import com.shuihuo.blog.result.Result;
import com.shuihuo.blog.result.ResultFactory;
import com.shuihuo.blog.tools.DoubleLists;
import com.shuihuo.blog.tools.RemoveDuplicate;
import com.shuihuo.blog.tools.SearchKey;
import com.shuihuo.blog.tools.UsernameGetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@CrossOrigin
@RestController
public class UserSpaceController {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ResultFactory resultFactory;

    @RequestMapping("/api/findmyblog")
    public Result searchArticle(@Valid @RequestBody SearchKey key){
        // System.out.println("key: "+key.getKeyword()+key.getPage());
        //找文章
        ArrayList<Article> searchResult= new ArrayList<>();
        searchResult.addAll(articleDao.findArticleByAuthorName(key.getKeyword()));
        ArrayList<Article> finalResult= RemoveDuplicate.removeDuplicate(searchResult);//文章最终结果

        if(finalResult.size()>=5){
            ArrayList<Article> returner=new ArrayList<Article>();
            if(finalResult.size()>=key.getPage()*5){
                for(int i=0;i<5;i++){
                    returner.add(finalResult.get(5*(key.getPage()-1)+i));

                }
                return resultFactory.buildSuccessResult(" ",finalResult.size(),returner);
            }else{
                for(int i=5*(key.getPage()-1);i<finalResult.size();i++){
                    returner.add(finalResult.get(i));
                }
                return resultFactory.buildSuccessResult(" ",finalResult.size(),returner);
            }
        }
        return resultFactory.buildSuccessResult(" ",finalResult.size(),finalResult);
    }


    @RequestMapping("/api/userinfo")
    public Result userInfo(@RequestBody UsernameGetter usernameGetter){
        String mail=userDao.findUserByUsername(usernameGetter.getUsername()).getMailAddress();
        return resultFactory.buildSuccessResult(mail,mail);
    }
}


