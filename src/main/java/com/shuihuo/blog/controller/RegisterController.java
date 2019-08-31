package com.shuihuo.blog.controller;

import com.shuihuo.blog.dao.UserDao;
import com.shuihuo.blog.entity.User;
import com.shuihuo.blog.result.Result;
import com.shuihuo.blog.result.ResultFactory;
import com.shuihuo.blog.tools.RandomCheckNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

@CrossOrigin
@RestController
public class RegisterController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ResultFactory resultFactory;



    protected static   String code;


    @PostMapping(value = "/api/checkmail")
    public Result checkmail(@Valid @RequestBody User requestUser) {
        //System.out.println(requestUser+requestUser.getMailAddress());

        User registeredUser = userDao.findUserByMailAddress(requestUser.getMailAddress());
        if(registeredUser != null) {
            return resultFactory.buildFailResult("邮箱已被占用");
        }else{
            RandomCheckNumber randomCheckNumber=new RandomCheckNumber();
            String code=randomCheckNumber.randomCodeGenerator();
            this.code=code;
            this.sendSimpleMail(requestUser.getMailAddress(),code);
            return resultFactory.buildSuccessResult("邮件已发送");
        }
    }

    public  MimeMessage getMimeMessage(Session session, String senderAddress, String recipientAddress) throws Exception{
        //创建一封邮件的实例对象
        MimeMessage msg = new MimeMessage(session);
        //设置发件人地址
        msg.setFrom(new InternetAddress(senderAddress));
        msg.setRecipient(MimeMessage.RecipientType.TO,new InternetAddress(recipientAddress));
        //设置邮件主题
        msg.setSubject("邮件主题","UTF-8");
        //设置邮件正文
        msg.setContent("这是您的验证码" + this.code, "text/html;charset=UTF-8");
        //设置邮件的发送时间,默认立即发送
        msg.setSentDate(new Date());

        return msg;
    }

    public void sendSimpleMail(String mailAddress,String code){
        String myMailAddress = "1072918507@qq.com";
        String myMailPassword = "iynnhwcavjklbffd";
        String myMailSMTPHost = "smtp.qq.com";
        MimeMessage msg=null;
        try{
            Properties props = new Properties();
            //设置用户的认证方式
            props.setProperty("mail.smtp.auth", "true");
            //设置传输协议
            props.setProperty("mail.transport.protocol", "smtp");
            //设置发件人的SMTP服务器地址
            props.setProperty("mail.smtp.host", myMailSMTPHost);
            //2、创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getInstance(props);
            //设置调试信息在控制台打印出来
            //session.setDebug(true);
            //3、创建邮件的实例对象
            msg = getMimeMessage(session, myMailAddress, mailAddress);
            //4、根据session对象获取邮件传输对象Transport
            Transport transport = session.getTransport();
            //设置发件人的账户名和密码
            transport.connect(myMailAddress, myMailPassword);
            //发送邮件，并发送到所有收件人地址，message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
            transport.sendMessage(msg,msg.getAllRecipients());
            //如果只想发送给指定的人，可以如下写法
            //transport.sendMessage(msg, new Address[]{new InternetAddress("xxx@qq.com")});
            //5、关闭邮件连接
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/api/register")
    public Result register(@Valid @RequestBody User requestUser, BindingResult bindingResult) {
        //System.out.println(requestUser.getCheckCode()+" "+this.code);
        if(bindingResult.hasErrors()) {
            return resultFactory.buildFailResult("系统错误，注册失败");
        }
        User registeredUser = userDao.findUserByUsername(requestUser.getUsername());
        if(registeredUser != null) {
            return resultFactory.buildFailResult("用户名已被占用");
        }else{
            if(!requestUser.getCheckCode().equals(this.code)){
                return resultFactory.buildFailResult("验证码错误");
            }else{
                userDao.save(requestUser);
                return resultFactory.buildSuccessResult("注册成功",requestUser);
            }
        }
    }
}

