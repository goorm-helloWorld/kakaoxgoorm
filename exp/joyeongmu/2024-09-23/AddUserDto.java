package org.example.mywebservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserDto {
    private String email;
    private String nickName;
    private String password;
}