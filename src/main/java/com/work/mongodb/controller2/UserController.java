package com.work.mongodb.controller2;


import com.work.mongodb.entity2.User;
import com.work.mongodb.service2.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/findAllUsers")
    public List<User> findAllUsers(){
        return userService.findAllUsers();
    }

    //测试是否能够返回正确的_id
    @RequestMapping(value = "/findUserByAccount",method = RequestMethod.POST)
    public User findUserByAccount(@RequestParam("account") String account){
        User user = userService.findUserByUserAccount(account);
        System.out.println(user.get_id());
        return user;
    }

    @RequestMapping(value = "/findUserById",method = RequestMethod.POST)
    public User findUserByUserId(@RequestParam("userId") String userId){
        User user = userService.findUserByUserId(userId);
        System.out.println(user.get_id());
        return user;
    }



    @RequestMapping(value = "/isExist",method = RequestMethod.POST)
    public boolean isExist(@RequestBody User user){
        return userService.isExist(user);
    }

    @RequestMapping(value = "/saveUser",method = RequestMethod.POST)
    public Map<String,Object> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    public Map<String,Object> deleteUserByUserId(@RequestParam("userId") String userId){
        return userService.deleteByUserId(userId);
    }

}
