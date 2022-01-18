package com.cocobob.server.controller;

import com.cocobob.server.domain.UserDTO;
import com.cocobob.server.mail.MailService;
import com.cocobob.server.service.UserService;
import com.cocobob.server.util.SecurityUtil;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private MailService mailService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(
            @Valid @RequestBody UserDTO userDto
    ) throws DuplicateMemberException {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN')") //user와 admin 두 가지 권한 접근 허용
    public ResponseEntity<UserDTO> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyUserWithAuthorities());
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasAnyRole('ADMIN')") //admin 권한만 접근 허용
    public ResponseEntity<UserDTO> getUserInfo(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserWithAuthorities(username));
    }

    @GetMapping("/getUsername")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Optional<String> getUsername() {
        Optional<String> username = SecurityUtil.getCurrentUsername();

        return username;
    }

    @GetMapping("/findpassword")
    @PreAuthorize("hasAnyRole('USER')")
    public String findPassword(){

        Optional<String> username = SecurityUtil.getCurrentUsername();
        String email = username.get();
        return mailService.sendMail(email);
    }
}

