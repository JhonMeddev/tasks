package com.testejhon.tasks.repository;

import com.testejhon.tasks.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {


    public Optional<UserModel> findByUsername(String username);

    public List<UserModel> findAllByUsernameContainingIgnoreCase(String usename);

}
