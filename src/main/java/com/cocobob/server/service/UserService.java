package com.cocobob.server.service;

import com.cocobob.server.domain.Authority;
import com.cocobob.server.domain.User;
import com.cocobob.server.domain.UserDTO;
import com.cocobob.server.repository.UserRepository;
import com.cocobob.server.util.SecurityUtil;
import javassist.bytecode.DuplicateMemberException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //회원가입
    @Transactional
    public UserDTO signup(UserDTO userDto) throws DuplicateMemberException {

        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new DuplicateMemberException("이미 가입되어 있는 유저입니다.");
        }

        //가입되지 않은 유저면 권한 정보 생성
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        //권한 정보를 유저 객체에 저장
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .build();

        //새로 가입한 회원 정보 DB에 저장
        return UserDTO.from(userRepository.save(user));

    }

    //username으로 권한 정보 가져오기
    @Transactional(readOnly = true)
    public UserDTO getUserWithAuthorities(String username) {
        return UserDTO.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
    }

    //현재 SecurityContext 에 저장된 username의 정보 가져오기
    @Transactional(readOnly = true)
    public UserDTO getMyUserWithAuthorities() {
        return UserDTO.from(SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername).orElse(null));
    }

    @Transactional(readOnly = true)
    public String verifyPassword(UserDTO userDTO) {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        if (userRepository.findOneWithAuthoritiesByUsername(username).orElse(null) == null) {
            return "패스워드를 변경할 권한이 없습니다.";
        }

        User user = userRepository.findByUsername(username);

        if (passwordEncoder.matches(password,user.getPassword())) {
            return "패스워드 일치";
        }
        else{
            return "패스워드 불일치";
        }
    }

    @Transactional
    public String updatePassword(UserDTO userDTO) throws DuplicateMemberException {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        User temp = userRepository.findByUsername(username);

        if (userRepository.findOneWithAuthoritiesByUsername(username).orElse(null) == null){
            throw new DuplicateMemberException("존재하지 않는 아이디입니다.");
        }
        User user = User.builder()
                .userId(temp.getUserId())
                .username(username)
                .password(passwordEncoder.encode(password))
                .authorities(temp.getAuthorities())
                .build();
        userRepository.save(user);

        return "패스워드를 변경하였습니다.";
    }
}
