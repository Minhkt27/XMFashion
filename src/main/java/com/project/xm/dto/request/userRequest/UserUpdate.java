package com.project.xm.dto.request.userRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserUpdate {
    private String name;

    private String phone;

    private String email;

    private String password;

    private String address;

    private int age;

    private int roleID;
}
