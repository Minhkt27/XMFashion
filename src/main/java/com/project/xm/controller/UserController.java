package com.project.xm.controller;

import com.project.xm.dto.respone.ApiResponse;
import com.project.xm.dto.request.userRequest.UserCreate;
import com.project.xm.dto.request.userRequest.UserUpdate;
import com.project.xm.model.User;
import com.project.xm.service.CartService;
import com.project.xm.service.UserService;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @PostMapping
    public ApiResponse<User> createUser(@RequestBody UserCreate request){
        ApiResponse<User> apiResponse = ApiResponse.<User>builder()
                .message("User created successfully!")  // Thông điệp
                .result(userService.createRequest(request))  // Dữ liệu trả về
                .build();
        return apiResponse;
    }
    @GetMapping
    public List<User> getAll(){
        var authentication= SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}",authentication.getName());
        log.info("Role: {}",authentication.getAuthorities());
        return userService.getAll();
    }

    @GetMapping("/myInfo")
    public User getMyInfo(){
        return userService.getMyInfo();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id")Long id){
        return userService.getById(id);
    }

    @GetMapping("/name/{name}")
    public List<User> getByName(@PathVariable("name") String name){
        return userService.getByName(name);
    }

    @GetMapping("/age/{age}")
    public List<User> getByAge(@PathVariable("age") int age){
        return userService.getByAge(age);
    }

    @GetMapping("/roleId/{role}")
    public List<User> getByRole(@PathVariable("role") int role){
        return userService.getByRole(role);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable("id") Long id, @RequestBody UserUpdate request){
        return userService.updateUser(id,request);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return "User has been deleted";
    }

//    @GetMapping
//    List<User> sortByCreatedAtDesc(){
//        return userService.softTimeDesc();
//    }

}
