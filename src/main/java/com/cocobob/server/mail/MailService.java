package com.cocobob.server.mail;

import com.cocobob.server.domain.User;
import com.cocobob.server.domain.UserDTO;
import com.cocobob.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailService {

    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDTO sendMail(String email){

        Optional<User> user1 = userRepository.findOneWithAuthoritiesByUsername(email);

        Optional<UserDTO> userDTO = Optional.ofNullable(UserDTO.from(user1.get()));

        if(user1.orElse(null) != null) {

            String pw = "";
            for (int i = 0; i < 12; i++) {
                pw += (char) ((Math.random() * 26) + 97);
            }

            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("임시 비밀번호 발급 안내 이메일");
            simpleMailMessage.setText(pw);

            User user = User.builder()
                    .userId(user1.get().getUserId())
                    .username(email)
                    .password(passwordEncoder.encode(pw))
                    .authorities(user1.get().getAuthorities())
                    .build();

            javaMailSender.send(simpleMailMessage);

            Optional<UserDTO> dto = Optional.ofNullable(UserDTO.from(user));

            return dto.get();
        }
        else {
            return userDTO.get();
        }
    }

}
