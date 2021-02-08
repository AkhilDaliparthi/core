package com.core.service;

import com.core.helper.UserHelper;
import com.core.dao.UserDetailsDAO;
import com.core.dto.UserDetailsDTO;
import com.core.exception.RestApiException;
import com.core.exception.UnAuthorizedException;
import com.core.model.User;
import com.core.request.UserAccount;
import com.core.util.CacheUtil;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Log4j2
@Service
public class UserService {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private UserDetailsDAO userDetailsDAO;

    @Autowired
    private UserHelper userHelper;

    @Autowired
    private Gson gson;

    public UserDetailsDTO createAccount(UserAccount userAccount) {
        User user = userHelper.buildUserFromDetails(userAccount);
        user = userDetailsDAO.save(user);
        log.info("User : {} got saved.", user.getUsername());
        UserDetailsDTO userDetails = userHelper.buildUserDetails(user);
        cacheUtil.set(userDetails.getToken(), gson.toJson(userDetails));
        return userDetails;
    }

    public UserDetailsDTO getUserDetailsByAccessToken(String accessToken) {
        if(cacheUtil.hasKey(accessToken)){
            log.info("Valid access token");
            return gson.fromJson(String.valueOf(cacheUtil.get(accessToken)), UserDetailsDTO.class);
        } else {
            throw new RestApiException("Session expired");
        }
    }

    public UserDetailsDTO validateLogin(String username, String password) {
        User user = userDetailsDAO.getUserByUsernameAndPassword(username, password);
        if(Objects.nonNull(user)) {
            log.info("User exists with username : {}", username);
            UserDetailsDTO userDetails = userHelper.buildUserDetails(user);
            cacheUtil.set(userDetails.getToken(), gson.toJson(userDetails));
            return userDetails;
        } else {
            throw new UnAuthorizedException("Invalid username/password");
        }
    }
}
