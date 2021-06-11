package com.work.mongodb.service;


import com.work.mongodb.dao.UserDao;
import com.work.mongodb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    UserDao userDao;


    //查询全部用户信息
    public List<User> findAllUsers(){
        return userDao.findAll();
    }


    //通过userId查询用户
    public User findUserByUserId(String userId){
        return userDao.findUserByUserId(userId);
    }


    //通过account查询用户
    public User findUserByUserAccount(String userName){
        return userDao.findUserByUserAccount(userName);
    }

    //判断用户是否已经存在
    public boolean isExist(User incomeUser){
        String incomeUserId = incomeUser.getUserId();
        User uncheckUser = findUserByUserId(incomeUserId);
        return uncheckUser != null;
    }

    //对用户进行信息修改
    public Map<String,Object> saveUser(User incomeUser){
        Map<String,Object> condition = new HashMap<>();
        if(!isExist(incomeUser)){
            userDao.save(incomeUser);
            condition.put("condition","插入完成");
        }
        else {
            User user = findUserByUserId(incomeUser.getUserId());
            String userAccount = incomeUser.getUserAccount();
            String password = incomeUser.getPassword();
            String power = incomeUser.getPower();
            String[] projectId = incomeUser.getProjectId();
            if(userAccount != null){
                user.setUserAccount(userAccount);
            }
            if(password != null){
                user.setPassword(password);
            }
            if(power != null){
                user.setPower(power);
            }
            if(projectId != null){
                user.setProjectId(projectId);
            }
            userDao.save(user);
            condition.put("condition","更新完成");
        }
        return condition;
    }
    //根据用户userId进行删除
    public Map<String,Object> deleteByUserId(String userId){
        Map<String,Object> condition = new HashMap<>();
        userDao.deleteUserByUserId(userId);
        condition.put("condition","删除完成！");
        return condition;
    }

}
