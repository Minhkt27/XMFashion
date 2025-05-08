package com.project.xm.dto.request.userRequest;

import com.project.xm.model.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserCreate {

    private String name;

    private String phone;

    private String email;

    private String password;

    private String address;

    private int age;

    private int roleID;


}
