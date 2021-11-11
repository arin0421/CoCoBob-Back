package com.cocobob.server.controller;

import com.cocobob.server.domain.User;
import com.cocobob.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    //    회원가입
    @RequestMapping(method = RequestMethod.POST, path = "/join")
    public User register(@RequestBody User user) {
        return userService.insertUser(user);
    }

    //이메일 유효성 검사
    @RequestMapping(method = RequestMethod.GET, path = "/join/{email}")
    public Map<String, Object> verifyEmail(@PathVariable String email){
        Map<String, Object> response = new HashMap<>();
        if(userService.existEmail(email)==false){
            response.put("result", "사용 가능한 이메일입니다.");
            response.put("email", email);
        }
        else{
            response.put("result", "FAIL");
            response.put("reason", "이미 존재하는 이메일입니다.");
        }
        return response;
    }
}
