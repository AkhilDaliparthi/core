package com.core.dao;

import com.core.model.User;
import com.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserDetailsDAO {

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        user.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }

    public List<User> getData() {
        return userRepository.findAll();
    }

    public User getUserByUsernameAndPassword(String username, String password){
        return userRepository.findUserByUsernameAndPassword(username, password);
    }
}
