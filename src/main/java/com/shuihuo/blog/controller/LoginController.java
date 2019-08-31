package com.shuihuo.blog.controller;

import com.shuihuo.blog.dao.UserDao;
import com.shuihuo.blog.entity.User;
import com.shuihuo.blog.result.Result;
import com.shuihuo.blog.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class LoginController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ResultFactory resultFactory;

    @PostMapping(value = "/api/login")
    public Result login(@Valid @RequestBody User requestUser, HttpSession httpSession){
        //System.out.println(requestUser.getMailAddress()+requestUser.getPassword());
        User getuser=userDao.findUserByMailAddress(requestUser.getMailAddress());
        if(getuser==null){
            return resultFactory.buildFailResult("邮箱未注册");
        }else{
            User user=userDao.findUserByMailAddressAndPassword(requestUser.getMailAddress(),requestUser.getPassword());
            if(user != null) {
                httpSession.setAttribute("user", user);
                user.setPassword("null");
                //System.out.println(user.getUsername());
                return resultFactory.buildSuccessResult("登录成功", user);
            }
            return resultFactory.buildFailResult("密码错误");
        }
    }
}
