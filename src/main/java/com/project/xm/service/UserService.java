package com.project.xm.service;

import com.project.xm.dto.request.userRequest.UserCreate;
import com.project.xm.dto.request.userRequest.UserUpdate;
import com.project.xm.model.User;
import com.project.xm.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

//    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public User createRequest(UserCreate request){
        User user=new User();

        if(userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("User existed");
        else if (userRepository.existsByPassword(request.getPassword())) {
            throw new RuntimeException("Password existed");
        }

        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setRoleId(request.getRoleID());
        user.setAge(request.getAge());

        return userRepository.save(user);
    }
    public User updateUser(Long id, UserUpdate request){
        User user= getById(id);

        user.setName(request.getName());
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAddress(request.getAddress());
        user.setRoleId(request.getRoleID());
        user.setAge(request.getAge());

        return userRepository.save(user);
    }
    @PreAuthorize("hasRole('1')")
    public List<User> getAll(){
        log.info("in method get user");
        return userRepository.findAll();
    }

    @PostAuthorize("hasRole('1')")
    public User getById(Long id){
        log.info("by id");
        return userRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
    }

    public User getMyInfo(){
        var context=SecurityContextHolder.getContext();
        String username=context.getAuthentication().getName();

        return userRepository.findByEmail(username).orElseThrow(()->new RuntimeException("User not found"));
    }
    public List<User> getByName(String key){
        return userRepository.findByNameContaining(key);
    }
    public List<User> getByRole(int role){
        return userRepository.findByRoleId(role);
    }
    public List<User> getByAge(int age){
        return userRepository.findByAge(age);
    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id))
            throw new RuntimeException("User not found");
        userRepository.deleteById(id);
    }
//    public List<User> softTimeDesc(){
//        return userRepository.findByOrderByCreatedAtDesc();
//    }
//    public List<User> sortByNameAsc(){
//        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
//    }
//    public List<User> sortByNameDesc(){
//        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//    }

}
