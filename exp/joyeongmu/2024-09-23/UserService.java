package org.example.mywebservice.service;

import lombok.RequiredArgsConstructor;
import org.example.mywebservice.domain.User;
import org.example.mywebservice.dto.request.AddUserDto;
import org.example.mywebservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserDto dto){
        User user = User.builder()
                .email(dto.getEmail())
                .nickName(dto.getNickName())
                .password(bCryptPasswordEncoder.encode(dto.getPassword())) //패스워드 암호화
                .build();
        return userRepository.save(user).getId();
    }
}
