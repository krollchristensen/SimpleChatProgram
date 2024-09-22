package com.example.simplechatprogramfinal.Usecase;

import com.example.simplechatprogramfinal.DBController.user.UserRepository;
import com.example.simplechatprogramfinal.Entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserUtil {

    private final UserRepository userRepository;

    @Autowired
    public UserUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);

    public Users getCurrentUser() {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.isAuthenticated()) {
                Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails) {
                    String Email = ((UserDetails) principal).getUsername();
                    return userRepository.getUserByEmail(Email);
                }
            }
            return null;
        }catch (DataAccessException e) {
            logger.error("DataAccessException occurred while trying to find all users: {}", e.getMessage(), e);
        }
        return null;
    }

    public String getCurrentUserEmail() {
        try{
            Users user = getCurrentUser();
            return (user != null) ? user.getEmail() : "";
        }catch (DataAccessException e) {
            logger.error("DataAccessException occurred while trying to find all users: {}", e.getMessage(), e);
        }

        return "";
    }

    public String getCurrentUsername() {
        try {
            Users user = getCurrentUser();
            return (user != null) ? user.getUsername() : "";
        } catch (DataAccessException e) {
            logger.error("DataAccessException occurred while trying to find all users: {}", e.getMessage(), e);
        }
        return "";
    }
}