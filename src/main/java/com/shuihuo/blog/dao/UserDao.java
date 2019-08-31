package com.shuihuo.blog.dao;

import com.shuihuo.blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface UserDao extends MongoRepository<User,Integer> {
    User findUserByMailAddressAndPassword(String mailAddress,String password);
    User findUserByMailAddress(String mailAddress);
    User findUserByUsername(String username);
    ArrayList<User> findUserByUsernameLike(String name);
    User findUserByUsernameAndPassword(String username,String password);
}
