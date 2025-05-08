package com.project.xm.repository;

import com.project.xm.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    //tìm
    List<User> findByNameContaining(String keyword);
    List<User> findByAgeGreaterThan(int age);
    List<User> findByAgeLessThan(int age);
    List<User> findByAge(int age);
    List<User> findByRoleId(int roleId);
//    login
    Optional<User> findByEmail(String email);
//    check
    boolean existsByEmail(String email);
    boolean existsByPassword(String password);
    //xếp
    List<User> findByOrderByCreatedAtDesc();
    List<User> findAll(Sort sort);





}
