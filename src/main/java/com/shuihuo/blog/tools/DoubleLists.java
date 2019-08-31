package com.shuihuo.blog.tools;

import com.shuihuo.blog.entity.Article;
import com.shuihuo.blog.entity.User;

import java.util.ArrayList;

public class DoubleLists {
    private ArrayList<Article> articleArrayList;
    private ArrayList<User> userArrayList;
    private Integer articleNumber;
    private Integer userNumber;

    public ArrayList<Article> getArticleArrayList() {
        return articleArrayList;
    }

    public ArrayList<User> getUserArrayList() {
        return userArrayList;
    }

    public void setArticleArrayList(ArrayList<Article> articleArrayList) {
        this.articleArrayList = articleArrayList;
    }

    public void setArticleNumber(int articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setUserArrayList(ArrayList<User> userArrayList) {
        this.userArrayList = userArrayList;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getArticleNumber() {
        return articleNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }
}
