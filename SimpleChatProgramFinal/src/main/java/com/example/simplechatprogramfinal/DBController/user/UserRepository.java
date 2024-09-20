package com.example.simplechatprogramfinal.DBController.user;

import com.example.simplechatprogramfinal.Entity.User;

public interface UserRepository {
    User getUserByEmail(String email);
}
