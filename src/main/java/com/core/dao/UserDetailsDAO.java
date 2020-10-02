package com.core.dao;

import com.core.model.User;
import com.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDetailsDAO {

    @Autowired
    private UserRepository userRepository;

    public List<User> getData() {
        return userRepository.findAll();
    }
}
