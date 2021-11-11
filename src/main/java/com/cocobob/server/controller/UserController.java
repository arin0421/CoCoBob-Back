package com.cocobob.server.controller;

import com.cocobob.server.domain.User;
import com.cocobob.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {

    @Autowired
    private UserService userService;
    //    회원가입
    @RequestMapping(method = RequestMethod.POST, path = "/join")
    public User register(@RequestBody User user) {
        return userService.insertUser(user);
    }
}
