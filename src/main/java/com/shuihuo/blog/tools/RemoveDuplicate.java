package com.shuihuo.blog.tools;

import com.shuihuo.blog.entity.Article;

import java.util.ArrayList;

public class RemoveDuplicate {
    public static ArrayList<Article> removeDuplicate(ArrayList<Article> arrayList){
        ArrayList<Article> listTmp=new ArrayList<Article>();
        for (Article a:arrayList){
            int flag=1;
            for (Article b:listTmp){
                if (a.getArticleId().equals(b.getArticleId())) {
                    flag = 0;
                    break;
                }
            }
            if(flag==1){
                listTmp.add(a);
            }
        }
        return listTmp;
    }
}
