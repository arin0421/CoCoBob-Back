package com.cocobob.server.mail;

import com.cocobob.server.domain.User;
import com.cocobob.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String sendMail(String email){

        User user1 = userRepository.findByUsername(email);
        if(userRepository.findOneWithAuthoritiesByUsername(email).orElse(null) != null) {

            String pw = "";
            for (int i = 0; i < 12; i++) {
                pw += (char) ((Math.random() * 26) + 97);
            }

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("임시 비밀번호 발급 안내 이메일");
            simpleMailMessage.setText(pw);

            User user = User.builder()
                    .userId(user1.getUserId())
                    .username(email)
                    .password(passwordEncoder.encode(pw))
                    .build();

            userRepository.save(user);

            javaMailSender.send(simpleMailMessage);

            return "임시 비밀번호 발급 성공";
        }
        else return "임시 비밀번호 발급 실패";
    }

}
