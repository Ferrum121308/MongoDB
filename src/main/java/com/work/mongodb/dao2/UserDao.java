package com.work.mongodb.dao2;

import com.work.mongodb.entity2.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserDao extends MongoRepository<User,String> {

    //通过用户名精确寻找用户
    User findUserByUserAccount(String userAccount);

    //通过用户名模糊查询用户
    List<User> findUsersByUserAccountContaining(String userAccount);

    //通过id查询用户
    User findUserByUserId(String userId);

    void deleteUserByUserId(String userId);
}
