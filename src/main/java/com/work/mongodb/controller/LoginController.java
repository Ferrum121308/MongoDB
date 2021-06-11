package com.work.mongodb.controller;


import com.work.mongodb.entity.LoginUser;
import com.work.mongodb.entity.User;
import com.work.mongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {


    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Map<String,Object> status(@RequestBody LoginUser loginUser){
        Map<String,Object> status = new HashMap<>();
        String userAccount = loginUser.getUserAccount();
        String password = loginUser.getPassword();
        System.out.println(password);
        User user = userService.findUserByUserAccount(userAccount);
        System.out.println(user.getPassword());
        if(user != null){
            if(user.getPassword().equals(password)){
                status.put("status","200");
                status.put("userId",user.getUserId());
                status.put("userAccount",user.getUserAccount());
                status.put("password",user.getPassword());
                status.put("power",user.getPower());
                status.put("projectId",user.getProjectId());
            }
            else{
                status.put("status","403");
            }
        }
        else {
            status.put("status","404");
        }
        return status;
    }


}
