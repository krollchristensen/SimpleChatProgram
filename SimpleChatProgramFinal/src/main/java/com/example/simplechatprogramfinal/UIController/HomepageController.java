package com.example.simplechatprogramfinal.UIController;

import com.example.simplechatprogramfinal.DBController.user.UserRepository;
import com.example.simplechatprogramfinal.Usecase.ChatClient;
import com.example.simplechatprogramfinal.Usecase.ReadServerConfigFile;
import com.example.simplechatprogramfinal.Usecase.UserUtil;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class HomepageController {
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
