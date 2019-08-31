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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;


@CrossOrigin
@RestController
public class SearchController {

    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ResultFactory resultFactory;
    @Autowired
    private UserDao userDao;


    @PostMapping(value = "/api/searcharticle")
    public Result searchArticle(@Valid @RequestBody SearchKey key){
       // System.out.println("key: "+key.getKeyword()+key.getPage());
        //找文章
        ArrayList<Article> searchResult= new ArrayList<>();
        searchResult.addAll(articleDao.findArticleByTitleContains(key.getKeyword()));
        searchResult.addAll(articleDao.findArticleByContextLike(key.getKeyword()));
        searchResult.addAll(articleDao.findArticleByAuthorNameLike(key.getKeyword()));
        ArrayList<Article> finalResult= RemoveDuplicate.removeDuplicate(searchResult);//文章最终结果

        DoubleLists doubleLists=new DoubleLists();

        doubleLists.setArticleNumber(finalResult.size());

        if(finalResult.size()>=5){
            ArrayList<Article> returner=new ArrayList<Article>();
            if(finalResult.size()>=key.getPage()*5){
                for(int i=0;i<5;i++){
                    returner.add(finalResult.get(5*(key.getPage()-1)+i));
                }
            }else{
                for(int i=5*(key.getPage()-1);i<finalResult.size();i++){
                    returner.add(finalResult.get(i));
                }
            }
            doubleLists.setArticleArrayList(returner);
        }else{
            doubleLists.setArticleArrayList(searchResult);
        }//搜索文章结束

        ArrayList<User> userArrayList =userDao.findUserByUsernameLike(key.getKeyword());//搜索用户匹配
        //System.out.println(userArrayList.size());
        doubleLists.setUserNumber(userArrayList.size());
        if(userArrayList.size()>=5){
            ArrayList<User> userReturner=new ArrayList<User>();
            if(userArrayList.size()>key.getPage()*5)  {
                for (int i = 0; i < 5; i++)
                userReturner.add(userArrayList.get(5 * (key.getPage() - 1) + i));
            }
            else{
                for(int i=5*(key.getPage()-1);i<userArrayList.size();i++){
                    userReturner.add(userArrayList.get(i));
                }
            }
            doubleLists.setUserArrayList(userReturner);
        }else{
            doubleLists.setUserArrayList(userArrayList);
        }//搜索用户结束

        System.out.println(doubleLists.getArticleArrayList()+" "+doubleLists.getArticleNumber());
        System.out.println(doubleLists.getUserArrayList()+" "+doubleLists.getUserNumber());
        return resultFactory.buildSuccessResult(doubleLists);
    }
}
