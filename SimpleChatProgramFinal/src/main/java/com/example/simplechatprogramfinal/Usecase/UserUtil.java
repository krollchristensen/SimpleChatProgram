package com.example.simplechatprogramfinal.Usecase;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.simplechatprogramfinal.DBController.user.UserRepository;
import com.example.simplechatprogramfinal.Entity.User;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class UserUtil {
    private static UserRepository userRepository;

    private UserUtil(){
    }

    private static void setUserRepository(UserRepository userRepository){
        UserUtil.userRepository = userRepository;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);

    public static User getCurrentUser(){
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication != null && authentication.isAuthenticated()){
                Object principal = authentication.getPrincipal();
                if(principal instanceof UserDetails){
                    String Email = ((UserDetails)principal).getUsername();
                    return userRepository.getUserByEmail(Email);
                }
            }
            return null;
        } catch (DataAccessException e){
            logger.error(e.getMessage());
        }
        return null;
    }

    public static String getCurrentUserEmail(){
        try{
            User user = getCurrentUser();
            return (user != null) ? user.getEmail() : null;
        } catch (DataAccessException e){
            logger.error(e.getMessage());
        }
        return null;
    }

    public static String getCurrentUserName(){
        try{
            User user = getCurrentUser();
            return (user != null) ? user.getUsername() : null;
        } catch (DataAccessException e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
