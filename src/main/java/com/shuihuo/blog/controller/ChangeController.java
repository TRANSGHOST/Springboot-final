package com.shuihuo.blog.controller;


import com.shuihuo.blog.dao.UserDao;
import com.shuihuo.blog.entity.User;
import com.shuihuo.blog.result.Result;
import com.shuihuo.blog.result.ResultFactory;
import com.shuihuo.blog.tools.PwdChangeInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class ChangeController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private ResultFactory resultFactory;
    @PostMapping(value = "/api/changemail")
    public Result changeMail(@Valid @RequestBody User requestUser, BindingResult bindingResult) {
       // System.out.println(requestUser.getCheckCode()+" "+RegisterController.code);
        if(bindingResult.hasErrors()) {
            return resultFactory.buildFailResult("系统错误，注册失败");
        }
        User registeredUser = userDao.findUserByUsername(requestUser.getUsername());

        if(!requestUser.getCheckCode().equals(RegisterController.code)){
            return resultFactory.buildFailResult("验证码错误");
        }else{
            userDao.save(requestUser);
            return resultFactory.buildSuccessResult("修改成功",requestUser);
        }
    }

    @PostMapping(value = "/api/changePwd")
    public Result changePwd(@Valid @RequestBody PwdChangeInfo pwdChangeInfo){
        User getuser=userDao.findUserByUsername(pwdChangeInfo.getUsername());
        if(getuser==null){
            return resultFactory.buildFailResult("无此用户  ");
        }else{
            User user=userDao.findUserByUsernameAndPassword(pwdChangeInfo.getUsername(),pwdChangeInfo.getOldPassword());
            if(user != null) {
                user.setPassword(pwdChangeInfo.getNewPassword());
                userDao.save(user);
                return resultFactory.buildSuccessResult("修改成功", user);
            }
            return resultFactory.buildFailResult("密码错误");
        }
    }
}
