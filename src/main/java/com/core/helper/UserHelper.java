package com.core.helper;

import com.core.dto.UserDetailsDTO;
import com.core.model.User;
import com.core.request.UserAccount;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class UserHelper {

    public UserDetailsDTO buildUserDetails(User user) {
        return UserDetailsDTO.builder()
                .token(UUID.randomUUID().toString())
                .userId(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public User buildUserFromDetails(UserAccount userAccount) {
        return User.builder()
                .username(userAccount.getUsername())
                .password(userAccount.getPassword())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
