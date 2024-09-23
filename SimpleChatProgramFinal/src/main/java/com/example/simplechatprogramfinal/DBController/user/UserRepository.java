package com.example.simplechatprogramfinal.DBController.user;

import com.example.simplechatprogramfinal.Entity.Users;

public interface UserRepository {
    Users getUserByEmail(String email);

}
